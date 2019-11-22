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
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;
import fag.edu.com.gerenciadordefichadeaviario.models.Result;

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
                        response.append(scanner.next());
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
                        l.setIntegrado(true);
                        l.save();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }//Remover essa linha ao descomentar abaixo--------------------------------------------

//        } else if(title.equalsIgnoreCase("Lotes")){
//            //GET HIDROMETRO
//            try {
//                List<Lote> loteList = new ArrayList<>();
//                TaskGet task = new TaskGet(context, "Hidrometros");
//                for (Aviario aviario : Aviario.listAll(Aviario.class)) {
//                    Result result = task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Hidrometros/byAviario" + aviario.getCdAviario()}).get();
//                    Type typeUser = new TypeToken<List<Lote>>() {
//                    }.getType();
//                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
//                    loteList = gson.fromJson(result.getContent(), typeUser);
//                    for (Lote l : loteList) {
//                        l.setIntegrado(true);
//                        l.save();
//                    }
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//
//        }
    }

}

