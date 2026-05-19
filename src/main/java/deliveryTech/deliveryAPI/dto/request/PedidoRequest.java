package deliveryTech.deliveryAPI.dto.request;

import deliveryTech.deliveryAPI.model.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long restauranteId;

    @NotNull
    private Endereco enderecoEntrega;

    @NotNull
    private List<ItemPedidoRequest> itens;
}
