package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"controller", "service", "dao"})
public class SpringConfig {
    
    @Bean
    public IClienteDAO clienteDAO() {
        return new ClienteDAOImpl();
    }
    
    // Outros beans podem ser definidos aqui
}
