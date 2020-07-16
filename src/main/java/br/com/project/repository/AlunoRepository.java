package br.com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.repository.data.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>  {

}
