package in.coder.abhijit.demo.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.coder.abhijit.demo.entity.Role;
import in.coder.abhijit.demo.entity.User;
import in.coder.abhijit.demo.repository.RoleRepository;
import in.coder.abhijit.demo.service.UserService;

@RestController
class UserController{

    
    private final UserService userService;

   
    private final PasswordEncoder passwordEncoder; // For encoding passwords

    
	private final RoleRepository roleRepository; // Repository to fetch roles from DB

	@Value("${jwt.secret}")
    private String jwtSecret;
   

    public UserController(UserService userService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

	@GetMapping("/jwtSecret")
	public void printJwtSeret(){
		System.out.println("Jwt secret is "+jwtSecret);
	}


   // Register a new user
	 @PostMapping("/register")
	 public String register(@RequestBody User user) {
	     // Encrypt password using BCrypt
	     user.setPassword(passwordEncoder.encode(user.getPassword()));

	  // Find roles by name
	        Set<Role> roles = roleRepository.findByNameIn(user.getRoles().stream()
	                .map(Role::getName)  // Extract role names
	                .collect(Collectors.toList())); // Convert to List

	        // Set roles to the user
	        user.setRoles(roles);

	        // Save the user
	        userService.saveUser(user);

	     return "User registered successfully!";
	 }


}