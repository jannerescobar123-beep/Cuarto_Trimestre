import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-botones',
  templateUrl: './botones.component.html',
  styleUrls: ['./botones.component.scss']
})
export class BotonesComponent {

  @Output() numeroPresionado = new EventEmitter<string>();
  @Output() calcularOperacion = new EventEmitter<void>();
  @Output() limpiarPantalla = new EventEmitter<void>();

  presionar(valor: string) {
    this.numeroPresionado.emit(valor);
  }

  calcular() {
    this.calcularOperacion.emit();
  }

  limpiar() {
    this.limpiarPantalla.emit();
  }
}
