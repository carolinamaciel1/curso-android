package com.thcborges.whatsappclone.activity;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thcborges.whatsappclone.Adapter.MensagemAdapter;
import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.helper.Base64Custom;
import com.thcborges.whatsappclone.helper.Preferencias;
import com.thcborges.whatsappclone.model.Conversa;
import com.thcborges.whatsappclone.model.Mensagem;

import java.util.ArrayList;

public class ConversaActiity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference firebase = databaseReference;

    private ListView listView;
    private ArrayAdapter<Mensagem> arrayAdapter;
    private ArrayList<Mensagem> mensagens;
    private ValueEventListener valueEventListenerMensagens;
    private Conversa conversa;

    // Destinatário
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;

    // Remetente
    private String idUsuarioLogado;
    private String nomeUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa_actiity);

        toolbar = (Toolbar) findViewById(R.id.tb_conversa);
        editMensagem = (EditText) findViewById(R.id.edit_mensagem);
        btMensagem = (ImageButton) findViewById(R.id.bt_enviar);
        listView = (ListView) findViewById(R.id.lv_mensagenss);

        // Recuperar os dados do usuário logado
        Preferencias preferencias = new Preferencias(ConversaActiity.this);
        idUsuarioLogado = preferencias.getIdentificador();
        nomeUsuarioLogado = preferencias.getNome();


        // Recuperar os dados enviados na intent
        Bundle extra = getIntent().getExtras();
        if (extra != null) {

            // Recuperar dados do contato (destinatário)
            nomeUsuarioDestinatario = extra.getString("nome");
            idUsuarioDestinatario = Base64Custom.converterBase64(extra.getString("email"));

        }

        // Configuração de toolbar
        toolbar.setTitle(nomeUsuarioDestinatario);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        /*****************************************************************************
         *  Montagem ListView e Adapter */
        mensagens = new ArrayList<>();
        /*arrayAdapter = new ArrayAdapter<String>(
                ConversaActiity.this,
                android.R.layout.simple_list_item_1,
                mensagens
        );*/
        arrayAdapter = new MensagemAdapter(
                ConversaActiity.this,
                mensagens
        );
        listView.setAdapter(arrayAdapter);

        /****************************************************************************
         *  Recuperar as mensagens do firebase*/
        firebase = databaseReference
                .child("mensagens")
                .child(idUsuarioLogado)
                .child(idUsuarioDestinatario);

        // Criar listener para mensagens
        valueEventListenerMensagens = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Limpar array list de mensagens
                mensagens.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    // Recupera mensagem individual
                    Mensagem mensagem = dados.getValue(Mensagem.class);

                    // Adicionar na lista de mensagens
                    mensagens.add(mensagem);

                }

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        firebase.addValueEventListener(valueEventListenerMensagens);

        // Enviar mensagem
        btMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoMensagem = editMensagem.getText().toString();

                // Testar se a mensagem está preenchida
                if (!textoMensagem.isEmpty()) {

                    /********************************************************
                     *  Salvar mensagem no firebase */
                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idUsuarioLogado);
                    mensagem.setMensagem(textoMensagem);

                    // Salvando mensagem para o remetente
                    Boolean retornoDestinatario = salvarMensagemFirebase(idUsuarioLogado, idUsuarioDestinatario, mensagem);
                    // Salvando mensagem para o remetente
                    Boolean retornoRemetente = salvarMensagemFirebase(idUsuarioDestinatario, idUsuarioLogado, mensagem);
                    if (!retornoDestinatario || !retornoRemetente) {
                        Toast.makeText(
                                ConversaActiity.this,
                                "Problema ao enviar a mensagem, tente novamente!",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {

                        // Apagar texto digitado
                        editMensagem.setText("");

                    }

                    /*******************************************************
                     *  Slavar conversas */

                    // Salvar conversa no firebase para o remetente
                    conversa = new Conversa();
                    conversa.setIdUsuario(idUsuarioDestinatario);
                    conversa.setNome(nomeUsuarioDestinatario);
                    conversa.setMensagem(textoMensagem);
                    Boolean retornoConversaRemetente = salvarConversasFirebase(idUsuarioLogado, idUsuarioDestinatario, conversa);

                    // Salvar conversa no firebase para o destinatário
                    conversa = new Conversa();
                    conversa.setIdUsuario(idUsuarioLogado);
                    conversa.setNome(nomeUsuarioLogado);
                    conversa.setMensagem(textoMensagem);
                    Boolean retornoConversaDestinatario = salvarConversasFirebase(idUsuarioDestinatario, idUsuarioLogado, conversa);

                    if (!retornoConversaDestinatario || !retornoConversaRemetente) {
                        Toast.makeText(ConversaActiity.this, "Problema ao salvar conversa, tente novamente",
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                }

            }
        });


    }

    private Boolean salvarMensagemFirebase(String idRemetente, String idDestinatario, Mensagem mensagem) {

        try {

            firebase = databaseReference.child("mensagens");
            firebase.child(idRemetente)
                    .child(idDestinatario)
                    .push()
                    .setValue(mensagem);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private Boolean salvarConversasFirebase(String idRemetente, String idDestinatario, Conversa conversa) {

        try {

            firebase = databaseReference.child("conversa");
            firebase.child(idRemetente)
                    .child(idDestinatario)
                    .setValue(conversa);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagens);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        firebase.addValueEventListener(valueEventListenerMensagens);
    }
}
