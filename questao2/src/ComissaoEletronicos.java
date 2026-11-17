import java.math.BigDecimal;

public class ComissaoEletronicos implements RegraComissao {
    private static final BigDecimal MINIMO = new BigDecimal("50.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.12")).max(MINIMO);
    }

    @Override
    public String getDescricao() {
        return "Eletrônicos (12%, mín. R$ 50,00)";
    }
}
