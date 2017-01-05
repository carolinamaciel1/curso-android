package frasesdodia.thcborges.com.br.frasesdodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase;
    private Button botaoNovaFrase;

    private String[] frases = {
            getString(R.string.frase01),
            getString(R.string.frase02),
            getString(R.string.frase03),
            getString(R.string.frase04),
            getString(R.string.frase05),
            getString(R.string.frase06),
            getString(R.string.frase07),
            getString(R.string.frase08),
            getString(R.string.frase09),
            getString(R.string.frase10),
            getString(R.string.frase11),
            getString(R.string.frase12),
            getString(R.string.frase13),
            getString(R.string.frase14),
            getString(R.string.frase15),
            getString(R.string.frase16),
            getString(R.string.frase17),
            getString(R.string.frase18),
            getString(R.string.frase19),
            getString(R.string.frase20),
            getString(R.string.frase21),
            getString(R.string.frase22),
            getString(R.string.frase23),
            getString(R.string.frase24),
            getString(R.string.frase25),
            getString(R.string.frase26),
            getString(R.string.frase27),
            getString(R.string.frase28),
            getString(R.string.frase29),
            getString(R.string.frase30),
            getString(R.string.frase31),
            getString(R.string.frase32),
            getString(R.string.frase33),
            getString(R.string.frase34),
            getString(R.string.frase35),
            getString(R.string.frase36),
            getString(R.string.frase37),
            getString(R.string.frase38),
            getString(R.string.frase39),
            getString(R.string.frase40),
            getString(R.string.frase41),
            getString(R.string.frase42),
            getString(R.string.frase43),
            getString(R.string.frase44),
            getString(R.string.frase45),
            getString(R.string.frase46),
            getString(R.string.frase47),
            getString(R.string.frase48),
            getString(R.string.frase49),
            getString(R.string.frase50),
            getString(R.string.frase51),
            getString(R.string.frase52),
            getString(R.string.frase53),
            getString(R.string.frase54),
            getString(R.string.frase55),
            getString(R.string.frase56),
            getString(R.string.frase57),
            getString(R.string.frase58),
            getString(R.string.frase59),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFrase);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(frases.length);
                
                textoNovaFrase.setText(frases[numeroAleatorio]);
            }
        });

    }
}
