package com.unicuritiba.plataforma_endividamento.model.repository;
import com.unicuritiba.plataforma_endividamento.model.UsuarioFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioFinanceiroRepository extends JpaRepository<UsuarioFinanceiro, Long> {
}
