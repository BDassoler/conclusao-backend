package br.com.senac.apiconclusaobackend.useCases.enderecos.impl.repositorys;

import br.com.senac.apiconclusaobackend.entitys.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecosClientesRepository extends JpaRepository<Clientes, Long> {
}
