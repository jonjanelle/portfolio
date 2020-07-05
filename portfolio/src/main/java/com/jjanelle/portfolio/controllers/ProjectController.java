package com.jjanelle.portfolio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jjanelle.portfolio.models.Project;
import com.jjanelle.portfolio.repositories.ProjectRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class ProjectController {

  private final ProjectRepository repository;

  ProjectController(ProjectRepository repository) {
    this.repository = repository;
  }
  
  @CrossOrigin
  @GetMapping("/projects")
  List<Project> all() {
	return (List<Project>)repository.findAll();
  }
  
  @CrossOrigin
  @GetMapping("/projects/search")
  List<Project> search(@RequestParam(required = false) String skill, @RequestParam(required = false) String title)
  {
      if (skill == null && title != null)
        return (List<Project>)repository.findByTitleContainingIgnoreCase(title);
      else if (skill != null && title == null)
        return (List<Project>)repository.findBySkillsContainingIgnoreCase(skill);
      else if (skill != null && title != null)
        return (List<Project>)repository.findBySkillsContainingIgnoreCaseAndTitleContainingIgnoreCase(skill, title);
      else 
        return new ArrayList<>();
  }
}