package fag.edu.com.gerenciadordefichadeaviario;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.AviarioTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.EnderecoTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.UsuarioTask;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Endereco;
import fag.edu.com.gerenciadordefichadeaviario.models.Estado;
import fag.edu.com.gerenciadordefichadeaviario.models.Municipio;
import fag.edu.com.gerenciadordefichadeaviario.models.Pais;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;
import fag.edu.com.gerenciadordefichadeaviario.models.Usuario;

public class AddAviario extends AppCompatActivity {

    private EditText et_numero_aviario, et_qt_aves, et_cep, et_referencia, et_logradouro, et_estrada;
    private Button bt_salvar_aviario;
    private TextView tv_codigo_aviario;
    private Spinner spPais, spEstado, spMunicipio;
    private Boolean isEdicao = false;
    private int tipoTela = 0;
    private int codigoAviario;
    private List<Pais> paisList;
    private List<Estado> estadoList;
    private List<Municipio> municipioList;

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
        carregaSpinners();
        carregaEventos();

    }

    private void carregaSpinners() {
        ArrayAdapter adapter = new ArrayAdapter<Pais>(AddAviario.this, R.layout.support_simple_spinner_dropdown_item, buscaPais());
        spPais.setAdapter(adapter);
    }


    private void carregaEventos() {

        bt_salvar_aviario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean existe = false;
                    List<Pais> paises = Pais.listAll(Pais.class);
                    if (paises.size() == 0) {
                        Pais.save(spPais.getSelectedItem());
                    }
                    List<Estado> estados = Estado.listAll(Estado.class);
                    if (estados.size() == 0) {
                        Estado.save(spEstado.getSelectedItem());
                    }
                    List<Municipio> municipios = Municipio.listAll(Municipio.class);
                    if (municipios.size() == 0) {
                        Municipio.save(spMunicipio.getSelectedItem());
                    }
                    //Checa Pais
                    for (Pais p : paises) {
                        if (p == spPais.getSelectedItem()) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        Pais.save(spPais.getSelectedItem());
                    }
                    existe = false;
                    //Checa Estado
                    for (Estado e : estados) {
                        if (e == spEstado.getSelectedItem()) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        Estado.save(spEstado.getSelectedItem());
                    }
                    existe = false;
                    //Checa Municipio
                    for (Municipio m : municipios) {
                        if (m == spMunicipio.getSelectedItem()) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        Municipio.save(spMunicipio.getSelectedItem());
                    }

                    Endereco endereco = new Endereco();
                    endereco.setCdEndereco(codigoAviario);
                    endereco.setDsAdjetivo(et_referencia.getText().toString());
                    endereco.setDsCep(et_cep.getText().toString());
                    endereco.setMunicipio((Municipio) spMunicipio.getSelectedItem());
                    endereco.setCdMunicipio(((Municipio) spMunicipio.getSelectedItem()).getCdMunicipio());
                    endereco.setDsEstrada(et_estrada.getText().toString());
                    endereco.setDsLogradouro(et_logradouro.getText().toString());
                    endereco.setDtCadastro(new Date());
                    endereco.setDtAtualizacao(new Date());
                    endereco.setIntegrado(false);

                    endereco.save();


                    Aviario aviario = null;
                    if (isEdicao) {
                        aviario = MainActivity.aviario_selecionado;
                    } else {
                        aviario = new Aviario();
                    }

                    aviario.setCdAviario(codigoAviario);
                    aviario.setUsuario(MainActivity.usuarioLogado);
                    aviario.setNrIdentificador(Integer.valueOf(et_numero_aviario.getText().toString()));
                    aviario.setNrCapAves(Integer.valueOf(et_qt_aves.getText().toString()));
                    aviario.setDtCadastro(new Date());
                    aviario.setDtAtualizacao(new Date());
                    aviario.setEndereco(endereco);
                    aviario.setBlAtivo(true);
                    aviario.setCdEndereco(endereco.getCdEndereco());
                    aviario.setCdUsuario(MainActivity.usuarioLogado.getCdUsuario());
                    aviario.setIntegrado(false);
                    if (isEdicao) {
                        aviario.update();
                    } else {
                        aviario.save();
                    }
                    integraDados();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finish();

            }
        });

        spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter adapter = new ArrayAdapter<Estado>(AddAviario.this, R.layout.support_simple_spinner_dropdown_item, buscaEstado());
                spEstado.setAdapter(adapter);

                adapter = new ArrayAdapter<Municipio>(AddAviario.this, R.layout.support_simple_spinner_dropdown_item, buscaMunicipio());
                spMunicipio.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter adapter = new ArrayAdapter<Municipio>(AddAviario.this, R.layout.support_simple_spinner_dropdown_item, buscaMunicipio());
                spMunicipio.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void carregaComponentes() {
        et_cep = findViewById(R.id.et_cep);
        et_numero_aviario = findViewById(R.id.et_numero_aviario);
        et_qt_aves = findViewById(R.id.et_qt_aves);
        et_referencia = findViewById(R.id.et_referencia);
        et_logradouro = findViewById(R.id.et_logradouro);
        et_estrada = findViewById(R.id.et_estrada);
        bt_salvar_aviario = findViewById(R.id.bt_salvar_aviario);
        spPais = findViewById(R.id.spPais);
        spEstado = findViewById(R.id.spEstado);
        spMunicipio = findViewById(R.id.spMunicipio);
        tv_codigo_aviario = findViewById(R.id.tv_codigo_aviario);

        if (tipoTela != -1) {
            tv_codigo_aviario.setText("Edição do Aviário # " + MainActivity.aviario_selecionado.getCdAviario());
            codigoAviario = MainActivity.aviario_selecionado.getCdAviario();
            isEdicao = true;
        } else {
            tv_codigo_aviario.setText("Criação de Aviário # " + verificaCodigoAviario());
        }
    }

    private void integraDados() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


        if (isEdicao) {

            List<Aviario> aviariosList = new ArrayList<>();
            for (Aviario a : Aviario.listAll(Aviario.class)) {
                if (!a.isIntegrado()) {
                    aviariosList.add(a);
                }
            }
            AviarioTask taskAviario = new AviarioTask(AddAviario.this, "PUT");
            taskAviario.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(aviariosList)});

        } else {
            List<Endereco> enderecoList = new ArrayList<>();
            if (Endereco.listAll(Endereco.class).size() > 0) {
                for (Endereco e : Endereco.listAll(Endereco.class)) {
                    if (!e.isIntegrado()) {
                        enderecoList.add(e);
                    }
                }
                EnderecoTask taskEndereco = new EnderecoTask(AddAviario.this);
                taskEndereco.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(enderecoList)});

                Endereco endereco = Endereco.last(Endereco.class);
                System.out.println(endereco);
                endereco.setIntegrado(true);
                endereco.update();
                System.out.println(endereco);

                Aviario aviario = Aviario.last(Aviario.class);
                aviario.setIntegrado(true);
                aviario.update();

            }
        }

    }


    private int verificaCodigoAviario() {
        try {
            TaskGet task1 = new TaskGet(this, "Aviarios");
            Result result1 = task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Aviarios/Count"}).get();
            System.out.println("VERIFICA Result1 ------------------->" + result1);

            Type typeUser1 = new TypeToken<Integer>() {
            }.getType();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            int tamanho = gson.fromJson(result1.getContent(), typeUser1);
            System.out.println("tamanho da Lista--------------> " + tamanho);
            codigoAviario = tamanho + 1;
            return tamanho + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 1;

    }

    private List<Pais> buscaPais() {

        try {
            TaskGet task = new TaskGet(this, "Pais");
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Pais/"}).get();
            System.out.println("VERIFICA Result ------------------->" + result);

            if (result != null && !result.isError()) {
                Type typeUser = new TypeToken<List<Pais>>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                paisList = gson.fromJson(result.getContent(), typeUser);
                for (Pais p : paisList) {
                    p.save();
                }
                return paisList;
            } else {
                Mensagem.ExibirMensagem(AddAviario.this, "Falha ao buscar Paises!", TipoMensagem.ERRO);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    private List<Estado> buscaEstado() {

        Pais pais = (Pais) spPais.getSelectedItem();


        try {
            TaskGet task = new TaskGet(AddAviario.this, "Estado");
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Estadoes/byPais/" + pais.getCdPais()}).get();
            System.out.println("VERIFICA Result ------------------->" + result);

            if (result != null && !result.isError()) {
                Type typeUser = new TypeToken<List<Estado>>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                estadoList = gson.fromJson(result.getContent(), typeUser);
                for (Estado e : estadoList) {
                    e.save();
                }
                return estadoList;
            } else {
                Mensagem.ExibirMensagem(AddAviario.this, "Falha ao buscar Estados!", TipoMensagem.ERRO);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }


    private List<Municipio> buscaMunicipio() {

        Estado estado = (Estado) spEstado.getSelectedItem();

        try {
            TaskGet task = new TaskGet(AddAviario.this, "Estado");
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Municipios/byEstado/" + estado.getCdEstado()}).get();
            System.out.println("VERIFICA Result ------------------->" + result);

            if (result != null && !result.isError()) {
                Type typeUser = new TypeToken<List<Municipio>>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                municipioList = gson.fromJson(result.getContent(), typeUser);
                for (Municipio m : municipioList) {
                    m.save();
                }
                return municipioList;
            } else {
                Mensagem.ExibirMensagem(AddAviario.this, "Falha ao buscar Municipios!", TipoMensagem.ERRO);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

}

