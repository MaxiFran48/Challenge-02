public class ConversorMonedas {

    public static void main(String[] args) {

        Interfaz interfazUsuario = new Interfaz();
        API conversionAPI = new API();

        System.out.println("Bienvenido a su aplicacion de conversion de monedas!");
        
        do {
            interfazUsuario.mostrarMonedas();

            Moneda monedaOrigen = interfazUsuario.seleccionarMoneda("origen");
            Moneda monedaDestino = interfazUsuario.seleccionarMoneda("destino");

            float montoMonedaOrigen = interfazUsuario.solicitarMonto();

            try {
                float montoMonedaDestino = conversionAPI.obtenerMontoConvertido(monedaOrigen, monedaDestino, montoMonedaOrigen);

                System.out.println("El monto convertido es: " + montoMonedaDestino);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (interfazUsuario.solicitarRespuestaParaContinuar());

    }
}
