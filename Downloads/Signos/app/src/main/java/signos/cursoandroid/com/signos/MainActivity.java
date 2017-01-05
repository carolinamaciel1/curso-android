package signos.cursoandroid.com.signos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listaSignos;
    private String[] signos = {
            "Áries", "Touro","Gêmeos","Câncer","Leão","Virgem",
            "Libra","Escorpião","Sagitário","Capricórnio","Aquário",
            "Peixes"
    };

    private String[] perfis = {
            "Áries placerat. In vulputate urna eu arcu. Aliquam erat volutpat. Suspendisse potenti.",
            "Touro viverra diam non justo. In nisl. Nullam sit amet magna in magna gravida vehicula.",
            "Gêmeos lectus justo, vulputate eget, mollis sed, tempor sed.",
            "Câncer sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
            "Leão ligula sapien, pulvinar a, vestibulum quis, facilisis vel, sapien.",
            "Virgem eget nisl. Donec vitae arcu.",
            "Libra ipsum dolor sit amet, consectetuer adipiscing elit.",
            "Escorpião gravida libero nec velit. Morbi scelerisque luctus velit.",
            "Sagitário dui sem, fermentum vitae, sagittis id, malesuada in, quam.",
            "Capricórnio mattis lacinia justo. Vestibulum facilisis auctor urna.",
            "Aquário in lorem sit amet leo accumsan lacinia.",
            "Peixes rutrum, orci vestibulum ullamcorper ultricies, lacus quam ultricies odio.",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSignos = (ListView) findViewById(R.id.listViewId);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );

        listaSignos.setAdapter( adaptador );

        listaSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int codigoPosicao = position;
                Toast.makeText(getApplicationContext(), perfis[codigoPosicao] , Toast.LENGTH_LONG).show();

            }
        });


    }

}
