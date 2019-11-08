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
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import fag.edu.com.gerenciadordefichadeaviario.R;
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
        if (jsonData.length > 0) {
            try {

                StringBuffer response = new StringBuffer();
                URL url = new URL("http://192.168.0.95:80/api/" + jsonData[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(30000);
                connection.connect();


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


            } catch (
                    MalformedURLException e) {
                e.printStackTrace();
            } catch (
                    IOException e) {
                e.printStackTrace();

            }
        }
        return new Result("ERRO", true);
    }

    @Override
    protected void onPostExecute(Result s) {
        super.onPostExecute(s);
        progress.cancel();
    }

}
