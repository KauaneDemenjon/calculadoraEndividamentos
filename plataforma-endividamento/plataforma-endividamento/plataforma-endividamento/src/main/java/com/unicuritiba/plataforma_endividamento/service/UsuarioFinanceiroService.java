package com.unicuritiba.plataforma_endividamento.service;


import com.unicuritiba.plataforma_endividamento.model.UsuarioFinanceiro;
import com.unicuritiba.plataforma_endividamento.model.repository.UsuarioFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioFinanceiroService {

    @Autowired
    private UsuarioFinanceiroRepository repository;

    public List<UsuarioFinanceiro> listarTodos() {
        return repository.findAll();
        // select * from usuario where id = 2;
    }

    public UsuarioFinanceiro buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UsuarioFinanceiro salvar(UsuarioFinanceiro usuario) {
        return repository.save(usuario);
    }

    public UsuarioFinanceiro atualizar(Long id, UsuarioFinanceiro novoUsuario) {
        UsuarioFinanceiro existente = buscarPorId(id);
        if (existente == null) return null;

        existente.setNome(novoUsuario.getNome());
        existente.setRendaMensal(novoUsuario.getRendaMensal());
        existente.setDespesasFixas(novoUsuario.getDespesasFixas());
        existente.setTotalDividas(novoUsuario.getTotalDividas());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public String calcularNivelEndividamento(UsuarioFinanceiro usuario) {
        if (usuario.getRendaMensal() <= 0) {
            return "Renda mensal inválida. Por favor, cadastre uma renda positiva.";
        }
        if (usuario.getTotalDividas() < 0) {
            return "Total de dívidas inválido. Verifique os valores informados.";
        }

        double percentual = (usuario.getTotalDividas() / usuario.getRendaMensal()) * 100;

        String situacao;
        String sugestaoPagamento;

        if (percentual <= 30) {
            situacao = "🟢 Situação controlada.";
            sugestaoPagamento = "Recomenda-se pagamento à vista, se possível, para evitar juros.";
        } else if (percentual <= 60) {
            situacao = "🟡 Atenção: alto nível de endividamento.";
            sugestaoPagamento = "Priorize quitar as dívidas maiores ou com juros mais altos. Avalie parcelamentos.";
        } else if (percentual <= 100) {
            situacao = "🔴 Crítico: comprometimento total da renda.";
            sugestaoPagamento = "Busque renegociar dívidas e, se possível, evitar novas. Considere parcelamentos ou acordos.";
        } else {
            situacao = "🚨 Emergência Financeira!";
            sugestaoPagamento = "Seu endividamento ultrapassa sua renda! Procure negociação urgente, revisão de gastos, e, se necessário, auxílio financeiro especializado.";
        }

        return situacao + " Nível de endividamento: " + String.format("%.2f", percentual) + "%. " + sugestaoPagamento;
    }
}

