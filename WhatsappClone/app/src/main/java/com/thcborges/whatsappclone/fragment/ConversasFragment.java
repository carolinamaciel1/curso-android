package com.thcborges.whatsappclone.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thcborges.whatsappclone.Adapter.ContatoAdapter;
import com.thcborges.whatsappclone.Adapter.ConversaAdapter;
import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.activity.ConversaActiity;
import com.thcborges.whatsappclone.helper.Base64Custom;
import com.thcborges.whatsappclone.helper.Preferencias;
import com.thcborges.whatsappclone.model.Conversa;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<Conversa> arrayaAdapter;
    private ArrayList<Conversa> conversas;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ValueEventListener valueEventListenerConversas;


    public ConversasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        /**************************************************************
         * Monta listview e adapter
         * ************************************************************/
        conversas = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.lv_conversas);
        arrayaAdapter = new ConversaAdapter(getActivity(), conversas);
        listView.setAdapter(arrayaAdapter);

        /**************************************************************
         * Recuperar conversas no firebase
         * ***********************************************************/
        Preferencias preferencias = new Preferencias(getActivity());
        String idUsuarioLogado = preferencias.getIdentificador();

        // Instância do firebase
        databaseReference = databaseReference.child("conversa").child(idUsuarioLogado);

        valueEventListenerConversas = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                conversas.clear();
                Log.i("Filhos", "Filhos: " + dataSnapshot.getChildren());

                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    Conversa conversa = dados.getValue(Conversa.class);

                    Log.i("Filhos", "Filhos: " + dados.getValue(Conversa.class));
                    Log.i("Filhos", "Filhos: " + conversa.getNome() + " " + conversa.getMensagem());
                    conversas.add(conversa);

                }

                arrayaAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addValueEventListener(valueEventListenerConversas);

        // Adicionar evento de click na lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Recuperar conversa para a posiçao
                Conversa conversa = conversas.get(i);

                // Criar intent para ConversaActivity
                Intent intent = new Intent(getActivity(), ConversaActiity.class);
                String email = Base64Custom.decodificarBase64(conversa.getIdUsuario());
                intent.putExtra("email", email);
                intent.putExtra("nome", conversa.getNome());

                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListenerConversas);
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListenerConversas);
    }

}
