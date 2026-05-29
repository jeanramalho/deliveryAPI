package deliveryTech.deliveryAPI.dto.response;

import deliveryTech.deliveryAPI.model.Usuario;
import lombok.Data;

@Data
public class UserResponse {
    private String message;
    private String email;
    private String nome;
    private String role;
    
    public static UserResponse fromEntity(Usuario usuario) {
        UserResponse response = new UserResponse();
        response.setMessage("Usuário criado com sucesso");
        if (usuario != null) {
            response.setEmail(usuario.getEmail());
            response.setNome(usuario.getNome());
            response.setRole(usuario.getRole() != null ? usuario.getRole().name() : null);
        }
        return response;
    }
}