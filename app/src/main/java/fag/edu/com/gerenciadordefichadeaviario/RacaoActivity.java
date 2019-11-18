package fag.edu.com.gerenciadordefichadeaviario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;

public class RacaoActivity extends AppCompatActivity {

    EditText et_nome_racao, et_tipo_racao;
    Button bt_salvar_racao;

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
                        racao.setCdRacao(Racao.listAll(Racao.class).size() + 1);
                        racao.setBlAtivo(true);
                        racao.setDsNome(et_nome_racao.getText().toString());
                        racao.setDsTipo(et_tipo_racao.getText().toString());
                        racao.setDtCadastro(new Date());
                        racao.setDtAtualizacao(new Date());
                        racao.save();
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

}
