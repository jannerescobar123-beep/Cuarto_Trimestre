// importa librerias
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

// funcion deocradora, se ejecuta antes de lo que esta decorando / anotacion
@NgModule({
  declarations: [
    AppComponent // decaracion de componentes de este modulo
  ],
  imports: [
    BrowserModule, //  lista de modulos se van a importar, para que sirva en caulquier navegador, inserta servicios
  ],
  providers: [], // 
  bootstrap: [AppComponent]  // es una propiedad que solo va en el modulo principal, indica cual es el componente iniciar la app
})
export class AppModule {}
