import java.util.Objects;

public final class SolicitacaoAmbiente {
    private final String nomeAmbiente;
    private final FabricaProvedor provedor;
    private final int quantidadeMaquinas;
    private final int gbArmazenamento;

    public SolicitacaoAmbiente(String nomeAmbiente, FabricaProvedor provedor, int quantidadeMaquinas, int gbArmazenamento) {
        if (nomeAmbiente == null || nomeAmbiente.isBlank()) {
            throw new IllegalArgumentException("Nome do ambiente é obrigatório");
        }
        if (quantidadeMaquinas < 1) {
            throw new IllegalArgumentException("Quantidade de máquinas deve ser ao menos 1");
        }
        if (gbArmazenamento < 1) {
            throw new IllegalArgumentException("Quantidade de GB deve ser ao menos 1");
        }
        this.nomeAmbiente = nomeAmbiente;
        this.provedor = Objects.requireNonNull(provedor, "Provedor é obrigatório");
        this.quantidadeMaquinas = quantidadeMaquinas;
        this.gbArmazenamento = gbArmazenamento;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public FabricaProvedor getProvedor() {
        return provedor;
    }

    public int getQuantidadeMaquinas() {
        return quantidadeMaquinas;
    }

    public int getGbArmazenamento() {
        return gbArmazenamento;
    }
}
