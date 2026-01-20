import java.util.Scanner;

public class Interfaz {
    public void mostrarMonedas() {
        int contador = 1;
        System.out.println();
        for (Moneda moneda: Moneda.values()) {
            System.out.println(contador + ") " + moneda.getNombre());
            contador++;
        }
    }

    public Moneda seleccionarMoneda(String tipoMoneda) {

        Scanner input = new Scanner(System.in);
        boolean operacionInvalida;
        int indiceMoneda = 0;

        do {
            operacionInvalida = false;

            System.out.println("\nPor favor, ingrese la moneda de " + tipoMoneda + " deseada: ");

            try {
                indiceMoneda = input.nextInt();

            } catch (Exception e) {
                operacionInvalida = true;
                System.out.println("\n[ERROR] El valor ingresado debe ser un numero!\n");
                input.nextLine();
                continue;
            }

            if (indiceMoneda > Moneda.values().length || indiceMoneda < 1) {
                operacionInvalida = true;
                System.out.println("\n[ERROR]El valor ingresado debe ser un valor disponible!\n");
            }

        } while (operacionInvalida);

        return Moneda.values()[indiceMoneda];
    }

    public float solicitarMonto() {

        boolean operacionInvalida;
        float monto = 0.0F;
        Scanner input = new Scanner(System.in);

        do {
            operacionInvalida = false;

            System.out.println("\nPor favor, ingrese el monto que desea convertir: ");

            try {
                monto = input.nextFloat();
                input.nextLine();
            } catch (Exception e) {
                operacionInvalida = true;
                System.out.println("\n[ERROR] El monto ingresado debe ser un numero!");
                continue;
            }

            if (monto <= 0) {
                operacionInvalida = true;
                System.out.println("[ERROR] El monto ingresado debe ser un valor valido!");
            }
        } while (operacionInvalida);

        return monto;
    }

    public boolean solicitarRespuestaParaContinuar () {
        boolean operacionInvalida;
        Scanner input = new Scanner(System.in);
        String respuesta;

        do {
            operacionInvalida = false;

            System.out.println("\nDesea seguir operando? [Si/No] ");

            respuesta = input.nextLine();

            if (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
                operacionInvalida = true;
                System.out.println("\n[ERROR] La respuesta ingresada solo puede ser si o no!");
            }
        } while (operacionInvalida);

        return respuesta.equalsIgnoreCase("si");
    }

}
