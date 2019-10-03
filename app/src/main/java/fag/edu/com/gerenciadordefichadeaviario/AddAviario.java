package fag.edu.com.gerenciadordefichadeaviario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddAviario extends AppCompatActivity {

    private EditText et_numero_aviario, et_qt_aves, et_cep, et_referencia;
    private Button bt_salvar_aviario;
    private Spinner spPais, spEstado, spMunicipio;
    private Boolean isEdicao = false;
    private int tipoTela = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aviario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            tipoTela = (int) getIntent().getExtras().get("EDICAO");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        carregaComponentes();
        carregaEventos();

    }

    private void carregaEventos() {

        bt_salvar_aviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void carregaComponentes() {
        et_cep = findViewById(R.id.et_cep);
        et_numero_aviario = findViewById(R.id.et_numero_aviario);
        et_qt_aves = findViewById(R.id.et_qt_aves);
        et_referencia = findViewById(R.id.et_referencia);
        bt_salvar_aviario = findViewById(R.id.bt_salvar_aviario);
        spPais = findViewById(R.id.spPais);
        spEstado = findViewById(R.id.spEstado);
        spMunicipio = findViewById(R.id.spMunicipio);
    }

}
