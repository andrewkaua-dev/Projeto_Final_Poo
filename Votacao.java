import java.util.ArrayList;

public class Votacao {
    private String nome;
    private ArrayList<Cargo> cargos;

    public Votacao(String nome) {
        this.nome = nome;
        this.cargos = new ArrayList<>();
    }

    public void adicionarCargo(Cargo cargo) {
        cargos.add(cargo);
    }

    public Cargo buscarCargo(String nomeCargo) {
        for (Cargo c : cargos) {
            if (c.getNome().equalsIgnoreCase(nomeCargo)) return c;
        }
        return null;
    }

    public void votar(String cargoNome, int numero) {
        Cargo cargo = buscarCargo(cargoNome);
        if (cargo != null) {
            cargo.votar(numero);
        } else {
            System.out.println("Cargo não encontrado.");
        }
    }

    public void exibirResultados() {
        for (Cargo c : cargos) {
            c.exibirResultado();
        }
    }

    public void resetar() {
        for (Cargo c : cargos) {
            c.resetar();
        }
    }

    public ArrayList<Cargo> getCargos() {
        return cargos;
    }

    public String getNome() {
        return nome;
    }
}
