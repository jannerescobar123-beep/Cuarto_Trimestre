import { Component } from '@angular/core';

type Estado = 'pendiente' | 'cargando' | 'exito';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.scss'
})
export class ListaComponent {
  estado: Estado = 'pendiente';
  elementos: any[] = [
    { id: 1, nombre: 'SIUUUUUUUUUUUUUUUUUU' },
    { id: 2, nombre: 'El bichooo' },
    { id: 3, nombre: 'La pulgaaaa' },
    { id: 4, nombre: 'Solo db!!!!!!' },
    { id: 5, nombre: 'A - E - I - O - U' }
  ];

  manejarBoton(): void {
    if (this.estado === 'pendiente') {
      this.estado = 'cargando';
      setTimeout(() => {
        this.estado = 'exito';
      }, 2000);
    } else if (this.estado === 'exito') {
      this.elementos = [];
      this.estado = 'pendiente';
    }
  }
}