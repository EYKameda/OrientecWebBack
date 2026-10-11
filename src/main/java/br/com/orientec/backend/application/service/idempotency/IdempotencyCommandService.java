package br.com.orientec.backend.application.service.idempotency;

import org.springframework.stereotype.Service;

/** Compatibility facade kept for adapters that need the idempotency boundary. */
@Service
public class IdempotencyCommandService {
    private final IdempotencyService service;
    public IdempotencyCommandService(IdempotencyService service){this.service=service;}
    public boolean begin(String key,String fingerprint){return service.begin(key,fingerprint);}
    public void complete(String key,String fingerprint,int status,String body){service.complete(key,fingerprint,status,body);}
}
