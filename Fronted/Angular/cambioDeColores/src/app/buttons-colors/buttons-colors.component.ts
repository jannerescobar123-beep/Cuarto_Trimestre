import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-buttons-colors',
  templateUrl: './buttons-colors.component.html',
  styleUrl: './buttons-colors.component.scss',
})
export class ButtonsColorsComponent {
  @Output()
  eventoColor = new EventEmitter<string>();

  enviarColor(color: string) {

    this.eventoColor.emit(color);
  }
}
