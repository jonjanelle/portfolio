package com.jjanelle.portfolio.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project implements Serializable {
	public Project() {}
	
	public Project(String title, String link, String linkText, String github, String skills, String image,
			String imageAlt, String description, Boolean isActive) {
		super();
		this.title = title;
		this.link = link;
		this.linkText = linkText;
		this.github = github;
		this.skills = skills;
		this.image = image;
		this.imageAlt = imageAlt;
		this.description = description;
		this.isActive = isActive;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;
	private String link;
	private String linkText;
	private String github;
	private String skills;
	private String image;
	private String imageAlt;
	private String description;
	private Boolean isActive;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageAlt() {
		return imageAlt;
	}

	public void setImageAlt(String imageAlt) {
		this.imageAlt = imageAlt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Project [title=" + title + ", link=" + link + ", github=" + github + ", skills=" + skills
				+ ", description=" + description + ", isActive=" + isActive + "]";
	}
}