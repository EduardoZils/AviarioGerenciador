package fag.edu.com.gerenciadordefichadeaviario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesos;

public class PesosActivity extends AppCompatActivity {

    TextView tv_aviario_principal_pesos;
    EditText et_qt_aves_peso, et_peso_total;
    Button bt_salvar_pesos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        carregaComponentes();
        carregaEventos();
    }


    private void carregaComponentes() {
        et_qt_aves_peso = findViewById(R.id.et_qt_aves_peso);
        et_peso_total = findViewById(R.id.et_peso_total);
        bt_salvar_pesos = findViewById(R.id.bt_salvar_pesos);
        tv_aviario_principal_pesos = findViewById(R.id.tv_aviario_principal_pesos);
        if (MainActivity.aviario_selecionado != null) {
            tv_aviario_principal_pesos.setText(String.valueOf(MainActivity.aviario_selecionado.getNrIdentificador()));
        }
    }

    private void carregaEventos() {


        bt_salvar_pesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (et_peso_total.getText().length() > 0 && et_qt_aves_peso.getText().length() > 0) {
                        Pesos pesos = new Pesos();
                        pesos.setCdPeso(Pesos.listAll(Pesos.class).size() + 1);
                        pesos.setCdLote(PesagemActivity.lote.getCdLote());
                        pesos.setBlAtivo(true);
                        pesos.setDtCadastro(new Date());
                        pesos.setDtAtualizacao(new Date());
                        pesos.setPesagem(PesagemActivity.pesagem);
                        pesos.setQtAvesUtilizadas(Integer.valueOf(et_qt_aves_peso.getText().toString()));
                        pesos.setVlPesagem(Double.parseDouble(et_peso_total.getText().toString()));
                        pesos.setIntegrado(false);
                        PesagemActivity.pesosList.add(pesos);
                    } else {
                        Mensagem.ExibirMensagem(PesosActivity.this, "Verifique se todos os campos estão devidamente preenchidos", TipoMensagem.ERRO);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
