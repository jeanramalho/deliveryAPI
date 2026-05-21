package deliveryTech.deliveryAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deliveryTech.deliveryAPI.dto.request.LoginRequest;
import deliveryTech.deliveryAPI.dto.request.RegisterRequest;
import deliveryTech.deliveryAPI.dto.response.LoginResponse;
import deliveryTech.deliveryAPI.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsável pela autenticação de usuários
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    /**
     * Registrar novo usuário
     * POST /api/auth/registrar
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegisterRequest request) {
        try {
            log.info("Recebida requisição de registro para email: {}", request.getEmail());
            usuarioService.registrar(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuário registrado com sucesso");
        } catch (IllegalArgumentException e) {
            log.warn("Erro ao registrar usuário: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro interno ao registrar usuário", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * Realizar login
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            log.info("Recebida requisição de login");
            LoginResponse response = usuarioService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.warn("Erro ao realizar login: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos");
        } catch (Exception e) {
            log.error("Erro interno ao realizar login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }

    /**
     * Validar token
     * POST /api/auth/validar
     */
    @PostMapping("/validar")
    public ResponseEntity<?> validarToken(@RequestParam String token) {
        try {
            log.debug("Validando token");
            boolean valido = usuarioService.validarToken(token);
            if (valido) {
                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Token inválido");
            }
        } catch (Exception e) {
            log.error("Erro ao validar token", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
        }
    }
}
