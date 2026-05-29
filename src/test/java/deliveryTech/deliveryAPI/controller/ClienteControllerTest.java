package deliveryTech.deliveryAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import deliveryTech.deliveryAPI.dto.request.ClienteRequest;
import deliveryTech.deliveryAPI.model.Cliente;
import deliveryTech.deliveryAPI.service.ClienteService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ClienteRequest clienteRequest;
    private Cliente cliente;
    private List<Cliente> clientes;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        clienteRequest = new ClienteRequest();
        clienteRequest.setNome("João Silva Santos");
        clienteRequest.setTelefone("11999999999");
        clienteRequest.setEmail("joao@email.com");
        clienteRequest.setEndereco("Av João da Silva");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva Santos");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Av João da Silva");
        cliente.setAtivo(true);

        clientes = Arrays.asList(cliente);
    }

    @Test
    @DisplayName("POST /api/clientes - Deve cadastrar cliente com sucesso")
    void deveCadastrarClienteComSucesso() throws Exception {
        when(clienteService.cadastrar(any(ClienteRequest.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva Santos"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    @DisplayName("GET /api/clientes - Deve listar clientes ativos")
    void deveListarClientesAtivos() throws Exception {
        when(clienteService.listarAtivos()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João Silva Santos"));
    }

    @Test
    @DisplayName("GET /api/clientes/{id} - Deve buscar cliente por ID")
    void deveBuscarClientePorId() throws Exception {
        when(clienteService.buscarPorId(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/clientes/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.nome").value("João Silva Santos"))
                .andExpect(jsonPath("$.message").value("Cliente encontrado"));
    }

    @Test
    @DisplayName("GET /api/clientes/{id} - Deve retornar 404 quando cliente não encontrado")
    void deveRetornar404QuandoClienteNaoEncontrado() throws Exception {
        when(clienteService.buscarPorId(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cliente não encontrado"));
    }

    @Test
    @DisplayName("GET /api/clientes/email/{email} - Deve buscar cliente por email")
    void deveBuscarClientePorEmail() throws Exception {
        when(clienteService.buscarPorEmail("joao@email.com")).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/clientes/email/{email}", "joao@email.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("joao@email.com"))
                .andExpect(jsonPath("$.nome").value("João Silva Santos"));
    }

    @Test
    @DisplayName("PUT /api/clientes/{id} - Deve atualizar cliente")
    void deveAtualizarCliente() throws Exception {
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(1L);
        clienteAtualizado.setNome("João Silva Atualizado");
        clienteAtualizado.setEmail("joao@email.com");
        clienteAtualizado.setAtivo(true);

        when(clienteService.atualizar(eq(1L), any(ClienteRequest.class))).thenReturn(clienteAtualizado);

        mockMvc.perform(put("/api/clientes/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Silva Atualizado"));
    }

    @Test
    @DisplayName("PATCH /api/clientes/{id}/status - Deve ativar/desativar cliente")
    void deveAtivarDesativarCliente() throws Exception {
        Cliente clienteInativo = new Cliente();
        clienteInativo.setId(1L);
        clienteInativo.setNome("João Silva Santos");
        clienteInativo.setEmail("joao@email.com");
        clienteInativo.setAtivo(false);

        when(clienteService.ativarDesativarCliente(1L)).thenReturn(clienteInativo);

        mockMvc.perform(patch("/api/clientes/{id}/status", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensagem").value("Cliente desativado com sucesso"))
                .andExpect(jsonPath("$.cliente.id").value(1))
                .andExpect(jsonPath("$.cliente.nome").value("João Silva Santos"))
                .andExpect(jsonPath("$.cliente.ativo").value(false));
    }

    @Test
    @DisplayName("GET /api/clientes/buscar - Deve buscar clientes por nome")
    void deveBuscarPorNome() throws Exception {
        when(clienteService.buscarPorNome("Silva")).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes/buscar")
                .param("nome", "Silva")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João Silva Santos"));
    }

    @Test
    @DisplayName("POST /api/clientes - Deve validar campos obrigatórios")
    void deveValidarCamposObrigatorios() throws Exception {
        ClienteRequest requestInvalido = new ClienteRequest();

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /api/clientes/email/{email} - Deve retornar 404 para email não encontrado")
    void deveRetornar404ParaEmailNaoEncontrado() throws Exception {
        when(clienteService.buscarPorEmail("inexistente@email.com")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/email/{email}", "inexistente@email.com"))
                .andExpect(status().isNotFound());
    }
}
