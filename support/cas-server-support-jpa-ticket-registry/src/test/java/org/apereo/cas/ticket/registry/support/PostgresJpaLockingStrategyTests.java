package org.apereo.cas.ticket.registry.support;

import org.apereo.cas.util.junit.EnabledIfPortOpen;

import org.junit.jupiter.api.Tag;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

/**
 * Unit test for {@link JpaLockingStrategy}.
 *
 * @author Marvin S. Addison
 * @since 3.0.0
 */
@TestPropertySource(properties = {
    "cas.ticket.registry.jpa.user=postgres",
    "cas.ticket.registry.jpa.password=password",
    "cas.ticket.registry.jpa.driver-class=org.postgresql.Driver",
    "cas.ticket.registry.jpa.url=jdbc:postgresql://localhost:5432/tickets",
    "cas.ticket.registry.jpa.dialect=org.hibernate.dialect.PostgreSQL95Dialect"
})
@EnabledIfPortOpen(port = 5432)
@Tag("Unknown")
@DirtiesContext
public class PostgresJpaLockingStrategyTests extends JpaLockingStrategyTests {
}