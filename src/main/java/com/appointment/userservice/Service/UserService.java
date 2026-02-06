package com.appointment.userservice.Service;
import com.appointment.userservice.dto.UserRegisterRequest;
import com.appointment.userservice.entity.User;
import com.appointment.userservice.Repository.UserRepository;
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
}

