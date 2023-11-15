package br.com.senac.apiconclusaobackend.useCases.produtos;

import br.com.senac.apiconclusaobackend.entitys.Produtos;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosResponseDom;

import java.util.List;

public interface ProdutosBusiness {
    List<ProdutosResponseDom> carregarProdutos();

    ProdutosResponseDom criarProdutos(ProdutosRequestDom produtosRequestDom) throws SenacException;

    ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException;

    void deletarProduto(Long id);

    Produtos carregarProdutoEntidade(Long id);

    ProdutosResponseDom carregarProdutoById(Long id);
}
