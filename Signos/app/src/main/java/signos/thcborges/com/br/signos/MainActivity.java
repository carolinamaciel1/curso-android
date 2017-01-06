package signos.thcborges.com.br.signos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listaSignos;
    private String[] signos = {
            "Áries", "Touro", "Gêmeos", "Câncer", "Leão", "Virgem", "Libra",
            "Escorpião", "Sargitário", " Capricórnio", "Aquário", "Peixes"
    };

    private String[] perfis = {
            "As pessoas nascidas neste signo são ansiosas, dinâmicas, rápidas e competitivas." +
                    " Quando se trata de fazer a bola rolar, o ariano é o melhor. Gosta de " +
                    "ser pioneiro.",
            "Forte, confiável Taurus lidera o caminho quando se trata de colher os frutos do " +
                    "trabalho duro. Os amantes de tudo o que é bom e bonito, taurinos se cercam " +
                    "de ganhos materiais.",
            "A versatilidade é uma palavra-chave para este signo. O Geminiano apresenta duas faces" +
                    " distintas de sua personalidade, e você nunca pode ter certeza de quem é se " +
                    "você está indo para ficar cara a cara.",
            "Profundamente intuitivo e sentimental, Câncer pode ser um dos signos do Zodíaco mais " +
                    " desafiadores para conhecer. Emoção é muito forte para esse signo, e quando se" +
                    " trata de família e do lar, nada é mais importante.",
            "Quando o Leão entra em cena, todos o olham. Este signo é expressivo, criativo e " +
                    "possui grande carisma. Ardente e seguro de si, um encanto de Leonino pode" +
                    " ser quase impossível de resistir.",
            "Sempre atento aos detalhes. Sua percepção apurada de tudo lhe torna capaz de " +
                    "organizar tudo de maneira precisa, enquanto a sua abordagem metódica não " +
                    "permite que nada se perderá.",
            "\"Eu equilibro\" é a frase-chave para este signo, e quando se trata de manter tudo " +
                    "em equilíbrio o libriano é o mais indicado. Amante da paz e diplomacia, este " +
                    "signo detesta estar sozinho.",
            "Debaixo de um exterior, controlado tranquilo bate intensamente o coração do " +
                    "Escorpião. Apaixonado, penetrante, e determinado, este signo irá sondar até " +
                    "que atinja a verdade.",
            "Curioso e enérgico, o Sagitário é o viajante do Zodíaco. Sua filosofia, abordagem " +
                    "de mente aberta para a vida motiva-o a refletir muito em busca do sentido " +
                    "da vida.",
            "Quando se trata de profissionalismo e os valores tradicionais, Capricórnio ganha mãos " +
                    "nas costas. Este signo adora praticidade e enfrenta a vida da maneira mais " +
                    "convencional possivel.",
            "Os aquarianos se apresentam de duas maneiras. Pode ser alguém que é tímido e quieto " +
                    "ou alguém turbulento, excêntrico e energético. Ambos são pensadores " +
                    "profundos com um amor de ajudar os outros.",
            "\"Compreender\" é a palavra-chave mais apropriada para este signo, gentil e " +
                    "carinhoso. Descontraído e capaz de aceitar bem as pessoas ao seu redor, os " +
                    "piscianos são encontrados frequentemente na companhia de uma variedade de " +
                    "personalidades diferentes."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSignos = (ListView) findViewById(R.id.listViewId);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );

        listaSignos.setAdapter(adapter);

        listaSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int codigoPosicao = i;
                Toast.makeText(getApplicationContext(), perfis[codigoPosicao], Toast.LENGTH_LONG).show();
            }
        });

    }
}
