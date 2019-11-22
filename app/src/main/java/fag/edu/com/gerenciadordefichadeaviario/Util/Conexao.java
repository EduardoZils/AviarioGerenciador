package fag.edu.com.gerenciadordefichadeaviario.Util;

import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {
    public static String ipApi = "http://192.168.43.8:80/api/";

    public static HttpURLConnection realizaConexao(String url, String method) {
        try {
            URL urlUsuario = new URL(ipApi + url);
            HttpURLConnection connection = (HttpURLConnection) urlUsuario.openConnection();

            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(20000);
            if (!method.equalsIgnoreCase("GET")) {
                connection.setDoInput(true);
            }
            connection.setReadTimeout(30000);
            connection.connect();

            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
