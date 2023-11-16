package br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl;

import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.PedidosItensService;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosItensServiceImpl implements PedidosItensService {

    @Autowired
    private PedidosItensBusinessImpl pedidosItensBusiness;

    @Override
    public List<PedidosItensResponseDom> carregarPedidosItens() {
        return pedidosItensBusiness.carregarPedidos();
    }

    @Override
    public PedidosItensResponseDom carregarPedidoItemById(Long id) {
        return pedidosItensBusiness.carregarPedidoItemById(id);
    }

    @Override
    public PedidosItensResponseDom criarPedidoItem(PedidosItensRequestDom pedidoItem) throws SenacException {
        return pedidosItensBusiness.criarPedidoItem(pedidoItem);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidoItem(Long id, PedidosItensRequestDom pedidoItem) throws SenacException {
        return pedidosItensBusiness.atualizarPedidoItem(id, pedidoItem);
    }

    @Override
    public void deletarPedidoItem(Long id) {
        pedidosItensBusiness.deletarPedidoItem(id);
    }
}
