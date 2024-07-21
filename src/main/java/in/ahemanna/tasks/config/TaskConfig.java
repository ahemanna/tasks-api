package in.ahemanna.tasks.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class TaskConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.registerModule(new Jsr310Module(Jsr310ModuleConfig
                .builder()
                .dateTimeOffsetFormatter(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .build()));
        return mapper;
    }
}
