import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BotonesComponent } from './calculadora/botones/botones.component';
import { CalculadoraComponent } from './pantalla/calculadora/calculadora.component';

@NgModule({
  declarations: [
    AppComponent,
    BotonesComponent,
    CalculadoraComponent
  ],
  imports: [
    BrowserModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
