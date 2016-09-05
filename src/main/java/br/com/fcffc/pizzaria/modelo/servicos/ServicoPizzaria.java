package br.com.fcffc.pizzaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.fcffc.pizzaria.model.repositorios.PizzariaRepositorio;
import br.com.fcffc.pizzaria.modelo.entidades.Pizzaria;

@Service
public class ServicoPizzaria {
	
	@Autowired private PizzariaRepositorio pizzariaRepositorio;
	
	public Pizzaria getPizzariaLogada(){
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		
		if(autenticado == null) throw new AuthenticationCredentialsNotFoundException("Usuário não logado.");
		
		UserDetails usuarioLogado = (UserDetails) autenticado.getPrincipal();
		return pizzariaRepositorio.findOneByLogin(usuarioLogado.getUsername());
	}

}
