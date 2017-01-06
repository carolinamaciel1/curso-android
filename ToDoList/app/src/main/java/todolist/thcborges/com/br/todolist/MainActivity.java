package todolist.thcborges.com.br.todolist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText textoTarefa;
    private EditText valorTexto;
    private Button botaoAdicionar;
    private ListView listaTarefa;
    private SQLiteDatabase bancoDados;

    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            // Recuperar componentes
            textoTarefa = (EditText) findViewById(R.id.descrcaoId);
            valorTexto = (EditText) findViewById(R.id.valorId);
            botaoAdicionar = (Button) findViewById(R.id.botãoAdicionarId);

            // Lista
            listaTarefa = (ListView) findViewById(R.id.listViewId);

            // Banco de dados
            bancoDados = openOrCreateDatabase("apptarefas", MODE_PRIVATE, null);

            bancoDados.execSQL(
                    "CREATE TABLE IF NOT EXISTS planilha (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "valor DOUBLE, " +
                            "descricao VARCHAR)"
            );

            botaoAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Double valor = Double.parseDouble(valorTexto.getText().toString());
                    String textoDigitado = textoTarefa.getText().toString();
                    salvarTarefa(valor, textoDigitado);

                }
            });


            listaTarefa.setLongClickable(true);
            listaTarefa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    removerTarefa(ids.get(i));
                    return false;
                }
            });

            // LIstar tarefas
            recuperarTarefas();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void salvarTarefa(double val, String texto){

        try {

            if ((texto.equals("")) || (val == 0)){

                Toast.makeText(MainActivity.this, "Digite umvalor e uma descrição", Toast.LENGTH_SHORT).show();

            } else {

                bancoDados.execSQL(
                        "INSERT INTO planilha " +
                                "(valor, descricao) VALUES (" + val + ", '" + texto + "')"
                );
                Toast.makeText(MainActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                recuperarTarefas();
                textoTarefa.setText("");
                valorTexto.setText("");

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void recuperarTarefas(){
        try {

            // Cursor recupera as tarefas
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM planilha ORDER BY id DESC", null);

            // Recuperar os ids das colunas do banco de dados
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaValor = cursor.getColumnIndex("valor");
            int indiceColunaDescricao = cursor.getColumnIndex("descricao");


            // Criar adaptador
            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();
            itensAdaptador = new ArrayAdapter<String>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text1,
                    itens
            );
            listaTarefa.setAdapter(itensAdaptador);

            // Listar as tarefas
            cursor.moveToFirst();
            while (cursor != null) {

                Log.i("Resultado - ", "ID Tarefa: " + cursor.getString(indiceColunaId) +
                        " Tarefa: " + cursor.getString(indiceColunaDescricao) +
                        "Valor: " + cursor.getString(indiceColunaValor)
                );

                String texto = cursor.getString(indiceColunaValor) +
                        " => " +
                        cursor.getString(indiceColunaDescricao);

                itens.add(texto);
                ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));

                cursor.moveToNext();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{

            bancoDados.execSQL("DELETE FROM planilha WHERE id = " + id);
            recuperarTarefas();
            Toast.makeText(this, "Tarefa removida com sucesso", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
