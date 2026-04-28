import { Component } from '@angular/core';
// define el componenete 
@Component({
  selector: 'app-root', // este es un componente conjunto de logica, estructura 
  templateUrl: './app.component.html', // direcion de archivo html donde va a estar su parte de estructura 
  styleUrl: './app.component.scss' // direcion de archivo scss
})
export class AppComponent {
  title = 'fundamentos';
}
