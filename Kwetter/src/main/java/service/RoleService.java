package service;

import dao.role.RoleDAO;
import domain.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RoleService {

    @Inject
    private RoleDAO roleDAO;

    public void createRole(Role role){
        roleDAO.create(role);
    }

    public Role getRoleByName(String name){
        return roleDAO.getByName(name);
    }
}
