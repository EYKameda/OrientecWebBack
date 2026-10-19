package br.com.orientec.backend.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/security")
public class SecurityModeController {
    private final SecurityPolicyService policy;
    public SecurityModeController(SecurityPolicyService policy) { this.policy = policy; }
    @GetMapping("/mode")
    public Mode mode() { return new Mode(policy.isDevModeActive(), policy.isSecurityRequired()); }
    public record Mode(boolean orientecDevMode, boolean securityRequired) {}
}
