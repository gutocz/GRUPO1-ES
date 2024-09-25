package ufcg.ES.RU.PedidosTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ufcg.ES.RU.Controller.PedidoController;
import ufcg.ES.RU.Model.Pedido;
import ufcg.ES.RU.service.PedidoService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PedidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks and inject them
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    public void testCriarPedidoMarmita() throws Exception {
        Pedido mockPedido = new Pedido(); // Supõe uma instância adequada
        when(pedidoService.criarPedido(anyString(), anyString())).thenReturn(mockPedido);

        mockMvc.perform(post("/api/pedidos/marmita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Matricula\": \"123456\", \"tipo\": \"Padrão\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCriarPedidoComerNoLocal() throws Exception {
        Pedido mockPedido = new Pedido(); // Supõe uma instância adequada
        when(pedidoService.criarPedidoComerNoLocal(anyString())).thenReturn(mockPedido);

        mockMvc.perform(post("/api/pedidos/local")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Matricula\": \"123456\"}"))
                .andExpect(status().isOk());
    }
}