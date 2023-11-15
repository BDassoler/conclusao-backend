package br.com.senac.apiconclusaobackend.useCases.produtos.impl.repositorys;

import br.com.senac.apiconclusaobackend.entitys.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
}