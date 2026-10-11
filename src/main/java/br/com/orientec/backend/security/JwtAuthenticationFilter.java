package br.com.orientec.backend.security;

import br.com.orientec.backend.application.service.sessao.SessaoUsuarioService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService; private final SessaoUsuarioService sessoes;
    public JwtAuthenticationFilter(JwtService jwtService,SessaoUsuarioService sessoes){this.jwtService=jwtService;this.sessoes=sessoes;}
    @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException{
        String h=request.getHeader("Authorization");
        if(h!=null&&h.startsWith("Bearer ")){
            try{
                var p=jwtService.parse(h.substring(7)); sessoes.validarEAtualizar(p.sessaoId());
                var authorities=p.permissoes().stream().map(x->new SimpleGrantedAuthority("PERM_"+x)).toList();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(p,null,authorities));
            }catch(SessaoUsuarioService.SessionAccessException ex){SecurityContextHolder.clearContext(); writeSessionError(response,ex.code,ex.getMessage()); return;}
            catch(RuntimeException ex){SecurityContextHolder.clearContext();}
        }
        chain.doFilter(request,response);
    }
    private void writeSessionError(HttpServletResponse r,String code,String message)throws IOException{
        r.setStatus(401); r.setContentType("application/problem+json;charset=UTF-8");
        r.getWriter().write("{\"type\":\"about:blank\",\"title\":\"Sessão expirada\",\"status\":401,\"code\":\""+code+"\",\"detail\":\""+message.replace("\\","\\\\").replace("\"","\\\"")+"\"}");
    }
}
