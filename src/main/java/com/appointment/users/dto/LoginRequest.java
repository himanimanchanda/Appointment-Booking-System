package com.appointment.users.dto;

public class LoginRequest {
    private String email;
    private String password;
 //Getter
  public String getEmail(){
      return email;
  }
  public String getPassword(){
      return password;
  }
//  Setter
    public void setEmail(String MyEmail){
      this.email=MyEmail;
    }
    public void setPassword(String password){
      this.password=password;
    }
}
