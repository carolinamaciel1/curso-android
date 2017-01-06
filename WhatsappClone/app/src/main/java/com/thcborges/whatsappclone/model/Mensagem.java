package com.thcborges.whatsappclone.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thcbo on 14/12/2016.
 */

public class Mensagem {

    private String idUsuario;
    private String mensagem;
    private String data;
    private String hora;

    public Mensagem() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;

        // Setando da e hora da mensagem
        SimpleDateFormat dateFormatData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormatHora = new SimpleDateFormat("HH:mm");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date dataAtual = cal.getTime();
        this.data = dateFormatData.format(dataAtual);
        this.hora = dateFormatHora.format(dataAtual);

    }
}
