package deliveryTech.deliveryAPI.service.impl;

import deliveryTech.deliveryAPI.model.Produto;
import deliveryTech.deliveryAPI.repository.ProdutoRepository;
import deliveryTech.deliveryAPI.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto cadastrar(Produto produto) {
        log.info("Cadastrando novo produto: {}", produto.getNome());
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> buscarPorRestaurante(Long restauranteId) {
        log.debug("Buscando produtos do restaurante ID: {}", restauranteId);
        return produtoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {
        log.info("Atualizando produto ID: {}", id);
        Produto existente = produtoRepository.findById(id).orElse(null);
        if (existente == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    @Override
    public void alterarDisponibilidade(Long id, boolean disponivel) {
        log.info("Alterando disponibilidade do produto ID: {}", id);
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produto.setDisponivel(disponivel);
            produtoRepository.save(produto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        log.debug("Listando todos os produtos");
        return produtoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        log.debug("Buscando produto ID: {}", id);
        return produtoRepository.findById(id);
    }

    @Override
    public void deletar(Long id) {
        log.info("Deletando produto ID: {}", id);
        produtoRepository.deleteById(id);
    }

    @Override
    public void inativar(Long id) {
        log.info("Inativando produto ID: {}", id);
        alterarDisponibilidade(id, false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> buscarPorCategoria(String categoria) {
        log.debug("Buscando produtos por categoria: {}", categoria);
        return produtoRepository.findByCategoriaAndDisponivel(categoria, true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) {
        log.debug("Buscando produtos por nome: {}", nome);
        return produtoRepository.findAll().stream()
                .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }
}
