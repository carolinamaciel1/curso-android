package com.thcborges.whatsappclone.helper;

import android.util.Base64;

/**
 * Created by thcbo on 12/12/2016.
 */

public class Base64Custom {

    public static String converterBase64(String texto){
        String textoConvertido = Base64.encodeToString(texto.getBytes(), Base64.DEFAULT);
        return textoConvertido.trim();

    }

    public static String decodificarBase64(String textoCodificado){
        byte[] byteDecodificado = Base64.decode(textoCodificado, Base64.DEFAULT);
        return new String(byteDecodificado);
    }

}
