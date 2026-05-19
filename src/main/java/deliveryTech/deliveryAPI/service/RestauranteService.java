package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.model.Restaurante;
import deliveryTech.deliveryAPI.dto.request.RestauranteRequest;
import java.math.BigDecimal;
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
    
    BigDecimal calcularTaxaEntrega(Long restauranteId, String cep);
    
    Restaurante alterarStatus(Long id, Boolean ativo);
    
    List<Restaurante> buscarProximos(String localidade);
}
