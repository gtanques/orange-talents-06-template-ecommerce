package orange.com.br.mercadolivre.configuracao.validacao.controller;

import orange.com.br.mercadolivre.configuracao.validacao.dto.ErroGlobalResponse;
import orange.com.br.mercadolivre.configuracao.validacao.dto.ErroResponse;
import orange.com.br.mercadolivre.configuracao.validacao.exceptions.ExcecaoPersonalizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private final MessageSource messageSource;

    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<?> handle(MethodArgumentNotValidException exception) {
        List<Object> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroResponse erro = new ErroResponse(e.getField(), mensagem);
            dto.add(erro);
        });

        List<ObjectError> errors = exception.getBindingResult().getGlobalErrors();
        errors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroGlobalResponse error = new ErroGlobalResponse(mensagem);
            dto.add(error);
        });

        return dto;
    }

    @ExceptionHandler(ExcecaoPersonalizada.class)
    public ResponseEntity<?> naoEncontrado(ExcecaoPersonalizada e) {
        ErroGlobalResponse erroDto = new ErroGlobalResponse(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(erroDto);
    }

}
