package deliveryTech.deliveryAPI.service.impl;

import deliveryTech.deliveryAPI.model.Restaurante;
import deliveryTech.deliveryAPI.dto.request.RestauranteRequest;
import deliveryTech.deliveryAPI.repository.RestauranteRepository;
import deliveryTech.deliveryAPI.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Override
    public Restaurante cadastrar(RestauranteRequest request) {
        log.info("Cadastrando novo restaurante: {}", request.getNome());
        Restaurante restaurante = Restaurante.builder()
                .nome(request.getNome())
                .categoria(request.getCategoria())
                .telefone(request.getTelefone())
                .taxaEntrega(request.getTaxaEntrega())
                .tempoEntregaMinutos(request.getTempoEntregaMinutos())
                .ativo(true)
                .build();
        return restauranteRepository.save(restaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Restaurante> buscarPorId(Long id) {
        log.debug("Buscando restaurante ID: {}", id);
        return restauranteRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurante> listarComFiltros(String categoria, Boolean ativo) {
        log.debug("Listando restaurantes com filtros - categoria: {}, ativo: {}", categoria, ativo);
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        
        if (categoria != null) {
            restaurantes = restaurantes.stream()
                    .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }
        
        if (ativo != null) {
            restaurantes = restaurantes.stream()
                    .filter(r -> r.getAtivo().equals(ativo))
                    .collect(Collectors.toList());
        }
        
        return restaurantes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurante> buscarPorCategoria(String categoria) {
        log.debug("Buscando restaurantes por categoria: {}", categoria);
        return restauranteRepository.findByCategoria(categoria);
    }

    @Override
    public Restaurante atualizar(Long id, RestauranteRequest request) {
        log.info("Atualizando restaurante ID: {}", id);
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        
        restaurante.setNome(request.getNome());
        restaurante.setCategoria(request.getCategoria());
        restaurante.setTelefone(request.getTelefone());
        restaurante.setTaxaEntrega(request.getTaxaEntrega());
        restaurante.setTempoEntregaMinutos(request.getTempoEntregaMinutos());
        
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void inativar(Long id) {
        log.info("Inativando restaurante ID: {}", id);
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        restaurante.setAtivo(false);
        restauranteRepository.save(restaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurante> listarAtivos() {
        log.debug("Listando restaurantes ativos");
        return restauranteRepository.findByAtivo(true);
    }

    @Override
    public java.math.BigDecimal calcularTaxaEntrega(Long restauranteId, String cep) {
        log.debug("Calculando taxa de entrega para restaurante ID: {} e CEP: {}", restauranteId, cep);
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        // TODO: Implementar lógica de cálculo de taxa baseada em CEP
        return restaurante.getTaxaEntrega();
    }

    @Override
    public Restaurante alterarStatus(Long id, Boolean ativo) {
        log.info("Alterando status do restaurante ID: {} para {}", id, ativo);
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        restaurante.setAtivo(ativo);
        return restauranteRepository.save(restaurante);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurante> buscarProximos(String localidade) {
        log.debug("Buscando restaurantes próximos à localidade: {}", localidade);
        // TODO: Implementar lógica de busca por proximidade (geolocalização)
        return listarAtivos();
    }
}
