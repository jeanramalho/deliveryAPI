package deliveryTech.deliveryAPI.repository;

import deliveryTech.deliveryAPI.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByRestauranteId(Long restauranteId);
    
    List<Produto> findByCategoriaAndDisponivel(String categoria, Boolean disponivel);
}
