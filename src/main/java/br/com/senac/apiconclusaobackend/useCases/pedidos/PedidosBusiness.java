package br.com.senac.apiconclusaobackend.useCases.pedidos;

import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosResponseDom;

import java.util.List;

public interface PedidosBusiness {
    List<PedidosResponseDom> carregarPedidos();
    PedidosResponseDom carregarPedidoEntidade(Long id)throws SenacException ;
    PedidosResponseDom criarPedido(PedidosRequestDom pedidosRequestDom) throws SenacException;
    PedidosResponseDom atualizarPedido(Long id, PedidosRequestDom pedido) throws SenacException;
    void deletarPedido(Long id);
}
