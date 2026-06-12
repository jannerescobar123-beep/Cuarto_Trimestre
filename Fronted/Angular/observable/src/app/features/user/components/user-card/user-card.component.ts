import { Component, Input } from '@angular/core';
import { Post } from '../../../../model/post.model';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrl: './user-card.component.scss'
})
export class UserCardComponent {

  @Input()
  post!: Post;

}