package br.com.fcffc.pizzaria.model.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.fcffc.pizzaria.modelo.entidades.Pizzaria;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Pizzaria, Long> {

	public Pizzaria findOneByLogin(String login);

}
