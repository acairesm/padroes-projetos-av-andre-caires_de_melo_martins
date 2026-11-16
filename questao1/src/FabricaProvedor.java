public interface FabricaProvedor {
    String getNome();

    Tarifa getTarifa();

    MaquinaVirtual criarMaquinaVirtual();

    ArmazenamentoObjetos criarArmazenamento(int gb);

    FilaMensagens criarFila();
}
