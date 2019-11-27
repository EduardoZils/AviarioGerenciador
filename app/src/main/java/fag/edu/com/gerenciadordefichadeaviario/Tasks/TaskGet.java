package fag.edu.com.gerenciadordefichadeaviario.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import fag.edu.com.gerenciadordefichadeaviario.MainActivity;
import fag.edu.com.gerenciadordefichadeaviario.R;
import fag.edu.com.gerenciadordefichadeaviario.Util.Conexao;
import fag.edu.com.gerenciadordefichadeaviario.Util.Mensagem;
import fag.edu.com.gerenciadordefichadeaviario.Util.TipoMensagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Alimentacao;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Mortalidade;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesagem;
import fag.edu.com.gerenciadordefichadeaviario.models.Pesos;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;
import fag.edu.com.gerenciadordefichadeaviario.models.Vacina;

public class TaskGet extends AsyncTask<String, Integer, Result> {

    private ProgressDialog progress;
    private Context context;
    private String title;


    public TaskGet(Context context, String title) {
        this.context = context;
        this.title = title;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Conectando ao banco de dados");
        progress.setMessage("Favor aguardar carregamento de " + title);
        progress.setIcon(R.drawable.ic_menu_share);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected Result doInBackground(String... jsonData) {
        //Object taskresult = null;
        //List<Object> taskresult = new ArrayList<>();

        HttpURLConnection connection = null;
        if (jsonData.length > 0) {
            try {

                StringBuffer response = new StringBuffer();

                connection = Conexao.realizaConexao(jsonData[0], "GET");


                System.out.println("=================Retorno da Req ==>   " + connection.getResponseCode());

                if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    Scanner scanner = new Scanner(connection.getInputStream());
                    while (scanner.hasNext()) {
                        response.append(scanner.next() + " ");
                    }
                } else {
                    System.out.println("========================== Erro ao realizar Conexão ==========================");
                    System.out.println(connection.getResponseMessage());
                    return new Result("VALOR NULO", true);
                }

                System.out.println("========================== RESULTADO ==========================");

                return new Result(response.toString(), false);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                connection.disconnect();
            }
        }
        return new Result("ERRO", true);
    }


    @Override
    protected void onPostExecute(Result s) {
        super.onPostExecute(s);
        progress.cancel();

        if (title.equalsIgnoreCase("Endereços")) {

            try {
                List<Aviario> aviarioList;
                TaskGet task = new TaskGet(context, "Aviarios");
                Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Aviarios/byUsuario/" + MainActivity.usuarioLogado.getCdUsuario()}).get();
                System.out.println(result);
                if (result != null && !result.isError()) {
                    Type typeUser = new TypeToken<List<Aviario>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    aviarioList = gson.fromJson(result.getContent(), typeUser);
                    System.out.println(aviarioList);
                    for (Aviario a : aviarioList) {
                        a.setIntegrado(true);
                        a.save();
                    }
                } else {
                    Mensagem.ExibirMensagem(context, "Falha ao buscar Aviarios!", TipoMensagem.ERRO);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (title.equalsIgnoreCase("Aviarios")) {

            try {
                List<Lote> loteList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Lotes");
                for (Aviario aviario : Aviario.listAll(Aviario.class)) {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Lotes/byAviario/" + aviario.getCdAviario()}).get();
                    System.out.println(result);
                    Type typeUser = new TypeToken<List<Lote>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    loteList = gson.fromJson(result.getContent(), typeUser);
                    System.out.println(loteList);
                    for (Lote l : loteList) {
                        l.setAviario(Aviario.findById(Aviario.class, aviario.getCdAviario()));
                        l.setIntegrado(true);
                        l.save();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (title.equalsIgnoreCase("Lotes")) {
            //GET HIDROMETRO
            try {
                List<Hidrometro> hidrometroList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Hidrometros");
                for (Lote lote : Lote.listAll(Lote.class)) {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Hidrometros/byLote/" + lote.getCdLote()}).get();
                    Type typeUser = new TypeToken<List<Hidrometro>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    hidrometroList = gson.fromJson(result.getContent(), typeUser);
                    for (Hidrometro l : hidrometroList) {
                        l.setLote(Lote.findById(Lote.class, lote.getCdAviario()));
                        l.setIntegrado(true);
                        l.save();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (title.equalsIgnoreCase("Hidrometros")) {

            //GET VACINAS
            try {
                List<Vacina> vacinaList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Vacinas");
                for (Lote lote : Lote.listAll(Lote.class)) {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Vacinas/byLote/" + lote.getCdLote()}).get();
                    Type typeUser = new TypeToken<List<Vacina>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    vacinaList = gson.fromJson(result.getContent(), typeUser);
                    for (Vacina l : vacinaList) {
                        l.setLote(Lote.findById(Lote.class, lote.getCdAviario()));
                        l.setIntegrado(true);
                        l.save();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (title.equalsIgnoreCase("Vacinas")) {
            //GET MORTALIDADE
            try {
                List<Mortalidade> mortalidadeList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Mortalidades");
                for (Lote lote : Lote.listAll(Lote.class)) {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Mortalidades/byLote/" + lote.getCdLote()}).get();
                    Type typeUser = new TypeToken<List<Mortalidade>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    mortalidadeList = gson.fromJson(result.getContent(), typeUser);
                    for (Mortalidade l : mortalidadeList) {
                        l.setLote(Lote.findById(Lote.class, lote.getCdAviario()));
                        l.setIntegrado(true);
                        l.save();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (title.equalsIgnoreCase("Mortalidades")) {

            //GET PESAGENS
            try {
                List<Pesagem> pesagemList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Pesagens");
                for (Lote lote : Lote.listAll(Lote.class)) {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Pesagems/byLote/" + lote.getCdLote()}).get();
                    Type typeUser = new TypeToken<List<Pesagem>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    pesagemList = gson.fromJson(result.getContent(), typeUser);
                    for (Pesagem l : pesagemList) {
                        l.setLote(Lote.findById(Lote.class, lote.getCdAviario()));
                        l.setIntegrado(true);
                        l.save();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (title.equalsIgnoreCase("Pesagens")) {

            //GET RAÇÕES
            try {
                List<Racao> racaoList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Rações");
                Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Racaos"}).get();
                Type typeUser = new TypeToken<List<Racao>>() {
                }.getType();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                racaoList = gson.fromJson(result.getContent(), typeUser);
                for (Racao l : racaoList) {
                    l.setIntegrado(true);
                    l.save();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

//---------------------------------------------------------------------------------------------------------
        } else if (title.equalsIgnoreCase("Rações")) {
//            //GET PESOS
//            try {
//                List<Pesos> pesosList = new ArrayList<>();
//                TaskGet task = new TaskGet(context, "Pesos");
//                for (Lote lotes : Lote.listAll(Lote.class)) {
//                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Pesos/byLote/" + lotes.getCdLote()}).get();
//                    Type typeUser = new TypeToken<List<Pesos>>() {
//                    }.getType();
//                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
//                    pesosList = gson.fromJson(result.getContent(), typeUser);
//                    for (Pesos l : pesosList) {
//                        l.setIntegrado(true);
//                        l.save();
//                    }
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } else if (title.equalsIgnoreCase("Pesos")) {
//
//        } else if (title.equalsIgnoreCase("Rações")) {
            //GET ALIMENTAÇÃO
            try {
                List<Alimentacao> alimentacaoList = new ArrayList<>();
                TaskGet task = new TaskGet(context, "Alimentos");
                for (Lote lotes : Lote.listAll(Lote.class)) {
                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Alimentacaos/byLote/" + lotes.getCdLote()}).get();
                    Type typeUser = new TypeToken<List<Alimentacao>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    alimentacaoList = gson.fromJson(result.getContent(), typeUser);
                    Lote lote_controle = lotes;
                    for (Alimentacao l : alimentacaoList) {
                        l.setLote(lote_controle);
                        l.setIntegrado(true);
                        for (Racao r : Racao.listAll(Racao.class)) {
                            if (r.getCdRacao() == l.getCdRacao()) {
                                l.setRacao(r);
                                break;
                            }
                        }
                        l.save();
                    }
                }



//                boolean excluir = false;
//                for (Racao r : Racao.listAll(Racao.class)) {
//                    excluir = true;
//                    for (Alimentacao l : alimentacaoList) {
//                        if (r.getCdRacao() == l.getCdRacao()) {
//                            l.setRacao(r);
//                            l.update();
//                            excluir = false;
//                            break;
//                        }
//                    }
////                    if (excluir) {
////                        r.delete();
////                    }
//                }


//                    for (Alimentacao a : Alimentacao.listAll(Alimentacao.class)) {
//                        for (Racao r : Racao.listAll(Racao.class)) {
//                            if (r.getCdRacao() == a.getCdRacao()) {
//
//                            }
//                        }
//                    }

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }

}

