package br.com.senac.apiconclusaobackend.controllers;

import br.com.senac.apiconclusaobackend.frameWork.annotions.LogRest;
import br.com.senac.apiconclusaobackend.frameWork.utils.ResponseUtil;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.produtos.ProdutosService;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.impl.ProdutosServiceImpl;
import br.com.senac.apiconclusaobackend.useCases.produtos.impl.repositorys.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosServiceImpl produtosService;

    @Autowired
    ProdutosRepository produtosRepository;

    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<ProdutosResponseDom>> carregarProdutos(){
        return ResponseEntity.ok(produtosService.carregarProdutos());
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<ProdutosResponseDom> carregarProdutoById(@PathVariable Long id){
        return ResponseEntity.ok(produtosService.carregarProdutoById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarProduto
            (@RequestBody ProdutosRequestDom produtosRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosService.criarProdutos(produtosRequestDom));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarProduto
            (@PathVariable Long id,
             @RequestBody ProdutosRequestDom ProdutosRequestDom){
        try {
            return ResponseEntity.ok(
                    produtosService.atualizarProduto(id, ProdutosRequestDom));
        } catch (SenacException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtosService.deletarProduto(id);

        return ResponseEntity.ok(null);
    }
}
