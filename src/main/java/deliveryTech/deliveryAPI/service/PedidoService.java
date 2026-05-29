package deliveryTech.deliveryAPI.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import deliveryTech.deliveryAPI.dto.request.ItemPedidoRequest;
import deliveryTech.deliveryAPI.model.Pedido;
import deliveryTech.deliveryAPI.model.StatusPedido;

public interface PedidoService {
    
    Pedido criar(Pedido pedido);
    
    Pedido buscarPorId(Long id);
    
    List<Pedido> buscarPorCliente(Long clienteId);
    
    Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade);
    
    Pedido confirmar(Long id);
    
    Pedido cancelar(Long id);
    
    List<Pedido> listarTodos();
    
    void deletar(Long id);
    
    List<Pedido> listarComFiltros(StatusPedido status, LocalDate dataInicio, LocalDate dataFim);
    
    Pedido atualizarStatus(Long id, StatusPedido novoStatus);
    
    List<Pedido> buscarPorRestaurante(Long restauranteId);

    BigDecimal calcularTotal(Pedido pedido);

    BigDecimal calcularTotalPedido(List<ItemPedidoRequest> itens);

    List<Pedido> buscarPorStatus(StatusPedido status);

    Optional<Pedido> buscarPorIdComItens(Long id);

    List<Pedido> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
}
