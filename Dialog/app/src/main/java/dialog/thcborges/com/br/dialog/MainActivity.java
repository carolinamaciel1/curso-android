package dialog.thcborges.com.br.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botao;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.botaoId);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new AlertDialog.Builder( MainActivity.this );

                dialog.setTitle("Título da dialog");

                dialog.setMessage("Mensagem");

                dialog.setCancelable(false);

                dialog.setIcon(android.R.drawable.ic_delete);

                dialog.setNegativeButton("Não",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Pressionado o botão \"Não\"", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Pressionado o boatão \"Sim\"", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.create();
                dialog.show();

            }
        });

    }
}
