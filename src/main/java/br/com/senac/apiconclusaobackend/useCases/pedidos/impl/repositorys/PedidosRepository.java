package br.com.senac.apiconclusaobackend.useCases.pedidos.impl.repositorys;

import br.com.senac.apiconclusaobackend.entitys.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
}
