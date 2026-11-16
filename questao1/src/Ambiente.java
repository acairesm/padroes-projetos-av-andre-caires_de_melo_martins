import java.util.ArrayList;
import java.util.List;

public final class Ambiente {
    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinas;
    private final ArmazenamentoObjetos armazenamento;
    private final FilaMensagens fila;

    public Ambiente(String nome, String provedor, String regiao,
                    List<MaquinaVirtual> maquinas, ArmazenamentoObjetos armazenamento, FilaMensagens fila) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = List.copyOf(maquinas);
        this.armazenamento = armazenamento;
        this.fila = fila;
        validarMesmoProvedor();
    }

    private void validarMesmoProvedor() {
        List<Recurso> recursos = new ArrayList<>(maquinas);
        recursos.add(armazenamento);
        recursos.add(fila);
        for (Recurso recurso : recursos) {
            if (!provedor.equals(recurso.getProvedor())) {
                throw new IllegalArgumentException(String.format(
                        "Recurso %s pertence ao provedor %s, mas o ambiente %s é do provedor %s",
                        recurso.getIdentificador(), recurso.getProvedor(), nome, provedor));
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getRegiao() {
        return regiao;
    }

    public List<MaquinaVirtual> getMaquinas() {
        return maquinas;
    }

    public ArmazenamentoObjetos getArmazenamento() {
        return armazenamento;
    }

    public FilaMensagens getFila() {
        return fila;
    }

    public double getCustoMensalEstimado() {
        return CalculadoraCusto.calcular(maquinas, armazenamento, fila);
    }

    public String gerarResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- AMBIENTE PROVISIONADO -----").append(System.lineSeparator());
        sb.append("Ambiente : ").append(nome).append(System.lineSeparator());
        sb.append("Provedor : ").append(provedor).append(System.lineSeparator());
        sb.append("Região   : ").append(regiao).append(System.lineSeparator());
        for (MaquinaVirtual maquina : maquinas) {
            sb.append("  VM            : ").append(maquina.getIdentificador()).append(System.lineSeparator());
        }
        sb.append("  Armazenamento : ").append(armazenamento.getIdentificador())
          .append(" (").append(armazenamento.getGb()).append(" GB)").append(System.lineSeparator());
        sb.append("  Fila          : ").append(fila.getIdentificador()).append(System.lineSeparator());
        sb.append(String.format("Custo mensal estimado: R$ %.2f", getCustoMensalEstimado())).append(System.lineSeparator());
        sb.append("---------------------------------");
        return sb.toString();
    }
}
