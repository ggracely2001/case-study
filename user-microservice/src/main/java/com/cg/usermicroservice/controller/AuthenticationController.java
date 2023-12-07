package com.cg.usermicroservice.controller;
import com.cg.usermicroservice.config.JwtUtil;
import com.cg.usermicroservice.entity.AuthenticationRequest;
import com.cg.usermicroservice.entity.AuthenticationResponse;
import com.cg.usermicroservice.entity.UserLogin;
import com.cg.usermicroservice.exception.DuplicateLabelException;
import com.cg.usermicroservice.repository.UserLoginRepository;
import com.cg.usermicroservice.service.MentorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api/users")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private MentorDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserLoginRepository repo;
    @PostMapping("/reg")
    public ResponseEntity<?> subscribe(@RequestBody AuthenticationRequest request){
        System.out.println("Gracely");
        String username = request.getUsername();
        String password=request.getPassword();
        UserLogin model = new UserLogin();
        model.setUsername(username);
        model.setPassword(password);
        try {
            UserLogin name = repo.findByUsername(request.getUsername());
            UserLogin userByName = null;
            if(name!=null && request.getUsername().equals(name.getUsername())){
                userByName = name;
            }
            if(!Objects.isNull(userByName)){
                throw new DuplicateLabelException("User already exists with username "+username);
            }
            else {
                repo.save(model);
            }
        }
        catch (DuplicateLabelException e) {
            return ResponseEntity.ok(new AuthenticationResponse(e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error while subscribing the user with the username "+username));
        }
        return ResponseEntity.ok(new AuthenticationResponse("client subscribed with username " + username));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}