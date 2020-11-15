package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;
import ch.zli.m223.punchclock.service.UserDetailsServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.*;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UserController {
    /*

   # registers a new user:
        curl -H "Content-Type: application/json" -X POST -d '{
        "username": "admin",
        "password": "password"
        }' http://localhost:8081/users/sign-up

     */

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl userDetailsService;

    public UserController(UserRepository userrepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userservice) {
        this.userRepository = userrepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userservice;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {

        Optional<User> usertodelete = userRepository.findById(id);
        if (usertodelete.isEmpty()) return;
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable long id, @Valid @RequestBody User user) {

        Optional<User> usertoedit = userRepository.findById(id);
        if (usertoedit.isEmpty()) return null;
        usertoedit.get().setUsername(user.getUsername());
        usertoedit.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(usertoedit.get());
    }

}