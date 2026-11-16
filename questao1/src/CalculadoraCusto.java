import java.util.List;

public final class CalculadoraCusto {
    public static final int HORAS_POR_MES = 730;

    private CalculadoraCusto() {
    }

    public static double estimar(Tarifa tarifa, int quantidadeMaquinas, int gbArmazenamento) {
        return calcular(tarifa.custoVmPorHora(), quantidadeMaquinas,
                tarifa.custoGbPorMes(), gbArmazenamento,
                tarifa.custoFixoFila());
    }

    public static double calcular(List<MaquinaVirtual> maquinas, ArmazenamentoObjetos armazenamento, FilaMensagens fila) {
        double custoMaquinas = 0.0;
        for (MaquinaVirtual maquina : maquinas) {
            custoMaquinas += maquina.getCustoPorHora() * HORAS_POR_MES;
        }
        return custoMaquinas
                + armazenamento.getGb() * armazenamento.getCustoPorGbMes()
                + fila.getCustoFixoMensal();
    }

    private static double calcular(double custoVmPorHora, int quantidadeMaquinas,
                                   double custoGbPorMes, int gbArmazenamento,
                                   double custoFixoFila) {
        return quantidadeMaquinas * custoVmPorHora * HORAS_POR_MES
                + gbArmazenamento * custoGbPorMes
                + custoFixoFila;
    }
}
