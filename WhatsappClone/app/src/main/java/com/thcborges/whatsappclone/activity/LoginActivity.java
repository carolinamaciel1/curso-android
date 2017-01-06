package com.thcborges.whatsappclone.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.helper.Base64Custom;
import com.thcborges.whatsappclone.helper.Permissao;
import com.thcborges.whatsappclone.helper.Preferencias;
import com.thcborges.whatsappclone.model.Usuario;

import java.util.HashMap;
import java.util.Random;
import java.util.jar.Manifest;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;

    private FirebaseAuth firebaseAuth;
    private String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        verificarUsuarioLogado();

        email = (EditText) findViewById(R.id.editloginEmail);
        senha = (EditText) findViewById(R.id.editLoginSenha);
        botaoLogar = (Button) findViewById(R.id.btLogar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin(usuario);
            }
        });

    }

    public void abrirCadastroUsuario(View view) {

        Intent intent = new Intent(LoginActivity.this, CadastroUsuariosActivity.class);
        startActivity(intent);

    }

    private void validarLogin(final Usuario user) {

        firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(
                LoginActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            // Recuperar dados do usuário
                            idUsuarioLogado = Base64Custom.converterBase64(user.getEmail());
                            DatabaseReference firebase = FirebaseDatabase.getInstance()
                                    .getReference()
                                    .child("usuarios")
                                    .child(idUsuarioLogado);
                            firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    Usuario usuario = dataSnapshot.getValue(Usuario.class);

                                    // Salvar os dados do usuário
                                    String identificadorUsuarioLogado = Base64Custom.converterBase64(user.getEmail());
                                    Preferencias preferencias = new Preferencias(LoginActivity.this);
                                    preferencias.salvarDados(identificadorUsuarioLogado, usuario.getNome());

                                    abrirTelaPrincipal();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        } else {

                            Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                }
        );

    }

    private void abrirTelaPrincipal() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void verificarUsuarioLogado() {

        if (firebaseAuth.getCurrentUser() != null) {

            abrirTelaPrincipal();

        }

    }

}


/*private EditText nome;
    private EditText codPais;
    private EditText codArea;
    private EditText telefone;
    private EditText email;
    private Button cadastrar;
    private String[] permissoesNecessarias = new String[] {
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.INTERNET
    };*//**//*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        *//**//*Permissao.validaPermissoes(1, this, permissoesNecessarias);

        nome = (EditText) findViewById(R.id.nomeId);
        //codPais = (EditText) findViewById(R.id.cadastroCodPaisId);
        //codArea = (EditText) findViewById(R.id.cadastroCodAreaId);
        //telefone = (EditText) findViewById(R.id.cadastroTelefoneId);
        email = (EditText) findViewById(R.id.emailId);
        cadastrar = (Button) findViewById(R.id.botaoCadastrarId);

        *//**//**//**//*
        * Link para máscaras de formato
        * https://github.com/rtoshiro/MaskFormatter
        * *//**//**//**//*
        *//**//**//**//*SimpleMaskFormatter simplemaskPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simplemaskArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simplemaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");

        MaskTextWatcher maskPais = new MaskTextWatcher(codPais, simplemaskPais);
        MaskTextWatcher maskArea = new MaskTextWatcher(codArea, simplemaskArea);
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simplemaskTelefone);

        codPais.addTextChangedListener(maskPais);
        codArea.addTextChangedListener(maskArea);
        telefone.addTextChangedListener(maskTelefone);*//**//**//**//*

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//**//**//**//*String nomeUsuario = nome.getText().toString();
                String telefoneCompleto =
                        codPais.getText().toString() +
                        codArea.getText().toString() +
                        telefone.getText().toString();

                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");

                // Gerar token
                Random random = new Random();
                int numeroRandomico = random.nextInt(8999) + 1000;

                String token = String.valueOf(numeroRandomico);
                String mensagemEnvio = "Whatsapp Clone código de confirmação: " + token;

                // Salvar dados para validação

                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                // Envio SMS
                boolean enviadoSms = enviaSMS("+" + telefoneSemFormatacao, mensagemEnvio);

                if (enviadoSms) {

                    Intent intent = new Intent(LoginActivity.this, ValidadorActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "Problema ao enviar o SMS, tente novamente!", Toast.LENGTH_SHORT).show();

                }*//**//**//**//*

                *//**//**//**//*HashMap<String, String> usuario = preferencias.getDadosUsuario();

                Log.i("TOKEN", "NOME: " + usuario.get("nome") + " FONE: " + usuario.get("telefone") + " TOKEN: " + usuario.get("token"));
*//**//**//**//*
            }
        });*//**//*

    }

    *//**//*//**//*//* Envio do SMS
    private boolean enviaSMS(String telefone, String mensagem) {
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int resultado : grantResults) {

            if (resultado == PackageManager.PERMISSION_DENIED) {
                alertaValidacaoPermissao();
            }

        }

    }


    private void alertaValidacaoPermissao() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar esse app é necessário aceitar as permissões.");

        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }*/