import java.math.BigDecimal;
import java.math.RoundingMode;

public class SeguroTransporte extends ServicoAdicional {
    public SeguroTransporte(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Seguro de transporte (1,5%)";
    }

    @Override
    protected BigDecimal getCusto() {
        return getValorVenda().multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
    }
}
