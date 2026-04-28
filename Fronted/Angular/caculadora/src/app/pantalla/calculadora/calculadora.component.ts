import { Component } from '@angular/core';

@Component({
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.scss']
})
export class CalculadoraComponent {

  pantalla: string = '';
  resultado: number = 0;

  agregar(valor: string) {
    this.pantalla += valor;
  }

  calcular() {
    if (this.pantalla.includes('+')) {
      const numeros = this.pantalla.split('+');
      this.resultado = Number(numeros[0]) + Number(numeros[1]);
    }
    else if (this.pantalla.includes('-')) {
      const numeros = this.pantalla.split('-');
      this.resultado = Number(numeros[0]) - Number(numeros[1]);
    }
    else if (this.pantalla.includes('*')) {
      const numeros = this.pantalla.split('*');
      this.resultado = Number(numeros[0]) * Number(numeros[1]);
    }
    else if (this.pantalla.includes('/')) {
      const numeros = this.pantalla.split('/');
      this.resultado = Number(numeros[0]) / Number(numeros[1]);
    }
  }

  limpiar() {
    this.pantalla = '';
    this.resultado = 0;
  }
}
