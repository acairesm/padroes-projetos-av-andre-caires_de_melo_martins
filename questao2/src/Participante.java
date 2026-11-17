public class Participante implements InteressadoLote, Licitante {
    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.printf("  [%s] Novo lance no lote \"%s\": %s%n",
                nome, lote.getNome(), Moeda.formatar(lance.valor()));
    }
}
