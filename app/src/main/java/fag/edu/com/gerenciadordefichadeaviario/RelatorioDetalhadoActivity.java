package fag.edu.com.gerenciadordefichadeaviario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Alimentacao;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Mortalidade;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;
import fag.edu.com.gerenciadordefichadeaviario.models.Vacina;

public class RelatorioDetalhadoActivity extends AppCompatActivity {
    private int tipoTela;
    private Lote lote;

    TextView tv_dt_chegada_r, tv_dt_termino_r, tv_mortalidade_r, tv_mortalidade_r2, tv_peso_total, tv_media_peso, tv_consumo_racao_ave;
    ListView lv_mortalidade_r, lv_racao_r, lv_vacina_r, lv_hidrometro_r;

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
                Snackbar.make(view, "Infelizmente está opção ainda não está valida", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        carregaComponentes();


        try {
            tipoTela = (int) getIntent().getExtras().get("LOTE");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Mensagem.ExibirMensagem(RelatorioDetalhadoActivity.this, "código do LOTE: " + tipoTela, TipoMensagem.SUCESSO);

        for (Lote l : Lote.listAll(Lote.class)) {
            if (l.getCdLote() == tipoTela) {
                lote = l;
                break;
            }
        }
        carregaDados();
    }

    private void carregaComponentes() {
        tv_dt_chegada_r = findViewById(R.id.tv_dt_chegada_r);
        tv_dt_termino_r = findViewById(R.id.tv_dt_termino_r);
        tv_mortalidade_r = findViewById(R.id.tv_mortalidade_r);
        tv_mortalidade_r2 = findViewById(R.id.tv_mortalidade_r2);
        tv_peso_total = findViewById(R.id.tv_peso_total);
        tv_media_peso = findViewById(R.id.tv_media_peso);
        tv_consumo_racao_ave = findViewById(R.id.tv_consumo_racao_ave);
        lv_racao_r = findViewById(R.id.lv_racao_r);
        lv_vacina_r = findViewById(R.id.lv_vacina_r);
        lv_hidrometro_r = findViewById(R.id.lv_hidrometro_r);
        lv_mortalidade_r = findViewById(R.id.lv_mortalidade_r);
    }

    private void carregaDados() {

        if (lote != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tv_dt_chegada_r.setText(sdf.format(lote.getDtChegada()));
            tv_dt_termino_r.setText(sdf.format(lote.getDtEntrega()));

            //MORTALIDADE -------------------------------------------------------------------------------------------------------------------------------------------------------
            int mortes = 0;
            float porcento = 0;
            List<Mortalidade> mortalidadeList = new ArrayList<>();
            for (Mortalidade m : Mortalidade.listAll(Mortalidade.class)) {
                if (m.getLote().getCdLote() == lote.getCdLote()) {
                    mortes += m.getNrAvesAbatidas() + m.getNrAvesEliminadas();
                    mortalidadeList.add(m);
                }
            }

            porcento = (float) ((mortes * 100) / lote.getQtAves());
            tv_mortalidade_r.setText(String.valueOf(mortes));
            tv_mortalidade_r2.setText(String.valueOf(porcento));
            lv_mortalidade_r.setAdapter(new ArrayAdapter<>(RelatorioDetalhadoActivity.this, R.layout.support_simple_spinner_dropdown_item, mortalidadeList));

            //ALIMENTACAO -------------------------------------------------------------------------------------------------------------------------------------------------------

            List<Alimentacao> alimentacaoList = new ArrayList<>();
            float qt_racao = 0;
            for (Alimentacao a : Alimentacao.listAll(Alimentacao.class)) {
                if (a.getLote().getCdLote() == lote.getCdLote()) {
                    alimentacaoList.add(a);
                    qt_racao += a.getQtRecebida();
                }
            }
            lv_racao_r.setAdapter(new ArrayAdapter<>(RelatorioDetalhadoActivity.this, R.layout.support_simple_spinner_dropdown_item, alimentacaoList));

            //PESAGEM -------------------------------------------------------------------------------------------------------------------------------------------------------

            float pesoTotal = 0;
            float pesoMedio = 0;
            float consumo = 0;
            List<Pesagem> pesagemList = new ArrayList<>();
            for (Pesagem p : Pesagem.listAll(Pesagem.class)) {
                if (p.getLote().getCdLote() == lote.getCdLote()) {
                    pesoTotal += p.getVlPesoMedio();
                    pesagemList.add(p);
                }
            }

            pesoMedio = pesoTotal / pesagemList.size();
            tv_peso_total.setText(String.valueOf(pesoTotal));
            tv_media_peso.setText(String.valueOf(pesoMedio));
            consumo = lote.getQtAves() / qt_racao;
            tv_consumo_racao_ave.setText(String.valueOf(consumo));


            //VACINA -------------------------------------------------------------------------------------------------------------------------------------------------------
            List<Vacina> vacinaList = new ArrayList<>();
            for (Vacina v : Vacina.listAll(Vacina.class)) {
                if (v.getLote().getCdLote() == lote.getCdLote()) {
                    vacinaList.add(v);
                }
            }
            lv_vacina_r.setAdapter(new ArrayAdapter<>(RelatorioDetalhadoActivity.this, R.layout.support_simple_spinner_dropdown_item, vacinaList));

            //AGUA -------------------------------------------------------------------------------------------------------------------------------------------------------

            List<Hidrometro> hidrometroList = new ArrayList<>();
            for (Hidrometro h : Hidrometro.listAll(Hidrometro.class)) {
                if (h.getLote().getCdLote() == lote.getCdLote()) {
                    hidrometroList.add(h);
                }
            }
            lv_hidrometro_r.setAdapter(new ArrayAdapter<>(RelatorioDetalhadoActivity.this, R.layout.support_simple_spinner_dropdown_item, hidrometroList));
        }
    }
}
