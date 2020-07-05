package com.jjanelle.portfolio;

import com.jjanelle.portfolio.services.JsonLoaderService;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.jjanelle.portfolio.models.Project;
import com.jjanelle.portfolio.repositories.ProjectRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProjectRepository projectRepository, JsonLoaderService jsonLoader) {
        return args -> {
            if (projectRepository.count() == 0) {
                log.info("Initializing database");
                try {
                    List<Project> projects = jsonLoader.readProjectsFile("projects.json");
                    projects.forEach(project -> projectRepository.save(project));
                } catch (IOException e) {
                    log.error("Error loading projects data file.", e);
                }
            }
        };
    }
}
