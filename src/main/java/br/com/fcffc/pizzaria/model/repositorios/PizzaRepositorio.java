package br.com.fcffc.pizzaria.model.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fcffc.pizzaria.modelo.entidades.Pizza;
import br.com.fcffc.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface PizzaRepositorio extends CrudRepository<Pizza, Long> {

	List<Pizza> findAllByProprietario(Pizzaria proprietario);

	Pizza findByIdAndProprietario(Long id, Pizzaria proprietario);

	List<Pizza> findAll();

}
