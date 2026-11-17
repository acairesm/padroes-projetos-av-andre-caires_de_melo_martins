public class PainelLeiloeiro implements InteressadoLote {
    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.printf("  [Painel] \"%s\" — maior lance: %s | total de lances: %d%n",
                lote.getNome(), Moeda.formatar(lote.getLanceAtual()), lote.getTotalLances());
    }
}
