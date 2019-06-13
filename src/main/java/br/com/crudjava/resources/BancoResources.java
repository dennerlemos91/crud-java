package br.com.crudjava.resources;

import br.com.crudjava.model.Banco;
import br.com.crudjava.resources.dto.BancoDTO;
import br.com.crudjava.service.BancoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/bancos")
public class BancoResources {

    private BancoService bancoService;

    @GetMapping("{id}")
    public ResponseEntity<Banco> find(@PathVariable Long id) {
        Banco banco = bancoService.find(id);
        return ResponseEntity.ok().body(banco);
    }

    @PostMapping
    public ResponseEntity<Banco> insert(@Valid @RequestBody BancoDTO objDto) {
        Banco obj = bancoService.fromDTO(objDto);
        obj = bancoService.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @PutMapping("{id}")
    public ResponseEntity<Banco> update(@Valid @RequestBody BancoDTO objDto, @PathVariable Long id) {
        Banco obj = bancoService.fromDTO(objDto);
        obj.setCodigo(id);
        obj = bancoService.update(obj);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obj);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bancoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
