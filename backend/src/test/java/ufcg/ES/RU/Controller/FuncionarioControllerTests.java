package ufcg.ES.RU.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ufcg.ES.RU.Model.DTO.SenhaDTO;
import ufcg.ES.RU.Model.Funcionario;
import ufcg.ES.RU.Repository.FuncionarioRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de funcionário")
public class FuncionarioControllerTests {

    final String URI_FUNCIONARIO = "/api/Funcionario";

    @Autowired
    MockMvc driver;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    Funcionario funcionarioT;

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
        funcionarioT = Funcionario.builder()
                .CPF("222")
                .email("t@t.t")
                .senha("222")
                .telefone("222")
                .curso("tt")
                .build();
        funcionarioRepository.save(funcionarioT);
    }

    @AfterEach
    void tearDown() {
        funcionarioRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de testes do domínio de Funcionário")
    public class FuncionarioTestCase {
        @Test
        @DisplayName("Quando criamos um funcionário com dados válidos")
        void criarFuncionario() throws Exception {
            // Arrange
            Funcionario funcionarioI = Funcionario.builder()
                    .CPF("123")
                    .email("teste@teste.t")
                    .senha("321")
                    .telefone("083")
                    .curso("cc")
                    .build();

            // Act
            String responseJSONString = driver.perform(post(URI_FUNCIONARIO + "/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(funcionarioI)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Funcionario funcionarioO = mapper.readValue(responseJSONString, Funcionario.class);

            // Assert
            assertAll(
                    () -> assertEquals(funcionarioO.getCPF(), funcionarioI.getCPF()),
                    () -> assertEquals(funcionarioO.getEmail(), funcionarioI.getEmail()),
                    // () -> assertEquals(funcionarioO.getSenha(), encryptedPassword),
                    () -> assertEquals(funcionarioO.getTelefone(), funcionarioI.getTelefone()),
                    () -> assertEquals(funcionarioO.getCurso(), funcionarioI.getCurso())
            );
        }

        @Test
        @DisplayName("Quando criamos um funcionário sem dados de entrada")
        void criarFuncionarioSemFuncionario() throws Exception {
            // Arrange
            Funcionario funcionarioO = null;

            // Act
            driver.perform(post(URI_FUNCIONARIO + "/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(funcionarioO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
        }

        @Test
        @DisplayName("Quando criamos um funcionário sem senha")
        void criarFuncionarioSemSenha() throws Exception {
            // Arrange
            Funcionario funcionarioI = Funcionario.builder()
                    .CPF("123")
                    .email("teste@teste.t")
                    .telefone("083")
                    .curso("cc")
                    .build();

            // Act
            driver.perform(post(URI_FUNCIONARIO + "/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(funcionarioI)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
        }

        @Test
        @DisplayName("Quando consultamos todos os funcionários")
        void FuncionarioGetAllTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_FUNCIONARIO + "/getAll")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Funcionario> funcionarios = mapper.readValue(responseJSONString,
                    new TypeReference<>() {
                    });

            // Assert
            assertAll(
                    () -> assertEquals(1, funcionarios.size()),
                    () -> assertEquals(funcionarios.get(0), funcionarioT)
            );
        }

        @Test
        @DisplayName("Quando consultamos todos os funcionários sem funcionários cadastrados")
        void FuncionarioGetAllWithoutFuncionarioTest() throws Exception {
            // Arrange
            funcionarioRepository.deleteAll();

            // Act
            driver.perform(get(URI_FUNCIONARIO + "/getAll")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

        }

        @Test
        @DisplayName("Quando consultamos um funcionário existente")
        void GetFuncionarioTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_FUNCIONARIO + "/222")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Funcionario funcionarioO = mapper.readValue(responseJSONString, Funcionario.class);

            // Assert
            assertAll(
                    () -> assertEquals(funcionarioO.getCPF(), funcionarioT.getCPF()),
                    () -> assertEquals(funcionarioO.getEmail(), funcionarioT.getEmail()),
                    // () -> assertEquals(alunoO.getSenha(), encryptedPassword),
                    () -> assertEquals(funcionarioO.getTelefone(), funcionarioT.getTelefone()),
                    () -> assertEquals(funcionarioO.getCurso(), funcionarioT.getCurso())
            );
        }

        @Test
        @DisplayName("Quando consultamos um funcionario sem passar o cpf na entrada")
        void GetFuncionarioWithoutCPFTest() throws Exception {
            // Arrange

            // Act
            driver.perform(get(URI_FUNCIONARIO + "/")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando consultamos um funcionario inexistente")
        void GetWrongFuncionarioTest() throws Exception {
            // Arrange

            // Act
            driver.perform(get(URI_FUNCIONARIO + "/111")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando atualizamos um funcionario existente")
        void UpdateFuncionarioTest() throws Exception {
            // Arrange
            Funcionario funcionarioI = Funcionario.builder()
                    .CPF("up")
                    .email("up")
                    .senha("up")
                    .telefone("up")
                    .curso("up")
                    .build();

            // Act
            String responseJSONString = driver.perform(put(URI_FUNCIONARIO + "/atualizaFuncionario/222")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(funcionarioI)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Funcionario funcionarioO = mapper.readValue(responseJSONString, Funcionario.class);

            // Assert
            assertAll(
                    () -> assertEquals(funcionarioO.getCPF(), funcionarioI.getCPF()),
                    () -> assertEquals(funcionarioO.getEmail(), funcionarioI.getEmail()),
                    // () -> assertEquals(funcionarioO.getSenha(), encryptedPassword),
                    () -> assertEquals(funcionarioO.getTelefone(), funcionarioI.getTelefone()),
                    () -> assertEquals(funcionarioO.getCurso(), funcionarioI.getCurso())
            );
        }

//        @Test // não da pra testar
//        @DisplayName("Quando atualizamos um funcionario sem passar a senha")
//        void UpdateFuncionarioWithoutPassTest() throws Exception {
//            // Arrange
//            Funcionario funcionarioI = Funcionario.builder()
//                    .CPF("222")
//                    .email("up")
//                    .telefone("up")
//                    .curso("up")
//                    .build();
//
//            funcionarioRepository.save(funcionarioI);
//
//            // Act
//            String responseJSONString = driver.perform(put(URI_FUNCIONARIO + "/atualizaFuncionario/222")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsString(funcionarioI)))
//                    .andExpect(status().isBadRequest())
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        }

//        @Test // não dá pra testar por causa de erro no fluxo
//        @DisplayName("Quando atualizamos um funcionario que não existe")
//        void UpdateInexistentFuncionarioTest() throws Exception {
//            // Arrange
//            Funcionario funcionarioI = Funcionario.builder()
//                    .CPF("222")
//                    .senha("up")
//                    .email("up")
//                    .telefone("up")
//                    .curso("up")
//                    .build();
//
//            // Act
//            String responseJSONString = driver.perform(put(URI_FUNCIONARIO + "/atualizaFuncionario/444")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsString(funcionarioI)))
//                    .andExpect(status().isNotFound())
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        }

        @Test
        @DisplayName("Quando validamos um cpf existente")
        void validaCPFExistente() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(put(URI_FUNCIONARIO + "/validaCPF/" + funcionarioT.getCPF())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isFound())
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
        @DisplayName("Quando validamos um cpf inexistente")
        void validaCPFInexistente() throws Exception {
            // Arrange

            // Act
            driver.perform(put(URI_FUNCIONARIO + "/validaCPF/999")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando deletamos um funcionario existente")
        void deleteFuncionarioExistente() throws Exception {
            // Arrange

            // Act
            driver.perform(delete(URI_FUNCIONARIO + "/deleteFuncionario/" + funcionarioT.getCPF())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando deletamos um funcionário inexistente")
        void deleteFuncionarioInexistente() throws Exception {
            // Arrange

            // Act
            driver.perform(delete(URI_FUNCIONARIO + "/deleteFuncionario/111")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando alteramos a senha de um funcionário existente")
        void funcionarioUpdatePassExistente() throws Exception {
            // Arrange
            SenhaDTO senha = new SenhaDTO(funcionarioT.getCPF(), "333");

            // Act
            driver.perform(put(URI_FUNCIONARIO + "/atualizaSenha/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(senha)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando alteramos a senha de um funcionário inexistente")
        void funcionarioUpdatePassInexistente() throws Exception {
            // Arrange
            SenhaDTO senha = new SenhaDTO("999", "333");

            // Act
            driver.perform(put(URI_FUNCIONARIO + "/atualizaSenha/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(senha)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

//        @Test // não dá pra inserir funcionário sem senha
//        @DisplayName("Quando alteramos a senha de um funcionário sem senha")
//        void funcionarioUpdatePassNoPass() throws Exception {
//            // Arrange
//            SenhaDTO senha = new SenhaDTO("111", "333");
//
//            // Act
//            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaSenha/")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsString(senha)))
//                    .andExpect(status().isNotFound())
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        }
    }
}

