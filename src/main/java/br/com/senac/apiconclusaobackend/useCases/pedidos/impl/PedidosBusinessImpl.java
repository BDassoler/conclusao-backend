package br.com.senac.apiconclusaobackend.useCases.pedidos.impl;

import br.com.senac.apiconclusaobackend.entitys.Clientes;
import br.com.senac.apiconclusaobackend.entitys.Enderecos;
import br.com.senac.apiconclusaobackend.entitys.Pedidos;
import br.com.senac.apiconclusaobackend.entitys.PedidosItens;
import br.com.senac.apiconclusaobackend.frameWork.annotions.Business;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.clientes.impl.repositorys.ClientesRespository;
import br.com.senac.apiconclusaobackend.useCases.enderecos.impl.repositorys.EnderecosRepository;
import br.com.senac.apiconclusaobackend.useCases.pedidos.PedidosBusiness;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.impl.mappers.PedidosMapper;
import br.com.senac.apiconclusaobackend.useCases.pedidos.impl.repositorys.PedidosRepository;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.mappers.PedidosItensMapper;
import br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.repositorys.PedidosItensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class PedidosBusinessImpl implements PedidosBusiness {
    @Autowired
    private PedidosRepository pedidosRepository;
    @Autowired
    private ClientesRespository clientesRepository;
    @Autowired
    private EnderecosRepository enderecosRepository;
    @Autowired
    private PedidosItensRepository pedidosItensRepository;

    @Override
    public List<PedidosResponseDom> carregarPedidos() {
        List<Pedidos> pedidosList = pedidosRepository.findAll();

        List<PedidosResponseDom> out = pedidosList
                .stream()
                .map(PedidosMapper::pedidosTopedidosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public PedidosResponseDom carregarPedidoEntidade(Long id) {

        var pedido = pedidosRepository.findById(id).get();
        var pedidosItensList = pedidosItensRepository.carregarPedidoItensByPedidoId(id);

        PedidosResponseDom out = PedidosMapper.pedidosTopedidosResponseDom(pedido);

       List<PedidosItensResponseDom> pedItensRespDom = pedidosItensList
                .stream()
                .map(PedidosItensMapper::pedidosItensToPedidosItensResponseDom)
                .collect(Collectors.toList());

        out.setPedidosItens(pedItensRespDom);

        return out;
    }

    @Override
    public PedidosResponseDom criarPedido(PedidosRequestDom pedidosRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedido(pedidosRequestDom);

        if (!messages.isEmpty()) {
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = clientesRepository.findById(pedidosRequestDom.getClienteId());
        if (!cliente.isPresent()) {
            throw new SenacException("Cliente não encontrado");
        }

        Optional<Enderecos> endereco = enderecosRepository.findById(pedidosRequestDom.getEnderecoId());
        if (!endereco.isPresent()) {
            throw new SenacException("Endereço não encontrado");
        }

        Pedidos pedidos = PedidosMapper.pedidosRequestDomTopedidos(pedidosRequestDom, cliente.get(), endereco.get());

        Pedidos resultPedidos = pedidosRepository.save(pedidos);

        PedidosResponseDom out = PedidosMapper.pedidosTopedidosResponseDom(resultPedidos);

        return out;
    }

    @Override
    public PedidosResponseDom atualizarPedido(Long id, PedidosRequestDom pedido) throws SenacException {
        List<String> messages = this.validacaoManutencaoPedido(pedido);

        if (!messages.isEmpty()) {
            throw new SenacException(messages);
        }

        Optional<Clientes> cliente = clientesRepository.findById(pedido.getClienteId());
        if (!cliente.isPresent()) {
            throw new SenacException("Cliente não encontrado");
        }

        Optional<Enderecos> endereco = enderecosRepository.findById(pedido.getEnderecoId());
        if (!endereco.isPresent()) {
            throw new SenacException("Endereco não encontrado");
        }

        Optional<Pedidos> pedidoRetorno = pedidosRepository.findById(id).map(record -> {
            record.setDataCriacao(pedido.getDataCriacao());
            record.setDataEntrega(pedido.getDataEntrega());
            record.setValorDesconto(pedido.getValorDesconto());
            record.setCliente(cliente.get());
            record.setEndereco(endereco.get());
            return pedidosRepository.save(record);
        });

        if (pedidoRetorno.isPresent() == false) {
            throw new SenacException("Pedido não encontrado!");
        }

        PedidosResponseDom out = PedidosMapper.pedidosTopedidosResponseDom(pedidoRetorno.get());

        return out;
    }

    @Override
    public void deletarPedido(Long id) {
        pedidosRepository.deleteById(id);
    }

    private List<String> validacaoManutencaoPedido(PedidosRequestDom pedido) {
        List<String> messages = new ArrayList<>();

        if (pedido.getDataEntrega() == null) {
            messages.add("Data de entrega precisa ser informada!");
        }

        if (pedido.getClienteId() == null) {
            messages.add("Cliente precisa ser informado!");
        }

        if (pedido.getEnderecoId() == null) {
            messages.add("Endereço precisa ser informado!");
        }

        return messages;
    }
}
