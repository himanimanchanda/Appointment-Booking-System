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
//   public UserService(UserRepository usp) {
//        this.usp = usp;
//    }
//    private final UserRepository userRepository;
    public User registerUser(UserRegisterRequest request) {
        if(usp.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered!");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();

        return usp.save(user);
    }
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
}

