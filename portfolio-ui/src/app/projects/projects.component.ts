import { Component, OnInit } from '@angular/core';
import { PortfolioService } from '../services/portfolio.service';
import Project from '../models/project';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss'],
})
export class ProjectsComponent implements OnInit {
  private _isLoading: boolean;
  private _projects: Project[] = [];

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this._isLoading = true;
    this.portfolioService
      .getProjects()
      .pipe(finalize(() => (this._isLoading = false)))
      .subscribe(
        (projects) => {
          this._projects = projects;
        },
        (err) => {
          console.error('Error loading project data', err);
        }
      );
  }

  public get isLoading(): boolean {
    return this._isLoading;
  }

  public get projects(): Project[] {
    return this._projects;
  }
}
