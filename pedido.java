import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    
    private int numero;
    private String cliente;
    private List<Item> itens;
    
    public Pedido(String cliente) {
        this.numero = contadorPedidos++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }
    
    // Método para adicionar item ao pedido
    public void adicionarItem(Item item) {
        itens.add(item);
    }
    
    // Método para calcular o valor total
    public double calcularTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }
    
    // Getters
    public int getNumero() {
        return numero;
    }
    
    public String getCliente() {
        return cliente;
    }
    
    public List<Item> getItens() {
        return itens;
    }
    
    // Método para exibir resumo do pedido
    public void exibirNotaFiscal() {
        System.out.println("\n==============================");
        System.out.println("    Restaurante Ki-Delícia    ");
        System.out.println("========================================");
        System.out.println("Pedido N°: " + numero);
        System.out.println("Cliente: " + cliente);
        System.out.println("---");
        System.out.println("Itens:");
        
        for (Item item : itens) {
            System.out.println("- " + item.getNome() + " R$ " + item.getPreco());
        }
        
        System.out.println("---");
        System.out.println("Total: R$ " + calcularTotal());
        System.out.println("========================================");
        System.out.println("   Obrigado pela preferência! :)   ");
        System.out.println("========================================\n");
    }
}
