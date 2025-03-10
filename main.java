package TokenRingSync;
import java.util.InputMismatchException;
import java.util.Scanner;

class TokenRing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o número de nós:");
        int n = scan.nextInt();
        int token = 0; // Inicializa o token com o processo 0
        int choice;

        // Exibe a estrutura do anel
        System.out.print("Topologia em anel: ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.println("0");

        while (true) {
            System.out.println("\nPortador atual do token: " + token);
            
            System.out.print("Digite o remetente (-1 para sair): ");
            int sender = scan.nextInt();
            if (sender == -1) {
                System.out.println("Saindo...");
                break;
            }

            System.out.print("Digite o receptor: ");
            int receiver = scan.nextInt();

            System.out.print("Digite os dados: ");
            scan.nextLine(); // Limpa o buffer
            String data = scan.nextLine();

            // Simulação da passagem do token até o remetente
            System.out.print("Passagem do token: ");
            for (int i = token; (i % n) != sender; i++) {
                System.out.print(i % n + " -> ");
            }
            System.out.println(sender);

            // O remetente envia os dados
            System.out.println("Remetente " + sender + " está enviando dados: " + data);

            // Dados sendo retransmitidos pelos nós intermediários
            for (int i = (sender + 1) % n; i != receiver; i = (i + 1) % n) {
                System.out.println("Dados " + data + " retransmitidos por " + i);
            }

            // O receptor recebe os dados
            System.out.println("Receptor " + receiver + " recebeu os dados: " + data);
            System.out.println("O token está atualmente no nó " + receiver);    
            // O token é atualizado para o último remetente
            token = sender;

            // Pergunta ao usuário se deseja continuar
            while (true) {
                try {
                    System.out.print("Deseja enviar novamente? (1 = Sim, 0 = Não): ");
                    choice = scan.nextInt();
                    if (choice == 1 || choice == 0) {
                        break;
                    } else {
                        System.out.println("Entrada inválida. Digite 1 para Sim ou 0 para Não.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida.");
                    scan.next(); // Limpa a entrada inválida
                }
            }

            if (choice == 0) {
                System.out.println("Encerrando transmissão.");
                break;
            }
        }

        scan.close();
    }
}