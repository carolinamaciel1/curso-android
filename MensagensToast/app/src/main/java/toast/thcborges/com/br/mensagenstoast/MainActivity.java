package toast.thcborges.com.br.mensagenstoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private static final int DURACAO_EXECUSSAO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.botaoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int duracaoExecussao = MainActivity.DURACAO_EXECUSSAO;
                Toast.makeText( getApplicationContext(), "Mensagem", duracaoExecussao ).show();
            }
        });

    }
}
