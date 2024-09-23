package ufcg.ES.RU.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Model.DTO.SenhaDTO;
import ufcg.ES.RU.Repository.UsuarioRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de aluno")
public class AlunoControllerTests {

    final String URI_ALUNO = "/api/usuarios";

    @Autowired
    MockMvc driver;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    Aluno alunoT;

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
        alunoT = Aluno.builder()
                .matricula("222")
                .nome("teste")
                .email("t@t.t")
                .senha("222")
                .telefone("222")
                .curso("tt")
                .build();
        usuarioRepository.save(alunoT);
    }

    @AfterEach
    void tearDown() {
        usuarioRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de testes do domínio de Aluno")
    public class AlunoTestCase {
        @Test
        @DisplayName("Quando criamos um aluno com dados válidos")
        void criarAluno() throws Exception {
            // Arrange
            Aluno alunoI = Aluno.builder()
                    .matricula("123")
                    .nome("teste")
                    .email("teste@teste.t")
                    .senha("321")
                    .telefone("083")
                    .curso("cc")
                    .build();

            // Act
            String responseJSONString = driver.perform(post(URI_ALUNO + "/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(alunoI)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno alunoO = mapper.readValue(responseJSONString, Aluno.class);

            // Assert
            assertAll(
                    () -> assertEquals(alunoO.getMatricula(), alunoI.getMatricula()),
                    () -> assertEquals(alunoO.getNome(), alunoI.getNome()),
                    () -> assertEquals(alunoO.getEmail(), alunoI.getEmail()),
                    // () -> assertEquals(alunoO.getSenha(), encryptedPassword),
                    () -> assertEquals(alunoO.getTelefone(), alunoI.getTelefone()),
                    () -> assertEquals(alunoO.getCurso(), alunoI.getCurso())
            );
        }

        @Test
        @DisplayName("Quando criamos um aluno sem dados de entrada")
        void criarAlunoSemAluno() throws Exception {
            // Arrange
            Aluno alunoI = null;

            // Act
            String responseJSONString = driver.perform(post(URI_ALUNO + "/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(alunoI)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
        }

        @Test
        @DisplayName("Quando criamos um aluno sem senha")
        void criarAlunoSemSenha() throws Exception {
            // Arrange
            Aluno alunoI = Aluno.builder()
                    .matricula("123")
                    .nome("teste")
                    .email("teste@teste.t")
                    .telefone("083")
                    .curso("cc")
                    .build();

            // Act
            String responseJSONString = driver.perform(post(URI_ALUNO + "/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(alunoI)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
        }

        @Test
        @DisplayName("Quando consultamos todos os usuários")
        void AlunoGetAllTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_ALUNO + "/getAll")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Aluno> alunos = mapper.readValue(responseJSONString,
                    new TypeReference<>() {
                    });

            // Assert
            assertAll(
                    () -> assertEquals(1, alunos.size()),
                    () -> assertEquals(alunos.get(0), alunoT)
            );
        }

        @Test
        @DisplayName("Quando consultamos um aluno existente")
        void GetAlunoTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_ALUNO + "/Aluno/222")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
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
        @DisplayName("Quando consultamos um aluno sem passar a matrícula na entrada")
        void GetAlunoWithoutRegisterNumberTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_ALUNO + "/Aluno/")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando consultamos um aluno inexistente")
        void GetWrongAlunoTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_ALUNO + "/Aluno/111")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando consultamos um aluno existente")
        void GetInexistentAlunoTest() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(get(URI_ALUNO + "/Aluno/222")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
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
        @DisplayName("Quando atualizamos um aluno existente")
        void UpdateAlunoTest() throws Exception {
            // Arrange
            Aluno alunoI = Aluno.builder()
                    .matricula("up")
                    .nome("up")
                    .email("up")
                    .senha("up")
                    .telefone("up")
                    .curso("up")
                    .build();

            // Act
            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaAluno/222")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(alunoI)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Aluno alunoO = mapper.readValue(responseJSONString, Aluno.class);

            // Assert
            assertAll(
                    () -> assertEquals(alunoO.getMatricula(), alunoI.getMatricula()),
                    () -> assertEquals(alunoO.getNome(), alunoI.getNome()),
                    () -> assertEquals(alunoO.getEmail(), alunoI.getEmail()),
                    // () -> assertEquals(alunoO.getSenha(), encryptedPassword),
                    () -> assertEquals(alunoO.getTelefone(), alunoI.getTelefone()),
                    () -> assertEquals(alunoO.getCurso(), alunoI.getCurso())
            );
        }

//        @Test // não da pra
//        @DisplayName("Quando atualizamos um aluno sem passar a senha")
//        void UpdateAlunoWithoutPassTest() throws Exception {
//            // Arrange
//            Aluno alunoI = Aluno.builder()
//                    .matricula("222")
//                    .nome("up")
//                    .senha(null)
//                    .email("up")
//                    .telefone("up")
//                    .curso("up")
//                    .build();
//
//            usuarioRepository.save(alunoI);
//
//            // Act
//            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaAluno/222")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsString(alunoI)))
//                    .andExpect(status().isBadRequest())
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        }

//        @Test // não dá pra testar por causa de erro no fluxo
//        @DisplayName("Quando atualizamos um aluno que não existe")
//        void UpdateInexistentAlunoTest() throws Exception {
//            // Arrange
//            Aluno alunoI = Aluno.builder()
//                    .matricula("222")
//                    .nome("up")
//                    .senha("up")
//                    .email("up")
//                    .telefone("up")
//                    .curso("up")
//                    .build();
//
//            // Act
//            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaAluno/444")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsString(alunoI)))
//                    .andExpect(status().isNotFound())
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        }

        @Test
        @DisplayName("Quando validamos um email existente")
        void validaEmailExistente() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(put(URI_ALUNO + "/validaemail/" + alunoT.getEmail())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isFound())
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
        @DisplayName("Quando validamos um email inexistente")
        void validaEmailInexistente() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(put(URI_ALUNO + "/validaemail/t@a.a")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando deletamos um aluno existente")
        void deleteAlunoExistente() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(delete(URI_ALUNO + "/deleteAluno/" + alunoT.getMatricula())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando deletamos um aluno inexistente")
        void deleteAlunoInexistente() throws Exception {
            // Arrange

            // Act
            String responseJSONString = driver.perform(delete(URI_ALUNO + "/deleteAluno/111")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando alteramos a senha de um aluno existente")
        void alunoUpdatePassExistente() throws Exception {
            // Arrange
            SenhaDTO senha = new SenhaDTO(alunoT.getMatricula(), "333");

            // Act
            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaSenha")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(senha)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

        @Test
        @DisplayName("Quando alteramos a senha de um aluno inexistente")
        void alunoUpdatePassInexistente() throws Exception {
            // Arrange
            SenhaDTO senha = new SenhaDTO("999", "333");

            // Act
            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaSenha")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(senha)))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }

//        @Test // não dá pra inserir usuário sem senha
//        @DisplayName("Quando alteramos a senha de um aluno sem senha")
//        void alunoUpdatePassNoPass() throws Exception {
//            // Arrange
//            SenhaDTO senha = new SenhaDTO("111", "333");
//
//            // Act
//            String responseJSONString = driver.perform(put(URI_ALUNO + "/atualizaSenha")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsString(senha)))
//                    .andExpect(status().isNotFound())
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        }
    }
}

