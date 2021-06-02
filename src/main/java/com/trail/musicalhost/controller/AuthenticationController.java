package com.trail.musicalhost.controller;

import com.trail.musicalhost.model.*;
import com.trail.musicalhost.service.JwtUtil;
import com.trail.musicalhost.service.UserDetailsServiceImpl;
import com.trail.musicalhost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="http://localhost:3000")
@RestController
public class AuthenticationController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception{
        try
        {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );

        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect Username or Password");
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest newUser)
    {
        if(userService.isExistingUser(newUser.getUserName()))
        {
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUserName(newUser.getUserName());
        user.setPassword(newUser.getPassword());
        user.setRole("ROLE_USER");
        user.setActive(true);

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(newUser.getFirstname());
        userProfile.setLastName(newUser.getLastName());
        userProfile.setEmail(newUser.getEmail());
        userProfile.setMobile(newUser.getMobile());
        userProfile.setPassword(newUser.getPassword());


        user.setProfile(userProfile);


        userService.save(user);
        return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }
}
