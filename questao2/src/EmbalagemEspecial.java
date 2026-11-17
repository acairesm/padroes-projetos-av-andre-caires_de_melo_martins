import java.math.BigDecimal;

public class EmbalagemEspecial extends ServicoAdicional {
    private static final BigDecimal CUSTO = new BigDecimal("180.00");

    public EmbalagemEspecial(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Embalagem especial";
    }

    @Override
    protected BigDecimal getCusto() {
        return CUSTO;
    }
}
