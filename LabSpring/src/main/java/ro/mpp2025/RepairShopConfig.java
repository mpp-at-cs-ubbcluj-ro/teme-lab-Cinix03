package ro.mpp2025;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.mpp2025.repository.ComputerRepairRequestRepository;
import ro.mpp2025.repository.ComputerRepairedFormRepository;
import ro.mpp2025.repository.jdbc.ComputerRepairRequestJdbcRepository;
import ro.mpp2025.repository.jdbc.ComputerRepairedFormJdbcRepository;
import ro.mpp2025.services.ComputerRepairServices;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfig {
    @Bean
    Properties getProps() {
        Properties props = new Properties();
        try{
            System.out.println("Searching bd.config in directory " + ((new File (".")).getAbsolutePath()));
            props.load(new FileReader ("bd.config"));
        } catch (IOException e) {
            System.err.println("Error reading bd.config" + e.getMessage());
        }
        return props;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
        return new ComputerRepairRequestJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
        return new ComputerRepairedFormJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairServices services(){
        return new ComputerRepairServices(requestsRepo(), formsRepo());
    }

}
