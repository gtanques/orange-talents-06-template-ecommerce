package orange.com.br.mercadolivre.configuracao.seguranca;

import orange.com.br.mercadolivre.configuracao.seguranca.service.AutenticaUsuarioService;
import orange.com.br.mercadolivre.configuracao.seguranca.service.GerenciadorDeTokenService;
import orange.com.br.mercadolivre.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class ConfiguracoesDeSeguranca extends WebSecurityConfigurerAdapter {

    private final AutenticaUsuarioService autenticaUsuarioService;
    private final GerenciadorDeTokenService geradorGerenciadorDeTokenService;
    private final UsuarioRepository usuarioRepository;
    private final AutenticaTokenViaFilter filtroAutenticacao;

    @Autowired
    public ConfiguracoesDeSeguranca(AutenticaUsuarioService autenticacaoService,
                                    GerenciadorDeTokenService geradorGerenciadorDeTokenService,
                                    UsuarioRepository usuarioRepository,
                                    AutenticaTokenViaFilter filtroAutenticacao) {
        this.autenticaUsuarioService = autenticacaoService;
        this.geradorGerenciadorDeTokenService = geradorGerenciadorDeTokenService;
        this.usuarioRepository = usuarioRepository;
        this.filtroAutenticacao = filtroAutenticacao;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticaUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/novo").permitAll()
                .antMatchers(HttpMethod.POST, "/notas-fiscais").permitAll()
                .antMatchers(HttpMethod.POST, "/ranking").permitAll()
            .anyRequest().authenticated()
            .and()
                .cors()
            .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(filtroAutenticacao, UsernamePasswordAuthenticationFilter.class)
            .headers().frameOptions().disable();
    }

}
