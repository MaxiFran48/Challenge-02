import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {

    public float obtenerMontoConvertido(Moneda monedaOrigen, Moneda monedaDestino, float monto) {

        String URL_API = "https://v6.exchangerate-api.com/v6/";
        String CLAVE_API = "444ae7ca1191553a6c20f7f7";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud_api = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + CLAVE_API + "/pair/" + monedaOrigen.getCodigo() + "/" + monedaDestino.getCodigo() + "/" + monto))
                .GET()
                .header("Accept", "application/json")
                .build();

        RespuestaCambioDTO respuestaCambio;

        try {
            HttpResponse<String> respuestaAPI = cliente.send(solicitud_api, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            respuestaCambio = gson.fromJson(respuestaAPI.body(), RespuestaCambioDTO.class);

            return respuestaCambio.getConversionResult();

        } catch (Exception e) {
            System.out.println("Error en la conexión");
        }

        throw new RuntimeException("Error en la conexión");
    }

}
