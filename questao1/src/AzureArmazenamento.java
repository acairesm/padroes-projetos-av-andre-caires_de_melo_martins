public final class AzureArmazenamento implements ArmazenamentoObjetos {
    public static final String PREFIXO = "blob://";
    public static final double CUSTO_POR_GB_MES = 0.02;

    private final String identificador;
    private final int gb;

    AzureArmazenamento(String identificador, int gb) {
        this.identificador = identificador;
        this.gb = gb;
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
    public int getGb() {
        return gb;
    }

    @Override
    public double getCustoPorGbMes() {
        return CUSTO_POR_GB_MES;
    }
}
