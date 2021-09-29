package com.aula.restAPI.agenda.contatos.controller;

import com.aula.restAPI.agenda.contatos.model.Contato;
import com.aula.restAPI.agenda.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ContatoController {
    private final ContatoRepository contatoRepository;

    @Autowired
    public ContatoController(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }


    @RequestMapping(value = "/contatos", method = RequestMethod.GET)
    public List<Contato> getContatos(){
        return contatoRepository.findAll();
    }

    @RequestMapping(value = "/contatos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contato> getContatosById(@PathVariable(value = "id")long id){
        Optional<Contato> contato = contatoRepository.findById(id);
        return contato.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/contatos", method =  RequestMethod.POST)
    public Contato saveContatos(@Valid @RequestBody Contato contato){
        return  contatoRepository.save(contato);
    }

    @RequestMapping(value = "/contatos/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Contato> updateContatos(@PathVariable(value = "id") long id, @Valid @RequestBody Contato newContato){
        Optional<Contato> oldContato = contatoRepository.findById(id);
        if(oldContato.isPresent()){
            Contato contato = oldContato.get();
            contato.setNome(newContato.getNome());
            contato.setTelefone(newContato.getTelefone());
            contato.setEmail(newContato.getEmail());
            contato.setUrlAvatar(newContato.getUrlAvatar());
            contatoRepository.save(contato);
            return new ResponseEntity<Contato>(contato, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contatos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id){
        Optional<Contato> contato = contatoRepository.findById(id);
        if(contato.isPresent()){
            contatoRepository.delete(contato.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
