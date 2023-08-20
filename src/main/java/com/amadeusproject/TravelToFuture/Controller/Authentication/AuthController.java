package com.amadeusproject.TravelToFuture.Controller.Authentication;

import com.amadeusproject.TravelToFuture.DTO.AuthResponseDTO;
import com.amadeusproject.TravelToFuture.DTO.LoginDTO;
import com.amadeusproject.TravelToFuture.DTO.RegisterDTO;
import com.amadeusproject.TravelToFuture.DataAccess.Role.RoleRepository;
import com.amadeusproject.TravelToFuture.DataAccess.User.UserRepository;
import com.amadeusproject.TravelToFuture.Entities.Role;
import com.amadeusproject.TravelToFuture.Entities.UserEntity;
import com.amadeusproject.TravelToFuture.Service.JWT.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Login with username and password.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }


    @Operation(summary = "Register with username and password")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("Username is taken.", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode((registerDTO.getPassword())));
        Role roles;
        if(registerDTO.getUsername().endsWith("@admin")){

            roles = roleRepository.findByName("ADMIN").get();
        }else {
            roles = roleRepository.findByName("USER").get();
        }
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success", HttpStatus.OK);

    }

}
