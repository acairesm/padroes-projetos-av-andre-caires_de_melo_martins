public final class AwsFila implements FilaMensagens {
    public static final String PREFIXO = "sqs-";
    public static final double CUSTO_FIXO_MENSAL = 0.40;

    private final String identificador;

    AwsFila(String identificador) {
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
    public double getCustoFixoMensal() {
        return CUSTO_FIXO_MENSAL;
    }
}
