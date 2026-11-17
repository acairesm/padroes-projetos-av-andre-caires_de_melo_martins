import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Lote implements Observavel {
    public enum Situacao { ABERTO, VENDIDO, NAO_VENDIDO }

    private static final BigDecimal INCREMENTO_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoReserva;
    private final List<Lance> lances = new ArrayList<>();
    private final List<InteressadoLote> interessados = new ArrayList<>();
    private RegraComissao regraComissao;
    private Situacao situacao = Situacao.ABERTO;
    private BigDecimal comissao = BigDecimal.ZERO;

    public Lote(String nome, Categoria categoria, BigDecimal precoInicial, BigDecimal precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.regraPadrao();
    }

    @Override
    public void acompanhar(InteressadoLote interessado) {
        if (!interessados.contains(interessado)) {
            interessados.add(interessado);
        }
    }

    @Override
    public void deixarDeAcompanhar(InteressadoLote interessado) {
        interessados.remove(interessado);
    }

    @Override
    public void notificar(Lance lance) {
        for (InteressadoLote interessado : List.copyOf(interessados)) {
            if (interessado != lance.licitante()) {
                interessado.atualizar(this, lance);
            }
        }
    }

    public void darLance(Licitante licitante, BigDecimal valor) {
        if (situacao != Situacao.ABERTO) {
            throw new IllegalStateException(String.format(
                    "Lance de %s recusado: o lote \"%s\" está encerrado.", licitante.getNome(), nome));
        }
        BigDecimal minimo = getLanceAtual().multiply(INCREMENTO_MINIMO).setScale(2, RoundingMode.HALF_UP);
        if (valor.compareTo(minimo) < 0) {
            throw new IllegalArgumentException(String.format(
                    "Lance de %s (%s) recusado: deve ser pelo menos 5%% maior que o lance atual (%s), mínimo %s.",
                    licitante.getNome(), Moeda.formatar(valor),
                    Moeda.formatar(getLanceAtual()), Moeda.formatar(minimo)));
        }
        Lance lance = new Lance(licitante, valor, LocalDateTime.now());
        lances.add(lance);
        notificar(lance);
    }

    public void trocarRegraComissao(RegraComissao novaRegra) {
        if (situacao != Situacao.ABERTO) {
            throw new IllegalStateException("A regra de comissão só pode ser trocada antes do encerramento.");
        }
        this.regraComissao = novaRegra;
    }

    public Situacao encerrar() {
        if (situacao != Situacao.ABERTO) {
            throw new IllegalStateException("O lote \"" + nome + "\" já foi encerrado.");
        }
        if (!lances.isEmpty() && getLanceAtual().compareTo(precoReserva) >= 0) {
            situacao = Situacao.VENDIDO;
            comissao = regraComissao.calcular(getLanceAtual()).setScale(2, RoundingMode.HALF_UP);
        } else {
            situacao = Situacao.NAO_VENDIDO;
        }
        return situacao;
    }

    public void imprimirAcerto() {
        System.out.println("=== Acerto do vendedor — " + nome + " ===");
        if (situacao != Situacao.VENDIDO) {
            System.out.println("Lote " + situacao + ": nada a repassar, nenhuma comissão cobrada.");
            return;
        }
        System.out.println("Valor de venda:     " + Moeda.formatar(getValorVenda()));
        System.out.println("Comissão cobrada:   " + Moeda.formatar(comissao) + "  [regra " + regraComissao.getDescricao() + "]");
        System.out.println("Líquido a repassar: " + Moeda.formatar(getValorVenda().subtract(comissao)));
    }

    public BigDecimal getLanceAtual() {
        return lances.isEmpty() ? precoInicial : lances.get(lances.size() - 1).valor();
    }

    public BigDecimal getValorVenda() {
        if (situacao != Situacao.VENDIDO) {
            throw new IllegalStateException("O lote \"" + nome + "\" não foi vendido.");
        }
        return getLanceAtual();
    }

    public Licitante getComprador() {
        return situacao == Situacao.VENDIDO ? lances.get(lances.size() - 1).licitante() : null;
    }

    public int getTotalLances() {
        return lances.size();
    }

    public List<Lance> getLances() {
        return List.copyOf(lances);
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecoReserva() {
        return precoReserva;
    }

    public RegraComissao getRegraComissao() {
        return regraComissao;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public Situacao getSituacao() {
        return situacao;
    }
}
