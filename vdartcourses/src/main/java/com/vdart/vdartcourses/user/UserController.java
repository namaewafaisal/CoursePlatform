package com.vdart.vdartcourses.user;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.ResourceNotFoundException;
import com.vdart.vdartcourses.Role;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired 
    private PasswordEncoder passwordEncoder;

    // For Typical Users
    // Sign up
    // login

    @PostMapping("/auth/register")
    @PreAuthorize("permitAll()")
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        return userService.saveUser(user);
    }

    @GetMapping("/userid/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public User getUserById(@PathVariable ObjectId id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public List<User> getUserByName(@RequestParam String username) {
        return userService.getUserByName(username);
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
        
    }

    @PutMapping("userid/{id}/setuserroles")
    @PreAuthorize("hasRole('ADMIN')")
    public User setRolesofUser(@PathVariable ObjectId id, @RequestParam Set<Role> role) {
        return userService.promoteUser(id,role);
    }

    @PutMapping("/userid/{id}/update")
    @PreAuthorize("hasAnyRole()")
    public User updateUser(@PathVariable ObjectId id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/userid/{id}/delete")
    @PreAuthorize("hasAnyRole()")
    public ResponseEntity<String> deleteUser(@PathVariable ObjectId id) {
        if (!userService.getUserById(id).isPresent()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    

    @GetMapping("/me")
    public User myProfile() {
        
    }
    

    
}