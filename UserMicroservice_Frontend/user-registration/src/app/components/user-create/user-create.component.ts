import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { error } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-create',
  standalone: false,
  templateUrl: './user-create.component.html',
  styleUrl: './user-create.component.css'
})
export class UserCreateComponent {
// onSubmit() {
// throw new Error('Method not implemented.');
// }
  user: User = new User();

  constructor(private userService: UserService,
    private router: Router
  ){}

  ngOnInit(): void {
  }

  saveUser(){
    this.userService.createUser(this.user).subscribe(data =>{
      console.log(data);
      this.goToUserList();
    },
  error => console.log(this.user) );
  }

  goToUserList(){
    this.router.navigate(['/users']);
   
  }

  onSubmit(){
    console.log(this.user);
    this.saveUser();
  }

}
