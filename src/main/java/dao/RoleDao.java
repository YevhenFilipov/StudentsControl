package dao;

import entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles();

    List<Role> getRoleById(int id);

}
