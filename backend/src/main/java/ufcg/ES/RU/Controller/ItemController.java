package ufcg.ES.RU.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.service.item.ItemDeleteService;
import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;
import ufcg.ES.RU.service.item.ItemBuscarService;
import ufcg.ES.RU.service.item.ItemCriarService;
import ufcg.ES.RU.service.item.ItemMudarService;

@Tag(name = "Item")
@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemCriarService itemCriarService;

    @Autowired
    private ItemBuscarService itemBuscarService;

    @Autowired
    private ItemDeleteService itemDeleteService;

    @Autowired
    private ItemMudarService itemMudarService;


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

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteItem(@Valid @PathVariable Long id){
        itemDeleteService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK


        ).body("item deletado");
    }

    @PutMapping("/{id}/put")
    public ResponseEntity mudarItem(@Valid @PathVariable Long id, @Valid @RequestBody ItemPostPutDTO itemPostPutDTO){
        return ResponseEntity.status(HttpStatus.OK).body(itemMudarService.mudarItem(itemPostPutDTO, id));
    }

}
