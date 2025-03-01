export class User {
    // user_id: number;          
    // username: string;         
    // email: string;            
    // password_hash: string;    
    // phone_number: string;  

    // constructor(
    //     public user_id: number = 0,
    //     public username: string = '',
    //     public email: string = '',
    //     public password_hash: string = '',
    //     public phone_number: string = "",
    // ){
    //     // this.user_id = user_id;
    //     // this.username = username;
    //     // this.email = email;
    //     // this.password_hash = password_hash;
    //     // this.phone_number = phone_number;
    // }

    user_id: number = 0; // Default value
  username: string = ''; // Default value
  email: string = ''; // Default value
  password_hash: string = ''; // Default value
  phone_number: string = ''; // Default value
  

  constructor(
    user_id?: number,
    username?: string,
    email?: string,
    password_hash?: string,
    phone_number?: string,
    
  ) {
    if (user_id) this.user_id = user_id;
    if (username) this.username = username;
    if (email) this.email = email;
    if (password_hash) this.password_hash = password_hash;
    if (phone_number) this.phone_number = phone_number;
    
  }
    
}
