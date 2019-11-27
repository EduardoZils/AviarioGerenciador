package fag.edu.com.gerenciadordefichadeaviario;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.LoteTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;

public class LoteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText et_qt_aves, et_linhagem;
    TextView tv_dt_estimada_entrega, tv_dt_chegada, tv_aviario_lote;
    Button bt_gerar_lote;
    private int day, mounth, year;
    private Date dt_selecionada = null;
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
        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_lote.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        } else {
            finish();
        }
    }

    private void carregaEventos() {
        bt_gerar_lote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (dt_selecionada != null && dt_selecionadaChegada != null) {
                        Aviario aviario = MainActivity.aviario_selecionado;
                        if (Integer.parseInt(et_qt_aves.getText().toString()) > aviario.getNrCapAves()) {
                            Mensagem.ExibirMensagem(LoteActivity.this, "O aviário não suporta esta quantidade de aves, seu valor maximo é " + aviario.getNrCapAves(), TipoMensagem.ERRO);
                        } else if (dt_selecionada.after(dt_selecionadaChegada)) {

                            Lote lote = new Lote();
                            lote.setCdLote(verificaCodigoLote());
                            lote.setCdAviario(aviario.getCdAviario());
                            lote.setDsLinhagem(et_linhagem.getText().toString());
                            lote.setAviario(MainActivity.aviario_selecionado);
                            lote.setDtAtualizacao(new Date());
                            lote.setDtCadastro(new Date());
                            lote.setDtChegada(dt_selecionadaChegada);
                            lote.setDtEntrega(dt_selecionada);
                            lote.setDtEstimadaEntrega(dt_selecionada);
                            lote.setQtAves(Integer.parseInt(et_qt_aves.getText().toString()));
                            lote.setBlAtivo(true);
                            lote.setIntegrado(false);


                            lote.save();

                            integraDados();
                        } else {
                            Mensagem.ExibirMensagem(LoteActivity.this, "Alguma data não foi informada!", TipoMensagem.ERRO);
                        }
                    } else {
                        Mensagem.ExibirMensagem(LoteActivity.this, "Datas inválidas, verifique se estão corretas!", TipoMensagem.ERRO);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finish();
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dt_selecionada = sdf.parse(dayOfMonth + "/" + (month + 1) + "/" + year);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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

    private int verificaCodigoLote() {
        try {
            TaskGet task1 = new TaskGet(this, "Lote");
            Result result1 = task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Lotes/Count"}).get();
            System.out.println("VERIFICA Result1 ------------------->" + result1);

            Type typeUser1 = new TypeToken<Integer>() {
            }.getType();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            int tamanho = gson.fromJson(result1.getContent(), typeUser1);
            System.out.println("tamanho da Lista--------------> " + tamanho);
            return tamanho + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 1;

    }

    private void integraDados() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        List<Lote> loteList = new ArrayList<>();
        if (Lote.listAll(Lote.class).size() > 0) {
            for (Lote e : Lote.listAll(Lote.class)) {
                if (!e.isIntegrado()) {
                    loteList.add(e);
                }
            }
            LoteTask taskLote = new LoteTask(LoteActivity.this, "POST");
            taskLote.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(loteList)});
            Lote l = Lote.last(Lote.class);
            System.out.println(l);
            l.setIntegrado(true);
            l.update();
            System.out.println(l);


        }
    }
}
