package br.com.senac.apiconclusaobackend.controllers;

import br.com.senac.apiconclusaobackend.frameWork.annotions.LogRest;
import br.com.senac.apiconclusaobackend.frameWork.utils.ResponseUtil;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.PedidosItensServiceImpl;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidosItens")
public class PedidosItensController {

    @Autowired
    private PedidosItensServiceImpl pedidosItensService;

    @Autowired
    PedidosItensRepository pedidosItensRepository;

    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<PedidosItensResponseDom>> carregarPedidosItens(){
        return ResponseEntity.ok(pedidosItensService.carregarPedidosItens());
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<PedidosItensResponseDom> carregarPedidoItemById(@PathVariable Long id){
        return ResponseEntity.ok(pedidosItensService.carregarPedidoItemById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedidoItem
            (@RequestBody PedidosItensRequestDom pedidosItensRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosItensService.criarPedidoItem(pedidosItensRequestDom));
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
    public ResponseEntity<?> atualizarPedidoItem
            (@PathVariable Long id,
             @RequestBody PedidosItensRequestDom pedidosItensRequestDom){
        try {
            return ResponseEntity.ok(
                    pedidosItensService.atualizarPedidoItem(id, pedidosItensRequestDom));
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
    public ResponseEntity<Void> deletarPedidoItem(@PathVariable Long id){
        pedidosItensService.deletarPedidoItem(id);

        return ResponseEntity.ok(null);
    }
}
