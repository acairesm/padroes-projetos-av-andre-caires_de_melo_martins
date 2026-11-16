import java.util.List;

public final class Main {
    private Main() {
    }

    private static FabricaProvedor escolherProvedor(String nome) {
        return switch (nome.toLowerCase()) {
            case "aws" -> new AwsFabrica();
            case "azure" -> new AzureFabrica();
            default -> throw new IllegalArgumentException("Provedor desconhecido: " + nome);
        };
    }

    public static void main(String[] args) throws InterruptedException {
        FabricaProvedor aws = escolherProvedor("aws");
        FabricaProvedor azure = escolherProvedor("azure");
        ProvisionadorAmbiente provisionador = new ProvisionadorAmbiente();

        List<SolicitacaoAmbiente> solicitacoes = List.of(
                new SolicitacaoAmbiente("api-pagamentos", aws, 2, 100),
                new SolicitacaoAmbiente("portal-web", aws, 1, 50),
                new SolicitacaoAmbiente("data-lake", azure, 1, 200),
                new SolicitacaoAmbiente("cluster-ml", azure, 10, 1000)
        );

        List<Thread> threads = solicitacoes.stream()
                .map(solicitacao -> new Thread(
                        () -> executar(provisionador, solicitacao),
                        "provisionamento-" + solicitacao.getNomeAmbiente()))
                .toList();

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println();
        HistoricoAuditoria.getInstancia().imprimir();
    }

    private static void executar(ProvisionadorAmbiente provisionador, SolicitacaoAmbiente solicitacao) {
        try {
            provisionador.provisionar(solicitacao);
        } catch (OrcamentoExcedidoException e) {
            System.out.printf("[RECUSADO] ambiente %s (%s): %s%n",
                    solicitacao.getNomeAmbiente(), solicitacao.getProvedor().getNome(), e.getMessage());
        }
    }
}
