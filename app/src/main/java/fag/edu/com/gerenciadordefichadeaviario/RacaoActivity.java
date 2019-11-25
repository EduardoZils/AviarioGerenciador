package fag.edu.com.gerenciadordefichadeaviario;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.RacaoTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;

public class RacaoActivity extends AppCompatActivity {

    EditText et_nome_racao, et_tipo_racao;
    Button bt_salvar_racao;
    int codigoRacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaComponentes();
        carregaEventos();
    }

    private void carregaComponentes() {
        et_nome_racao = findViewById(R.id.et_nome_racao);
        et_tipo_racao = findViewById(R.id.et_tipo_racao);
        bt_salvar_racao = findViewById(R.id.bt_salvar_racao);
    }

    private void carregaEventos() {
        bt_salvar_racao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_nome_racao.getText().length() > 0 && et_tipo_racao.getText().length() > 0) {
                    try {
                        Racao racao = new Racao();
                        racao.setCdRacao(carregaCodigoRacao());
                        racao.setBlAtivo(true);
                        racao.setIntegrado(false);
                        racao.setDsNome(et_nome_racao.getText().toString());
                        racao.setDsTipo(et_tipo_racao.getText().toString());
                        racao.setDtCadastro(new Date());
                        racao.setDtAtualizacao(new Date());
                        racao.save();
                        Mensagem.ExibirMensagem(RacaoActivity.this, "Ração cadastrada com sucesso!", TipoMensagem.SUCESSO);
                        integraDados();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    finish();
                } else {
                    Mensagem.ExibirMensagem(RacaoActivity.this, "Favor verifique se todos os campos estão devidamente preenchidos!", TipoMensagem.ERRO);

                }
            }
        });
    }

    private int carregaCodigoRacao() {
        try {
            TaskGet task1 = new TaskGet(this, "Raçoes");
            Result result1 = task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Racaos/Count"}).get();
            System.out.println("VERIFICA Result1 ------------------->" + result1);

            Type typeUser1 = new TypeToken<Integer>() {
            }.getType();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            int tamanho = gson.fromJson(result1.getContent(), typeUser1);
            System.out.println("tamanho da Lista--------------> " + tamanho);
            codigoRacao = tamanho + 1;
            return tamanho + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 1;


    }

    private void integraDados() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        List<Racao> racaoList = new ArrayList<>();
        if (Racao.listAll(Racao.class).size() > 0) {
            for (Racao e : Racao.listAll(Racao.class)) {
                if (!e.isIntegrado()) {
                    racaoList.add(e);
                }
            }
            RacaoTask taskRacao = new RacaoTask(RacaoActivity.this, "POST");
            taskRacao.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(racaoList)});

            Racao endereco = Racao.last(Racao.class);
            System.out.println(endereco);
            endereco.setIntegrado(true);
            endereco.update();
            System.out.println(endereco);

        }

    }

}
