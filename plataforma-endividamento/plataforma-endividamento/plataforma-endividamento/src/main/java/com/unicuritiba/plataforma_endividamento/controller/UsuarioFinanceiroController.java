package com.unicuritiba.plataforma_endividamento.controller;

import com.unicuritiba.plataforma_endividamento.model.UsuarioFinanceiro;
import com.unicuritiba.plataforma_endividamento.service.UsuarioFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // permite acesso do frontend
public class UsuarioFinanceiroController {

    @Autowired
    private UsuarioFinanceiroService service;

    // GET /api/usuarios
    @GetMapping
    public List<UsuarioFinanceiro> listarTodos() {
        return service.listarTodos();
    }

    // GET /api/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        UsuarioFinanceiro usuario = service.buscarPorId(id);
        if (usuario == null) return ResponseEntity.notFound().build();

        String recomendacao = service.calcularNivelEndividamento(usuario);

        return ResponseEntity.ok().body(new UsuarioComRecomendacao(usuario, recomendacao));
    }

    // POST /api/usuarios
    @PostMapping
    public UsuarioFinanceiro criar(@RequestBody UsuarioFinanceiro usuario) {
        return service.salvar(usuario);
    }

    // PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioFinanceiro> atualizar(@PathVariable Long id, @RequestBody UsuarioFinanceiro novoUsuario) {
        UsuarioFinanceiro atualizado = service.atualizar(id, novoUsuario);
        if (atualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Classe interna para resposta personalizada
    static class UsuarioComRecomendacao {
        public UsuarioFinanceiro usuario;
        public String recomendacao;

        public UsuarioComRecomendacao(UsuarioFinanceiro usuario, String recomendacao) {
            this.usuario = usuario;
            this.recomendacao = recomendacao;
        }
    }
}

