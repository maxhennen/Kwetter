package dao.user;

import domain.user.User;

import java.util.ArrayList;
import java.util.List;

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
    public User findUserByUserName(String userName) {
        for(User u : users){
            if(u.getUsername().equals(userName)){
                return u;
            }
        }
        return null;
    }

    @Override
    public User findUserByID(long id) {
        for(User u : users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllFollowing(User u) {
        return u.getFollowing();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        return u.getFollowers();
    }
}
