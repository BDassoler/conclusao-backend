package br.com.senac.apiconclusaobackend.useCases.clientes.impl;

import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.clientes.ClientesService;
import br.com.senac.apiconclusaobackend.useCases.clientes.domanis.ClientesRequestDom;
import br.com.senac.apiconclusaobackend.useCases.clientes.domanis.ClientesResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClientesBusinessImpl clientesBusiness;

    @Override
    public List<ClientesResponseDom> carregarClientes() {
        return clientesBusiness.carregarClientes();
    }

    @Override
    public ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws SenacException {
        return clientesBusiness.criarCliente(clientesRequestDom);
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        return clientesBusiness.atualizarCliente(id, clientesRequestDom);
    }

    @Override
    public void deletarCliente(Long id) {
        clientesBusiness.deletarCliente(id);
    }

    @Override
    public ClientesResponseDom carregarClienteById(Long id) {
        return clientesBusiness.carregarClienteById(id);
    }
}
