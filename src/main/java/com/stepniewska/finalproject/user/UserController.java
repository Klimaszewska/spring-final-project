package com.stepniewska.finalproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

/*    @Autowired
    UserRepository userRepository;*/


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/unauth/credentials")
    public String getCredentials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String result = authentication.getName() + ", roles: ";
        result += String.join(",", authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toArray(String[]::new));
        return result;
    }


    @PostMapping("/auth/login")
    public String displayLoginMessage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String result = "Welcome, " + userRepository.findByEmail(authentication.getName()).getDisplayedUserName() + "!";
        String result = "Welcome, " + userService.findDisplayedUserName(authentication.getName()) + "!";
        return result;
    }

    @GetMapping("/unauth/users")
    public List<User> getAll() {
        return userService.findAll();
    }


/*    // just a sample method for a list return - will be removed
    @GetMapping("/unauth/users")
    public List<String> displayUserList(){
        List<User> inputUserList = userRepository.findAll();
        List<String> displayedUserList = new ArrayList<>();
        for (User user : inputUserList) {
                displayedUserList.add(user.getDisplayedUserName() + "," + user.getEmail());
        }
        return displayedUserList;
    }*/


}
