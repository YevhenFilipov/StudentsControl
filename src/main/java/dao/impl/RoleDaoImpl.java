package dao.impl;

import dao.RoleDao;
import database.DataService;
import entity.Role;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private final DataService DataService = new DataService();

    @Override
    public List<Role> getRoles() {
        return DataService.getAllRoles();
    }

    @Override
    public List<Role> getRoleById(int id) {
        return DataService.getRolesById(id);
    }


}
