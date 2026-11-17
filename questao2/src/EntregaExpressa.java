import java.math.BigDecimal;

public class EntregaExpressa extends ServicoAdicional {
    private static final BigDecimal CUSTO = new BigDecimal("450.00");

    public EntregaExpressa(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Entrega expressa";
    }

    @Override
    protected BigDecimal getCusto() {
        return CUSTO;
    }
}
