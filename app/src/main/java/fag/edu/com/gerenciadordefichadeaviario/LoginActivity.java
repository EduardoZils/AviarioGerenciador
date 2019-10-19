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

import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
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

                List<Usuario> usuarios = Usuario.find(Usuario.class, "ds_email = ? and ds_senha = ?", new String[]{et_email.getText().toString(), et_senha.getText().toString()});
                if (usuarios.size() > 0) {
                    //Fazer Login
                } else {
                    TaskGet task = new TaskGet(LoginActivity.this, "Usuarios");
                    try {
                        Result usuario = (Result) task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios/"}).get();
                        //List<Usuario> usuarioList = (List<Usuario>) usuario;
                        //tvResultado.setText(usuario.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

    }

}
