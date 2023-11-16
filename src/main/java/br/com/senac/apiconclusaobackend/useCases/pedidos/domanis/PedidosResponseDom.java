package br.com.senac.apiconclusaobackend.useCases.pedidos.domanis;

import br.com.senac.apiconclusaobackend.useCases.pedidosItens.domanis.PedidosItensResponseDom;

import java.time.LocalDate;
import java.util.List;

public class PedidosResponseDom {

    private Long id;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private float valorDesconto;
    private Long clienteId;
    private Long enderecoId;
    private Long valortotal;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    private PedidosClienteResponseDom cliente;
    private PedidosEnderecosResponseDom endereco;
    private List<PedidosItensResponseDom> pedidosItens;

    public List<PedidosItensResponseDom> getPedidosItens() {
        return pedidosItens;
    }

    public void setPedidosItens(List<PedidosItensResponseDom> pedidosItens) {
        this.pedidosItens = pedidosItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public PedidosClienteResponseDom getCliente() {
        return cliente;
    }

}
