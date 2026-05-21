package deliveryTech.deliveryAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deliveryTech.deliveryAPI.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    
    List<Restaurante> findByAtivo(Boolean ativo);
    
    List<Restaurante> findByCategoria(String categoria);
}
