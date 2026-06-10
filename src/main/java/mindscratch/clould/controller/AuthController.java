package mindscratch.clould.controller;

import mindscratch.clould.dto.LoginRequest;
import mindscratch.clould.dto.LoginResponse;
import mindscratch.clould.dto.RegisterRequest;
import mindscratch.clould.security.JwtService;
import mindscratch.clould.service.UserService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        userService.register(
                request.getUsername(),
                request.getPassword()
        );

        return "User registered";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(auth.getName());

        return new LoginResponse(token);
    }
}



// package mindscratch.clould.controller;

// import mindscratch.clould.dto.LoginRequest;
// import mindscratch.clould.dto.LoginResponse;
// import mindscratch.clould.security.JwtService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final JwtService jwtService;

//     public AuthController(JwtService jwtService) {
//         this.jwtService = jwtService;
//     }

//     @PostMapping("/login")
//     public LoginResponse login(
//             @RequestBody LoginRequest request) {

//         if (!"admin".equals(request.getUsername())
//                 || !"admin".equals(request.getPassword())) {

//             throw new RuntimeException("Invalid credentials");
//         }

//         String token =
//                 jwtService.generateToken(
//                         request.getUsername());

//         return new LoginResponse(token);
//     }
// }
