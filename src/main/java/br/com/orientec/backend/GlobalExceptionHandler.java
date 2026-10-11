package br.com.orientec.backend;

import br.com.orientec.backend.application.service.sessao.SessaoEstacaoService;
import br.com.orientec.backend.application.service.sessao.SessaoUsuarioService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidation(MethodArgumentNotValidException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);pd.setTitle("Dados inválidos");pd.setDetail(ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).reduce((a,b)->a+"; "+b).orElse("Requisição inválida"));return pd;}
    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraint(ConstraintViolationException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);pd.setTitle("Violação de validação");pd.setDetail(ex.getMessage());return pd;}
    @ExceptionHandler(BadCredentialsException.class)
    ProblemDetail handleCredentials(BadCredentialsException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);pd.setTitle("Credenciais inválidas");pd.setDetail(ex.getMessage());return pd;}
    @ExceptionHandler(AccessDeniedException.class)
    ProblemDetail handleDenied(AccessDeniedException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.FORBIDDEN);pd.setTitle("Acesso negado");pd.setDetail("O usuário não possui a permissão necessária.");return pd;}
    @ExceptionHandler(SessaoEstacaoService.StationAccessException.class)
    ProblemDetail handleStation(SessaoEstacaoService.StationAccessException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.FORBIDDEN);pd.setTitle("Estação não autorizada");pd.setDetail(ex.getMessage());pd.setProperty("code",ex.code);return pd;}
    @ExceptionHandler(SessaoUsuarioService.SessionAccessException.class)
    ProblemDetail handleSession(SessaoUsuarioService.SessionAccessException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);pd.setTitle("Sessão expirada");pd.setDetail(ex.getMessage());pd.setProperty("code",ex.code);return pd;}
    @ExceptionHandler(IllegalArgumentException.class)
    ProblemDetail handleIllegalArgument(IllegalArgumentException ex){ProblemDetail pd=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);pd.setTitle("Operação inválida");pd.setDetail(ex.getMessage());return pd;}
}
