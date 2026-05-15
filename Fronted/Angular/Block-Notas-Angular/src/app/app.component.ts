import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
   tareas: string[] = [];

  guardarTarea(tarea: string) {

    if (tarea.trim() !== '') {

      this.tareas.push(tarea);
    }
  }

  borrarTarea(index: number) {

    this.tareas.splice(index, 1);
  }
}
