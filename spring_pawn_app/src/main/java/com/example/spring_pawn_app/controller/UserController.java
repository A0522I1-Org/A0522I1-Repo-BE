package com.example.spring_pawn_app.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.spring_pawn_app.dto.request.SignInForm;
import com.example.spring_pawn_app.dto.request.SignUpForm;
import com.example.spring_pawn_app.dto.response.JwtResponse;
import com.example.spring_pawn_app.dto.response.ResponseMessage;
import com.example.spring_pawn_app.model.Role;
import com.example.spring_pawn_app.model.User;
import com.example.spring_pawn_app.security.jwt.JwtProvider;
import com.example.spring_pawn_app.security.userprincal.UserPrinciple;
import com.example.spring_pawn_app.service.role.IRoleService;
import com.example.spring_pawn_app.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("exist account in DB!"), HttpStatus.OK);
        }

        User user = new User(signUpForm.getUsername(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            Role userRole = roleService.findByName(role).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(userRole);
        });
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(new ResponseMessage("create success!!!"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
}
