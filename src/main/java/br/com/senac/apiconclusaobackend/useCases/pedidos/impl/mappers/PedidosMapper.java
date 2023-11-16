package br.com.senac.apiconclusaobackend.useCases.pedidos.impl.mappers;

import br.com.senac.apiconclusaobackend.entitys.Clientes;
import br.com.senac.apiconclusaobackend.entitys.Pedidos;
import br.com.senac.apiconclusaobackend.entitys.Enderecos;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosEnderecosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.pedidos.domanis.PedidosResponseDom;

import java.time.LocalDate;

public class PedidosMapper {
    public static PedidosResponseDom pedidosTopedidosResponseDom(Pedidos pedidos) {
        PedidosResponseDom out = new PedidosResponseDom();
        out.setId(pedidos.getId());
        out.setDataCriacao(LocalDate.now());
        out.setDataEntrega(pedidos.getDataEntrega());
        out.setValorDesconto(pedidos.getValorDesconto());
        out.setClienteId(pedidos.getCliente().getId());
        out.setEnderecoId(pedidos.getEndereco().getId());

        return out;
    }

    public static Pedidos pedidosRequestDomTopedidos(PedidosRequestDom pedidosRequestDom, Clientes cliente, Enderecos endereco) {
        Pedidos out = new Pedidos();
        out.setId(pedidosRequestDom.getId());
        out.setDataCriacao(LocalDate.now());
        out.setDataEntrega(pedidosRequestDom.getDataEntrega());
        out.setValorDesconto(pedidosRequestDom.getValorDesconto());
        out.setCliente((cliente));
        out.setEndereco(endereco);

        return out;
    }

    public static PedidosEnderecosResponseDom enderecosTopedidosEnderecosResponseDom(Enderecos endereco) {
        PedidosEnderecosResponseDom out = new PedidosEnderecosResponseDom();
        out.setId(endereco.getId());
        out.setBairro(endereco.getBairro());
        out.setCidade(endereco.getCidade());
        out.setRua(endereco.getRua());
        out.setEstado(endereco.getEstado());

        return out;
    }
}
