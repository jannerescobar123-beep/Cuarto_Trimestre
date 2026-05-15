import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.scss'
})
export class ListaComponent {

  @Input()
  tareas: string[] = [];

  @Output()
  eliminar = new EventEmitter<number>();

  eliminarTarea(index: number) {

    this.eliminar.emit(index);
  }
}
