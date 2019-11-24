package fag.edu.com.gerenciadordefichadeaviario.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import fag.edu.com.gerenciadordefichadeaviario.AddAviario;
import fag.edu.com.gerenciadordefichadeaviario.R;
import fag.edu.com.gerenciadordefichadeaviario.Util.Conexao;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Endereco;

public class EnderecoTask extends AsyncTask<String, Integer, List<Endereco>> {

    private ProgressDialog progress;
    private Context context;
    private String method;

    public EnderecoTask(Context context, String method) {
        this.context = context;
        this.method = method;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Aguarde");
        progress.setMessage("Estamos cadastrando seu endereço em nosso sistema...");
        progress.setIcon(R.drawable.ic_fazenda);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected List<Endereco> doInBackground(String... jsonData) {
        List<Endereco> enderecoResult = null;
        HttpURLConnection connection = null;
        String data = jsonData[0];
        try {
            System.out.println("==================================================================== ENDERECO TASK ====================================================================");
            StringBuffer response = new StringBuffer();
            if (method.equals("PUT")) {
                connection = Conexao.realizaConexao("Enderecoes/" + jsonData[1], method);
            } else {
                connection = Conexao.realizaConexao("Enderecoes", method);
            }

            //Escrevo na Conexão que montamos
            OutputStream os = new BufferedOutputStream(connection.getOutputStream());
            //Escrevo na requisição do nosso JSON
            os.write(jsonData[0].getBytes());
            os.flush();

            System.out.println("=================Retorno da Req ==>   " + connection.getResponseCode());

            if (response.length() > 0) {
                response.toString();
            }

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNext()) {
                    response.append(scanner.next());
                }
            } else {
                System.out.println("========================== Erro ao realizar Conexão ==========================");
                System.out.println(connection.getResponseMessage());
            }

            System.out.println("========================== RESULTADO ==========================");
            System.out.println(data.toString());
            System.out.println(response.toString());

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.disconnect();
        }
        return enderecoResult;
    }


    @Override
    protected void onPostExecute(List<Endereco> s) {
        super.onPostExecute(s);
        progress.cancel();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        if (method.equals("POST")) {
            List<Aviario> aviariosList = new ArrayList<>();
            for (Aviario a : Aviario.listAll(Aviario.class)) {
                if (!a.isIntegrado()) {
                    aviariosList.add(a);
                }
            }
            AviarioTask taskAviario = new AviarioTask(context, "POST");
            taskAviario.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(aviariosList)});
        } else if (method.equals("PUT")) {
            AviarioTask taskAviario = new AviarioTask(context, "PUT");
            taskAviario.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(AddAviario.aviarioEdicao), String.valueOf(AddAviario.aviarioEdicao.getCdAviario())});
        }

    }
}

