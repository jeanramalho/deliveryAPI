package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    
    Produto cadastrar(Produto produto);
    
    List<Produto> buscarPorRestaurante(Long restauranteId);
    
    Produto atualizar(Long id, Produto produto);
    
    void alterarDisponibilidade(Long id, boolean disponivel);
    
    List<Produto> listarTodos();
    
    Optional<Produto> buscarPorId(Long id);
    
    void deletar(Long id);
    
    void inativar(Long id);
}
