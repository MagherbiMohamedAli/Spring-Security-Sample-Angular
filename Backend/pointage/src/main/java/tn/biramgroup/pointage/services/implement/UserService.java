package tn.biramgroup.pointage.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.biramgroup.pointage.Repository.RoleRepository;
import tn.biramgroup.pointage.Repository.StatusRepository;
import tn.biramgroup.pointage.Repository.UserRepository;
import tn.biramgroup.pointage.model.Role;
import tn.biramgroup.pointage.model.Status;
import tn.biramgroup.pointage.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    StatusRepository statusRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            Set<Role> existingRoles = new HashSet<>();
            for (Role role : user.getRoles()) {
                Role existingRole = roleRepository.findByRole(role.getRole())
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role.getRole() + " not found."));
                existingRoles.add(existingRole);
            }
            user.setRoles(existingRoles);
            for (Status status : user.getStatuses()) {
                statusRepository.save(status);
            }

            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email already exists.");
        }
    }
    public void removeUser(Long id){
        userRepository.deleteById(Math.toIntExact(id));
    }


    public User findUserById(Long userId){
        User targetUser = userRepository.findById(Math.toIntExact(userId)).get();
        return targetUser;
    }


}
