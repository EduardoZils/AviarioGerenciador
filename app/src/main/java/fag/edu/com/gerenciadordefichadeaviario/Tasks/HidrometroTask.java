package fag.edu.com.gerenciadordefichadeaviario.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import fag.edu.com.gerenciadordefichadeaviario.R;
import fag.edu.com.gerenciadordefichadeaviario.Util.Conexao;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Racao;

public class HidrometroTask extends AsyncTask<String, Integer, List<Hidrometro>> {

    private ProgressDialog progress;
    private Context context;
    private String method;

    public HidrometroTask(Context context, String method) {
        this.context = context;
        this.method = method;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Aguarde");
        progress.setMessage("Estamos cadastrando seus registros de Água em nosso sistema...");
        progress.setIcon(R.drawable.ic_agua);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected List<Hidrometro> doInBackground(String... jsonData) {
        List<Hidrometro> racaoResult = new ArrayList<>();
        HttpURLConnection connection = null;
        String data = jsonData[0];
        try {

            StringBuffer response = new StringBuffer();
            connection = Conexao.realizaConexao("Hidrometros", method);

            //Escrevo na Conexão que montamos
            OutputStream os = new BufferedOutputStream(connection.getOutputStream());
            //Escrevo na requisição do nosso JSON
            os.write(jsonData[0].getBytes());
            os.flush();

            System.out.println("=================Retorno da Req ==>   " + connection.getResponseCode());

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

        } finally {
            connection.disconnect();
        }
        return racaoResult;
    }


    @Override
    protected void onPostExecute(List<Hidrometro> s) {
        super.onPostExecute(s);
        progress.cancel();
    }
}

