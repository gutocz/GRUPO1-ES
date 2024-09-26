package ufcg.ES.RU.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.DTO.marmita.MarmitaGetDTO;
import ufcg.ES.RU.Model.DTO.marmita.MarmitaPostDTO;
import ufcg.ES.RU.service.marmita.MarmitaBuscarService;
import ufcg.ES.RU.service.marmita.MarmitaCriarService;

@Tag(name="Marmita")
@RestController
@RequestMapping("/api/marmita")
public class MarmitaController {

    @Autowired
    private MarmitaCriarService marmitaCriarService;

    @Autowired
    private MarmitaBuscarService marmitaBuscarService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/create")
    public ResponseEntity criarMarmita(@Valid @RequestBody MarmitaPostDTO marmitaPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(marmitaCriarService.criarMarmita(marmitaPostDTO), MarmitaGetDTO.class));
    }

    @GetMapping("/getAll")
    public ResponseEntity listarTodasMarmitas(){
        return ResponseEntity.status(HttpStatus.OK).body(marmitaBuscarService.listarTodasMarmitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(marmitaBuscarService.buscarMarmitaPorId(id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity criarMarmita(@Valid @PathVariable("id") Long id) {
        marmitaCriarService.deleteMarmita(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado");
    }
}
