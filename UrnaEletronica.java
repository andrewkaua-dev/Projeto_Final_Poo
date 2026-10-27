import java.io.FileWriter;
import java.io.IOException;

public class UrnaEletronica {
    private Votacao votacaoAtual;

    public void novaVotacao(String nome) {
        votacaoAtual = new Votacao(nome);
        System.out.println("Nova votação criada: " + nome);
    }

    public Votacao getVotacaoAtual() {
        return votacaoAtual;
    }

    public void salvarCSV() {
        if (votacaoAtual == null) {
            System.out.println("Nenhuma votação ativa.");
            return;
        }

        try {
            String arquivo = "resultado_" + votacaoAtual.getNome() + ".csv";
            FileWriter writer = new FileWriter(arquivo);

            writer.write("Cargo;Candidato;Número;Votos\n");

            for (Cargo cargo : votacaoAtual.getCargos()) {

                for (Candidato c : cargo.getCandidatos()) {
                    writer.write(
                        cargo.getNome() + ";" +
                        c.getNome() + ";" +
                        c.getNumero() + ";" +
                        c.getVotos() + "\n"
                    );
                }

                writer.write(
                    cargo.getNome() + ";BRANCO;;" + cargo.getVotosBranco() + "\n" +
                    cargo.getNome() + ";NULO;;" + cargo.getVotosNulo() + "\n"
                );
            }

            writer.close();
            System.out.println("CSV salvo com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar CSV.");
        }
    }
}
