package com.cg.adminmicroservice.controller;
import com.cg.adminmicroservice.config.JwtUtil;
import com.cg.adminmicroservice.entity.Admin;
import com.cg.adminmicroservice.entity.AuthenticationRequest;
import com.cg.adminmicroservice.entity.AuthenticationResponse;
import com.cg.adminmicroservice.exception.DuplicateLabelException;
import com.cg.adminmicroservice.repository.AdminRepository;
import com.cg.adminmicroservice.service.AdminDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/api/admins")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private AdminDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AdminRepository repo;
    @PostMapping("/reg")
    public ResponseEntity<?> subscribe(@RequestBody AuthenticationRequest request){
        System.out.println("Gracely");
        String username = request.getUsername();
        String password=request.getPassword();
        Admin model = new Admin();
        model.setUsername(username);
        model.setPassword(password);
        try {
            Admin name = repo.findByUsername(request.getUsername());
            Admin adminByName = null;
            if(name!=null && request.getUsername().equals(name.getUsername())){
                adminByName = name;
            }
            if(!Objects.isNull(adminByName)){
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