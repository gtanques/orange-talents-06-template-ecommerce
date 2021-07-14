package orange.com.br.mercadolivre.configuracao.seguranca;

import orange.com.br.mercadolivre.configuracao.seguranca.service.GerenciadorDeTokenService;
import orange.com.br.mercadolivre.usuarios.Usuario;
import orange.com.br.mercadolivre.usuarios.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class AutenticaTokenViaFilter extends OncePerRequestFilter {

    private final GerenciadorDeTokenService gerenciadorDeTokenService;
    private final UsuarioRepository usuarioRepository;

    public AutenticaTokenViaFilter(GerenciadorDeTokenService gerenciadorDeTokenService, UsuarioRepository usuarioRepository) {
        this.gerenciadorDeTokenService = gerenciadorDeTokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        if(Objects.nonNull(token) && !token.isEmpty()){
            boolean valido = gerenciadorDeTokenService.validaToken(token);
            if (valido) {
                autenticarCliente(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idUsuario = gerenciadorDeTokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring(7);
    }

}
