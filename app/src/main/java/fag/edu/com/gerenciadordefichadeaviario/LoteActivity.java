package fag.edu.com.gerenciadordefichadeaviario;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;

public class LoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText et_qt_aves, et_linhagem;
    TextView tv_dt_estimada_entrega, tv_dt_chegada, tv_aviario_lote;
    Button bt_gerar_lote;
    private int day, mounth, year;
    private Date dt_selecionada;
    private Date dt_selecionadaChegada;
    private int definicao;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog, datePickerDialogChegada;//Dialog pra Date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lote);
        carregaComponentes();
        carregaEventos();

    }


    private void carregaComponentes() {
        et_qt_aves = findViewById(R.id.et_qt_aves);
        et_linhagem = findViewById(R.id.et_linhagem);
        tv_dt_estimada_entrega = findViewById(R.id.tv_dt_estimada_entrega);
        tv_dt_chegada = findViewById(R.id.tv_dt_chegada);
        tv_aviario_lote = findViewById(R.id.tv_aviario_lote);
        bt_gerar_lote = findViewById(R.id.bt_gerar_lote);

        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(LoteActivity.this, this, year, mounth, day);
        datePickerDialogChegada = new DatePickerDialog(LoteActivity.this, this, year, mounth, day);

        tv_aviario_lote.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
    }

    private void carregaEventos() {
        bt_gerar_lote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Aviario aviario = MainActivity.aviario_selecionado;
                    if (Integer.parseInt(et_qt_aves.getText().toString()) > aviario.getNrCapAves()) {
                        Mensagem.ExibirMensagem(LoteActivity.this,"O aviário não suporta esta quantidade de aves, seu valor maximo é " + aviario.getNrCapAves(), TipoMensagem.ERRO);
                    }

                    Lote lote = new Lote();
                    lote.setCd_lote(Lote.listAll(Lote.class).size() + 1);
                    lote.setDs_linhagem(et_linhagem.getText().toString());
                    lote.setAviario(MainActivity.aviario_selecionado);
                    lote.setDt_atualizacao(new Date());
                    lote.setDt_cadastro(new Date());
                    lote.setDt_entrega(dt_selecionada);
                    lote.setDt_estimado_entrega(dt_selecionadaChegada);
                    lote.setQt_aves(Integer.parseInt(et_qt_aves.getText().toString()));
                    lote.save();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tv_dt_chegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                definicao = 2;
                datePickerDialogChegada.show();
            }
        });

        tv_dt_estimada_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                definicao = 1;
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (definicao == 1) {
            dt_selecionada = new Date(year - 1900, month, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.format(dt_selecionada);
            System.out.println(sdf.format(dt_selecionada));
            tv_dt_estimada_entrega.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        } else {
            dt_selecionadaChegada = new Date(year - 1900, month, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.format(dt_selecionadaChegada);
            System.out.println(sdf.format(dt_selecionadaChegada));
            tv_dt_chegada.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    }
}
