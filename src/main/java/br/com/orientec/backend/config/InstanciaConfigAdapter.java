package br.com.orientec.backend.config;

import br.com.orientec.backend.application.port.out.instancia.InstanciaConfigPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InstanciaConfigAdapter implements InstanciaConfigPort {
    private final Long sedeCodigo;
    public InstanciaConfigAdapter(@Value("${orientec.instancia.sede-codigo:}") String value) {
        if(value==null||value.isBlank()){this.sedeCodigo=null;return;}
        try{this.sedeCodigo=Long.valueOf(value);}catch(NumberFormatException e){throw new IllegalStateException("ORIENTEC_SEDE_CODIGO inválido: "+value,e);}
    }
    @Override public Long getSedeCodigo(){return sedeCodigo;}
}
