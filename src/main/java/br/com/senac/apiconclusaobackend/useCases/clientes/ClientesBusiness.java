package br.com.senac.apiconclusaobackend.useCases.clientes;

import br.com.senac.apiconclusaobackend.entitys.Clientes;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.clientes.domanis.ClientesRequestDom;
import br.com.senac.apiconclusaobackend.useCases.clientes.domanis.ClientesResponseDom;

import java.util.List;

public interface ClientesBusiness {
    public List<ClientesResponseDom> carregarClientes();
    ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws Exception;
    ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException;
    void deletarCliente(Long id);
    Clientes carregarClienteEntidade(Long id);
    ClientesResponseDom carregarClienteById(Long id);
}
