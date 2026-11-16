import java.util.concurrent.atomic.AtomicInteger;

public final class AzureFabrica implements FabricaProvedor {
    public static final String NOME = "Azure";

    private static final Tarifa TARIFA = new Tarifa(
            AzureMaquinaVirtual.CUSTO_POR_HORA,
            AzureArmazenamento.CUSTO_POR_GB_MES,
            AzureFila.CUSTO_FIXO_MENSAL);

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
        return new AzureMaquinaVirtual(AzureMaquinaVirtual.PREFIXO + sequenciaMaquina.incrementAndGet());
    }

    @Override
    public ArmazenamentoObjetos criarArmazenamento(int gb) {
        return new AzureArmazenamento(AzureArmazenamento.PREFIXO + sequenciaArmazenamento.incrementAndGet(), gb);
    }

    @Override
    public FilaMensagens criarFila() {
        return new AzureFila(AzureFila.PREFIXO + sequenciaFila.incrementAndGet());
    }
}
