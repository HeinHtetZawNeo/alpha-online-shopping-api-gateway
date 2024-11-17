package alpha.olsp.apiGateway.controller;

import alpha.olsp.apiGateway.dto.AuthRequest;
import alpha.olsp.apiGateway.dto.AuthResponse;
import alpha.olsp.apiGateway.dto.UserRegisterRequest;
import alpha.olsp.apiGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil; // Utility class for token generation

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }

        final String jwt = jwtUtil.generateToken(authRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        // Forward the request to user-service or handle registration logic here
        // Example: call user-service endpoint /api/v1/c/register using RestTemplate or WebClient

        return ResponseEntity.ok("User registered successfully");
    }
}
