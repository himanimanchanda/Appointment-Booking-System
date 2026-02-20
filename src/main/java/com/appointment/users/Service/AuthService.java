package com.appointment.users.Service;

import com.appointment.users.Repository.OrganisationRepository;
import com.appointment.users.Repository.UserRepository;
import com.appointment.users.dto.UserRegisterRequest;
import com.appointment.users.entity.Role;
import com.appointment.users.entity.User;
import com.appointment.users.exception.AccountDisabledException;
import com.appointment.users.exception.InvalidCredentialsException;
import com.appointment.users.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository usp;
    private final PasswordEncoder pse;
    private final OrganisationRepository orr;
    public AuthService(UserRepository userRepository,PasswordEncoder paswordEncoder, OrganisationRepository orr){
        this.usp= userRepository;
        this.pse=paswordEncoder;
        this.orr=orr;
    }

    public User login(String email, String password){
        System.out.println(password);
        User userdata=usp.findByEmail(email).orElseThrow(()->new InvalidCredentialsException("Invalid credentials"));

//        if(!pse.matches(password, userdata.getPassword())) throw new InvalidCredentialsException("Invalid Credentials");
        if(!userdata.isIsactive()){
//            System.out.println(userdata.isActive());
            throw new AccountDisabledException();
        }
        return userdata;
    }

    public User signup(UserRegisterRequest urr){
        usp.findByEmail(urr.getEmail()).ifPresent(user ->{
            throw new UserAlreadyExistsException("Email already existed");
        } );
        User userdata=new User();
        userdata.setName(urr.getName());
        userdata.setEmail(urr.getEmail());
        userdata.setPassword(pse.encode(urr.getPassword()));
        userdata.setRole(Role.USER);
        userdata.setPhone(urr.getPhone());
        userdata.setIsactive(true);

        return usp.save(userdata);
    }
}
