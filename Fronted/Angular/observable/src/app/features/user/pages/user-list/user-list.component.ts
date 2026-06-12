import { Component, Input } from '@angular/core';
import { User } from '../../user.model';
import { Post } from '../../../../model/post.model';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss'
})
export class UserListComponent {
@Input() posts: Post[];}
