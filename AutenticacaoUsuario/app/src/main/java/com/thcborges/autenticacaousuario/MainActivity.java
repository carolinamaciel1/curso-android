package com.thcborges.autenticacaousuario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            Log.i("verificaUsuario", "Usuario está logado");
            firebaseAuth.signOut();
        } else {
            Log.i("verificaUsuario", "Usuário não está logado");
        }

        // Cadastro
        /*firebaseAuth.createUserWithEmailAndPassword("thcborges@gmail.com", "thiago123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) { // sucesso ao cadastrar usuário
                            Log.i("createuser", "Suceso ao cadastrar usuário!!");
                        } else {
                            Log.i("createuser", "Erro ao caadstrar usuário!!");
                        }

                    }
                });*/

        // Login do usuário
        firebaseAuth.signInWithEmailAndPassword("thcborges@gmail.com", "thiago123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) { // Sucesso ao logar
                            Log.i("signIn", "Sucesso ao logar");
                        } else {
                            Log.i("signIn", "Erro ao logar" + task.getException());
                        }

                    }
                });

    }
}
