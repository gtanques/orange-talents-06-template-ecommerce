package orange.com.br.mercadolivre.configuracao.seguranca.controller;

import orange.com.br.mercadolivre.configuracao.seguranca.dto.TokenRequest;
import orange.com.br.mercadolivre.configuracao.seguranca.dto.TokenResponse;
import orange.com.br.mercadolivre.configuracao.seguranca.service.GerenciadorDeTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticaController {

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final GerenciadorDeTokenService gerenciadorDeTokenService;

    public AutenticaController(AuthenticationManager authManager, GerenciadorDeTokenService gerenciadorDeTokenService) {
        this.authManager = authManager;
        this.gerenciadorDeTokenService = gerenciadorDeTokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid TokenRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = gerenciadorDeTokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
