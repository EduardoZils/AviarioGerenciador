package fag.edu.com.gerenciadordefichadeaviario;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Mortalidade;

public class MortalidadeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText et_aves_eliminadas, et_aves_mortas;
    TextView tv_dt_leitura_mortalidade, tv_aviario_principal_mortalidade;
    ImageButton bt_mais_mortas, bt_menos_mortas, bt_mais_eliminadas, bt_menos_eliminadas;
    Button bt_salvar_mortalidade;


    private Date dt_selecionada;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private Lote lote;
    private int day, mounth, year, elimadas = 0, mortas;
    private boolean isEdicao;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Mortalidade aviario_mortalidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortalidade);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        carregaComponentes();
        carregaEventos();

        if (!validaLote()) {
            Mensagem.ExibirMensagem(MortalidadeActivity.this, "Este aviário não possui LOTE ativo!", TipoMensagem.ERRO);
            finish();
        }
        validaEdicao();

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

    private void validaEdicao() {
        if (Mortalidade.listAll(Mortalidade.class).isEmpty()) {
            isEdicao = false;
        } else {
            if (Mortalidade.listAll(Mortalidade.class) == null) {
                isEdicao = false;
            } else {
                for (Mortalidade m : Mortalidade.listAll(Mortalidade.class)) {
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                    //if (m.getDtMorte().getDay() == new Date().getDay() && m.getDtMorte().getMonth() != new Date().getMonth() && m.getDtMorte().getYear() != new Date().getYear()) {


                    if (sdf.format(new Date()).equalsIgnoreCase(sdf.format(m.getDtMorte()))) {
                        Mensagem.ExibirMensagem(MortalidadeActivity.this, "Você está editando o valor de hoje", TipoMensagem.ALERTA);

                        et_aves_eliminadas.setText(String.valueOf(m.getNrAvesEliminadas()));
                        et_aves_mortas.setText(String.valueOf(m.getNrAvesAbatidas()));
                        isEdicao = true;
                        aviario_mortalidade = m;

                        dt_selecionada = m.getDtMorte();
                        sdf.format(dt_selecionada);
                        System.out.println(sdf.format(dt_selecionada));
                        tv_dt_leitura_mortalidade.setText(sdf.format(dt_selecionada));
                        break;
                    } else {
                        isEdicao = false;
                    }
                }
            }
        }

    }


    private void carregaComponentes() {

        et_aves_eliminadas = findViewById(R.id.et_aves_eliminadas);
        et_aves_mortas = findViewById(R.id.et_aves_mortas);
        bt_mais_mortas = findViewById(R.id.bt_mais_mortas);
        bt_menos_mortas = findViewById(R.id.bt_menos_mortas);
        bt_mais_eliminadas = findViewById(R.id.bt_mais_eliminadas);
        bt_menos_eliminadas = findViewById(R.id.bt_menos_eliminadas);
        bt_salvar_mortalidade = findViewById(R.id.bt_salvar_mortalidade);
        tv_dt_leitura_mortalidade = findViewById(R.id.tv_dt_leitura_mortalidade);
        tv_aviario_principal_mortalidade = findViewById(R.id.tv_aviario_principal_mortalidade);

        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_mortalidade.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }

        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(MortalidadeActivity.this, this, year, mounth, day);
    }

    private void carregaEventos() {

        bt_mais_eliminadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_aves_eliminadas.getText().toString().equals("")) {
                    et_aves_eliminadas.setText("0");
                }
                elimadas = Integer.valueOf(et_aves_eliminadas.getText().toString());

                et_aves_eliminadas.setText(String.valueOf(elimadas + 1));
            }
        });

        bt_menos_eliminadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_aves_eliminadas.getText().toString().equals("")) {
                    et_aves_eliminadas.setText("0");
                }
                elimadas = Integer.valueOf(et_aves_eliminadas.getText().toString());
                if (elimadas > 0) {
                    et_aves_eliminadas.setText(String.valueOf(elimadas - 1));
                } else {
                    Mensagem.ExibirMensagem(MortalidadeActivity.this, "Não tem como o número de aves ser inferior a zero!", TipoMensagem.ALERTA);
                }
            }
        });

        bt_mais_mortas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_aves_mortas.getText().toString().equals("")) {
                    et_aves_mortas.setText("0");
                }
                mortas = Integer.valueOf(et_aves_mortas.getText().toString());

                et_aves_mortas.setText(String.valueOf(mortas + 1));
            }
        });

        bt_menos_mortas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_aves_mortas.getText().toString().equals("")) {
                    et_aves_mortas.setText("0");
                }
                mortas = Integer.valueOf(et_aves_mortas.getText().toString());
                if (mortas > 0) {
                    et_aves_mortas.setText(String.valueOf(mortas - 1));
                } else {
                    Mensagem.ExibirMensagem(MortalidadeActivity.this, "Não tem como o número de aves ser inferior a zero!", TipoMensagem.ALERTA);
                }
            }
        });
        tv_dt_leitura_mortalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        bt_salvar_mortalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean podeGravar;
                if (Mortalidade.listAll(Mortalidade.class).isEmpty()) {
                    podeGravar = true;
                } else {
                    Date hoje = new Date();

                    // Compara se a data de hoje é maior que a data selecionada utilizando SDF para não ter problema com horas/minutos/segundos/milesimos {
                    if (sdf.format(hoje).compareTo(sdf.format(dt_selecionada)) < 0) {
                        Mensagem.ExibirMensagem(MortalidadeActivity.this, "Você não pode inserir valores em datas fururas", TipoMensagem.ERRO);
                        podeGravar = false;
                    } else {
                        podeGravar = true;
                    }
                }
                if (podeGravar) {
                    try {

                        if (isEdicao) {
                            int codigo = aviario_mortalidade.getCdMortalidade();
                            Date data = aviario_mortalidade.getDtMorte();
                            aviario_mortalidade.delete();

                            Mortalidade m = new Mortalidade();
                            m.setCdMortalidade(codigo);
                            m.setCdLote(lote.getCdLote());
                            m.setLote(lote);
                            m.setBlAtivo(true);
                            m.setDtAtualizacao(new Date());
                            m.setDtCadastro(new Date());
                            m.setDtMorte(data);
                            m.setNrAvesAbatidas(Integer.valueOf(et_aves_mortas.getText().toString()));
                            m.setNrAvesEliminadas(Integer.valueOf(et_aves_eliminadas.getText().toString()));
                            m.setIntegrado(false);
                            m.save();
                            Mensagem.ExibirMensagem(MortalidadeActivity.this, "Mortes atualizadas com sucesso!", TipoMensagem.SUCESSO);
                        } else {
                            Mortalidade m = new Mortalidade();
                            m.setCdMortalidade(Mortalidade.listAll(Mortalidade.class).size() + 1);
                            m.setCdLote(lote.getCdLote());
                            m.setLote(lote);
                            m.setBlAtivo(true);
                            m.setDtMorte(dt_selecionada);
                            m.setDtAtualizacao(new Date());
                            m.setDtCadastro(new Date());
                            m.setNrAvesAbatidas(Integer.valueOf(et_aves_mortas.getText().toString()));
                            m.setNrAvesEliminadas(Integer.valueOf(et_aves_eliminadas.getText().toString()));
                            m.setIntegrado(false);
                            m.save();
                            Mensagem.ExibirMensagem(MortalidadeActivity.this, "Mortes salvas com sucesso!", TipoMensagem.SUCESSO);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        try {
            dt_selecionada = sdf.parse(dayOfMonth + "/" + (month + 1) + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.format(dt_selecionada);
        System.out.println(sdf.format(dt_selecionada));
        tv_dt_leitura_mortalidade.setText(sdf.format(dt_selecionada));
    }
}
