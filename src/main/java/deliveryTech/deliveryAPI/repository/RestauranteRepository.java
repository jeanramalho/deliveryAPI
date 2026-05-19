package deliveryTech.deliveryAPI.repository;

import deliveryTech.deliveryAPI.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    
    List<Restaurante> findByAtivo(Boolean ativo);
    
    List<Restaurante> findByCategoria(String categoria);
}
