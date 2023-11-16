package br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl;


import br.com.senac.apiconclusaobackend.entitys.*;
import br.com.senac.apiconclusaobackend.frameWork.annotions.Business;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.pedidos.impl.repositorys.PedidosRepository;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.PedidosItensBusiness;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.mappers.PedidosItensMapper;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.impl.mappers.ProdutosMapper;
import br.com.senac.apiconclusaobackend.useCases.produtos.impl.repositorys.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class PedidosItensBusinessImpl implements PedidosItensBusiness {

    @Autowired
    private PedidosItensRepository pedidosItensRepository;

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Override
    public List<PedidosItensResponseDom> carregarPedidos() {
        List<PedidosItens> pedidosItensList = pedidosItensRepository.findAll();

        List<PedidosItensResponseDom> out = pedidosItensList.stream()
                .map(PedidosItensMapper::pedidosItensToPedidosItensResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidosItensResponseDom carregarPedidoItemById(Long id) {
        PedidosItens pedidoItem = pedidosItensRepository.findById(id).get();

        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidoItem);

        return out;
    }

    @Override
    public PedidosItensResponseDom criarPedidoItem(PedidosItensRequestDom pedidoItem) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedidoItem(pedidoItem);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Pedidos> pedido = pedidosRepository.findById(pedidoItem.getPedidoId());
        if(!pedido.isPresent()){
            throw new SenacException("Pedido não encontrado");
        }

        Optional<Produtos> produto = produtosRepository.findById(pedidoItem.getProdutoId());
        if(!produto.isPresent()){
            throw new SenacException("Produto não encontrado");
        }

        PedidosItens pedidoItemRetorno = pedidosItensRepository.save(PedidosItensMapper.pedidosItensResquestDomToPedidosItens(pedidoItem, produto.get(), pedido.get()));

        return PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidoItemRetorno);
    }

    @Override
    public PedidosItensResponseDom atualizarPedidoItem(Long id, PedidosItensRequestDom pedidoItem) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedidoItem(pedidoItem);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Pedidos> pedido = pedidosRepository.findById(pedidoItem.getPedidoId());
        if(!pedido.isPresent()){
            throw new SenacException("Pedido não encontrado");
        }

        Optional<Produtos> produto = produtosRepository.findById(pedidoItem.getProdutoId());
        if(!produto.isPresent()){
            throw new SenacException("Produto não encontrado");
        }

        Optional<PedidosItens> pedidoItemRetorno = pedidosItensRepository.findById(id).map(record -> {
            record.setProduto(produto.get());
            record.setPedido(pedido.get());
            record.setQuantidade(pedidoItem.getQuantidade());
            record.setValorUnitario(pedidoItem.getValorUnitario());

            return pedidosItensRepository.save(record);
        });

        if(pedidoItemRetorno.isPresent() == false){
            throw new SenacException("Pedido Item não encontrado");
        }

        PedidosItensResponseDom out = PedidosItensMapper.pedidosItensToPedidosItensResponseDom(pedidoItemRetorno.get());

        return out;
    }

    @Override
    public void deletarPedidoItem(Long id) {
        pedidosItensRepository.deleteById(id);
    }
    private List<String> validacaoManutencaoPedidoItem(PedidosItensRequestDom pedidoItem){
        List<String> messages = new ArrayList<>();

        if(pedidoItem.getPedidoId() == null){
            messages.add("Pedido não informado!");
        }

        if(pedidoItem.getProdutoId() == null){
            messages.add("Produto não informado!");
        }
        if(pedidoItem.getValorUnitario() == null || pedidoItem.getValorUnitario() == 0){
            messages.add("Valor unitário não informado!");
        }
        if(pedidoItem.getQuantidade() == null || pedidoItem.getQuantidade() == 0){
            messages.add("Quantidade não informada!");
        }

        return messages;
    }
}
