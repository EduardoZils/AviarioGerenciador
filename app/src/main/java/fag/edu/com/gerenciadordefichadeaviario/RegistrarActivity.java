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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.UsuarioTask;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;
import fag.edu.com.gerenciadordefichadeaviario.models.Usuario;

public class RegistrarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText et_nome, et_cpf, et_rg, et_senha, et_senhaC, et_email;
    TextView tv_dtnascimento;
    Button bt_registrar;
    private int day, mounth, year;
    private Date dt_selecionada = null;
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

        tv_dtnascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dt_selecionada != null) {
                    Usuario u = new Usuario();
                    boolean registra = true;
                    u.setCdUsuario(verificaCodigoUsuario());
                    if (u.getCdUsuario() == -1) {
                        Mensagem.ExibirMensagem(RegistrarActivity.this, "Este e-mail já está cadastrado no sistema!", TipoMensagem.ERRO);
                        et_email.setBackgroundColor(Color.rgb(247, 118, 118));
                        registra = false;
                    }

                    u.setDsNome(et_nome.getText().toString());
                    u.setDsEmail(et_email.getText().toString());
                    u.setDsCpf(et_cpf.getText().toString());
                    u.setDsRg(et_rg.getText().toString());
                    u.setDtNascimento(dt_selecionada);
                    u.setDtAtualizacao(new Date());
                    u.setBlAtivo(true);
                    u.setDtCadastro(new Date());

                    if (et_senha.getText().toString().equalsIgnoreCase(et_senhaC.getText().toString())) {
                        u.setDsSenha(et_senha.getText().toString());
                    } else {
                        Mensagem.ExibirMensagem(RegistrarActivity.this, "As senhas não coincidem", TipoMensagem.ERRO);
                        et_senha.setBackgroundColor(Color.rgb(247, 118, 118));
                        et_senhaC.setBackgroundColor(Color.rgb(247, 118, 118));
                        registra = false;
                    }
                    if (registra) {
                        try {
                            UsuarioTask task = new UsuarioTask(RegistrarActivity.this, "POST");
                            Usuario usuario = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{new Gson().toJson(u)}).get();
                            System.out.println("VERIFICA Result ------------------->" + usuario);
                            Mensagem.ExibirMensagem(RegistrarActivity.this, "Usuário cadastrado com sucesso!", TipoMensagem.SUCESSO);
                            u.save();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        finish();

                    }
                } else {
                    Mensagem.ExibirMensagem(RegistrarActivity.this, "Alguma data não foi informada!", TipoMensagem.ERRO);
                }
            }
        });
    }

    private void loadXML() {
        et_nome = findViewById(R.id.et_nome);
        et_cpf = findViewById(R.id.et_cpf);
        et_rg = findViewById(R.id.et_rg);
        et_senha = findViewById(R.id.et_senha);
        et_senhaC = findViewById(R.id.et_senhaC);
        tv_dtnascimento = findViewById(R.id.tv_dt_nascimento);
        et_email = findViewById(R.id.et_email);
        bt_registrar = findViewById(R.id.bt_registrar);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dt_selecionada = sdf.parse(dayOfMonth + "/" + (month + 1) + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_dtnascimento.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
    }


    private int verificaCodigoUsuario() {
        //Se for a API montada por vocês
        try {

            TaskGet task = new TaskGet(this, "Usuarios");
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios/byEmail/" + et_email.getText().toString()}).get();
            System.out.println("VERIFICA Result ------------------->" + result);

            if (result != null && !result.isError()) {
                Type typeUser = new TypeToken<Usuario>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                Usuario usuario = gson.fromJson(result.getContent(), typeUser);

                return -1;
            } else {  // Caso não encontre um e-mail pertencente irá efetuar a busca de todos os registros e devolver o código necessário
                TaskGet task1 = new TaskGet(this, "Usuarios");
                Result result1 = task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios/Count"}).get();
                System.out.println("VERIFICA Result1 ------------------->" + result1);

                Type typeUser1 = new TypeToken<Integer>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                int tamanho = gson.fromJson(result1.getContent(), typeUser1);
                System.out.println("tamanho da Lista--------------> " + tamanho);
                return tamanho + 1;
            }


            //System.out.println("VERIFICA VIROU USUARIO ------------------> " + u.getDsNome());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
