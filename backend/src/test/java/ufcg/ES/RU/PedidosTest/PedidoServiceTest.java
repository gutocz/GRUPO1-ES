package ufcg.ES.RU.PedidosTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Model.Pedido;
import ufcg.ES.RU.Repository.PedidoRepository;
import ufcg.ES.RU.Repository.UsuarioRepository;
import ufcg.ES.RU.service.AlunoService;
import ufcg.ES.RU.service.PedidoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks e injeta no PedidoService
    }

    @Test
    public void testCriarPedido() {
        Aluno aluno = new Aluno();
        aluno.setMatricula("123456");
        when(usuarioRepository.findById(anyString())).thenReturn(Optional.of(aluno));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido result = pedidoService.criarPedido("123456", "Padrão");

        assertNotNull(result);
        assertEquals("123456", result.getAluno().getMatricula());
    }


    @Test
    public void testCriarPedidoAlunoInexistente() {
        String matricula = "inexistente";
        String tipo = "Padrão";

        when(usuarioRepository.findById(matricula)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.criarPedido(matricula, tipo);
        });

        assertEquals("Aluno não encontrado", exception.getMessage());
    }

    @Test
    public void testConfirmarEntrega() {
        String token = "token123";
        Pedido pedido = Pedido.builder()
                .token(token)
                .entregue(false)
                .tipo("Padrão")
                .build();

        when(pedidoRepository.findByToken(token)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido updatedPedido = pedidoService.confirmarEntrega(token);

        assertNotNull(updatedPedido);
        assertTrue(updatedPedido.isEntregue());
        assertEquals("Padrão", updatedPedido.getTipo());
    }

    @Test
    public void testConfirmarEntregaPedidoNaoEncontrado() {
        String token = "token123";

        when(pedidoRepository.findByToken(token)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.confirmarEntrega(token);
        });

        assertEquals("Pedido não encontrado", exception.getMessage());
    }

    @Test
    public void testConfirmarEntregaPedidoJaEntregue() {
        String token = "token123";
        Pedido pedido = new Pedido();
        pedido.setToken(token);
        pedido.setEntregue(true);

        when(pedidoRepository.findByToken(token)).thenReturn(Optional.of(pedido));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.confirmarEntrega(token);
        });

        assertEquals("Pedido já foi entregue.", exception.getMessage());
    }
}

