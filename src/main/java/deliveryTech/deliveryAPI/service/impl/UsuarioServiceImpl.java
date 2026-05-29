package deliveryTech.deliveryAPI.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;
import deliveryTech.deliveryAPI.model.Role;
import deliveryTech.deliveryAPI.model.Usuario;
import deliveryTech.deliveryAPI.repository.UsuarioRepository;
import deliveryTech.deliveryAPI.security.JWTUtil;
import deliveryTech.deliveryAPI.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Override
    public void registrar(RegisterRequest request) {
        log.info("Registrando novo usuário: {}", request.getEmail());
        
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        
        Usuario usuario = Usuario.builder()
            .email(request.getEmail())
            .senha(passwordEncoder.encode(request.getSenha()))
            .nome(request.getNome())
            .role(parseRole(request.getRole()))
            .ativo(true)
            .dataCriacao(LocalDateTime.now())
            .build();
        
        usuarioRepository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Realizando login do usuário");
        
        Usuario usuario = usuarioRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos"));
        
        if (!passwordEncoder.matches(request.getPassword(), usuario.getSenha())) {
            throw new IllegalArgumentException("Usuário ou senha inválidos");
        }
        
        LoginResponse response = new LoginResponse();
        response.setToken(jwtUtil.gerarToken(usuario.getEmail()));
        response.setUsername(usuario.getNome());
        response.setMessage("Login realizado com sucesso");
        
        return response;
    }

    @Override
    public boolean validarToken(String token) {
        log.debug("Validando token");
        return jwtUtil.validarToken(token) != null;
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
