package com.appointment.users.Service;
import com.appointment.users.Repository.OrganisationRepository;
import com.appointment.users.dto.UserRegisterRequest;
import com.appointment.users.dto.UserResponse;
import com.appointment.users.entity.Organisation;
import com.appointment.users.entity.Role;
import com.appointment.users.entity.User;
import com.appointment.users.Repository.UserRepository;
import com.appointment.users.exception.AccountDisabledException;
import com.appointment.users.exception.InvalidCredentialsException;
import com.appointment.users.exception.ResourceNotFoundException;
import com.appointment.users.exception.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   private final UserRepository usp;
   private final PasswordEncoder pse;
   private final OrganisationRepository orr;
    public UserService(UserRepository userRepository,PasswordEncoder paswordEncoder, OrganisationRepository orr){
        this.usp= userRepository;
        this.pse=paswordEncoder;
        this.orr=orr;

    }
     public List<Organisation> getAllOrganizationService() {
        return orr.findAll();
    }
}

