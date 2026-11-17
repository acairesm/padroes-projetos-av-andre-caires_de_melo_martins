import java.math.BigDecimal;

public class ComissaoArte implements RegraComissao {
    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.20"));
    }

    @Override
    public String getDescricao() {
        return "Arte (20%)";
    }
}
