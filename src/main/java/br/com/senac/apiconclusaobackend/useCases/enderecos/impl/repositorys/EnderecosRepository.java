package br.com.senac.apiconclusaobackend.useCases.enderecos.impl.repositorys;

import br.com.senac.apiconclusaobackend.entitys.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {
}
