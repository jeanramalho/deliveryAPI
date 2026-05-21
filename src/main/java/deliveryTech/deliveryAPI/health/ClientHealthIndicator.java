package deliveryTech.deliveryAPI.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import deliveryTech.deliveryAPI.repository.ClienteRepository;

/**
 * Health indicator para verificar a saúde do serviço de clientes
 * Nota: Este componente está desativado por compatibilidade
 */
@Component("client")
public class ClientHealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(ClientHealthIndicator.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean verificarSaude() {
        try {
            long totalClientes = clienteRepository.count();
            logger.debug("Serviço de clientes verificado. Total de clientes: {}", totalClientes);
            return true;
        } catch (Exception e) {
            logger.error("Erro ao verificar a saúde do serviço de clientes.");
            return false;
        }
    }
}