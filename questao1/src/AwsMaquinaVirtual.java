public final class AwsMaquinaVirtual implements MaquinaVirtual {
    public static final String PREFIXO = "i-";
    public static final double CUSTO_POR_HORA = 0.10;

    private final String identificador;

    AwsMaquinaVirtual(String identificador) {
        this.identificador = identificador;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getProvedor() {
        return AwsFabrica.NOME;
    }

    @Override
    public double getCustoPorHora() {
        return CUSTO_POR_HORA;
    }
}
