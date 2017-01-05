package gasolinaoualcool.thcborges.com.br.gasolinaoualcool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText precoAlcool;
    private EditText precoGasilna;
    private Button botaoVerificar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precoAlcool = (EditText) findViewById(R.id.precoAlcoolId);
        precoGasilna = (EditText) findViewById(R.id.precoGasolinaId);
        botaoVerificar = (Button) findViewById(R.id.botaoVerificarId);
        textoResultado = (TextView) findViewById(R.id.TextoResultadoId);

        botaoVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Recuperar os valores digitados
                String textoPrecoAlcool = precoAlcool.getText().toString();
                String textoPrecoGasolina = precoGasilna.getText().toString();

                if (textoPrecoAlcool.isEmpty() || textoPrecoGasolina.isEmpty()){
                    textoResultado.setText("Por favor digite os valores!");
                } else {

                    // Converter valores para números
                    Double valorAlcool = Double.parseDouble(textoPrecoAlcool);
                    Double valorGasolina = Double.parseDouble(textoPrecoGasolina);

                    // Cálculos => alcool / gasolina

                    Double resultado = valorAlcool / valorGasolina;

                    if (resultado > 0.7){
                        // Gasolina
                        textoResultado.setText("É melhor utilizar Gasolina");
                    }else {
                        // Alcool
                        textoResultado.setText("É melhor utilizar Álcool");
                    }
                }
            }
        });

    }
}
