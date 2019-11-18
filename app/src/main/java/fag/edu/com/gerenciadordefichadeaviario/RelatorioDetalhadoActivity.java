package fag.edu.com.gerenciadordefichadeaviario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;

public class RelatorioDetalhadoActivity extends AppCompatActivity {
    private int tipoTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_detalhado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            tipoTela = (int) getIntent().getExtras().get("LOTE");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Mensagem.ExibirMensagem(RelatorioDetalhadoActivity.this, "código do LOTE: " + tipoTela, TipoMensagem.SUCESSO);
    }
}
