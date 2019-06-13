package br.com.crudjava.resources;

import br.com.crudjava.model.Usuario;
import br.com.crudjava.resources.dto.UsuarioDTO;
import br.com.crudjava.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/usuarios")
public class UsuarioResources {

    private UsuarioService usuarioService;

    @GetMapping("{id}")
    public ResponseEntity<Usuario> find(@PathVariable Long id) {
        Usuario usuario = usuarioService.find(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioDTO objDto) {
        Usuario obj = usuarioService.fromDTO(objDto);
        obj = usuarioService.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@Valid @RequestBody UsuarioDTO objDto, @PathVariable Long id) {
        Usuario obj = usuarioService.fromDTO(objDto);
        obj.setId(id);
        obj = usuarioService.update(obj);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obj);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> list = usuarioService.findAll();
        List<UsuarioDTO> usuariosDTOList = list.stream().map(obj -> usuarioService.fromUsuario(obj)).collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTOList);
    }
}
