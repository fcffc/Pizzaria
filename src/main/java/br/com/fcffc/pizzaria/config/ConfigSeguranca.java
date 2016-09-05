package br.com.fcffc.pizzaria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.fcffc.pizzaria.modelo.servicos.ServicoAutenticacao;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired private ServicoAutenticacao servicoAutenticacao;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(servicoAutenticacao)
			.passwordEncoder(encoder());
		
		
		/* Lgin e password na memória c/ Acesso PIZZARIA
		 * auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("PIZZARIA");*/		
	}

	// Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/app/pizzas/**", "/app/ingredientes/**").hasRole("PIZZARIA").anyRequest()
				.permitAll().and().formLogin().loginPage("/login.jsp").loginProcessingUrl("/autenticar")
				.defaultSuccessUrl("/app/pizzas").failureUrl("/login.jsp?semacesso=true").usernameParameter("usuario")
				.passwordParameter("senha").and().logout().logoutUrl("/sair").logoutSuccessUrl("/login.jsp?saiu=true");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[]args){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}

}
