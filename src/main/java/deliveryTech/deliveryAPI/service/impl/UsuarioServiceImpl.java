package deliveryTech.deliveryAPI.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;
import deliveryTech.deliveryAPI.model.Usuario;
import deliveryTech.deliveryAPI.repository.UsuarioRepository;
import deliveryTech.deliveryAPI.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void registrar(RegisterRequest request) {
        log.info("Registrando novo usuário: {}", request.getEmail());
        
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .senha(request.getSenha())
                .nome(request.getNome())
                .ativo(true)
                .build();
        
        usuarioRepository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Realizando login do usuário");
        
        Usuario usuario = usuarioRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos"));
        
        // TODO: Implementar validação de senha com encriptação
        
        LoginResponse response = new LoginResponse();
        response.setToken("TOKEN_PLACEHOLDER");
        response.setUsername(usuario.getNome());
        response.setMessage("Login realizado com sucesso");
        
        return response;
    }

    @Override
    public boolean validarToken(String token) {
        log.debug("Validando token");
        // TODO: Implementar validação de token JWT
        return false;
    }
}
