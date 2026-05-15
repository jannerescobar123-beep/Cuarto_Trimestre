import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrl: './formulario.component.scss'
})
export class FormularioComponent {

  tareaNueva: string = '';

  @Output()
  tareaCreada = new EventEmitter<string>();

  guardar() {

    this.tareaCreada.emit(this.tareaNueva);

    this.tareaNueva = '';
  }
}
