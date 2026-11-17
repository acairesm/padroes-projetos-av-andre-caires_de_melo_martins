import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Auditor implements InteressadoLote {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        String registro = String.format("%s | %s | %s | %s",
                lance.instante().format(FORMATO), lote.getNome(),
                lance.licitante().getNome(), Moeda.formatar(lance.valor()));
        registros.add(registro);
        System.out.println("  [Auditor] registrado: " + registro);
    }

    public List<String> getRegistros() {
        return List.copyOf(registros);
    }
}
