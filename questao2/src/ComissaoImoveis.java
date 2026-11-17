import java.math.BigDecimal;

public class ComissaoImoveis implements RegraComissao {
    private static final BigDecimal FIXO = new BigDecimal("1500.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.03")).add(FIXO);
    }

    @Override
    public String getDescricao() {
        return "Imóveis (3% + R$ 1.500,00)";
    }
}
