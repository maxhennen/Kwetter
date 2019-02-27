package dao.like;

import domain.Like;

import java.util.ArrayList;
import java.util.List;

public class LikeDAOTest implements LikeDAO{

    List<Like> likes = new ArrayList<>();

    @Override
    public void create(Like l) {
        likes.add(l);
    }

    @Override
    public void removeLike(Like l) {
        likes.remove(l);
    }

    @Override
    public List<Like> findAll() {
        return likes;
    }

    @Override
    public Like get(long id) {
        for (Like l: likes) {
            if(l.getId() == id){
                return l;
            }
        }
        return null;
    }
}
