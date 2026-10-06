package com.poo.atividade_refac.controllers;

import com.poo.atividade_refac.contracts.LivroOperations;
import com.poo.atividade_refac.entities.Livro;
import com.poo.atividade_refac.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController implements LivroOperations {
    @Autowired
    private LivroRepository livroRepository;

    @Override
    @GetMapping
    public List<Livro> listar() {

        System.out.println("Listando livros");

        return livroRepository.findAll();
    }

    @Override
    @PostMapping
    public Livro cadastrar(
            @RequestBody Livro livro) {

        // validação
        if (livro.getTitulo() == null
                || livro.getTitulo().isBlank()) {
            throw new RuntimeException(
                    "Título obrigatório");
        }

        if (livro.getPreco() <= 0) {
            throw new RuntimeException(
                    "Preço inválido");
        }

        // regra de negócio
        if ("ROMANCE".equals(
                livro.getCategoria())) {

            livro.setPreco(
                    livro.getPreco() * 0.9);

        } else if ("TECNOLOGIA".equals(
                livro.getCategoria())) {

            livro.setPreco(
                    livro.getPreco() * 0.8);

        } else if ("INFANTIL".equals(
                livro.getCategoria())) {

            livro.setPreco(
                    livro.getPreco() * 0.7);

        } else if ("HQ".equals(
                livro.getCategoria())) {

            livro.setPreco(
                    livro.getPreco() * 0.6);
        }

        // log
        System.out.println(
                "Salvando livro: "
                        + livro.getTitulo());

        // persistência
        livroRepository.save(livro);

        // relatório
        gerarRelatorio();

        // email
        enviarEmailPromocional();

        return livro;
    }

    @Override
    @PutMapping("/{id}")
    public Livro atualizar(
            @PathVariable Long id,
            @RequestBody Livro livro) {

        Livro livroBanco =
                livroRepository.findById(id)
                        .orElseThrow();

        livroBanco.setTitulo(
                livro.getTitulo());

        livroBanco.setAutor(
                livro.getAutor());

        livroBanco.setPreco(
                livro.getPreco());

        livroBanco.setCategoria(
                livro.getCategoria());

        return livroRepository.save(
                livroBanco);
    }

    @Override
    @DeleteMapping("/{id}")
    public void remover(
            @PathVariable Long id) {

        Livro livro =
                livroRepository.findById(id)
                        .orElseThrow();

        if ("RARO".equals(
                livro.getCategoria())) {

            throw new RuntimeException(
                    "Livros raros não podem ser removidos");
        }

        livroRepository.delete(livro);
    }

    @Override
    public void gerarRelatorio() {

        List<Livro> livros =
                livroRepository.findAll();

        System.out.println(
                "Quantidade de livros: "
                        + livros.size());
    }

    @Override
    public void exportarPdf() {

        throw new UnsupportedOperationException(
                "Não implementado");
    }

    @Override
    public void enviarEmailPromocional() {

        System.out.println(
                "Email promocional enviado");
    }

    @Override
    public void sincronizarComERP() {

        throw new UnsupportedOperationException(
                "Não implementado");
    }

    @Override
    public void gerarEtiqueta() {

        throw new UnsupportedOperationException(
                "Não implementado");
    }
}
