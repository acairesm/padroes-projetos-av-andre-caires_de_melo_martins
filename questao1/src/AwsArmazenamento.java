public final class AwsArmazenamento implements ArmazenamentoObjetos {
    public static final String PREFIXO = "s3://";
    public static final double CUSTO_POR_GB_MES = 0.023;

    private final String identificador;
    private final int gb;

    AwsArmazenamento(String identificador, int gb) {
        this.identificador = identificador;
        this.gb = gb;
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
    public int getGb() {
        return gb;
    }

    @Override
    public double getCustoPorGbMes() {
        return CUSTO_POR_GB_MES;
    }
}
