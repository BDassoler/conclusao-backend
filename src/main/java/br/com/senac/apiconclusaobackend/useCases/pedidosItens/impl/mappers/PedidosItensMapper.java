package br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.mappers;

import br.com.senac.apiconclusaobackend.entitys.*;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;

public class PedidosItensMapper {

    public static PedidosItensResponseDom pedidosItensToPedidosItensResponseDom(PedidosItens pedidosItens) {
        PedidosItensResponseDom out = new PedidosItensResponseDom();
        out.setId(pedidosItens.getId());
        out.setQuantidade(pedidosItens.getQuantidade());
        out.setValorUnitario(pedidosItens.getValorUnitario());

        out.setProdutoId(pedidosItens.getProduto().getId());
        out.setPedidoId(pedidosItens.getPedido().getId());
        return out;
    }

    public static PedidosItens pedidosItensResquestDomToPedidosItens(PedidosItensRequestDom pedidos, Produtos produto, Pedidos pedido) {

        PedidosItens out = new PedidosItens();

        out.setId(pedidos.getId());
        out.setQuantidade(pedidos.getQuantidade());
        out.setValorUnitario(pedidos.getValorUnitario());
        out.setProduto(produto);
        out.setPedido(pedido);

        return out;
    }
}
