import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';

const routes: Routes = [
  // Route for the user list
  {
    path: 'users', component: UserListComponent,
  },
  {
    path: 'user-create', component: UserCreateComponent,
  },
  {
    path:'update-user/:user_id', component: UpdateUserComponent
  },
  // Default route
  {
    path: '', redirectTo: '/users', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
