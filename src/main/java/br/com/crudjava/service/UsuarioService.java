package br.com.crudjava.service;

import br.com.crudjava.model.Usuario;
import br.com.crudjava.repository.UsuarioRepository;
import br.com.crudjava.resources.dto.UsuarioDTO;
import br.com.crudjava.service.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private AgenciaService agenciaService;

    public Usuario find(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(
                () -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario insert(Usuario obj) {
        obj.setId(null);
        return usuarioRepository.save(obj);
    }

    public Usuario update(Usuario obj) {
        Usuario newObj = find(obj.getId());
        updateData(newObj, obj);
        return usuarioRepository.save(obj);
    }

    public void delete(Long id) {
        find(id);
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Não é possivel excluir.");
        }
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario fromDTO(UsuarioDTO objDto) {
        return Usuario.builder()
                .id(objDto.getId())
                .nome(objDto.getNome())
                .cep(objDto.getCep())
                .email(objDto.getEmail())
                .agencia(agenciaService.find(objDto.getIdAgencia()))
                .conta(objDto.getConta())
                .build();
    }

    public UsuarioDTO fromUsuario(Usuario obj) {
        return UsuarioDTO.builder()
                .id(obj.getId())
                .nome(obj.getNome())
                .cep(obj.getCep())
                .email(obj.getEmail())
                .idAgencia(obj.getAgencia().getId())
                .conta(obj.getConta())
                .build();
    }
    private void updateData(Usuario newObj, Usuario obj) {
       newObj.setNome(obj.getNome());
       newObj.setEmail(obj.getEmail());
       newObj.setCep(obj.getCep());
       newObj.setAgencia(obj.getAgencia());
       newObj.setConta(obj.getConta());
    }



}
