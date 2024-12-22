package in.coder.abhijit.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.coder.abhijit.demo.entity.Role;
import in.coder.abhijit.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Optional<User> optionalUser = userRepository.findByUsername(username);
        // if (optionalUser.isPresent()) {
        // User user = optionalUser.get();
        // UserDetails userDetails =
        // org.springframework.security.core.userdetails.User.builder()
        // .username(user.getUsername())
        // .password(user.getPassword())
        // .authorities(user.getRoles().stream().map(role ->
        // role.getName()).toArray(String[]::new))
        // .accountExpired(false)
        // .accountLocked(false)
        // .credentialsExpired(false)
        // .disabled(!user.getIsActive())
        // .build();
        // return userDetails;

        // } else {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'loadUserByUsername'");
        // }

        return userRepository.findByUsername(username)
                .map(user -> 
                
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRoles().stream()
                                .map(Role::getName)
                                .toArray(String[]::new))
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(!user.getIsActive())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    }

}
