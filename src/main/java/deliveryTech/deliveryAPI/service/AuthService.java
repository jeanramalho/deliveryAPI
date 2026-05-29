package deliveryTech.deliveryAPI.service;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;
import deliveryTech.deliveryAPI.dto.response.UserResponse;
import deliveryTech.deliveryAPI.model.Usuario;

public interface AuthService {
    
    LoginResponse login(LoginRequest request);

    Usuario register(RegisterRequest request);

    UserResponse getCurrentUser();
}
