package br.com.crudjava.service;

import br.com.crudjava.model.Agencia;
import br.com.crudjava.repository.AgenciaRepository;
import br.com.crudjava.resources.dto.AgenciaDTO;
import br.com.crudjava.service.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AgenciaService {

    private AgenciaRepository agenciaRepository;
    private BancoService bancoService;

    public Agencia find(Long id) {
        Optional<Agencia> obj = agenciaRepository.findById(id);
        return obj.orElseThrow(
                () -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Agencia.class.getName()));
    }

    public Agencia insert(Agencia obj) {
        return agenciaRepository.save(obj);
    }

    public Agencia update(Agencia obj) {
        Agencia newObj = find(obj.getId());
        updateData(newObj, obj);
        return agenciaRepository.save(newObj);
    }

    public void delete(Long id) {
        find(id);
        try {
            agenciaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Não é possivel excluir agência que possui usuarios vinculados!");
        }
    }

    public List<Agencia> findAll() {
        return agenciaRepository.findAll();
    }

    public Agencia fromDTO(AgenciaDTO objDto) {
        return Agencia.builder()
                .endereco(objDto.getEndereco())
                .banco(bancoService.find(objDto.getIdBanco()))
                .build();
    }

    public AgenciaDTO fromAgencia(Agencia obj) {
        return AgenciaDTO.builder()
                .endereco(obj.getEndereco())
                .idBanco(obj.getBanco().getCodigo())
                .build();
    }

    private void updateData(Agencia newObj, Agencia obj) {
        newObj.setBanco(obj.getBanco());
        newObj.setEndereco(obj.getEndereco());
    }



}
