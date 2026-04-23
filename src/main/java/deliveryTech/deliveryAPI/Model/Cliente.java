package deliveryTech.deliveryAPI.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

    @Column(unique = true)
    private String email;

    @Builder.Default
    private Boolean ativo = true;

    @Builder.Default
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @JsonIgnore
    public void inativar() {
        this.ativo = false;
    }

    // Construtor personalizado para uso em testes ou outras situações específicas
    public Cliente(Long id, String nome, String telefone, String endereco, 
    String email, Boolean ativo, LocalDateTime dataCriacao, Object unused) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }

   


}
