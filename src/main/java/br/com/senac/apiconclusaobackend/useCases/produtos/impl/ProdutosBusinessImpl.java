package br.com.senac.apiconclusaobackend.useCases.produtos.impl;

import br.com.senac.apiconclusaobackend.entitys.Produtos;
import br.com.senac.apiconclusaobackend.frameWork.annotions.Business;
import br.com.senac.apiconclusaobackend.frameWork.utils.SenacException;
import br.com.senac.apiconclusaobackend.frameWork.utils.StringUtil;
import br.com.senac.apiconclusaobackend.useCases.produtos.ProdutosBusiness;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosRequestDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.domanis.ProdutosResponseDom;
import br.com.senac.apiconclusaobackend.useCases.produtos.impl.mappers.ProdutosMapper;
import br.com.senac.apiconclusaobackend.useCases.produtos.impl.repositorys.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ProdutosBusinessImpl implements ProdutosBusiness {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Override
    public List<ProdutosResponseDom> carregarProdutos() {
        List<Produtos> produtosList = produtosRepository.findAll();

        List<ProdutosResponseDom> out = produtosList
                .stream()
                .map(ProdutosMapper:: produtosToProdutosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ProdutosResponseDom criarProdutos(ProdutosRequestDom produtosRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoProduto(produtosRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Produtos produtos = ProdutosMapper.produtosRequestDomToProdutos(produtosRequestDom);

        Produtos resultClientes = produtosRepository.save(produtos);

        ProdutosResponseDom out = ProdutosMapper.produtosToProdutosResponseDom(resultClientes);

        return out;
    }

    @Override
    public ProdutosResponseDom atualizarProduto(Long id, ProdutosRequestDom produtosRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoProduto(produtosRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Produtos> produtos = produtosRepository.findById(id).map(record -> {
            record.setNome(produtosRequestDom.getNome());
            record.setDescricao(produtosRequestDom.getDescricao());

            return produtosRepository.save(record);
        });

        if(!produtos.isPresent()){
            throw new SenacException("Produto informando não existe!");
        }

        ProdutosResponseDom out =
                ProdutosMapper.produtosToProdutosResponseDom(produtos.get());

        return out;
    }

    @Override
    public void deletarProduto(Long id) {
        produtosRepository.deleteById(id);
    }

    @Override
    public Produtos carregarProdutoEntidade(Long id) {
        Produtos produto = produtosRepository.findById(id).get();

        return produto;
    }

    @Override
    public ProdutosResponseDom carregarProdutoById(Long id) {
        Produtos produto = produtosRepository.findById(id).get();

        ProdutosResponseDom out = ProdutosMapper.produtosToProdutosResponseDom(produto);

        return out;
    }

    private List<String> validacaoManutencaoProduto(ProdutosRequestDom produto){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(produto.getNome())){
            messages.add("Produto informado não possui nome!");
        }

        if(StringUtil.validarString(produto.getDescricao())){
            messages.add("Produto informado não possui descrição!");
        }

        return messages;
    }
}
