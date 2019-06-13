package br.com.crudjava.service;

import br.com.crudjava.model.Banco;
import br.com.crudjava.repository.BancoRepository;
import br.com.crudjava.resources.dto.BancoDTO;
import br.com.crudjava.service.exceptions.DataIntegrityException;
import br.com.crudjava.service.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BancoService {

    private BancoRepository bancoRepository;

    public Banco find(Long id) {
        Optional<Banco> obj = bancoRepository.findById(id);
        return obj.orElseThrow(
                () -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Banco.class.getName()));
    }

    public Banco insert(Banco banco) {
        return bancoRepository.save(banco);
    }

    public Banco update(Banco obj) {
        Banco newObj = find(obj.getCodigo());
        updateData(newObj, obj);
        return bancoRepository.save(newObj);
    }

    public void delete(Long id) {
        find(id);
        try {
            bancoRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não é possivel excluir banco que possui agencias vinculadas.");
        }
    }

    public Banco fromDTO(BancoDTO objDto) {
        return Banco.builder().codigo(objDto.getCodigo()).nome(objDto.getNome()).build();
    }

    private void updateData(Banco newObj, Banco obj) {
        newObj.setNome(obj.getNome());
    }



}
