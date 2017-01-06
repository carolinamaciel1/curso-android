package sqlite.thcborges.com.br.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            // tabeela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3) )");

            //bancoDados.execSQL("DROP TABLE pessoas");

            // inserir dados
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Mariana', 18)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Thiago', 50)");

            bancoDados.execSQL("UPDATE pessoas SET idade = 28 WHERE id = 1");
            //bancoDados.execSQL("UPDATE pessoas  SET idade = 28 WHERE nome = 'MARCOS'");

            //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'MARCOS'");
            bancoDados.execSQL("DELETE FROM pessoas WHERE id = 3");

            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas", null);

            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null) {

                Log.i("RESULTADO - id:", cursor.getString(indiceColunaId));
                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));


                cursor.moveToNext();

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
