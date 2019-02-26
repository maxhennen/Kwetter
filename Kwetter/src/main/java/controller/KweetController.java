package controller;

import domain.Kweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.KweetServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/kweet")
public class KweetController {

    @Autowired
    private KweetServiceImpl kweetService;

    @RequestMapping("/findAll")
    public List<Kweet> findAll(){
        return kweetService.findAll();
    }

    @RequestMapping("/save")
    public void save(@RequestParam("kweet") Kweet kweet){
        kweetService.save(kweet);
    }

    @RequestMapping("/findById")
    public Kweet findOneById(@RequestParam("id") long id){
        return kweetService.findOneById(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("id") long id){
        kweetService.delete(id);
    }

    @RequestMapping("/findByUsername")
    public List<Kweet> findByUsername(@RequestParam("username") String username){
        return kweetService.findByUserUsername(username);
    }
}
