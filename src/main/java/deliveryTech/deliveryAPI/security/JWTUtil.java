package deliveryTech.deliveryAPI.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    // Gera um token JWT para o usuário
    public String gerarToken(String email) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + expiration);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        
        return Jwts.builder()
                .subject(email)
                .issuedAt(agora)
                .expiration(dataExpiracao)
                .signWith(key)
                .compact();
    }
    
    // Valida um token JWT e retorna as claims
    public Claims validarToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null; // Token inválido
        }
    }
    
    // Extrai o email (subject) do token
    public String getEmailFromToken(String token) {
        Claims claims = validarToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    public String generateToken(String name) {
        return gerarToken(name);
    }
}
