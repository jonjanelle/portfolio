import { Injectable } from '@angular/core';
import Project from '../models/project';
import { Observable, of } from 'rxjs';
import { LabeledIcon } from '../models/labeledIcon';
import { LinkIcon } from '../models/linkIcon';
import { HttpClient } from '@angular/common/http';
import { ContactMessage } from '../models/contactMessage';

@Injectable({
  providedIn: 'root',
})
export class PortfolioService {
  private readonly serviceBaseUrl: string = 'http://portfolio-env.eba-qyngfppc.ca-central-1.elasticbeanstalk.com';

  private readonly _resumeIcons: LinkIcon[] = [
    {name: "Resume", icon: "fa-id-card", link: "assets/resume_janelle_jon.pdf"},
    {name: "GitHub", icon: "fa-github", link: "https://github.com/jonjanelle"},
    {name: "LinkedIn", icon: "fa-linkedin-square", link: "https://www.linkedin.com/in/jon-janelle-93174763/"}
  ];

  private readonly _languageIcons: LabeledIcon[] = [
    { name: 'C#/.NET Core', icon: 'devicon-csharp-plain colored' },
    { name: 'Java/Spring Boot', icon: 'devicon-java-plain-wordmark colored' },
    { name: 'Ruby/Rails', icon: 'devicon-rails-plain-wordmark colored' },
    { name: 'JavaScript', icon: 'devicon-javascript-plain colored' },
    { name: 'Angular', icon: 'devicon-angularjs-plain colored' },
    { name: 'React', icon: 'devicon-react-original-wordmark colored' },
    { name: 'HTML5', icon: 'devicon-html5-plain-wordmark colored' },
    { name: 'CSS3', icon: 'devicon-css3-plain-wordmark colored' },
    { name: 'SASS', icon: 'devicon-sass-original colored' },
    { name: 'Python', icon: 'devicon-python-plain-wordmark colored' },
    { name: 'Laravel', icon: 'devicon-laravel-plain colored' },
    { name: 'PHP', icon: 'devicon-php-plain colored' },
    { name: 'git', icon: 'devicon-git-plain-wordmark colored' },
    { name: 'Linux', icon: 'devicon-linux-plain colored' },
    { name: 'PostgreSQL', icon: 'devicon-postgresql-plain-wordmark colored' },
    { name: 'MySQL', icon: 'devicon-mysql-plain-wordmark colored' },
    { name: 'Redis', icon: 'devicon-redis-plain colored' },
  ];


  constructor(
    private http: HttpClient
  ) {}

  public getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.serviceBaseUrl}/projects`);
  }

  public getLanguageIcons(): Observable<LabeledIcon[]> {
    return of(this._languageIcons);
  }

  public getResumeIcons(): Observable<LinkIcon[]> {
    return of(this._resumeIcons);
  }

  public sendEmail(contactMessage: ContactMessage): Observable<ContactMessage> {
    return this.http.post<ContactMessage>(`${this.serviceBaseUrl}/email`, contactMessage);
  }
}
