package com.example.MySQLExperiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/experiment") // This means the URLs will start with /experiment
public class MainController {
    @Autowired //This will get the bean called userRepository
                //which was auto generated by spring
    private UserRepository userRepository;

    @PostMapping(path="/add") //Map only POST requests
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email){
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        //Returns a JSON or XML document with the users in it
        return userRepository.findAll();
    }
}
