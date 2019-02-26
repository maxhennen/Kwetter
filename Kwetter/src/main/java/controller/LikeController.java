package controller;

import domain.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.LikeServiceImpl;

import java.util.List;

@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeServiceImpl likeService;

    @RequestMapping("/findAll")
    public List<Like> findAll(){
        return likeService.findAll();
    }

    @RequestMapping("/save")
    public void save(@RequestParam("like") Like like){
        likeService.save(like);
    }

    @RequestMapping("/findOneById")
    public Like findOneById(@RequestParam("id") long id){
        return likeService.findOneById(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("id") long id){
        likeService.delete(id);
    }

}