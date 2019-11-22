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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import fag.edu.com.gerenciadordefichadeaviario.R;
import fag.edu.com.gerenciadordefichadeaviario.Util.Conexao;
import fag.edu.com.gerenciadordefichadeaviario.models.Aviario;
import fag.edu.com.gerenciadordefichadeaviario.models.Endereco;
import fag.edu.com.gerenciadordefichadeaviario.models.Hidrometro;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;

public class LoteTask extends AsyncTask<String, Integer, List<Lote>> {

    private ProgressDialog progress;
    private Context context;

    public LoteTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(context);
        progress.setTitle("Aguarde");
        progress.setMessage("Estamos registrando seu lote em nosso sistema...");
        progress.setIcon(R.drawable.ic_fazenda);
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    protected List<Lote> doInBackground(String... jsonData) {
        List<Lote> loteResult = null;
        HttpURLConnection connection = null;
        String data = jsonData[0];
        try {

            StringBuffer response = new StringBuffer();

            connection = Conexao.realizaConexao("Lotes", "POST");

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
        return loteResult;
    }


    @Override
    protected void onPostExecute(List<Lote> s) {
        super.onPostExecute(s);
        progress.cancel();

        List<Hidrometro> hidrometroList = new ArrayList<>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        for (Hidrometro h : Hidrometro.listAll(Hidrometro.class)) {
            if (!h.isIntegrado()) {
                hidrometroList.add(h);
            }
        }
        //AviarioTask taskAviario = new AviarioTask(context);
        //taskAviario.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{gson.toJson(hidrometroList)});

    }
}

