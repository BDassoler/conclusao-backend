package br.com.senac.apiconclusaobackend.useCases.enderecos.impl.mappers;

import br.com.senac.apiconclusaobackend.entitys.Clientes;
import br.com.senac.apiconclusaobackend.entitys.Enderecos;
import br.com.senac.apiconclusaobackend.useCases.enderecos.domanis.EnderecosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.enderecos.domanis.EnderecosResponseDom;

public class EnderecosMapper {
    public static EnderecosResponseDom enderecosToEnderecosResponseDom(Enderecos endereco){
        EnderecosResponseDom out = new EnderecosResponseDom();
        out.setId(endereco.getId());
        out.setBairro(endereco.getBairro());
        out.setCidade(endereco.getCidade());
        out.setRua(endereco.getRua());
        out.setEstado(endereco.getEstado());
        out.setClienteId(endereco.getCliente().getId());

        return out;
    }

    public static Enderecos enderecosResquestDomToEnderecos(EnderecosRequestDom enderecos, Clientes cliente){
        Enderecos out = new Enderecos();
        out.setBairro(enderecos.getBairro());
        out.setRua(enderecos.getRua());
        out.setCidade(enderecos.getCidade());
        out.setEstado(enderecos.getEstado());
        out.setCliente(cliente);

        return out;
    }
}
