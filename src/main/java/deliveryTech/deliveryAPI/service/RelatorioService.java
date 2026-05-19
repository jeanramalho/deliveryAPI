package deliveryTech.deliveryAPI.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RelatorioService {
    
    List<Map<String, Object>> relatorioVendasPorRestaurante(LocalDate dataInicio, LocalDate dataFim);
    
    List<Map<String, Object>> relatorioProdutosMaisVendidos(int limite, LocalDate dataInicio, LocalDate dataFim);
    
    List<Map<String, Object>> relatorioClientesAtivos(int limite, LocalDate dataInicio, LocalDate dataFim);
    
    Map<String, Object> relatorioPedidosPorPeriodo(LocalDate dataInicio, LocalDate dataFim, String agrupamento);
    
    Map<String, Object> resumoVendas(LocalDate dataInicio, LocalDate dataFim);
}
