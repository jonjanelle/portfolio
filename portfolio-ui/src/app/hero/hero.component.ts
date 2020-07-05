import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.scss']
})
export class HeroComponent implements OnInit {
  public isDark: boolean = false;

  constructor() { }

  ngOnInit(): void {
     setTimeout(() => {
       this.isDark = true;
     }, 250); 
  }

}
