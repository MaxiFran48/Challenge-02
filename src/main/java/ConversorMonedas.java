import com.google.gson.Gson;
import tools.jackson.core.tree.ObjectTreeNode;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.BiConsumer;

public class ConversorMonedas {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String url_api = "https://v6.exchangerate-api.com/v6/444ae7ca1191553a6c20f7f7/pair";
        Map<String, String> DICCIONARIO_MONEDAS = new HashMap<>();
        
        DICCIONARIO_MONEDAS.put("EUR", "Euro");
        DICCIONARIO_MONEDAS.put("USD", "Dolares");
        DICCIONARIO_MONEDAS.put("ARS", "Pesos Argentinos");

        Object[] LISTA_MONEDAS = DICCIONARIO_MONEDAS.values().toArray();
        Object[] LISTA_CODIGOS = DICCIONARIO_MONEDAS.keySet().toArray();

        String moneda_origen;
        String moneda_destino;

        System.out.println("Bienvenido a su aplicacion de conversion de monedas!");

        boolean operacion_invalida;

        float monto_moneda_origen = 0;
        float monto_moneda_destino = 0;

        int indice_moneda_origen = 0;
        int indice_moneda_destino = 0;
        String respuesta;

        do {
            int contador = 1;
            System.out.println();
            for (Object moneda: LISTA_MONEDAS) {
                System.out.println(contador + ") " + (String)moneda);
                contador++;
            }

            do {
                operacion_invalida = false;

                System.out.println("\nPor favor, ingrese la moneda de origen deseada: ");

                try {
                    indice_moneda_origen = input.nextInt();

                } catch (Exception e) {
                    operacion_invalida = true;
                    System.out.println("\n[ERROR] El valor ingresado debe ser un numero!\n");
                    input.nextLine();
                    continue;
                }

                if (indice_moneda_origen > LISTA_MONEDAS.length || indice_moneda_origen < 1) {
                    operacion_invalida = true;
                    System.out.println("\n[ERROR]El valor ingresado debe ser un valor disponible!\n");
                }


            } while (operacion_invalida);

            do {
                operacion_invalida = false;

                System.out.println("\nPor favor, ingrese la moneda de destino deseada: ");

                try {
                    indice_moneda_destino = input.nextInt();
                } catch (Exception e) {
                    operacion_invalida = true;
                    System.out.println("\n[ERROR] El valor ingresado debe ser un numero!");
                    input.nextLine();
                    continue;
                }

                if (indice_moneda_destino > LISTA_MONEDAS.length || indice_moneda_destino < 1) {
                    operacion_invalida = true;
                    System.out.println("\n[ERROR] El valor ingresado debe ser un valor disponible!");
                }
            } while (operacion_invalida);

            do {
                operacion_invalida = false;

                System.out.println("\nPor favor, ingrese el monto que desea convertir: ");

                try {
                    monto_moneda_origen = input.nextFloat();
                    input.nextLine();
                } catch (Exception e) {
                    operacion_invalida = true;
                    System.out.println("\n[ERROR] El monto ingresado debe ser un numero!");
                    continue;
                }

                if (monto_moneda_origen <= 0) {
                    operacion_invalida = true;
                    System.out.println("[ERROR] El monto ingresado debe ser un valor valido!");
                }
            } while (operacion_invalida);

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud_api = HttpRequest.newBuilder()
                    .uri(URI.create(url_api + "/" + (String)LISTA_CODIGOS[indice_moneda_origen - 1] + "/" + (String)LISTA_CODIGOS[indice_moneda_destino - 1] + "/" + monto_moneda_origen))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            try {
                HttpResponse<String> respuesta_api = cliente.send(solicitud_api, HttpResponse.BodyHandlers.ofString());

                Gson gson = new Gson();

                ContenidoRespuestaCambio contenido_respuesta_cambio = gson.fromJson(respuesta_api.body(), ContenidoRespuestaCambio.class);

                System.out.println("\nValor equivalente: " + contenido_respuesta_cambio.getConversionResult());

            } catch (Exception e) {
                System.out.println("Error en la conexión");
            }

            do {
                operacion_invalida = false;

                System.out.println("\nDesea seguir operando? [Si/No] ");

                respuesta = input.nextLine();

                if (!respuesta.toLowerCase().strip().equals("si") && !respuesta.toLowerCase().strip().equals("no")) {
                    operacion_invalida = true;
                    System.out.println("\n[ERROR] La respuesta ingresada solo puede ser si o no!");
                }
            } while (operacion_invalida);

        } while (respuesta.toLowerCase().strip().equals("si"));


    }
}
