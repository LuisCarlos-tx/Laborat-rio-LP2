import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {
    private List<Pedido> pedidos;
    private Scanner scanner;
    
    public Restaurante() {
        pedidos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public void executar() {
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    registrarPedido();
                    break;
                case 2:
                    removerPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            System.out.println(); // Linha em branco para melhor visualização
        } while (opcao != 4);
        
        scanner.close();
    }
    
    private void exibirMenu() {
        System.out.println("=== Sistema de Gerenciamento de Pedidos ===");
        System.out.println("1. Registrar Pedido");
        System.out.println("2. Remover Pedido");
        System.out.println("3. Listar Pedidos");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private void registrarPedido() {
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        
        Pedido pedido = new Pedido(nomeCliente);
        
        System.out.println("Adicionando itens ao pedido (digite 'fim' para finalizar):");
        
        while (true) {
            System.out.print("Nome do item (ou 'fim' para finalizar): ");
            String nomeItem = scanner.nextLine();
            
            if (nomeItem.equalsIgnoreCase("fim")) {
                break;
            }
            
            System.out.print("Preço do item: R$ ");
            double precoItem = scanner.nextDouble();
            scanner.nextLine(); // Limpar buffer
            
            Item item = new Item(nomeItem, precoItem);
            pedido.adicionarItem(item);
        }
        
        pedidos.add(pedido);
        pedido.exibirNotaFiscal();
        System.out.println("Pedido registrado com sucesso!");
    }
    
    private void removerPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos cadastrados!");
            return;
        }
        
        System.out.print("Digite o número do pedido a ser removido: ");
        int numeroPedido = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        boolean removido = false;
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getNumero() == numeroPedido) {
                pedidos.remove(i);
                removido = true;
                System.out.println("Pedido removido com sucesso!");
                break;
            }
        }
        
        if (!removido) {
            System.out.println("Pedido não encontrado!");
        }
    }
    
    private void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos cadastrados!");
            return;
        }
        
        System.out.println("=== LISTA DE PEDIDOS ===");
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido N°: " + pedido.getNumero());
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Itens:");
            
            for (Item item : pedido.getItens()) {
                System.out.println("  - " + item.getNome() + " R$ " + item.getPreco());
            }
            
            System.out.println("Total: R$ " + pedido.calcularTotal());
            System.out.println("-----------------------------");
        }
    }
    
    public static void main(String[] args) {
        Restaurante sistema = new Restaurante();
        sistema.executar();
    }
}
