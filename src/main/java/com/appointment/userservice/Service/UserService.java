package com.appointment.userservice.Service;
import com.appointment.userservice.dto.UserRegisterRequest;
import com.appointment.userservice.entity.User;
import com.appointment.userservice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User registerUser(UserRegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered!");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();

        return userRepository.save(user);
    }
}

