package com.praxsoft.SrvHTTP03.services;

import com.praxsoft.SrvHTTP03.domain.Artigo;
import com.praxsoft.SrvHTTP03.repository.ArtigosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService {

    @Autowired
    private ArtigosRepository artigosRepository;

    public List<Artigo> listar() {
        return artigosRepository.findAll();
    }

    public List<Artigo> buscarTitulo(String titulo) {
        return artigosRepository.findByTituloIgnoreCase(titulo);
    }

    public List<Artigo> buscarTituloContem(String titulo) {
        return artigosRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Artigo> buscarAutor(String autor) {
        return artigosRepository.findByAutorIgnoreCase(autor);
    }

    public Artigo salvarArtigo(Artigo artigo) {
        artigo.setId(null);
        return artigosRepository.save(artigo);
    }

    public Artigo atualizarArtigo(Artigo artigo, long id) {
        artigo.setId(id);
        return artigosRepository.save(artigo);
    }

    public boolean apagarArtigo(long id) {
        try {
            artigosRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
