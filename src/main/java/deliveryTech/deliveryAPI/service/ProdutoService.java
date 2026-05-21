package deliveryTech.deliveryAPI.service;

import java.util.List;
import java.util.Optional;

import deliveryTech.deliveryAPI.model.Produto;

public interface ProdutoService {
    
    Produto cadastrar(Produto produto);
    
    List<Produto> buscarPorRestaurante(Long restauranteId);
    
    Produto atualizar(Long id, Produto produto);
    
    void alterarDisponibilidade(Long id, boolean disponivel);
    
    List<Produto> listarTodos();
    
    Optional<Produto> buscarPorId(Long id);
    
    void deletar(Long id);
    
    void inativar(Long id);
    
    List<Produto> buscarPorCategoria(String categoria);
    
    List<Produto> buscarPorNome(String nome);
}
