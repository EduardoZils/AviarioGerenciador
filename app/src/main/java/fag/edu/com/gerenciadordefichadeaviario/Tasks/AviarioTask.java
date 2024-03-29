package fag.edu.com.gerenciadordefichadeaviario.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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

import fag.edu.com.gerenciadordefichadeaviario.R;
import fag.edu.com.gerenciadordefichadeaviario.Util.Conexao;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Endereco;

public class AviarioTask extends AsyncTask<String, Integer, List<Aviario>> {

    private ProgressDialog progress;
    private Context context;
    private String method;

    public AviarioTask(Context context, String method) {
        this.context = context;
        this.method = method;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Aguarde");
        progress.setMessage("Estamos cadastrando o aviário em nosso sistema...");
        progress.setIcon(R.drawable.ic_user_add);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected List<Aviario> doInBackground(String... jsonData) {
        List<Aviario> aviarioResult = new ArrayList<>();
        HttpURLConnection connection = null;
        String data = jsonData[0];
        try {
            System.out.println("==================================================================== AVIARIOTASK ====================================================================");
            StringBuffer response = new StringBuffer();
            if (method.equals("PUT")) {
                connection = Conexao.realizaConexao("Aviarios/" + jsonData[1],  method);
            } else {
                connection = Conexao.realizaConexao("Aviarios", method);
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
                    response.append(scanner.next() + " ");
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

        } finally {
            connection.disconnect();
        }
        return aviarioResult;
    }


    @Override
    protected void onPostExecute(List<Aviario> s) {
        super.onPostExecute(s);
        progress.cancel();

        for (Aviario a : Aviario.listAll(Aviario.class)) {
            if (!a.isIntegrado()) {
                a.setIntegrado(true);
                a.update();
            }
        }
    }
}

