import java.math.BigDecimal;

public abstract class ServicoAdicional implements Compra {
    protected final Compra compra;

    protected ServicoAdicional(Compra compra) {
        this.compra = compra;
    }

    protected abstract String getDescricao();

    protected abstract BigDecimal getCusto();

    @Override
    public BigDecimal getValorVenda() {
        return compra.getValorVenda();
    }

    @Override
    public String getDetalhes() {
        return compra.getDetalhes() + "\n  + " + getDescricao() + ": " + Moeda.formatar(getCusto());
    }

    @Override
    public BigDecimal getTotal() {
        return compra.getTotal().add(getCusto());
    }
}
