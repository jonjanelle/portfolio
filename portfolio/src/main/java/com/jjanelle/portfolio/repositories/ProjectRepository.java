package com.jjanelle.portfolio.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jjanelle.portfolio.models.Project;

@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
  List<Project> findByTitleContainingIgnoreCase(@Param("title") String title);
  List<Project> findBySkillsContainingIgnoreCase(@Param("skills") String skills);
  List<Project> findBySkillsContainingIgnoreCaseAndTitleContainingIgnoreCase(@Param("skills") String skills, @Param("title") String title);
}