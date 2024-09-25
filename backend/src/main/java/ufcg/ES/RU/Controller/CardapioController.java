package ufcg.ES.RU.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioPostDTO;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioPutItensDTO;
import ufcg.ES.RU.service.cardapio.CardapioBuscarService;
import ufcg.ES.RU.service.cardapio.CardapioCriarService;
import ufcg.ES.RU.service.cardapio.CardapioDeleteService;
import ufcg.ES.RU.service.cardapio.CardapioMudarItens;

@Tag(name = "Cardapio")
@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {

    @Autowired
    private CardapioCriarService cardapioCriarService;

    @Autowired
    private CardapioBuscarService cardapioBuscarService;

    @Autowired
    private CardapioDeleteService cardapioDeleteService;

    @Autowired
    private CardapioMudarItens cardapioMudarItens;

    @GetMapping("/getAll")
    public ResponseEntity buscarCardapios(){
        return ResponseEntity.status(HttpStatus.OK).body(cardapioBuscarService.listarTodosCardapios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarItemPorId(@Valid @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cardapioBuscarService.buscarCardapioPorId(id));
    }

    @PostMapping("/create")
    public ResponseEntity criarCardapio(@Valid @RequestBody CardapioPostDTO cardapioPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardapioCriarService.criarCardapio(cardapioPostDTO));
    }

    //**
    // Os itens que já foram cadastrados no cardapio serão sobrescritos*/
    @PutMapping("/{id}/addItens")
    public ResponseEntity adicionarItens(@Valid @RequestBody CardapioPutItensDTO cardapioPutItensDTO, @PathVariable("id") Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(cardapioMudarItens.adicionarItens(cardapioPutItensDTO, id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteCardapio(@Valid @PathVariable("id") Long id){
        cardapioDeleteService.deleteCardapio(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Cardapio deletado");
    }

}
