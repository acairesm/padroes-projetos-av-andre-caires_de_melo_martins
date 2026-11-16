public final class AzureFila implements FilaMensagens {
    public static final String PREFIXO = "sb-";
    public static final double CUSTO_FIXO_MENSAL = 0.50;

    private final String identificador;

    AzureFila(String identificador) {
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
    public double getCustoFixoMensal() {
        return CUSTO_FIXO_MENSAL;
    }
}
