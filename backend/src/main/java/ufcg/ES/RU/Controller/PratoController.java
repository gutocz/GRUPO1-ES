package ufcg.ES.RU.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.prato.PratoPostPutDTO;
import ufcg.ES.RU.service.prato.PratoBuscarService;
import ufcg.ES.RU.service.prato.PratoCriarService;

@RestController
@RequestMapping("/api/prato")
public class PratoController {

    @Autowired
    private PratoCriarService pratoCriarService;

    @Autowired
    private PratoBuscarService pratoBuscarService;


    @PostMapping("")
    public ResponseEntity<?> criaPrato(@Valid @RequestBody PratoPostPutDTO pratoPostPutDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(pratoCriarService.criarPrato(pratoPostPutDTO));
    }

    @GetMapping
    public ResponseEntity<?> listarPratos() {
        return ResponseEntity.status(HttpStatus.OK).body(pratoBuscarService.listarPratos());
    }

}
