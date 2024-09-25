package ufcg.ES.RU.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Model.DTO.LoginDTO;
import ufcg.ES.RU.Model.Funcionario;
import ufcg.ES.RU.Repository.FuncionarioRepository;
import ufcg.ES.RU.Repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de funcionário")
public class LoginControllerTests {

    final String URI_LOGIN = "/api/login";

    @Autowired
    MockMvc driver;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    Funcionario funcionarioT;

    Aluno alunoT;

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());

        funcionarioT = Funcionario.builder()
                .CPF("222")
                .email("t@t.t")
                .senha(new BCryptPasswordEncoder().encode("222"))
                .telefone("222")
                .curso("tt")
                .build();
        alunoT = Aluno.builder()
                .matricula("333")
                .nome("teste")
                .email("t3@t3.t")
                .senha(new BCryptPasswordEncoder().encode("333"))
                .telefone("333")
                .curso("tt")
                .build();
        funcionarioRepository.save(funcionarioT);
        usuarioRepository.save(alunoT);
    }

    @AfterEach
    void tearDown() {
        funcionarioRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de testes do domínio de Login")
    public class LoginTestCase {
        @Test
        @DisplayName("Quando logamos um aluno existente")
        void loginAluno() throws Exception {
            // Arrange
            LoginDTO dto = new LoginDTO(alunoT.getMatricula(), "333");

            // Act
            String responseJSONString = driver.perform(post(URI_LOGIN + "/usuario")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno alunoO = mapper.readValue(responseJSONString, Aluno.class);

            // Assert
            assertAll(
                    () -> assertEquals(alunoO.getMatricula(), alunoT.getMatricula()),
                    () -> assertEquals(alunoO.getNome(), alunoT.getNome()),
                    () -> assertEquals(alunoO.getEmail(), alunoT.getEmail()),
                    // () -> assertEquals(alunoO.getSenha(), encryptedPassword),
                    () -> assertEquals(alunoO.getTelefone(), alunoT.getTelefone()),
                    () -> assertEquals(alunoO.getCurso(), alunoT.getCurso())
            );
        }

        @Test
        @DisplayName("Quando logamos um aluno existente com senha errada")
        void loginAlunoWrongPass() throws Exception {
            // Arrange
            LoginDTO dto = new LoginDTO(alunoT.getMatricula(), "444");

            // Act
            driver.perform(post(URI_LOGIN + "/usuario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando logamos um aluno inexistente")
        void loginInexistentAluno() throws Exception {
            // Arrange
            LoginDTO dto = new LoginDTO("444", "333");

            // Act
            driver.perform(post(URI_LOGIN + "/usuario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando logamos um funcionário existente")
        void loginFuncionario() throws Exception {
            // Arrange
            LoginDTO dto = new LoginDTO(funcionarioT.getCPF(), "222");

            // Act
            String responseJSONString = driver.perform(post(URI_LOGIN + "/funcionario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Funcionario funcionarioO = mapper.readValue(responseJSONString, Funcionario.class);

            // Assert
            assertAll(
                    () -> assertEquals(funcionarioO.getCPF(), funcionarioT.getCPF()),
                    () -> assertEquals(funcionarioO.getEmail(), funcionarioT.getEmail()),
                    // () -> assertEquals(funcionarioO.getSenha(), encryptedPassword),
                    () -> assertEquals(funcionarioO.getTelefone(), funcionarioT.getTelefone()),
                    () -> assertEquals(funcionarioO.getCurso(), funcionarioT.getCurso())
            );
        }

        @Test
        @DisplayName("Quando logamos um aluno existente com senha errada")
        void loginFuncionarioWrongPass() throws Exception {
            // Arrange
            LoginDTO dto = new LoginDTO(funcionarioT.getCPF(), "111");

            // Act
            driver.perform(post(URI_LOGIN + "/funcionario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando logamos um funcionário inexistente")
        void loginInexistentFuncionario() throws Exception {
            // Arrange
            LoginDTO dto = new LoginDTO("444", "222");

            // Act
            driver.perform(post(URI_LOGIN + "/funcionario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }
    }
}

