public interface Observavel {
    void acompanhar(InteressadoLote interessado);

    void deixarDeAcompanhar(InteressadoLote interessado);

    void notificar(Lance lance);
}
