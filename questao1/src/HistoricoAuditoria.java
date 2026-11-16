import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class HistoricoAuditoria {
    private static final class Holder {
        private static final HistoricoAuditoria INSTANCIA = new HistoricoAuditoria();
    }

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private HistoricoAuditoria() {
    }

    public static HistoricoAuditoria getInstancia() {
        return Holder.INSTANCIA;
    }

    public synchronized RegistroAuditoria registrar(String ambiente, String provedor, Resultado resultado, String motivo) {
        RegistroAuditoria registro = new RegistroAuditoria(LocalDateTime.now(), ambiente, provedor, resultado, motivo);
        registros.add(registro);
        return registro;
    }

    public synchronized List<RegistroAuditoria> getRegistros() {
        return List.copyOf(registros);
    }

    public synchronized void imprimir() {
        System.out.println("===== HISTÓRICO DE AUDITORIA (" + registros.size() + " registros) =====");
        int indice = 1;
        for (RegistroAuditoria registro : registros) {
            System.out.printf("%d. %s%n", indice++, registro);
        }
        System.out.println("=".repeat(55));
    }
}
