package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;

public interface UsuarioService {
    
    void registrar(RegisterRequest request);
    
    LoginResponse login(LoginRequest request);
    
    boolean validarToken(String token);
}
