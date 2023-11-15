package br.com.senac.apiconclusaobackend.useCases.produtos.impl;

import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.useCases.produtos.ProdutosService;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    private ProdutosBusinessImpl produtosBusiness;

    @Override
    public List<ProdutosResponseDom> carregarProdutos() {
        return produtosBusiness.carregarProdutos();
    }

    @Override
    public ProdutosResponseDom criarProdutos(ProdutosRequestDom produtosRequestDom) throws SenacException {
        return produtosBusiness.criarProdutos(produtosRequestDom);
    }

    @Override
    public ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException {
        return produtosBusiness.atualizarProduto(id, produtosRequestDom);
    }

    @Override
    public void deletarProduto(Long id) {
        produtosBusiness.deletarProduto(id);
    }

    @Override
    public ProdutosResponseDom carregarProdutoById(Long id) {
        return produtosBusiness.carregarProdutoById(id);
    }
}
