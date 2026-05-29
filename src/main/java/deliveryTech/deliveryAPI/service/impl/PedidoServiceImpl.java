package deliveryTech.deliveryAPI.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deliveryTech.deliveryAPI.dto.request.ItemPedidoRequest;
import deliveryTech.deliveryAPI.model.Pedido;
import deliveryTech.deliveryAPI.model.Produto;
import deliveryTech.deliveryAPI.model.StatusPedido;
import deliveryTech.deliveryAPI.repository.PedidoRepository;
import deliveryTech.deliveryAPI.repository.ProdutoRepository;
import deliveryTech.deliveryAPI.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

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

    @Override
    public void deletar(Long id) {
        log.info("Deletando pedido ID: {}", id);
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarComFiltros(StatusPedido status, java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
        log.debug("Listando pedidos com filtros - status: {}, período: {} a {}", status, dataInicio, dataFim);
        // TODO: Implementar lógica de filtro com repositório customizado
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        log.info("Atualizando status do pedido ID: {} para {}", id, novoStatus);
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorRestaurante(Long restauranteId) {
        log.debug("Buscando pedidos do restaurante ID: {}", restauranteId);
        return pedidoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calcularTotal(Pedido pedido) {
        if (pedido == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal subtotal = pedido.getItens() == null
            ? BigDecimal.ZERO
            : pedido.getItens().stream()
                .map(item -> item.getSubtotal() != null ? item.getSubtotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxaEntrega = pedido.getRestaurante() != null && pedido.getRestaurante().getTaxaEntrega() != null
            ? pedido.getRestaurante().getTaxaEntrega()
            : BigDecimal.ZERO;

        return subtotal.add(taxaEntrega);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calcularTotalPedido(List<ItemPedidoRequest> itens) {
        if (itens == null || itens.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return itens.stream()
            .map(item -> {
                Produto produto = produtoRepository.findById(item.getProdutoId()).orElse(null);
                if (produto == null || produto.getPreco() == null) {
                    return BigDecimal.ZERO;
                }
                return produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorStatus(StatusPedido status) {
        return pedidoRepository.findAll().stream()
            .filter(pedido -> pedido.getStatus() == status)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorIdComItens(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return pedidoRepository.findAll().stream()
            .filter(pedido -> pedido.getDataPedido() != null)
            .filter(pedido -> !pedido.getDataPedido().isBefore(inicio) && !pedido.getDataPedido().isAfter(fim))
            .collect(Collectors.toList());
    }
}
