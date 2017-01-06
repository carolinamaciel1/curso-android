package com.thcborges.whatsappclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.model.Contato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thcbo on 13/12/2016.
 */

public class ContatoAdapter extends ArrayAdapter{

    private Context context;
    private ArrayList<Contato> contatos;

    public ContatoAdapter(Context c, ArrayList<Contato> objects) {
        super(c, 0, objects);
        this.context = c;
        this.contatos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // Verifica se a lista está preenchida
        if (contatos != null) {

            // Inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Monta a view a partir do xml
            view = inflater.inflate(R.layout.lista_contatos, parent, false);

            // Recuperar elementos para exibiç~~ao
            TextView textView = (TextView) view.findViewById(R.id.tv_nome);

            Contato contato = contatos.get(position);
            textView.setText(contato.getNome());

        }

        return view;

    }
}
