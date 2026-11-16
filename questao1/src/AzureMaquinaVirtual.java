public final class AzureMaquinaVirtual implements MaquinaVirtual {
    public static final String PREFIXO = "vm-";
    public static final double CUSTO_POR_HORA = 0.12;

    private final String identificador;

    AzureMaquinaVirtual(String identificador) {
        this.identificador = identificador;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getProvedor() {
        return AzureFabrica.NOME;
    }

    @Override
    public double getCustoPorHora() {
        return CUSTO_POR_HORA;
    }
}
