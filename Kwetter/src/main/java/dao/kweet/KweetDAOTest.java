package dao.kweet;

import domain.Kweet;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class KweetDAOTest implements KweetDAO {

    List<Kweet> kweets = new ArrayList<>();

    @Override
    public void create(Kweet k) {
        kweets.add(k);
    }

    @Override
    public void edit(Kweet k) {

        for (Kweet kw: kweets) {
            if(kw.getId() == k.getId()){
                kweets.remove(kw);
                kweets.add(k);
                break;
            }
        }
    }

    @Override
    public void removeKweet(Kweet k) {
        kweets.remove(k);
    }

    @Override
    public List<Kweet> findAll() {
        return kweets;
    }

    @Override
    public Kweet get(long id) {
        for (Kweet k: kweets) {
            if(k.getId() == id){
                return k;
            }
        }
        return null;
    }

    @Override
    public List<Kweet> getKweetsByUsername(String username) {
        List<Kweet> kweetsByUsername = new ArrayList<>();

        for (Kweet k: kweets) {
            if(k.getUser().getUsername().equals(username)){
                kweetsByUsername.add(k);
            }
        }
        return kweetsByUsername;
    }
}
