package br.com.senac.apiconclusaobackend.controllers;

import br.com.senac.apiconclusaobackend.frameWork.annotions.LogRest;
import br.com.senac.apiconclusaobackend.frameWork.utils.ResponseUtil;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.enderecos.domanis.EnderecosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.enderecos.domanis.EnderecosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.enderecos.impl.EnderecosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosServiceImpl enderecosService;

    @GetMapping("/carregar")
    @LogRest
    public ResponseEntity<List<EnderecosResponseDom>> carregarEnderecos(){
        List<EnderecosResponseDom> out = enderecosService.carregarEnderecos();

        return ResponseEntity.ok(out);
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarEndereco(@RequestBody EnderecosRequestDom endereco){
        try {
            EnderecosResponseDom out = enderecosService.criarEndereco(endereco);

            return ResponseEntity.status(HttpStatus.CREATED).body(out);
        } catch (SenacException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecosRequestDom endereco){
        try {
            EnderecosResponseDom out = enderecosService.atualizarEndereco(id, endereco);

            return ResponseEntity.ok(out);
        } catch (SenacException senac) {
            senac.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(senac.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id){
        enderecosService.deletarEndereco(id);

        return ResponseEntity.ok(null);
    }
}
