package br.com.senac.apiconclusaobackend.controllers;

import br.com.senac.apiconclusaobackend.frameWork.annotions.LogRest;
import br.com.senac.apiconclusaobackend.frameWork.utils.ResponseUtil;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.impl.PedidosServiceImpl;
import br.com.senac.apiconclusaobackend.useCases.pedidos.impl.repositorys.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosServiceImpl pedidosService;

    @Autowired
    PedidosRepository pedidosRepository;

    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<PedidosResponseDom>> carregarPedidos(){
        return ResponseEntity.ok(pedidosService.carregarPedidos());
    }

    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<PedidosResponseDom> carregarProdutoById(@PathVariable Long id){
        return ResponseEntity.ok(pedidosService.carregarPedidoById(id));
    }

    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarPedidos
            (@RequestBody PedidosRequestDom pedidosRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.criarPedido(pedidosRequestDom));
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
    public ResponseEntity<?> atualizarPedido
            (@PathVariable Long id,
             @RequestBody PedidosRequestDom pedidosRequestDom){
        try {
            return ResponseEntity.ok(
                    pedidosService.atualizarPedido(id, pedidosRequestDom));
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
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id){
        pedidosService.deletarPedido(id);

        return ResponseEntity.ok(null);
    }
}
