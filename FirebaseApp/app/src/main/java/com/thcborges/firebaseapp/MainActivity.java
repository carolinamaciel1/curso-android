package com.thcborges.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuarioReferencia = firebaseReferencia.child("usuarios");
    private DatabaseReference produtosReferencia = firebaseReferencia.child("produtos").child("002").child("cor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.textId);

/*

        Produto produto = new Produto();
        produto.setDescricao("Notebook WP40");
        produto.setCor("Branco");
        produto.setFabricante("Acer");
        produto.setPreco(2800.00);

        */
/*Usuario usuario = new Usuario();
        usuario.setNome("Ana Helena");
        usuario.setSobrenome("Silva");
        usuario.setIdade(25);
        usuario.setSexo("Feminino");*//*


        produtosReferencia.child("002").setValue(produto);
*/
        produtosReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
                texto.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
