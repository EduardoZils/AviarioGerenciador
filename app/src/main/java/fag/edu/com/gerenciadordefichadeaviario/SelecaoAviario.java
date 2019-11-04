package fag.edu.com.gerenciadordefichadeaviario;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;

public class SelecaoAviario extends AppCompatActivity {

    private Button btAdicionarAviario, btEditarAviario, btLoteAviario;
    private ListView lvAviario;
    private ArrayAdapter adapterAviario;
    private Aviario aviario_selecionado;

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

    private void carregaEventos() {
        btAdicionarAviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecaoAviario.this, AddAviario.class);
                intent.putExtra("EDICAO", -1);
                startActivity(intent);
                atualizaLista();
            }
        });
        btEditarAviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelecaoAviario.this, AddAviario.class);

                intent.putExtra("EDICAO", aviario_selecionado.getCdAviario());
                startActivity(intent);
                atualizaLista();
            }
        });

        lvAviario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aviario_selecionado = Aviario.listAll(Aviario.class).get(position);
            }
        });

        btLoteAviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void carregaComponentes() {
        btAdicionarAviario = findViewById(R.id.btAdicionarAviario);
        btEditarAviario = findViewById(R.id.btEditarAviario);
        btLoteAviario = findViewById(R.id.btLoteAviario);
        lvAviario = findViewById(R.id.lvAviario);
    }

    private void atualizaLista() {
        if (Aviario.listAll(Aviario.class) != null && Aviario.listAll(Aviario.class).size() != 0) {
            List<Aviario> aviarioList = Aviario.listAll(Aviario.class);
            lvAviario.setAdapter(adapterAviario = new ArrayAdapter<>(SelecaoAviario.this,
                    R.layout.support_simple_spinner_dropdown_item,
                    aviarioList));
        } else if (Aviario.listAll(Aviario.class) == null) {
            try {
                TaskGet task1 = new TaskGet(this, "Aviarios");
                Result result = task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Aviarios/Count"}).get();
                System.out.println("VERIFICA Result1 ------------------->" + result);


                Type typeAviario = new TypeToken<List<Aviario>>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                List<Aviario> aviarioList = gson.fromJson(result.getContent(), typeAviario);

                lvAviario.setAdapter(adapterAviario = new ArrayAdapter<>(SelecaoAviario.this,
                        R.layout.support_simple_spinner_dropdown_item,
                        aviarioList));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } else {
            Mensagem.ExibirMensagem(SelecaoAviario.this, "Não existem aviários cadastrados! Adicione um novo aviário no canto superior direito!", TipoMensagem.ALERTA);
        }
    }


}
