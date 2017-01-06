package com.thcborges.whatsappclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.model.Conversa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thcbo on 19/12/2016.
 */

public class ConversaAdapter  extends ArrayAdapter<Conversa>{

    private ArrayList<Conversa> conversas;
    private Context context;
    private Conversa conversa;


    public ConversaAdapter(Context c, ArrayList<Conversa> objects) {
        super(c, 0, objects);

        this.context = c;
        this.conversas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (conversas != null) {

            // Inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Monta a view a partir do xml
            view = inflater.inflate(R.layout.lista_conversas, parent, false);

            // Recuperar elementos da tela
            TextView nome = (TextView) view.findViewById(R.id.text_nome);
            TextView ultimaMensagem = (TextView) view.findViewById(R.id.text_ultima_conversa);

            // Setar valores nos campos da tela
            conversa = conversas.get(position);
            nome.setText(conversa.getNome());
            ultimaMensagem.setText(conversa.getMensagem());


        }

        return view;
    }
}
