package controller;

import domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/save")
    public void save(@RequestParam("user") User user){
        userService.save(user);
    }

    @RequestMapping("/findOne")
    public User findOne(@RequestParam("id") long id){
        return userService.findOne(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("id") long id){
        userService.delete(id);
    }

    @RequestMapping("/findByUsername")
    public void findByUsername(@RequestParam("username") String username){
        userService.findByUsername(username);
    }
}
