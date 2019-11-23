package fag.edu.com.gerenciadordefichadeaviario;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarContext;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.Tasks.AviarioTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.EnderecoTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.LoteTask;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.TaskGet;
import fag.edu.com.gerenciadordefichadeaviario.Tasks.UsuarioTask;
import fag.edu.com.gerenciadordefichadeaviario.models.Alimentacao;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Endereco;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Mortalidade;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesos;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;
import fag.edu.com.gerenciadordefichadeaviario.models.Usuario;
import fag.edu.com.gerenciadordefichadeaviario.models.Vacina;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    public static Usuario usuarioLogado;
    public static Aviario aviario_selecionado;
    public static TextView tv_bem_vindo, tv_aviario_principal;
    private SwipeRefreshLayout mSwipeToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        tv_bem_vindo = findViewById(R.id.tv_bem_vindo);
        tv_aviario_principal = findViewById(R.id.tv_aviario_principal);
        mSwipeToRefresh = findViewById(R.id.swipe_refresh_container1);
        // Seta o Listener para atualizar o conteudo quando o gesto for feito
        mSwipeToRefresh.setOnRefreshListener(this);

        // O esquema de cores
        mSwipeToRefresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );


        tv_bem_vindo.setText("Bem vindo " + usuarioLogado.getDsNome() + ", estavamos lhe esperando");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fazenda) {
            Intent intent = new Intent(MainActivity.this, SelecaoAviario.class);
            startActivity(intent);
        } else if (id == R.id.nav_morte) {
            Intent intent = new Intent(MainActivity.this, MortalidadeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_agua) {
            Intent intent = new Intent(MainActivity.this, HidrometroActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_balanca) {
            Intent intent = new Intent(MainActivity.this, PesagemActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_seringa) {
            Intent intent = new Intent(MainActivity.this, VacinaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_comida) {
            Intent intent = new Intent(MainActivity.this, AlimentacaoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_relatorio) {
            Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_export) {
            exportaDados();
        } else if (id == R.id.nav_import) {
            importaDados();
        } else if (id == R.id.nav_logoff) {
            usuarioLogado = null;
            aviario_selecionado = null;
            tv_aviario_principal = null;
            Usuario.deleteAll(Usuario.class);
            Endereco.deleteAll(Endereco.class);
            Aviario.deleteAll(Aviario.class);
            Lote.deleteAll(Lote.class);
            Racao.deleteAll(Racao.class);
            Vacina.deleteAll(Vacina.class);
            Alimentacao.deleteAll(Alimentacao.class);
            Mortalidade.deleteAll(Mortalidade.class);
            Pesagem.deleteAll(Pesagem.class);
            Pesos.deleteAll(Pesos.class);
            Hidrometro.deleteAll(Hidrometro.class);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void importaDados() {
        Endereco.deleteAll(Endereco.class);
        Aviario.deleteAll(Aviario.class);
        Lote.deleteAll(Lote.class);
        Racao.deleteAll(Racao.class);
        Vacina.deleteAll(Vacina.class);
        Alimentacao.deleteAll(Alimentacao.class);
        Mortalidade.deleteAll(Mortalidade.class);
        Pesagem.deleteAll(Pesagem.class);
        Pesos.deleteAll(Pesos.class);
        Hidrometro.deleteAll(Hidrometro.class);


        try {
            List<Endereco> enderecoList = new ArrayList<>();
            TaskGet task = new TaskGet(this, "Endereços");
            Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Enderecoes/"}).get();
            Type typeUser = new TypeToken<List<Endereco>>() {
            }.getType();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            enderecoList = gson.fromJson(result.getContent(), typeUser);
            for (Endereco e : enderecoList) {
                e.setIntegrado(true);
                e.save();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void exportaDados() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


        List<Lote> loteList = new ArrayList<>();
        if (Lote.listAll(Lote.class).size() > 0) {
            for (Lote l : Lote.listAll(Lote.class)) {
                if (!l.isIntegrado()) {
                    loteList.add(l);
                }
            }
            LoteTask loteTask = new LoteTask(MainActivity.this);
            loteTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(loteList)});
        }


    }

    @Override
    public void onRefresh() {
        // Executar a atualizacao
        if (aviario_selecionado != null) {
            tv_aviario_principal.setText(String.valueOf(aviario_selecionado.getNrIdentificador()));
        }
        mSwipeToRefresh.setRefreshing(false);
    }


}
