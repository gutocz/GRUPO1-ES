package ufcg.ES.RU.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.prato.ItemPostPutDTO;
import ufcg.ES.RU.service.prato.ItemBuscarService;
import ufcg.ES.RU.service.prato.ItemCriarService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemCriarService itemCriarService;

    @Autowired
    private ItemBuscarService itemBuscarService;


    @PostMapping("/create")
    public ResponseEntity<?> criaPrato(@Valid @RequestBody ItemPostPutDTO itemPostPutDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCriarService.criarPrato(itemPostPutDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> listarPratos() {
        return ResponseEntity.status(HttpStatus.OK).body(itemBuscarService.listarItem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarItemPorId(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(itemBuscarService.buscarItemPorId(id));
    }

}
