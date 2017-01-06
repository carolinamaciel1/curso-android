package sharedepreferences.thcborges.com.br.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private  static  final String ARQUIVO_PREFERÊNCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.textoNomeId);
        textoExibicao = (TextView) findViewById(R.id.textoExibicaoId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERÊNCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (textoNome.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Por favor, escreva seu nome", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("nome", textoNome.getText().toString());
                    editor.apply();
                    String mensagem = "Olá, " + textoNome.getText().toString();
                    textoExibicao.setText(mensagem);

                }

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERÊNCIA, 0);
        if (sharedPreferences.contains("nome")) {
            String nomeUsuario = sharedPreferences.getString("nome", "usuário não definido");
            String mensagem = "Olá, " + nomeUsuario;
            textoExibicao.setText(mensagem);
        } else {
            textoExibicao.setText(getString(R.string.boas_vindas));
        }

    }
}
