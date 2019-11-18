package fag.edu.com.gerenciadordefichadeaviario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;

public class RelatorioActivity extends AppCompatActivity {

    ListView lv_lotes;
    TextView tv_aviario_principal_relatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv_lotes = findViewById(R.id.lv_lotes);
        tv_aviario_principal_relatorio = findViewById(R.id.tv_aviario_principal_relatorio);

        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_relatorio.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }

        carregaLista();
        carregaComponentes();

    }

    private void carregaComponentes() {

        lv_lotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Lote lote = Lote.listAll(Lote.class).get(i);

                Intent intent = new Intent(RelatorioActivity.this, RelatorioDetalhadoActivity.class);
                intent.putExtra("LOTE", lote.getCdLote());
                startActivity(intent);
            }
        });


    }

    private void carregaLista() {
        Aviario aviario = MainActivity.aviario_selecionado;
        if (Lote.listAll(Lote.class).size() != 0) {
            //List<Lote> loteList = Lote.listAll(Lote.class);
            List<Lote> loteList = new ArrayList<>();
            for (Lote l : Lote.listAll(Lote.class)) {
                if (l.getAviario().getCdAviario() == aviario.getCdAviario()) {
                    loteList.add(l);
                }
            }
            lv_lotes.setAdapter(new ArrayAdapter<>(RelatorioActivity.this, R.layout.support_simple_spinner_dropdown_item, loteList));
        } else {
            Mensagem.ExibirMensagem(RelatorioActivity.this, "Não existem LOTES cadastrados!", TipoMensagem.ALERTA);
        }
    }

}
