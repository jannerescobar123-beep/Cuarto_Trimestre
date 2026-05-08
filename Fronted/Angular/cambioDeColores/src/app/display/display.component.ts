import { Component } from '@angular/core';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrl: './display.component.scss'
})
export class DisplayComponent {
colorActual: String = 'white';
textoColor: String = 'Blanco';

cambiarColor(color: string){
this.colorActual = color;
this.textoColor = color;
}
}
