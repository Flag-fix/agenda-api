package com.aula.restAPI.agenda.pessoas.controller;

import com.aula.restAPI.agenda.pessoas.model.Pessoa;
import com.aula.restAPI.agenda.pessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    @RequestMapping(value = "/pessoas", method = RequestMethod.GET)
    public List<Pessoa> getPessoas(){
        return pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> getPessoasById(@PathVariable(value = "id")long id){
        Optional<Pessoa> contato = pessoaRepository.findById(id);
        return contato.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/pessoas", method =  RequestMethod.POST)
    public Pessoa savePessoas(@Valid @RequestBody Pessoa pessoa){
        return  pessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoas/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> updatePessoas(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa){
        Optional<Pessoa> oldContato = pessoaRepository.findById(id);
        if(oldContato.isPresent()){
            Pessoa pessoa = oldContato.get();
            pessoa.setNome(newPessoa.getNome());
            pessoa.setContato(newPessoa.getContato());
            pessoa.setFoto(newPessoa.getFoto());
            pessoa.setReferencia(newPessoa.getReferencia());
            pessoa.setData(newPessoa.getData());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id){
        Optional<Pessoa> contato = pessoaRepository.findById(id);
        if(contato.isPresent()){
            pessoaRepository.delete(contato.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
