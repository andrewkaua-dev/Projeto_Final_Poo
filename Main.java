import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UrnaEletronica urna = new UrnaEletronica();

        int op;

        do {
            System.out.println("\n==== URNA ELETRÔNICA ====");
            System.out.println("1 - Criar nova votação");
            System.out.println("2 - Cadastrar cargo");
            System.out.println("3 - Cadastrar candidato");
            System.out.println("4 - Iniciar votação");
            System.out.println("5 - Encerrar votação e exibir resultados");
            System.out.println("6 - Salvar CSV");
            System.out.println("7 - Resetar votação");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1: // NOVA VOTAÇÃO
                    System.out.print("Nome da votação: ");
                    String nome = sc.nextLine();
                    urna.novaVotacao(nome);
                    break;

                case 2: // CADASTRAR CARGO
                    if (urna.getVotacaoAtual() == null) {
                        System.out.println("Crie uma votação primeiro.");
                        break;
                    }
                    System.out.print("Nome do cargo: ");
                    String cargoNome = sc.nextLine();
                    urna.getVotacaoAtual().adicionarCargo(new Cargo(cargoNome));
                    System.out.println("Cargo adicionado!");
                    break;

                case 3: // CADASTRAR CANDIDATO
                    if (urna.getVotacaoAtual() == null) {
                        System.out.println("Crie uma votação primeiro.");
                        break;
                    }

                    System.out.print("Cargo: ");
                    String cargoCand = sc.nextLine();
                    Cargo cargo = urna.getVotacaoAtual().buscarCargo(cargoCand);

                    if (cargo == null) {
                        System.out.println("Cargo não encontrado.");
                        break;
                    }

                    System.out.print("Nome do candidato: ");
                    String nomeCand = sc.nextLine();

                    System.out.print("Número: ");
                    int numero = sc.nextInt();
                    sc.nextLine();

                    cargo.adicionarCandidato(new Candidato(nomeCand, numero, cargoCand));
                    System.out.println("Candidato adicionado!");
                    break;

                case 4: // INICIAR VOTAÇÃO
                    if (urna.getVotacaoAtual() == null) {
                        System.out.println("Crie uma votação primeiro.");
                        break;
                    }

                    String continuar = "s";

                    while (continuar.equalsIgnoreCase("s")) {
                        for (Cargo cg : urna.getVotacaoAtual().getCargos()) {
                            System.out.println("\nVotando para " + cg.getNome());
                            System.out.print("Digite número do candidato (0 = nulo): ");
                            int num = sc.nextInt();
                            sc.nextLine();

                            urna.getVotacaoAtual().votar(cg.getNome(), num);
                        }

                        System.out.print("Outro eleitor? (s/n): ");
                        continuar = sc.nextLine();
                    }
                    break;

                case 5: // RESULTADOS
                    if (urna.getVotacaoAtual() == null) {
                        System.out.println("Crie uma votação primeiro.");
                        break;
                    }
                    urna.getVotacaoAtual().exibirResultados();
                    break;

                case 6: // SALVAR CSV
                    urna.salvarCSV();
                    break;

                case 7: // RESETAR
                    if (urna.getVotacaoAtual() != null) {
                        urna.getVotacaoAtual().resetar();
                        System.out.println("Votação resetada!");
                    }
                    break;
            }

        } while (op != 0);

        sc.close();
        System.out.println("Sistema encerrado!");
    }
}
