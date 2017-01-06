package com.thcborges.whatsappclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.helper.Preferencias;
import com.thcborges.whatsappclone.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thcbo on 14/12/2016.
 */

public class MensagemAdapter extends ArrayAdapter<Mensagem> {

    private Context context;
    private ArrayList<Mensagem> mensagens;

    public MensagemAdapter(Context c, ArrayList<Mensagem> objects) {
        super(c, 0, objects);
        this.context = c;
        this.mensagens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (mensagens != null) {

            // Recuperar mensagem
            Mensagem mensagem = mensagens.get(position);

            //Recupera usuário logado
            Preferencias preferencias = new Preferencias(context);
            String idUsuarioLogado = preferencias.getIdentificador();

            // Inicializa objeto para montagem do layout
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            if (idUsuarioLogado.equals(mensagem.getIdUsuario())){
                view = layoutInflater.inflate(R.layout.item_mensagem_direita, parent, false);
            }else {
                view = layoutInflater.inflate(R.layout.item_mensagem_esquerda, parent, false);
            }

            // Monta a view a partir do xml
            //view = layoutInflater.inflate(R.layout.item_mensagem_esquerda, parent, false);

            // Recuperar elementos para exibição
            TextView textView = (TextView) view.findViewById(R.id.tv_mensagem);
            textView.setText(mensagem.getMensagem());

        }

        return view;

    }
}
