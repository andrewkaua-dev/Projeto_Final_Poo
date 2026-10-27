import java.util.ArrayList;

public class Cargo {
    private String nome;
    private ArrayList<Candidato> candidatos;
    private int votosBranco;
    private int votosNulo;

    public Cargo(String nome) {
        this.nome = nome;
        this.candidatos = new ArrayList<>();
        this.votosBranco = 0;
        this.votosNulo = 0;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCandidato(Candidato c) {
        candidatos.add(c);
    }

    public Candidato buscarCandidato(int numero) {
        for (Candidato c : candidatos) {
            if (c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    // Alterado: 0 = nulo, número inválido = branco
    public void votar(int numero) {
        if (numero == 0) {  
            votosNulo++; // 0 agora conta como nulo
            return;
        }

        Candidato c = buscarCandidato(numero);
        if (c != null) {
            c.receberVoto();
        } else {
            votosBranco++; // número inválido agora conta como branco
        }
    }

    public void exibirResultado() {
        System.out.println("\n=== Resultado para " + nome + " ===");
        for (Candidato c : candidatos) {
            System.out.println(c.getNome() + " (" + c.getNumero() + "): " + c.getVotos() + " votos");
        }
        System.out.println("Brancos: " + votosBranco);
        System.out.println("Nulos: " + votosNulo);
    }

    public void resetar() {
        for (Candidato c : candidatos) {
            try {
                java.lang.reflect.Field votosField = Candidato.class.getDeclaredField("votos");
                votosField.setAccessible(true);
                votosField.setInt(c, 0);
            } catch (Exception e) {}
        }
        votosBranco = 0;
        votosNulo = 0;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public int getVotosBranco() {
        return votosBranco;
    }

    public int getVotosNulo() {
        return votosNulo;
    }
}
