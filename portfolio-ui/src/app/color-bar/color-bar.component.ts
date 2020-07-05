import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-color-bar',
  templateUrl: './color-bar.component.html',
  styleUrls: ['./color-bar.component.scss']
})
export class ColorBarComponent implements OnInit {
  private boxCoords = [];
  private boxDivs = null;
  constructor() { 
    this.updateColor = this.updateColor.bind(this);
  }

  ngOnInit(): void {
    this.createDivs(40);
    window.document.addEventListener('mousemove', (event) =>
      this.updateColor(event)
    );
  }

  updateColor(event: any) {
    let mPos = [event.clientX, event.clientY];

    if (this.boxDivs == null) {
      this.boxDivs = document.getElementsByClassName('color-square');
    }

    if (this.boxCoords.length == 0) {
      this.getBoxCoords(40);
    }

    for (let i = 0; i < this.boxDivs.length; i++) {
      var offsets = this.boxCoords[i];
      const newCol = this.getColor(mPos, [offsets.left, offsets.top], 6);
      this.boxDivs[i].style.background =
        'rgb(' + newCol[0] + ',' + newCol[1] + ',' + newCol[2] + ')';
    }
  }

  private getBoxCoords(size: number): void {
    for (var i = 0; i < this.boxDivs.length; i++) {
      this.boxCoords.push(this.boxDivs[i].getBoundingClientRect());
    }
  }

  private createDivs(size: number): void {
    let h = 250;
    let w = window.innerWidth; // returns width of browser viewport
    for (let i = 0; i < Math.floor(w / size); i++) {
      for (let j = 0; j < Math.floor(h / size); j++) {
        let div = document.createElement('div');
        div.style.width = size + 'px';
        div.style.height = size + 'px';
        div.style.float = 'left';
        div.style.zIndex = '2';
        div.style.borderRadius = '0';
        div.style.margin = '0';
        div.className = 'color-square';
        document.getElementById('color-container').appendChild(div);
      }
    }
  }

  private getDist(p1: number[], p2: number[]): number {
    return ((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2) ** 0.5;
  }

  private getColor(center: number[], point: number[], scale: number): number[] {
    const d = Math.floor(this.getDist(center, point) / scale);
    return [255 - (d % 255), 0, d % 255];
  }

}
