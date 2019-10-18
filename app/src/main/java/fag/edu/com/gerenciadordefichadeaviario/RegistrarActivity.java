package fag.edu.com.gerenciadordefichadeaviario;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.UsuarioTask;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;
import fag.edu.com.gerenciadordefichadeaviario.models.Usuario;

public class RegistrarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText et_nome, et_cpf, et_rg, et_senha, et_senhaC, et_dtnasc, et_email;
    Button bt_registrar;
    private int day, mounth, year;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;//Dialog pra Date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadXML();
        loadEvents();

    }

    private void loadEvents() {
        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(RegistrarActivity.this, this, year, mounth, day);

        et_dtnasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario u = new Usuario();

                u.setCdUsuario(verificaCodigoUsuario());

                u.setDsNome(et_nome.getText().toString());
                u.setDsEmail(et_email.getText().toString());
                u.setDsCpf(et_cpf.getText().toString());
                u.setDsRg(et_rg.getText().toString());
                u.setDtAtualizacao(new Date());
                u.setDtCadastro(new Date());

                if (et_senha.getText().toString().equals(et_senhaC.getText().toString())) {
                    u.setDsSenha(et_senha.getText().toString());
                } else {
                    Mensagem.ExibirMensagem(RegistrarActivity.this, "As senhas não coincidem", TipoMensagem.ERRO);
                    et_senha.setBackgroundColor(Color.rgb(247, 118, 118));
                    et_senhaC.setBackgroundColor(Color.rgb(247, 118, 118));
                }
                UsuarioTask usuarioTask = new UsuarioTask(RegistrarActivity.this);


                //    String userarios = usuarioTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                //            new String[]{new Gson().toJson(u)}).get();

                // } catch (ExecutionException e) {
                //     e.printStackTrace();
                // } catch (InterruptedException ie) {
                //     ie.printStackTrace();

            }
        });
    }

    private void loadXML() {
        et_nome = findViewById(R.id.et_nome);
        et_cpf = findViewById(R.id.et_cpf);
        et_rg = findViewById(R.id.et_rg);
        et_senha = findViewById(R.id.et_senha);
        et_senhaC = findViewById(R.id.et_senhaC);
        et_dtnasc = findViewById(R.id.et_dtnasc);
        et_email = findViewById(R.id.et_email);
        bt_registrar = findViewById(R.id.bt_registrar);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Mensagem.ExibirMensagem(RegistrarActivity.this, "Data selecionada (" + dayOfMonth + "/" + (month + 1) + "/" + year + ")", TipoMensagem.ALERTA);
        et_dtnasc.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
    }


    private int verificaCodigoUsuario() {
        //Se for a API montada por vocês
        Type type = new TypeToken<Usuario>() {
        }.getType();

        try {
            TaskGet task = new TaskGet(this, "Usuarios");
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios"}).get();
            System.out.println("VERIFICA Result ------------------->" + result);

            Usuario u = null;
            //u =  new Gson().fromJson(result, type);
            //System.out.println("VERIFICA CODIGO USUARIO 2------------------>" + u.getDsNome());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
