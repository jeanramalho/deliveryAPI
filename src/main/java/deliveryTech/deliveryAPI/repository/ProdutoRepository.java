package deliveryTech.deliveryAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deliveryTech.deliveryAPI.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByRestauranteId(Long restauranteId);
    
    List<Produto> findByCategoriaAndDisponivel(String categoria, Boolean disponivel);
}
