package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.model.Pedido;
import java.util.List;

public interface PedidoService {
    
    Pedido criar(Pedido pedido);
    
    Pedido buscarPorId(Long id);
    
    List<Pedido> buscarPorCliente(Long clienteId);
    
    Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade);
    
    Pedido confirmar(Long id);
    
    Pedido cancelar(Long id);
    
    List<Pedido> listarTodos();
}
