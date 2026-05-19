package deliveryTech.deliveryAPI.service.impl;

import deliveryTech.deliveryAPI.model.Pedido;
import deliveryTech.deliveryAPI.repository.PedidoRepository;
import deliveryTech.deliveryAPI.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido criar(Pedido pedido) {
        log.info("Criando novo pedido");
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        log.debug("Buscando pedido ID: {}", id);
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> buscarPorCliente(Long clienteId) {
        log.debug("Buscando pedidos do cliente ID: {}", clienteId);
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Override
    public Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade) {
        log.info("Adicionando item ao pedido ID: {}", pedidoId);
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        // Implementar lógica de adição de item
        return pedido;
    }

    @Override
    public Pedido confirmar(Long id) {
        log.info("Confirmando pedido ID: {}", id);
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        // Implementar lógica de confirmação
        return pedido;
    }

    @Override
    public Pedido cancelar(Long id) {
        log.info("Cancelando pedido ID: {}", id);
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        // Implementar lógica de cancelamento
        return pedido;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        log.debug("Listando todos os pedidos");
        return pedidoRepository.findAll();
    }
}
