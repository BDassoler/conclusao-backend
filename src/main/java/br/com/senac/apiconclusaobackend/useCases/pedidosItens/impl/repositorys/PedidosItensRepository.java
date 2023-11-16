package br.com.senac.apiconclusaobackend.useCases.pedidosItens.impl.repositorys;

import br.com.senac.apiconclusaobackend.entitys.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PedidosItensRepository extends JpaRepository<PedidosItens, Long> {

   @Query("Select a from pedido_item a where a.pedido_id =:pedidoId")
   List<PedidosItens> carregarPedidoItensByPedidoId(@Param("pedidoId") Long pedidoId);

}
