import java.math.BigDecimal;

public class CompraLote implements Compra {
    private final Lote lote;

    public CompraLote(Lote lote) {
        if (lote.getSituacao() != Lote.Situacao.VENDIDO) {
            throw new IllegalArgumentException("Só há compra de lote vendido.");
        }
        this.lote = lote;
    }

    @Override
    public BigDecimal getValorVenda() {
        return lote.getValorVenda();
    }

    @Override
    public String getDetalhes() {
        return "Lote: " + lote.getNome() + " (comprador: " + lote.getComprador().getNome() + ")\n"
                + "Valor de venda: " + Moeda.formatar(getValorVenda());
    }

    @Override
    public BigDecimal getTotal() {
        return getValorVenda();
    }
}
