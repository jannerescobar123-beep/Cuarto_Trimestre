import { Component } from '@angular/core';
import { User } from './features/user/user.model';
import { UserService } from './features/user/service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  users: User[] = [];
  isLoading = false;
  constructor(private userService:UserService){

  }

  onClick(){
    this.isLoading = true;
    this.userService.getAllUsers().subscribe({
      next: (data: User[]) => {
        this.users = data;
        this.isLoading = false;
      },
      error: () => {},
      complete: () => {},
    });
  }
}
