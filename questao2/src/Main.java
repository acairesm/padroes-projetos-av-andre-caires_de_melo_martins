import java.math.BigDecimal;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Categoria arte = new Categoria("Arte", new ComissaoArte());
        Categoria veiculos = new Categoria("Veículos", new ComissaoVeiculos());

        Lote quadro = new Lote("Quadro Aurora", arte, valor("10000"), valor("15000"));
        Lote fusca = new Lote("Fusca 1972", veiculos, valor("20000"), valor("60000"));

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        for (InteressadoLote interessado : new InteressadoLote[] {ana, bruno, carla, painel, auditor}) {
            quadro.acompanhar(interessado);
        }
        for (InteressadoLote interessado : new InteressadoLote[] {ana, bruno, painel, auditor}) {
            fusca.acompanhar(interessado);
        }

        titulo("Lances no lote " + quadro.getNome() + " (" + arte.nome() + ")");
        lance(quadro, ana, "10500");
        lance(quadro, bruno, "10800");
        lance(quadro, bruno, "12000");
        System.out.println("> Carla deixa de acompanhar " + quadro.getNome());
        quadro.deixarDeAcompanhar(carla);
        lance(quadro, ana, "13000");
        lance(quadro, carla, "16000");

        titulo("Lances no lote " + fusca.getNome() + " (" + veiculos.nome() + ")");
        lance(fusca, bruno, "20000");
        lance(fusca, bruno, "22000");
        lance(fusca, ana, "25000");

        titulo("Campanha: lote " + quadro.getNome() + " passa a usar a regra de Eletrônicos");
        System.out.println("Regra anterior: " + quadro.getRegraComissao().getDescricao());
        quadro.trocarRegraComissao(new ComissaoEletronicos());
        System.out.println("Regra vigente:  " + quadro.getRegraComissao().getDescricao());

        titulo("Encerramento");
        encerrar(quadro);
        encerrar(fusca);
        lance(quadro, ana, "20000");

        titulo("Acertos dos vendedores");
        quadro.imprimirAcerto();
        System.out.println();
        fusca.imprimirAcerto();

        titulo("Faturas do comprador");
        Compra semServicos = new CompraLote(quadro);
        Compra comServicos = new EntregaExpressa(
                new EmbalagemEspecial(
                        new EmbalagemEspecial(
                                new CertificadoAutenticidade(
                                        new SeguroTransporte(new CompraLote(quadro))))));
        imprimirFatura(semServicos);
        System.out.println();
        imprimirFatura(comServicos);

        titulo("Registros do auditor");
        auditor.getRegistros().forEach(System.out::println);
    }

    private static void imprimirFatura(Compra compra) {
        System.out.println("=== Fatura do comprador ===");
        System.out.println(compra.getDetalhes());
        System.out.println("Total a pagar: " + Moeda.formatar(compra.getTotal()));
    }

    private static void lance(Lote lote, Participante participante, String valor) {
        System.out.printf("> %s oferece %s em \"%s\"%n", participante.getNome(), Moeda.formatar(valor(valor)), lote.getNome());
        try {
            lote.darLance(participante, valor(valor));
            System.out.println("  Lance aceito.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("  " + e.getMessage());
        }
    }

    private static void encerrar(Lote lote) {
        Lote.Situacao situacao = lote.encerrar();
        System.out.printf("%s: %s (maior lance %s, reserva %s)%s%n", lote.getNome(), situacao,
                Moeda.formatar(lote.getLanceAtual()), Moeda.formatar(lote.getPrecoReserva()),
                situacao == Lote.Situacao.VENDIDO ? " — comprador: " + lote.getComprador().getNome() : "");
    }

    private static void titulo(String texto) {
        System.out.println("\n##### " + texto + " #####");
    }

    private static BigDecimal valor(String valor) {
        return new BigDecimal(valor);
    }
}
