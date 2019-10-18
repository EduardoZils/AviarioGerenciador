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
import fag.edu.com.gerenciadordefichadeaviario.models.Usuario;

public class UsuarioTask extends AsyncTask<String, Integer, Usuario> {

    private ProgressDialog progress;
    private Context context;

    public UsuarioTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Aguarde");
        progress.setMessage("Estamos cadastrando você em nosso sistema...");
        progress.setIcon(R.drawable.ic_menu_share);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected Usuario doInBackground(String... jsonData) {
        Usuario usuarioResult = null;

        String data = jsonData[0];
        try {

            StringBuffer response = new StringBuffer();

            URL urlUsuario = new URL("http://192.168.0.95:80/api/usuarios");
            HttpURLConnection connection = (HttpURLConnection) urlUsuario.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(20000);
            connection.setDoInput(true);
            connection.setReadTimeout(30000);
            connection.connect();

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

        }
        return usuarioResult;
    }


    @Override
    protected void onPostExecute(Usuario s) {
        super.onPostExecute(s);
        progress.cancel();
    }
}

