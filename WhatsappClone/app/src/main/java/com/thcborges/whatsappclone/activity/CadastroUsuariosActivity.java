package com.thcborges.whatsappclone.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thcborges.whatsappclone.R;
import com.thcborges.whatsappclone.helper.Base64Custom;
import com.thcborges.whatsappclone.model.Usuario;

public class CadastroUsuariosActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuarios);
        nome = (EditText) findViewById(R.id.editCadastroNomeId);
        nome.setFocusable(true);
        telefone = (EditText) findViewById(R.id.editCadastroTelefoneId);

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("+NN (NN) NNNNN-NNNN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(telefone, simpleMaskFormatter);
        telefone.addTextChangedListener(maskTextWatcher);

        email= (EditText) findViewById(R.id.editCadastroEmailId);
        senha = (EditText) findViewById(R.id.editCadastroSenhaId);

        Button botaoCadastrar = (Button) findViewById(R.id.botaoCadastrarId);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setTelefone(telefone.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                cadastrarUsuario(usuario);

            }
        });

    }

    public void cadastrarUsuario(final Usuario user) {

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(CadastroUsuariosActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    String identificador = Base64Custom.converterBase64(user.getEmail());
                    //String id = firebaseAuth.getCurrentUser().getUid();
                    user.setId(identificador);
                    user.salvar();

                    Toast.makeText(CadastroUsuariosActivity.this, "Cadastro criado com sucesso.", Toast.LENGTH_SHORT).show();

                    finish();

                } else {
                    Toast.makeText(CadastroUsuariosActivity.this, "Erro ao cadastrar usuário" + task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
/*
* */