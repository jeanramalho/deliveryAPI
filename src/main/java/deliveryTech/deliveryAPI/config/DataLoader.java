package deliveryTech.deliveryAPI.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import deliveryTech.deliveryAPI.model.Cliente;
import deliveryTech.deliveryAPI.model.Produto;
import deliveryTech.deliveryAPI.model.Restaurante;
import deliveryTech.deliveryAPI.repository.ClienteRepository;
import deliveryTech.deliveryAPI.repository.PedidoRepository;
import deliveryTech.deliveryAPI.repository.ProdutoRepository;
import deliveryTech.deliveryAPI.repository.RestauranteRepository;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== INICIANDO CARGA DE DADOS DE TESTE ===");

        // Inserir dados de teste (sem limpar dados existentes)
        inserirClientes();
        inserirRestaurantes();

        System.out.println("=== CARGA DE DADOS CONCLUÍDA ===");
        
        // ✅ ADICIONAR: Spring Boot iniciada com sucesso + Bender
        System.out.println("\n✅ Spring Boot Application iniciada com sucesso!");
        
        
        // ✅ INFORMAR sobre captura automática
        System.out.println("\n🎯 SISTEMA DE CAPTURA AUTOMÁTICA ATIVO!");
        System.out.println("📁 Respostas serão salvas em: ./entregaveis/");
        System.out.println("🔄 Faça requisições para /api/* e veja os arquivos sendo gerados!\n");
    }


private void inserirClientes() {
        System.out.println("--- Inserindo clientes ---");

        Cliente cliente1 = Cliente.builder()
            .nome("João Silva")
            .email("joao@email.com")
            .telefone("11987654321")
            .endereco("Rua das Flores, 123 - Vila Madalena, São Paulo - SP")
            .ativo(true)
            .build();

        Cliente cliente2 = Cliente.builder()
            .nome("Maria Santos")
            .email("maria@email.com")
            .telefone("11876543210")
            .endereco("Av. Paulista, 456 - Bela Vista, São Paulo - SP")
            .ativo(true)
            .build();

        Cliente cliente3 = Cliente.builder()
            .nome("Pedro Oliveira")
            .email("pedro@email.com")
            .telefone("11765432109")
            .endereco("Rua Augusta, 789 - Consolação, São Paulo - SP")
            .ativo(false)
            .build();

        Cliente cliente4 = Cliente.builder()
            .nome("Ana Costa")
            .email("ana@email.com")
            .telefone("11654321098")
            .endereco("Rua Oscar Freire, 321 - Jardins, São Paulo - SP")
            .ativo(true)
            .build();

        Cliente cliente5 = Cliente.builder()
            .nome("Carlos Ferreira")
            .email("carlos@email.com")
            .telefone("11543210987")
            .endereco("Rua 25 de Março, 654 - Centro, São Paulo - SP")
            .ativo(true)
            .build();

        clienteRepository.saveAll(List.of(cliente1, cliente2, cliente3, cliente4, cliente5));
        System.out.println("✓ 5 clientes inseridos");
    }

    private void inserirRestaurantes() {
        System.out.println("--- Inserindo Restaurantes ---");

        Restaurante restaurante1 = Restaurante.builder()
            .nome("Pizza Express")
            .categoria("Italiana")
            .telefone("1133333333")
            .taxaEntrega(new BigDecimal("3.50"))
            .ativo(true)
            .build();

        Restaurante restaurante2 = Restaurante.builder()
            .nome("Burger King")
            .categoria("Fast Food")
            .telefone("1144444444")
            .taxaEntrega(new BigDecimal("5.00"))
            .ativo(true)
            .build();

        Restaurante restaurante3 = Restaurante.builder()
            .nome("Sushi House")
            .categoria("Japonesa")
            .telefone("1155555555")
            .taxaEntrega(new BigDecimal("4.00"))
            .ativo(true)
            .build();

        Restaurante restaurante4 = Restaurante.builder()
            .nome("Gyros Athenas")
            .categoria("Grega")
            .telefone("1166666666")
            .taxaEntrega(new BigDecimal("6.50"))
            .ativo(true)
            .build();

        Restaurante restaurante5 = Restaurante.builder()
            .nome("Chiparia do Porto")
            .categoria("Frutos do Mar")
            .telefone("1177777777")
            .taxaEntrega(new BigDecimal("7.00"))
            .ativo(true)
            .build();

        restauranteRepository.saveAll(List.of(restaurante1, restaurante2, restaurante3, restaurante4, restaurante5));
        System.out.println("✓ 5 restaurantes inseridos");

        inserirProdutos(restaurante1, restaurante2, restaurante3, restaurante4, restaurante5);
    }

    private void inserirProdutos(Restaurante pizzaExpress, Restaurante burgerKing, Restaurante sushiHouse, Restaurante gyrosAthenas, Restaurante chipariaPorto) {
        System.out.println("--- Inserindo Produtos ---");

        Produto produto1 = Produto.builder()
            .nome("Pizza Margherita")
            .categoria("Pizza")
            .descricao("Pizza clássica com molho de tomate, mussarela e manjericão")
            .preco(new BigDecimal("25.90"))
            .restaurante(pizzaExpress)
            .disponivel(true)
            .build();

        Produto produto2 = Produto.builder()
            .nome("Pizza Pepperoni")
            .categoria("Pizza")
            .descricao("Pizza com molho de tomate, mussarela e pepperoni")
            .preco(new BigDecimal("29.90"))
            .restaurante(pizzaExpress)
            .disponivel(true)
            .build();

        Produto produto3 = Produto.builder()
            .nome("Big Burger")
            .categoria("Hambúrguer")
            .descricao("Hambúrguer duplo com queijo, alface, tomate e molho especial")
            .preco(new BigDecimal("18.50"))
            .restaurante(burgerKing)
            .disponivel(true)
            .build();

        Produto produto4 = Produto.builder()
            .nome("Batata Frita Grande")
            .categoria("Acompanhamento")
            .descricao("Porção grande de batatas fritas crocantes")
            .preco(new BigDecimal("8.90"))
            .restaurante(burgerKing)
            .disponivel(true)
            .build();

        Produto produto5 = Produto.builder()
            .nome("Sushi Salmão")
            .categoria("Sushi")
            .descricao("8 peças de sushi de salmão fresco")
            .preco(new BigDecimal("32.00"))
            .restaurante(sushiHouse)
            .disponivel(true)
            .build();

        Produto produto6 = Produto.builder()
            .nome("Hot Roll")
            .categoria("Sushi")
            .descricao("8 peças de hot roll empanado com salmão")
            .preco(new BigDecimal("28.50"))
            .restaurante(sushiHouse)
            .disponivel(true)
            .build();

        Produto produto7 = Produto.builder()
            .nome("Gyros de Cordeiro")
            .categoria("Espeto")
            .descricao("Espeto de cordeiro grelhado com molho tzatziki, tomate e cebola roxa")
            .preco(new BigDecimal("35.90"))
            .restaurante(gyrosAthenas)
            .disponivel(true)
            .build();

        Produto produto8 = Produto.builder()
            .nome("Souvlaki de Frango")
            .categoria("Espeto")
            .descricao("Espetinho de frango marinado com ervas gregas e batata frita")
            .preco(new BigDecimal("28.50"))
            .restaurante(gyrosAthenas)
            .disponivel(true)
            .build();

        Produto produto9 = Produto.builder()
            .nome("Fish & Chips Tradicional")
            .categoria("Peixe")
            .descricao("Filé de bacalhau empanado com batatas fritas e molho tártaro")
            .preco(new BigDecimal("42.90"))
            .restaurante(chipariaPorto)
            .disponivel(true)
            .build();

        Produto produto10 = Produto.builder()
            .nome("Porção de Camarão Empanado")
            .categoria("Frutos do Mar")
            .descricao("500g de camarão empanado com molho agridoce")
            .preco(new BigDecimal("52.00"))
            .restaurante(chipariaPorto)
            .disponivel(true)
            .build();

        produtoRepository.saveAll(List.of(
            produto1, produto2, produto3, produto4, produto5,
            produto6, produto7, produto8, produto9, produto10
        ));
        System.out.println("✓ 10 produtos inseridos");
    }
}
