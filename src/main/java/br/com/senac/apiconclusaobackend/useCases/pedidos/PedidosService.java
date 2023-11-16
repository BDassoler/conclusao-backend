package br.com.senac.apiconclusaobackend.useCases.pedidos;

import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosResponseDom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidosService {

    List<PedidosResponseDom> carregarPedidos();
    PedidosResponseDom carregarPedidoById(Long id) throws SenacException ;
    PedidosResponseDom criarPedido(PedidosRequestDom pedido) throws SenacException;
    PedidosResponseDom atualizarPedido(Long id, PedidosRequestDom pedido) throws SenacException;
    void deletarPedido(Long id);
}
