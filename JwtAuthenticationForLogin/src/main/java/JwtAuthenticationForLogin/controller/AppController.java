package JwtAuthenticationForLogin.controller;

import JwtAuthenticationForLogin.Repository.RoleRepo;
import JwtAuthenticationForLogin.Repository.UserRepo;
import JwtAuthenticationForLogin.dto.JwtResponse;
import JwtAuthenticationForLogin.dto.Logindto;
import JwtAuthenticationForLogin.dto.Registerdto;
import JwtAuthenticationForLogin.helper.JwtUtil;
import JwtAuthenticationForLogin.model.*;
import JwtAuthenticationForLogin.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@CrossOrigin
//  (origins="http://localhost:3000")
//'http://localhost:3000'
@RestController
@RequestMapping("/controller")
public class AppController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    public AppController() {
    }

    @RequestMapping("/welcome")
    public String welcome() {
        String text="welcome to jwt";
        return text;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Registerdto registerdto){
        System.out.println(0);
        if(userRepo.existsByemailid(registerdto.getEmailid())){
            return new ResponseEntity<>("email is already taken", HttpStatus.BAD_REQUEST);
        }
        User user=new User();
        System.out.println(1);
        user.setEmailid(registerdto.getEmailid());
        System.out.println(2);
        user.setName(registerdto.getName());
        user.setPhone(registerdto.getPhone());
        System.out.println(3);
        user.setPassword(passwordEncoder.encode(registerdto.getPassword()));
        System.out.println(4);
        Roles roles = roleRepo.findByName("ROLE_USER").get();
        System.out.println(5);
        user.setRoles(Collections.singleton(roles));
        System.out.println(6);

        userRepo.save(user);
        System.out.println(7);
        return new ResponseEntity<>("user registered successfully",HttpStatus.OK);

    }
 //   @PostMapping("/login")
  // public ResponseEntity<JwtResponse> LoginUser(@RequestBody Logindto logindto){
//        System.out.println(" u r in controller1");
 //       try{
//            System.out.println(" u r in controller2");
//            String lo=logindto.getEmailid();
//            System.out.println(lo);
           // Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmailid(),logindto.getPassword()));
//            System.out.println(lo);
//            System.out.println(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String token=this.jwtUtil.generateToken(authentication);
//            System.out.println(" u r in controller3");
//            return ResponseEntity.ok(new JwtResponse(token))
//            }catch (UsernameNotFoundException e){
 //          return new ResponseEntity<JwtResponse>(new JwtResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
     //  }
      // UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(logindto.getEmailid());
//      String token=this.jwtUtil.generateToken(userDetails);
 //      return ResponseEntity.ok(new JwtResponse(token));
//
    @PostMapping("login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody Logindto logindto){
        try {
           this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmailid(),logindto.getPassword()));

        }catch (UsernameNotFoundException e){
            return  new ResponseEntity<>(new JwtResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);

        }
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmailid(),logindto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=this.jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }
   }



