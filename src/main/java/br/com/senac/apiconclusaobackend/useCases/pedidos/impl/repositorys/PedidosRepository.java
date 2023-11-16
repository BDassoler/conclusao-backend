package br.com.senac.apiconclusaobackend.useCases.pedidos.impl.repositorys;

import br.com.senac.apiconclusaobackend.entitys.Pedidos;
import br.com.senac.apiconclusaobackend.entitys.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {


}
