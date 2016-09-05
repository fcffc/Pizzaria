package br.com.fcffc.pizzaria.model.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;
import br.com.fcffc.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface IngredienteRepositorio extends CrudRepository<Ingrediente, Long> {

	public List<Ingrediente> findAllByProprietario(Pizzaria proprietario);

	public Ingrediente findByIdAndProprietario(Long id, Pizzaria proprietario);
}
