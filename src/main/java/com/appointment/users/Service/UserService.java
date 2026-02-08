package com.appointment.users.Service;
import com.appointment.users.dto.UserRegisterRequest;
import com.appointment.users.entity.Role;
import com.appointment.users.entity.User;
import com.appointment.users.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository usp;

    public User login(String email,String password){
        User userdata=usp.findByEmail(email).orElseThrow(()->new RuntimeException("Invalid Email"));
//        if(!password.equals(userdata.getPassword())) throw new RuntimeException("Invalid Password");
        return userdata;

    }
    public User signup(UserRegisterRequest urr){
        usp.findByEmail(urr.getEmail()).ifPresent(user ->{
            throw new RuntimeException("Email already existed");
        } );
        User userdata=new User();
        userdata.setName(urr.getName());
        userdata.setEmail(urr.getEmail());
        userdata.setPassword(urr.getPassword());
        userdata.setRole(Role.PATIENT);
        userdata.setPhone(urr.getPhone());
        userdata.setActive(true);

        return usp.save(userdata);
    }

//    it will be called on /admin/onboard
    public User onboardService(UserRegisterRequest urr){
        usp.findByEmail(urr.getEmail()).ifPresent(user -> {
            throw new RuntimeException("user already exist");
        });
        User use=new User();
        use.setEmail(urr.getEmail());
        use.setName(urr.getName());
        use.setRole(urr.getRole());
        use.setPassword(urr.getPassword());
        use.setActive(true);
        use.setPhone(urr.getPhone());
        return usp.save(use);
    }
}

