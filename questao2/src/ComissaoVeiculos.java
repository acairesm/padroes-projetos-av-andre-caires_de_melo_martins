import java.math.BigDecimal;

public class ComissaoVeiculos implements RegraComissao {
    private static final BigDecimal TETO = new BigDecimal("5000.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.08")).min(TETO);
    }

    @Override
    public String getDescricao() {
        return "Veículos (8%, máx. R$ 5.000,00)";
    }
}
