package fag.edu.com.gerenciadordefichadeaviario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Util.Adapters.SelecaoAdapter;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;

public class SelecaoAviario extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Button btAdicionarAviario, btEditarAviario, btLoteAviario;
    private ListView lvAviario;
    private SwipeRefreshLayout mSwipeToRefresh;
    private SelecaoAdapter adapterAviario;
    private boolean aviarioEmLote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_aviario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaComponentes();
        atualizaLista();
        carregaEventos();


    }

    @Override
    public void onRefresh() {
        // Executar a atualizacao
        atualizaLista();
        mSwipeToRefresh.setRefreshing(false);
    }

    private void carregaEventos() {
        btAdicionarAviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecaoAviario.this, AddAviario.class);
                intent.putExtra("EDICAO", -1);
                startActivity(intent);
            }
        });
        btEditarAviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecaoAviario.this, AddAviario.class);

                intent.putExtra("EDICAO", MainActivity.aviario_selecionado.getCdAviario());
                startActivity(intent);
                atualizaLista();
            }
        });

        lvAviario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.aviario_selecionado = Aviario.listAll(Aviario.class).get(position);
                String txt = null;


                if (Lote.listAll(Lote.class).isEmpty()) {
                    txt = "NÃO";
                    aviarioEmLote = false;
                } else {
                    for (Lote l : Lote.listAll(Lote.class)) {
                        if (l.getAviario().getCdAviario() == MainActivity.aviario_selecionado.getCdAviario()) {
                            aviarioEmLote = true;
                            txt = "SIM";
                            break;
                        } else {
                            txt = "NÃO";
                            aviarioEmLote = false;
                        }
                    }
                }
                Mensagem.ExibirMensagem(SelecaoAviario.this, "Novo aviário selecionado" + MainActivity.aviario_selecionado.getNrIdentificador() + "\n" +
                        "Em lote: " + txt, TipoMensagem.SUCESSO);
                atualizaCampo();
            }

            private void atualizaCampo() {
                if (aviarioEmLote) {
                    btLoteAviario.setText("FINALIZAR LOTE");
                } else {
                    btLoteAviario.setText("INICIAR LOTE");

                }
            }
        });

        btLoteAviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean erro = false;
                List<Lote> loteList = Lote.listAll(Lote.class);
                for (Lote l : loteList) {
                    if (aviarioEmLote && l.isBlAtivo()) {
                        if (l.getAviario().getCdAviario() == MainActivity.aviario_selecionado.getCdAviario()) {
                            Mensagem.ExibirMensagem(SelecaoAviario.this, "LOTE FINALIZADO!", TipoMensagem.SUCESSO);
                            erro = true;
                            l.setBlAtivo(false);
                        }
                    }

                }
                if (!erro) {
                    Intent intent = new Intent(SelecaoAviario.this, LoteActivity.class);
                    startActivity(intent);

                }

            }
        });
    }

    private void carregaComponentes() {
        btAdicionarAviario = findViewById(R.id.btAdicionarAviario);
        btEditarAviario = findViewById(R.id.btEditarAviario);
        btLoteAviario = findViewById(R.id.btLoteAviario);
        lvAviario = findViewById(R.id.lvAviario);
        // Recupera o SwipeRefreshLayout
        mSwipeToRefresh = findViewById(R.id.swipe_refresh_container);

        // Seta o Listener para atualizar o conteudo quando o gesto for feito
        mSwipeToRefresh.setOnRefreshListener(this);

        // O esquema de cores
        mSwipeToRefresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );

    }

    private void atualizaLista() {
        if (Aviario.listAll(Aviario.class).size() != 0) {
            List<Aviario> aviarioList = Aviario.listAll(Aviario.class);
            lvAviario.setAdapter(new ArrayAdapter<>(SelecaoAviario.this, R.layout.support_simple_spinner_dropdown_item, aviarioList));

        }  else {
            Mensagem.ExibirMensagem(SelecaoAviario.this, "Não existem aviários cadastrados! Adicione um novo aviário no canto superior direito!", TipoMensagem.ALERTA);
        }
    }


}
