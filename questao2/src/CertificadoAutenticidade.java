import java.math.BigDecimal;

public class CertificadoAutenticidade extends ServicoAdicional {
    private static final BigDecimal CUSTO = new BigDecimal("300.00");

    public CertificadoAutenticidade(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Certificado de autenticidade";
    }

    @Override
    protected BigDecimal getCusto() {
        return CUSTO;
    }
}
