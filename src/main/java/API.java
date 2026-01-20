import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {

    private final String API_KEY;

    public API(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public float obtenerMontoConvertido(Moneda monedaOrigen, Moneda monedaDestino, float monto) {

        String URL_API = "https://v6.exchangerate-api.com/v6/";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud_api = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + API_KEY + "/pair/" + monedaOrigen.getCodigo() + "/" + monedaDestino.getCodigo() + "/" + monto))
                .GET()
                .header("Accept", "application/json")
                .build();

        RespuestaCambioDTO respuestaCambio;

        try {
            HttpResponse<String> respuestaAPI = cliente.send(solicitud_api, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            respuestaCambio = gson.fromJson(respuestaAPI.body(), RespuestaCambioDTO.class);

            return respuestaCambio.getConversionResult();

        } catch (IOException | InterruptedException e) {
            System.out.println("Error en la conexión");
            throw new ApiRequestException("Error en la conexión con la API: " + e.getMessage());
        }

    }

}
