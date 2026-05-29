package deliveryTech.deliveryAPI.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;
import deliveryTech.deliveryAPI.dto.response.UserResponse;
import deliveryTech.deliveryAPI.model.Role;
import deliveryTech.deliveryAPI.model.Usuario;
import deliveryTech.deliveryAPI.repository.UsuarioRepository;
import deliveryTech.deliveryAPI.security.JWTUtil;
import deliveryTech.deliveryAPI.service.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getSenha())) {
            throw new IllegalArgumentException("Usuário ou senha inválidos");
        }

        return new LoginResponse(
            jwtUtil.gerarToken(usuario.getEmail()),
            usuario.getNome(),
            "Login realizado com sucesso"
        );
    }

    @Override
    public Usuario register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Role role = parseRole(request.getRole());

        Usuario usuario = Usuario.builder()
            .email(request.getEmail())
            .senha(passwordEncoder.encode(request.getSenha()))
            .nome(request.getNome())
            .role(role)
            .ativo(true)
            .dataCriacao(LocalDateTime.now())
            .build();

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserResponse getCurrentUser() {
        UserResponse response = new UserResponse();
        response.setMessage("Recurso disponível via contexto de segurança");
        return response;
    }

    private Role parseRole(String roleValue) {
        if (roleValue == null || roleValue.isBlank()) {
            return Role.CLIENTE;
        }

        try {
            return Role.valueOf(roleValue.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Papel inválido: " + roleValue);
        }
    }
}
