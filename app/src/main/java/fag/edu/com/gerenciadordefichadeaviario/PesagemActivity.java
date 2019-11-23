package fag.edu.com.gerenciadordefichadeaviario;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesos;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;

public class PesagemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, SwipeRefreshLayout.OnRefreshListener {
    public static Lote lote;
    private SwipeRefreshLayout mSwipeToRefresh;
    private int day, mounth, year;
    private Date dt_selecionada = null;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    public static List<Pesos> pesosList = new ArrayList<Pesos>();

    Button bt_adicionar_pesos, bt_salvar_pesagem;
    ListView lv_pesos;
    TextView tv_dt_pesagem, tv_aviario_principal_pesagem;
    public static Pesagem pesagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesagem);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!Pesagem.listAll(Pesagem.class).isEmpty()) {
            pesagem = new Pesagem(Pesagem.listAll(Pesagem.class).size() + 1);
        } else {
            pesagem = new Pesagem(1);
        }
        if (!validaLote()) {
            Mensagem.ExibirMensagem(PesagemActivity.this, "Este aviário não possui LOTE ativo!", TipoMensagem.ERRO);
            finish();
        }


        carregaComponentes();
        carregaEventos();
        atualizaList();

    }

    private boolean validaLote() {
        boolean inLote = false;
        if (MainActivity.aviario_selecionado == null) {
            return inLote;
        }
        if (!Lote.listAll(Lote.class).isEmpty()) {
            for (Lote l : Lote.listAll(Lote.class)) {
                if (l.getAviario().getCdAviario() == MainActivity.aviario_selecionado.getCdAviario()) {
                    inLote = true;
                    lote = l;
                    break;
                }
            }
            return inLote;
        } else {
            return inLote;
        }
    }

    private void carregaComponentes() {

        bt_adicionar_pesos = findViewById(R.id.bt_adicionar_pesos);
        bt_salvar_pesagem = findViewById(R.id.bt_salvar_pesagem);
        lv_pesos = findViewById(R.id.lv_pesos);
        tv_dt_pesagem = findViewById(R.id.tv_dt_pesagem);
        tv_aviario_principal_pesagem = findViewById(R.id.tv_aviario_principal_pesagem);

        // Recupera o SwipeRefreshLayout
        mSwipeToRefresh = findViewById(R.id.swipe_refresh_container_pesagem);

        // Seta o Listener para atualizar o conteudo quando o gesto for feito
        mSwipeToRefresh.setOnRefreshListener(this);

        // O esquema de cores
        mSwipeToRefresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );

        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_pesagem.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }

        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(PesagemActivity.this, this, year, mounth, day);

    }

    private void atualizaList() {
        try {
            if (pesosList.size() > 0) {
                lv_pesos.setAdapter(new ArrayAdapter<>(PesagemActivity.this, R.layout.support_simple_spinner_dropdown_item, pesosList));
            } else {
                Mensagem.ExibirMensagem(PesagemActivity.this, "Lista de Pesos vazia, favor adicione mais!", TipoMensagem.ALERTA);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void carregaEventos() {

        bt_adicionar_pesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesagemActivity.this, PesosActivity.class);
                startActivity(intent);
            }
        });

        bt_salvar_pesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pesosList.size() > 0) {
                    pesagem.setCdPesagem(Pesagem.listAll(Pesagem.class).size() + 1);
                    pesagem.setBlAtivo(true);
                    pesagem.setDtAtualizacao(new Date());
                    pesagem.setDtCadastro(new Date());
                    pesagem.setDtPesagem(dt_selecionada);
                    pesagem.setCdLote(lote.getCdLote());
                    pesagem.setLote(lote);
                    pesagem.setNmSemana(Calendar.getInstance().getFirstDayOfWeek());
                    pesagem.setQtPesagens(pesosList.size());
                    Double valorMedio = 0.0;
                    for (Pesos p : pesosList) {
                        valorMedio += p.getVlPesagem();
                    }
                    valorMedio = valorMedio / pesosList.size();


                    pesagem.setVlPesoMedio(valorMedio);
                    pesagem.setVlPesoSemana(valorMedio);
                    pesagem.setIntegrado(false);
                    pesagem.save();
                    Mensagem.ExibirMensagem(PesagemActivity.this, "Pesagem salva com sucesso!", TipoMensagem.SUCESSO);

                    for (Pesos p : pesosList) {
                        p.setCdPesagem(pesagem.getCdPesagem());
                        p.save();
                    }

                    pesosList = new ArrayList<Pesos>();
                    atualizaList();
                } else {
                    Mensagem.ExibirMensagem(PesagemActivity.this, "Lista de Pesos vazia, favor adicione mais!", TipoMensagem.ALERTA);
                }
            }
        });

        tv_dt_pesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dt_selecionada = new Date(year - 1900, month, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.format(dt_selecionada);
        System.out.println(sdf.format(dt_selecionada));
        tv_dt_pesagem.setText(sdf.format(dt_selecionada));
    }

    @Override
    public void onRefresh() {
        atualizaList();
        mSwipeToRefresh.setRefreshing(false);
    }
}
