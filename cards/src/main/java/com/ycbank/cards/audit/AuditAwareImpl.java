package com.ycbank.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl") // This annotation registers the class as a Spring bean with the name "auditAwareImpl"
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS"); // This is a placeholder. In a real application, you would retrieve the current user's ID or username.
    }
}
