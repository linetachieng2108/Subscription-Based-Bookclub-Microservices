import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-update-user',
  standalone: false,
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent implements OnInit {
  user_id!: number;
  user: User = new User();

  constructor(private userService: UserService, private route:ActivatedRoute, private router: Router) { }

  

  ngOnInit(): void {
    // this.user = new User();
    this.user_id = this.route.snapshot.params['user_id'];
    // we need to retrieve user from the route through activated route
      this.userService.getUserById(this.user_id).subscribe(data =>{
        this.user = data;
      }, error => console.log(error));
  }

  onSubmit(){
    this.userService.updateUser(this.user_id, this.user).subscribe( data =>{
      this.goToUserList();
    },error => console.log(error));
  }

  goToUserList(){
    this.router.navigate(['/users']);
   
  }

  // updateUser(){
  //   this.userService.updateUser(this.user)
  //   .subscribe(data =>{
  //     console.log(data);
  //     this.user = new User();
  //     this.goToUserList();

  //   },error => console.log(error))
  // }




}
