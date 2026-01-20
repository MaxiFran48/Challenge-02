import lombok.Getter;

@Getter
public enum Moneda {
    EUR("EUR", "Euro"),
    USD("USD", "Dolares"),
    ARS("ARS", "Pesos Argentinos");

    private final String codigo;
    private final String nombre;

    Moneda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

}
