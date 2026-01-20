public class ConversorMonedas {

    public Interfaz interfazUsuario;
    public API conversionAPI;

    public ConversorMonedas(Interfaz interfazUsuario, API conversionAPI) {
        this.interfazUsuario = interfazUsuario;
        this.conversionAPI = conversionAPI;
    }

    public void iniciar() {

        System.out.println("Bienvenido a su aplicacion de conversion de monedas!");

        do {
            interfazUsuario.mostrarMonedas();

            Moneda monedaOrigen = interfazUsuario.seleccionarMoneda("origen");
            Moneda monedaDestino = interfazUsuario.seleccionarMoneda("destino");

            float montoMonedaOrigen = interfazUsuario.solicitarMonto();

            try {
                float montoMonedaDestino = conversionAPI.obtenerMontoConvertido(monedaOrigen, monedaDestino, montoMonedaOrigen);

                System.out.println("El monto convertido es: " + montoMonedaDestino);

            } catch (ApiRequestException e) {
                System.out.println(e.getMessage());
            }

        } while (interfazUsuario.solicitarRespuestaParaContinuar());
    }

    public static void main(String[] args) {
        Interfaz interfazUsuario = new Interfaz();
        API conversionAPI = new API("444ae7ca1191553a6c20f7f7");
        ConversorMonedas conversorMonedas = new ConversorMonedas(interfazUsuario, conversionAPI);

        conversorMonedas.iniciar();
    }
}
