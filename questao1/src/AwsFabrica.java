import java.util.concurrent.atomic.AtomicInteger;

public final class AwsFabrica implements FabricaProvedor {
    public static final String NOME = "Aws";

    private static final Tarifa TARIFA = new Tarifa(
            AwsMaquinaVirtual.CUSTO_POR_HORA,
            AwsArmazenamento.CUSTO_POR_GB_MES,
            AwsFila.CUSTO_FIXO_MENSAL);

    private final AtomicInteger sequenciaMaquina = new AtomicInteger();
    private final AtomicInteger sequenciaArmazenamento = new AtomicInteger();
    private final AtomicInteger sequenciaFila = new AtomicInteger();

    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public Tarifa getTarifa() {
        return TARIFA;
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AwsMaquinaVirtual(AwsMaquinaVirtual.PREFIXO + sequenciaMaquina.incrementAndGet());
    }

    @Override
    public ArmazenamentoObjetos criarArmazenamento(int gb) {
        return new AwsArmazenamento(AwsArmazenamento.PREFIXO + sequenciaArmazenamento.incrementAndGet(), gb);
    }

    @Override
    public FilaMensagens criarFila() {
        return new AwsFila(AwsFila.PREFIXO + sequenciaFila.incrementAndGet());
    }
}
