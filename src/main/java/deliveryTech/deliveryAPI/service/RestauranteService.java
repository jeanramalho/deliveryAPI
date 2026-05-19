package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.model.Restaurante;
import deliveryTech.deliveryAPI.dto.request.RestauranteRequest;
import java.util.List;
import java.util.Optional;

public interface RestauranteService {
    
    Restaurante cadastrar(RestauranteRequest request);
    
    Optional<Restaurante> buscarPorId(Long id);
    
    List<Restaurante> listarComFiltros(String categoria, Boolean ativo);
    
    List<Restaurante> buscarPorCategoria(String categoria);
    
    Restaurante atualizar(Long id, RestauranteRequest request);
    
    void inativar(Long id);
    
    List<Restaurante> listarAtivos();
}
