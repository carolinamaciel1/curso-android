package preferenciascorusuario.thcborges.com.br.preferenciascorusuario;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button botaoSalvar;

    private RelativeLayout layout;

    private static final String ARQUIVO_REFERENCIA = "ArqPreferencia";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);
        layout = (RelativeLayout) findViewById(R.id.layoutId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();
                radioButtonSelecionado = (RadioButton) findViewById(idRadioButtonEscolhido);

                if (idRadioButtonEscolhido > 0) {
                    radioButtonSelecionado = (RadioButton) findViewById(idRadioButtonEscolhido);
                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_REFERENCIA, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String corEscolhida = radioButtonSelecionado.getText().toString();
                    editor.putString("corEscolhida", corEscolhida);
                    editor.apply();
                    setBackground(corEscolhida);

                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_REFERENCIA, 0);
        if (sharedPreferences.contains("corEscolhida")){
            String corRecuperada = sharedPreferences.getString("corEscolhida", "Laranja");
            setBackground(corRecuperada);
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setBackground(String cor) {

        if (cor.equals("Azul")) {
            layout.setBackgroundColor(Color.parseColor("#495b7c"));
        }else if (cor.equals("Laranja")) {
            layout.setBackgroundColor(Color.parseColor("#ffce26"));
        } else if (cor.equals("Verde")) {
            layout.setBackgroundColor(Color.parseColor("#009670"));
        }

    }

}
