import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: false,
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {
  
  users: User[]=[];

  constructor(private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // this.users =[{
    //   "user_id":1,
    //   "username":"John",
    //   "email":"john@gmail.com",
    //   "password_hash":"1234",
    //   "phone_number":"072222222"
    // }]
    this.getUsers();
  }

  private getUsers(){
    this.userService.getUserList().subscribe(data =>{
      this.users = data;
    });
  }

  updateUser(user_id:number){
    this.router.navigate(['update-user', user_id]);
  }

  deleteUser(user_id:number){
    this.userService.deleteUser(user_id).subscribe(data => {
      console.log(data);
      this.getUsers();
    })
  }

}
