import { Component, Input, input } from '@angular/core';
import { User } from '../../user.model';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrl: './user-card.component.scss'
})
export class UserCardComponent {
@Input() user: User;
}
