package br.com.senac.apiconclusaobackend.useCases.pedidosItens;

import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;

import java.util.List;

public interface PedidosItensBusiness {
    List<PedidosItensResponseDom> carregarPedidos();
    PedidosItensResponseDom carregarPedidoItemById(Long id);
    PedidosItensResponseDom criarPedidoItem(PedidosItensRequestDom pedidoItem) throws SenacException;
    PedidosItensResponseDom atualizarPedidoItem(Long id, PedidosItensRequestDom pedidoItem) throws SenacException;
    void deletarPedidoItem(Long id);
}
