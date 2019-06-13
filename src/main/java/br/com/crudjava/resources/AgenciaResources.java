package br.com.crudjava.resources;

import br.com.crudjava.model.Agencia;
import br.com.crudjava.resources.dto.AgenciaDTO;
import br.com.crudjava.service.AgenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/agencias")
@Api(tags = "Agencias")
public class AgenciaResources {

    private AgenciaService agenciaService;

    @ApiOperation(value = "Busca uma agência pelo identificador}")
    @GetMapping("{id}")
    public ResponseEntity<Agencia> find(@PathVariable Long id) {
        Agencia categoria = agenciaService.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @ApiOperation(value = "Insere uma agência na base de dados")
    @PostMapping
    public ResponseEntity<Agencia> insert(@Valid @RequestBody AgenciaDTO objDto) {
        Agencia obj = agenciaService.fromDTO(objDto);
        obj = agenciaService.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @ApiOperation(value = "Atualiza uma agência na base de dados")
    @PutMapping("{id}")
    public ResponseEntity<Agencia> update(@Valid @RequestBody AgenciaDTO objDto, @PathVariable Long id) {
        Agencia obj = agenciaService.fromDTO(objDto);
        obj.setId(id);
        obj = agenciaService.update(obj);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obj);
    }

    @ApiOperation(value = "Remove uma agencia da base de dados")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Busca todas as agências da base de dados")
    @GetMapping
    public ResponseEntity<List<AgenciaDTO>> findAll() {
        List<Agencia> list = agenciaService.findAll();
        List<AgenciaDTO> categoriaDTOList = list.stream().map(obj -> agenciaService.fromAgencia(obj)).collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDTOList);
    }


}
