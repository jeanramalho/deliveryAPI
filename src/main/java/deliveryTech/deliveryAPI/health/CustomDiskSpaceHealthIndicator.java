package deliveryTech.deliveryAPI.health;

import java.io.File;
import org.springframework.stereotype.Component;

/**
 * Verificador de espaço em disco personalizado
 * Nota: Este componente está desativado por compatibilidade com Spring Boot 4.0.5
 */
@Component("customDiskSpace")
public class CustomDiskSpaceHealthIndicator {

    private static final long THRESHOLD = 1024 * 1024 * 100L; // 100 MB

    public boolean verificarEspacoDisco() {
        try {
            File diskPath = new File(".");
            long freeSpace = diskPath.getFreeSpace();
            return freeSpace > THRESHOLD;
        } catch (Exception e) {
            return false;
        }
    }
}
