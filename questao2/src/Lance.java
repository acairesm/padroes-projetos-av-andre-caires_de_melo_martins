import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Lance(Licitante licitante, BigDecimal valor, LocalDateTime instante) {
}
