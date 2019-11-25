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
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Vacina;

public class VacinaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Lote lote;
    private int day, mounth, year;
    private Date dt_selecionada;
    private Calendar calendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;

    EditText et_nome_vacina, et_prescricao_vacina, et_dosagem_vacina, et_forma_de_uso_vacina;
    TextView tv_aviario_principal_vacina, tv_dt_vacina;
    Button bt_adicionar_vacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaComponentes();
        carregaEventos();

        if (!validaLote()) {
            Mensagem.ExibirMensagem(VacinaActivity.this, "Este aviário não possui LOTE ativo!", TipoMensagem.ERRO);
            finish();
        }

        if (Vacina.listAll(Vacina.class).isEmpty()) {
            Mensagem.ExibirMensagem(VacinaActivity.this, "Tome cuidado ao preencher os campos, não será possivel altera-los após aplicada a vacina!", TipoMensagem.ALERTA);
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

    private void carregaComponentes() {
        et_nome_vacina = findViewById(R.id.et_nome_vacina);
        et_prescricao_vacina = findViewById(R.id.et_prescricao_vacina);
        et_forma_de_uso_vacina = findViewById(R.id.et_forma_de_uso_vacina);
        et_dosagem_vacina = findViewById(R.id.et_dosagem_vacina);
        tv_aviario_principal_vacina = findViewById(R.id.tv_aviario_principal_vacina);
        tv_dt_vacina = findViewById(R.id.tv_dt_vacina);
        bt_adicionar_vacina = findViewById(R.id.bt_adicionar_vacina);


        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_vacina.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }

        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(VacinaActivity.this, this, year, mounth, day);
    }

    private void carregaEventos() {

        bt_adicionar_vacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if(et_forma_de_uso_vacina.getText().length() > 0 && et_nome_vacina.getText().length() > 0 && et_prescricao_vacina.getText().length() > 0 && et_dosagem_vacina.getText().length() > 0){
                        Vacina v = new Vacina();
                        v.setCdVacina(Vacina.listAll(Vacina.class).size() + 1);
                        v.setCdLote(lote.getCdLote());
                        v.setBlAtivo(true);
                        v.setIntegrado(false);
                        v.setDsFormaUso(et_forma_de_uso_vacina.getText().toString());
                        v.setDsNome(et_nome_vacina.getText().toString());
                        v.setDsPrescricao(et_prescricao_vacina.getText().toString());
                        v.setQtUsada(Double.parseDouble(et_dosagem_vacina.getText().toString()));
                        v.setLote(lote);
                        v.setDtCadastro(new Date());
                        v.setDtAtualizacao(new Date());
                        v.setDtUso(dt_selecionada);
                        v.save();
                    }else{
                        Mensagem.ExibirMensagem(VacinaActivity.this, "Verifique se todos os campos estão devidamente preenchidos!", TipoMensagem.ERRO);
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tv_dt_vacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
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
        tv_dt_vacina.setText(sdf.format(dt_selecionada));
    }
}
