import { Component, OnInit } from '@angular/core';
import { Post } from './model/post.model';
import { UserService } from './features/user/service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  posts: Post[] = [];
  isLoading = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
  this.isLoading = true;

  setTimeout(() => {

    this.userService.getAllPosts().subscribe({
      next: (data: Post[]) => {
        this.posts = data;
        this.isLoading = false;
      }
    });

  }, 3000);

}

  
}