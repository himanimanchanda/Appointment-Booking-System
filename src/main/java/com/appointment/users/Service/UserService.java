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
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository usp;
   private final PasswordEncoder pse;
   private final OrganisationRepository orr;


    public User login(String email,String password){
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
        userdata.setRole(Role.PATIENT);
        userdata.setPhone(urr.getPhone());
        userdata.setIsactive(true);

        return usp.save(userdata);
    }

//    it will be called on /admin/onboard
    public User onboardService(UserRegisterRequest urr,Long orgId){
        usp.findByEmail(urr.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistsException("user already exist");
        });
        Organisation org=orr.findById(orgId).orElseThrow(()-> new RuntimeException("organisation not found"));
        User use=new User();
        use.setEmail(urr.getEmail());
        use.setName(urr.getName());
        use.setRole(urr.getRole());
        use.setPassword(pse.encode(urr.getPassword()));
        use.setIsactive(true);
        use.setPhone(urr.getPhone());
        use.setOrganisation(org);
        return usp.save(use);

    }

    public User getUserByEmail(String email){
        return usp.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    public List<User> getAllUserService(){
        return usp.findAll();
    }

   public User getUserByIdService(long id){
        return usp.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
   }
   public User deleteUserByIdService(Long id){
            User user =usp.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            usp.deleteById(id);
        return user;
   }
   public User changeActiveStatusService(Long id){
         User user =usp.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
         boolean activestatus = user.isIsactive();
         user.setIsactive(!activestatus);
         return usp.save(user);

   }


}

