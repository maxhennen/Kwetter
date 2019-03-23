package dao.user;

import domain.Follower;
import domain.Following;
import domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class UserDAOTest implements UserDAO {

    List<User> users = new ArrayList<>();

    @Override
    public void createUser(User u) {
        users.add(u);
    }

    @Override
    public void editUser(User u) {
        for(User user : users){
            if(user.getId() == u.getId()){
                users.remove(user);
                users.add(u);
            }
        }
    }

    @Override
    public void removeUser(User u) {
        users.remove(u);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User findUserByEmail(String email) {
        for(User u : users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllFollowing(User u) {
        throw  new NotImplementedException();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        throw  new NotImplementedException();
    }

    @Override
    public void addFollower(String emailFollower, String emailFollowing) {

    }

    @Override
    public void removeFollower(Follower follower, Following following) {

    }

    @Override
    public void removeFollowing(Following following, Follower follower) {

    }

    @Override
    public Follower getFollowerByEmail(String email) {
        return null;
    }

    @Override
    public Following getFollowingByEmail(String email) {
        return null;
    }

}
