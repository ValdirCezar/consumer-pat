package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/consumers")
public class ConsumerController {

    private static final String RETORNA_SUCESSO = "Retorna sucesso";
    private static final String OBJETO_NAO_ENCONTRADO = "Foi uma exceção de objeto não encontrado";
    private static final String FOI_UM_ERRO_INTERNO = "Foi gerado um erro interno";
    private static final String EXCECAO_BAD_REQUEST = "Retorna uma exceção Bad Request";

    @Autowired
    private ConsumerService service;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Consumer> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @GetMapping(value = "/list")
    public ResponseEntity<List<Consumer>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = RETORNA_SUCESSO),
            @ApiResponse(code = 400, message = EXCECAO_BAD_REQUEST),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PostMapping(value = "/create")
    public ResponseEntity<Consumer> create(@RequestBody Consumer obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 400, message = EXCECAO_BAD_REQUEST),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Consumer> update(@PathVariable Integer id, @RequestBody Consumer obj) {
        return ResponseEntity.ok().body(service.update(id, obj));
    }

}
