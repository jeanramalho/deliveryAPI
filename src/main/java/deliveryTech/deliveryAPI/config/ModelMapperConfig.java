package deliveryTech.deliveryAPI.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import deliveryTech.deliveryAPI.dto.response.PedidoResponse;
import deliveryTech.deliveryAPI.model.Pedido;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        // Configuração para Pedido → PedidoResponse
        mapper.addMappings(new PropertyMap<Pedido, PedidoResponse>() {
            @Override
            protected void configure() {
                map().setClienteId(source.getCliente().getId());
                map().setRestauranteId(source.getRestaurante().getId());
            }
        });
        
        return mapper;
    }
}
