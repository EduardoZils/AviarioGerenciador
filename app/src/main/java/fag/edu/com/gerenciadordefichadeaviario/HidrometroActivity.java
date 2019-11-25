package fag.edu.com.gerenciadordefichadeaviario;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;

public class HidrometroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView tv_aviario_principal_hidrometro, tv_dt_leitura_agua;
    private EditText tv_leitura_atual;
    private Button bt_salvar_hidrometro;
    private Date dt_selecionada;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private Lote lote;

    private int day, mounth, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidrometro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaComponentes();
        carregaEventos();

        if (!validaLote()) {
            Mensagem.ExibirMensagem(HidrometroActivity.this, "Este aviário não possui LOTE ativo!", TipoMensagem.ERRO);
            finish();
        }


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

    private void carregaEventos() {
        bt_salvar_hidrometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean podeGravar = false;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date hoje = new Date();
                if (Hidrometro.listAll(Hidrometro.class).isEmpty()) {
                    podeGravar = true;
                } else {
                    for (Hidrometro h : Hidrometro.listAll(Hidrometro.class)) {
                        podeGravar = true;
                        if (sdf.format(hoje).equalsIgnoreCase(sdf.format(h.getDtColeta()))) {
                            Mensagem.ExibirMensagem(HidrometroActivity.this, "Você já inseriu uma leitura hoje", TipoMensagem.ERRO);
                            podeGravar = false;
                            break;
                        }
                        if (sdf.format(hoje).compareTo(sdf.format(h.getDtColeta())) > 0) {
                            Mensagem.ExibirMensagem(HidrometroActivity.this, "Você não pode efetuar lançamentos futuros", TipoMensagem.ERRO);
                            podeGravar = false;
                            break;
                        }
                    }
                }
                if (podeGravar) {
                    if (tv_leitura_atual.getText().length() > 0) {
                        Hidrometro h = new Hidrometro();
                        h.setCdHidrometro(Hidrometro.listAll(Hidrometro.class).size() + 1);
                        h.setLote(lote);
                        h.setCdLote(lote.getCdLote());
                        h.setDtAtualizacao(new Date());
                        h.setDtCadastro(new Date());
                        h.setDtColeta(dt_selecionada);
                        h.setQtGasto(Double.parseDouble(tv_leitura_atual.getText().toString()));
                        h.setBlAtivo(true);
                        h.setIntegrado(false);


                        h.save();

                        Mensagem.ExibirMensagem(HidrometroActivity.this, "Leitura salva com sucesso!", TipoMensagem.SUCESSO);
                    } else {
                        Mensagem.ExibirMensagem(HidrometroActivity.this, "Verifique se todos os campos estão devidamente preenchidos!", TipoMensagem.ERRO);
                    }
                }
            }
        });

        tv_dt_leitura_agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void carregaComponentes() {
        tv_aviario_principal_hidrometro = findViewById(R.id.tv_aviario_principal_hidrometro);
        tv_dt_leitura_agua = findViewById(R.id.tv_dt_leitura_agua);
        tv_leitura_atual = findViewById(R.id.tv_leitura_atual);
        bt_salvar_hidrometro = findViewById(R.id.bt_salvar_hidrometro);
        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_hidrometro.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }


        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(HidrometroActivity.this, this, year, mounth, day);


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dt_selecionada = sdf.parse(dayOfMonth + "/" + (month + 1) + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf.format(dt_selecionada);
        System.out.println(sdf.format(dt_selecionada));
        tv_dt_leitura_agua.setText(sdf.format(dt_selecionada));


    }
}
