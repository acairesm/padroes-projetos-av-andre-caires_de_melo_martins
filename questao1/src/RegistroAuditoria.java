import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RegistroAuditoria(
        LocalDateTime dataHora,
        String ambiente,
        String provedor,
        Resultado resultado,
        String motivo) {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    @Override
    public String toString() {
        return String.format("%s | ambiente=%s | provedor=%s | %s | %s",
                dataHora.format(FORMATO), ambiente, provedor, resultado, motivo);
    }
}
