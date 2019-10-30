package fag.edu.com.gerenciadordefichadeaviario;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarContext;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;
import fag.edu.com.gerenciadordefichadeaviario.models.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email, et_senha;
    private Button bt_login, bt_teste;
    private TextView tv_registre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this); //responsavel por iniciar o sugar
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loadXML();
        loadAutoAccess();
        loadEvents();
        loadPermissions();
    }

    private void loadPermissions() {
        // if (ContextCompat.checkSelfPermission(this, Manifest.permission.Read_))
    }

    private void loadXML() {
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        bt_login = findViewById(R.id.bt_login);
        tv_registre = findViewById(R.id.tv_registre);
        bt_teste = findViewById(R.id.bt_teste);
    }

    private void loadEvents() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TaskGet task = new TaskGet(LoginActivity.this, "Usuarios");
                try {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios/byEmail/" + et_email.getText().toString()}).get();
                    System.out.println("VERIFICA Result ------------------->" + result);

                    if (result != null && !result.isError()) {
                        Type typeUser = new TypeToken<Usuario>() {
                        }.getType();
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        Usuario usuario = gson.fromJson(result.getContent(), typeUser);
                        if (usuario.getDsSenha().equals(et_senha.getText().toString()) && usuario.getDsEmail().equals(et_email.getText().toString())) {
                            MainActivity.usuarioLogado = usuario;
                            usuario.save();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Mensagem.ExibirMensagem(LoginActivity.this, "E-mail ou senha não coincidem", TipoMensagem.ALERTA);
                        }
                    } else {
                        Mensagem.ExibirMensagem(LoginActivity.this, "Esta conta não existe", TipoMensagem.ERRO);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        });

        tv_registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });

        bt_teste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadAutoAccess() {
        if (Usuario.first(Usuario.class) != null) {

            Usuario usuario = Usuario.first(Usuario.class);

            MainActivity.usuarioLogado = usuario;

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
