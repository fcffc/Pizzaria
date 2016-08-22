package br.com.fcffc.pizzaria.model.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;

@Repository
public interface IngredienteRepositorio extends CrudRepository<Ingrediente, Long> {

}
