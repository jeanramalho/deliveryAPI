package deliveryTech.deliveryAPI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;
import deliveryTech.deliveryAPI.dto.response.UserResponse;
import deliveryTech.deliveryAPI.model.Usuario;
import deliveryTech.deliveryAPI.repository.UsuarioRepository;
import deliveryTech.deliveryAPI.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return usuario;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        throw new UnsupportedOperationException("Login ainda não implementado");
    }

    @Override
    public Usuario register(RegisterRequest request) {
        throw new UnsupportedOperationException("Registro ainda não implementado");
    }

    @Override
    public UserResponse getCurrentUser() {
        throw new UnsupportedOperationException("getCurrentUser ainda não implementado");
    }
}
