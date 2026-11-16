import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class Configuracao {
    private static final String NOME_ARQUIVO = "config.properties";

    private static final String[] CAMINHOS_CANDIDATOS = {
            NOME_ARQUIVO,
            "src/" + NOME_ARQUIVO,
            "questao1/src/" + NOME_ARQUIVO
    };

    private static final String CHAVE_REGIAO = "regiao.padrao";
    private static final String CHAVE_ORCAMENTO = "orcamento.mensal.maximo";

    private final String regiaoPadrao;
    private final double orcamentoMensalMaximo;

    private Configuracao() {
        Properties propriedades = carregarArquivo();
        this.regiaoPadrao = obterObrigatoria(propriedades, CHAVE_REGIAO);
        this.orcamentoMensalMaximo = Double.parseDouble(obterObrigatoria(propriedades, CHAVE_ORCAMENTO));
    }

    private static final class Holder {
        private static final Configuracao INSTANCIA = new Configuracao();
    }

    public static Configuracao getInstancia() {
        return Holder.INSTANCIA;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public double getOrcamentoMensalMaximo() {
        return orcamentoMensalMaximo;
    }

    private static Properties carregarArquivo() {
        Properties propriedades = new Properties();
        try {
            for (String caminho : CAMINHOS_CANDIDATOS) {
                Path arquivo = Path.of(caminho);
                if (Files.isRegularFile(arquivo)) {
                    try (InputStream entrada = Files.newInputStream(arquivo)) {
                        propriedades.load(entrada);
                    }
                    System.out.println("[Configuracao] arquivo lido de: " + arquivo.toAbsolutePath());
                    return propriedades;
                }
            }
            try (InputStream entrada = Configuracao.class.getClassLoader().getResourceAsStream(NOME_ARQUIVO)) {
                if (entrada != null) {
                    propriedades.load(entrada);
                    System.out.println("[Configuracao] arquivo lido do classpath");
                    return propriedades;
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler " + NOME_ARQUIVO, e);
        }
        throw new IllegalStateException("Arquivo " + NOME_ARQUIVO + " não encontrado. "
                + "Execute o programa a partir de questao1/ ou da raiz do repositório.");
    }

    private static String obterObrigatoria(Properties propriedades, String chave) {
        String valor = propriedades.getProperty(chave);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Propriedade obrigatória ausente em " + NOME_ARQUIVO + ": " + chave);
        }
        return valor.trim();
    }
}
