import java.util.Scanner; // Importação para entrada de dados do usuário

// Classe principal para gerenciar a lista duplamente encadeada de histórico de navegação
public class BrowserHistory {

    // Nó da lista duplamente encadeada
    static class Node {
        String url; // URL da página
        Node next; // Próximo nó
        Node prev; // Nó anterior

        // Construtor do nó
        Node(String url) {
            this.url = url;
            this.next = null;
            this.prev = null;
        }
    }

    // Classe da lista duplamente encadeada
    static class DoublyLinkedList {
        private Node head; // Início da lista
        private Node tail; // Fim da lista

        // Construtor da lista
        DoublyLinkedList() {
            head = null;
            tail = null;
        }

        // Método para adicionar uma nova página ao final do histórico
        void addPage(String url) {
            Node newNode = new Node(url);
            if (head == null) {
                head = tail = newNode; // Lista está vazia
            } else {
                tail.next = newNode; // Conecta o último nó ao novo
                newNode.prev = tail; // Define o nó anterior
                tail = newNode; // Atualiza o final da lista
            }
        }

        // Método para remover uma página específica do histórico
        boolean removePage(String url) {
            Node current = head;
            while (current != null) {
                if (current.url.equals(url)) { // Encontra o nó com a URL correspondente
                    if (current.prev != null) {
                        current.prev.next = current.next; // Ajusta o ponteiro do nó anterior
                    } else {
                        head = current.next; // Atualiza o início da lista
                    }

                    if (current.next != null) {
                        current.next.prev = current.prev; // Ajusta o ponteiro do próximo nó
                    } else {
                        tail = current.prev; // Atualiza o final da lista
                    }

                    return true; // Página removida com sucesso
                }
                current = current.next; // Avança para o próximo nó
            }
            return false; // Página não encontrada
        }

        // Método para listar todas as páginas no histórico
        void listPages() {
            Node current = head;
            if (current == null) {
                System.out.println("O histórico está vazio.");
                return;
            }

            System.out.println("Histórico de navegação:");
            while (current != null) {
                System.out.println("- " + current.url);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        // Criação da lista duplamente encadeada para o histórico
        DoublyLinkedList history = new DoublyLinkedList();

        // Dados iniciais do histórico
        String[] initialUrls = {
                "www.google.com", "www.facebook.com", "www.youtube.com",
                "www.twitter.com", "www.instagram.com", "www.chatgpt.com",
                "www.github.com", "www.stackoverflow.com", "www.reddit.com",
                "www.wikipedia.org"
        };

        // Adicionando os dados iniciais ao histórico
        for (String url : initialUrls) {
            history.addPage(url);
        }

        // Scanner para entrada de dados do usuário
        Scanner scanner = new Scanner(System.in);

        // Menu interativo
        while (true) {
            System.out.println("\n=== Menu Histórico de Navegação ===");
            System.out.println("1. Listar todas as páginas");
            System.out.println("2. Adicionar uma nova página");
            System.out.println("3. Remover uma página");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (choice) {
                case 1:
                    // Listar páginas
                    System.out.println("\n=== Histórico Atual ===");
                    history.listPages();
                    break;

                case 2:
                    // Adicionar nova página
                    System.out.print("Digite a URL da nova página: ");
                    String newUrl = scanner.nextLine();
                    history.addPage(newUrl);
                    System.out.println("Página adicionada com sucesso!");
                    break;

                case 3:
                    // Remover página
                    System.out.print("Digite a URL da página a ser removida: ");
                    String removeUrl = scanner.nextLine();
                    boolean removed = history.removePage(removeUrl);
                    if (removed) {
                        System.out.println("Página removida com sucesso!");
                    } else {
                        System.out.println("Página não encontrada no histórico.");
                    }
                    break;

                case 4:
                    // Sair do programa
                    System.out.println("Saindo do programa. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
