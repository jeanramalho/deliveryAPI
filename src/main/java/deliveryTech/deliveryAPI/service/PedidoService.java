package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.model.Pedido;
import deliveryTech.deliveryAPI.model.StatusPedido;
import java.time.LocalDate;
import java.util.List;

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
}
