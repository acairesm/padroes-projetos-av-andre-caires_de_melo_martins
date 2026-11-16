import java.util.ArrayList;
import java.util.List;

public final class ProvisionadorAmbiente {
    public Ambiente provisionar(SolicitacaoAmbiente solicitacao) throws OrcamentoExcedidoException {
        FabricaProvedor fabrica = solicitacao.getProvedor();
        Configuracao configuracao = Configuracao.getInstancia();
        HistoricoAuditoria historico = HistoricoAuditoria.getInstancia();

        double custoEstimado = CalculadoraCusto.estimar(
                fabrica.getTarifa(),
                solicitacao.getQuantidadeMaquinas(),
                solicitacao.getGbArmazenamento());

        if (custoEstimado > configuracao.getOrcamentoMensalMaximo()) {
            String motivo = String.format("custo estimado R$ %.2f excede o orçamento mensal máximo de R$ %.2f",
                    custoEstimado, configuracao.getOrcamentoMensalMaximo());
            historico.registrar(solicitacao.getNomeAmbiente(), fabrica.getNome(), Resultado.RECUSADO, motivo);
            throw new OrcamentoExcedidoException(motivo);
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < solicitacao.getQuantidadeMaquinas(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }
        ArmazenamentoObjetos armazenamento = fabrica.criarArmazenamento(solicitacao.getGbArmazenamento());
        FilaMensagens fila = fabrica.criarFila();

        Ambiente ambiente = new Ambiente(
                solicitacao.getNomeAmbiente(),
                fabrica.getNome(),
                configuracao.getRegiaoPadrao(),
                maquinas, armazenamento, fila);

        historico.registrar(ambiente.getNome(), ambiente.getProvedor(), Resultado.ACEITO,
                String.format("custo estimado R$ %.2f dentro do orçamento", ambiente.getCustoMensalEstimado()));

        System.out.println(ambiente.gerarResumo());
        return ambiente;
    }
}
