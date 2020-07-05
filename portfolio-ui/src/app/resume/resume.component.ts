import { Component, OnInit } from '@angular/core';
import { PortfolioService } from '../services/portfolio.service';
import { LabeledIcon } from '../models/labeledIcon';
import { finalize, flatMap } from 'rxjs/operators';
import { forkJoin } from 'rxjs';
import { LinkIcon } from '../models/linkIcon';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.scss'],
})
export class ResumeComponent implements OnInit {
  private _isLoading: boolean;
  private _experienceIcons: LabeledIcon[];
  private _resumeLinks: LinkIcon[];

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this._isLoading = true;
    forkJoin(this.portfolioService.getLanguageIcons(), this.portfolioService.getResumeIcons())
    .pipe(finalize(() => this._isLoading = false))  
    .subscribe(results => {
      this._experienceIcons = results[0];
      this._resumeLinks = results[1];
    }, (err) => {
      console.error('Error loading experience icons', err);
    });
  }

  public get experienceIcons(): LabeledIcon[] {
    return this._experienceIcons;
  }

  public get resumeLinks(): LinkIcon[] {
    return this._resumeLinks;
  }

  public get isLoading(): boolean {
    return this._isLoading;
  }
}