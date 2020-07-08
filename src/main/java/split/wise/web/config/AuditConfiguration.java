package split.wise.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import split.wise.web.service.impl.AuditorAwareImpl;


@Configuration
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
