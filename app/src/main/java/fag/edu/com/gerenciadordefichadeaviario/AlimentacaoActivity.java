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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Alimentacao;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;

public class AlimentacaoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, SwipeRefreshLayout.OnRefreshListener {
    private Lote lote;
    Button bt_adicionar_racao, bt_salvar_alimentacao;
    TextView tv_dt_recebimento, tv_aviario_principal_alimentacao;
    EditText et_qt_recebida;
    Spinner sp_alimentacao;

    private SwipeRefreshLayout mSwipeToRefresh;
    private int day, mounth, year;
    private Date dt_selecionada = null;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!validaLote()) {
            Mensagem.ExibirMensagem(AlimentacaoActivity.this, "Este aviário não possui LOTE ativo!", TipoMensagem.ERRO);
            finish();
        }

        carregaComponentes();
        carregaEventos();


    }

    private boolean validaLote() {
        boolean inLote = false;
        if (MainActivity.aviario_selecionado == null) {
            return inLote;
        }
        if (!Lote.listAll(Lote.class).isEmpty()) {
            for (Lote l : Lote.listAll(Lote.class)) {
                if (l.getCdAviario() == MainActivity.aviario_selecionado.getCdAviario()) {
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

        bt_adicionar_racao = findViewById(R.id.bt_adicionar_racao);
        bt_salvar_alimentacao = findViewById(R.id.bt_salvar_alimentacao);
        tv_dt_recebimento = findViewById(R.id.tv_dt_recebimento);
        tv_aviario_principal_alimentacao = findViewById(R.id.tv_aviario_principal_alimentacao);
        et_qt_recebida = findViewById(R.id.et_qt_recebida);
        sp_alimentacao = findViewById(R.id.sp_alimentacao);
        // Recupera o SwipeRefreshLayout
        mSwipeToRefresh = findViewById(R.id.swipe_refresh_container_alimentacao);

        // Seta o Listener para atualizar o conteudo quando o gesto for feito
        mSwipeToRefresh.setOnRefreshListener(this);

        // O esquema de cores
        mSwipeToRefresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );

        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_alimentacao.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }

        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(AlimentacaoActivity.this, this, year, mounth, day);

        atualizaSpinner();


    }

    private void atualizaSpinner() {
        if (Racao.listAll(Racao.class).size() != 0) {
            List<Racao> racaoList = Racao.listAll(Racao.class);
            sp_alimentacao.setAdapter(new ArrayAdapter<>(AlimentacaoActivity.this, R.layout.support_simple_spinner_dropdown_item, racaoList));
        } else {
            Mensagem.ExibirMensagem(AlimentacaoActivity.this, "Você não possui rações cadastradas, favor cadastre-as!", TipoMensagem.ALERTA);
        }
    }

    private void carregaEventos() {
        bt_adicionar_racao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlimentacaoActivity.this, RacaoActivity.class);
                startActivity(intent);
            }
        });

        bt_salvar_alimentacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Racao.listAll(Racao.class).size() > 0 && et_qt_recebida.getText().length() > 0) {
                    Alimentacao alimentacao = new Alimentacao();
                    alimentacao.setCdAlimentacao(Alimentacao.listAll(Alimentacao.class).size() + 1);
                    alimentacao.setBlAtivo(true);
                    alimentacao.setDtCadastro(new Date());
                    alimentacao.setDtAtualizacao(new Date());
                    alimentacao.setLote(lote);
                    alimentacao.setQtRecebida(Double.parseDouble(et_qt_recebida.getText().toString()));
                    alimentacao.setRacao((Racao) sp_alimentacao.getSelectedItem());
                    alimentacao.save();
                    Mensagem.ExibirMensagem(AlimentacaoActivity.this, "Alimentação salva com sucesso!", TipoMensagem.SUCESSO);
                } else {
                    Mensagem.ExibirMensagem(AlimentacaoActivity.this, "Favor verifique se todos os campos estão devidamente preenchidos!", TipoMensagem.ERRO);
                }

            }
        });

        tv_dt_recebimento.setOnClickListener(new View.OnClickListener() {
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
        tv_dt_recebimento.setText(sdf.format(dt_selecionada));
    }

    @Override
    public void onRefresh() {
        atualizaSpinner();
        mSwipeToRefresh.setRefreshing(false);
    }
}
