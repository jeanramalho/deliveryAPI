package deliveryTech.deliveryAPI.health;

import org.springframework.stereotype.Component;

/**
 * Verificador de saúde de serviço externo
 * Nota: Este componente está desativado por compatibilidade com Spring Boot 4.0.5
 */
@Component("externalService")
public class ExternalServiceHealthIndicator {

    private boolean serviceUp = true;

    public boolean verificarServico() {
        return serviceUp;
    }

    public void setServiceUp(boolean serviceUp) {
        this.serviceUp = serviceUp;
    }
}
