package deliveryTech.deliveryAPI.dto.response;

import deliveryTech.deliveryAPI.model.StatusPedido;
import deliveryTech.deliveryAPI.model.Endereco;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {
    private Long id;
    private Long clienteId;
    private Long restauranteId;
    private Endereco enderecoEntrega;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private LocalDateTime dataPedido;
    private List<ItemPedidoResponse> itens;
}