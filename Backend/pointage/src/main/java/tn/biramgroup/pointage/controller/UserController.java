package tn.biramgroup.pointage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.biramgroup.pointage.Repository.RoleRepository;
import tn.biramgroup.pointage.Repository.UserRepository;
import tn.biramgroup.pointage.model.User;
import tn.biramgroup.pointage.services.IUser;
import tn.biramgroup.pointage.services.implement.UserService;
import tn.biramgroup.pointage.springSecurityJwt.Message;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(new Message("Utilisateur Introuvable"), HttpStatus.FORBIDDEN);
    }

}
