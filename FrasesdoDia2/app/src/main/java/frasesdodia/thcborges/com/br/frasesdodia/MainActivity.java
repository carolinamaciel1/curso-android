package frasesdodia.thcborges.com.br.frasesdodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
    private Button botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

    private String[] frases = {"teste", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(frases.length);

                textoNovaFrase.setText(frases[numeroAleatorio]);

            }

            /*Largo de são francisco, 26 sala 803*/
        });

    }
}
