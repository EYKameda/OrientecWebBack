package br.com.orientec.backend.adapter.out.persistence.entity.idempotency;
import br.com.orientec.backend.adapter.out.persistence.entity.idempotency.*;
import br.com.orientec.backend.adapter.out.persistence.repository.idempotency.*;
import br.com.orientec.backend.application.service.idempotency.*;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "idempotency_key")
public class IdempotencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 120)
    private String key;

    @Column(nullable = false, length = 100)
    private String request_hash;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private OffsetDateTime created_at;

    @Column(nullable = false)
    private OffsetDateTime updated_at;

    @Column(name = "response_body", columnDefinition = "text")
    private String response_body;

    private Integer response_status;

    protected IdempotencyRecord() {}

    public IdempotencyRecord(String key, String requestHash) {
        this.key = key;
        this.request_hash = requestHash;
        this.status = "PROCESSING";
        this.created_at = OffsetDateTime.now();
        this.updated_at = this.created_at;
    }

    public Long getId() { return id; }
    public String getKey() { return key; }
    public String getRequestHash() { return request_hash; }
    public String getStatus() { return status; }
    public OffsetDateTime getCreatedAt() { return created_at; }
    public OffsetDateTime getUpdatedAt() { return updated_at; }
    public String getResponseBody() { return response_body; }
    public Integer getResponseStatus() { return response_status; }

    public void complete(int status, String body) {
        this.status = "COMPLETED";
        this.response_status = status;
        this.response_body = body;
        this.updated_at = OffsetDateTime.now();
    }
}
