package dao.impl;

import dao.AccountDao;
import dao.RoleDao;
import database.DataService;
import entity.Account;
import entity.Role;

import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private final DataService dataservice = new DataService();
    private final RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Account getAccountByLogin(String login) {
        final Account account = dataservice.getAccountByLogin(login);
        account.setRoles(this.getAccountRoles(account.getId()));
        return account;
    }

    @Override
    public List<Role> getAccountRoles(int idAccount) {
        final List<Integer> idRoles = dataservice.getIdAccountRoles(idAccount);
        final List<Role> accountRoles = new ArrayList<Role>();

        for (Integer idRole : idRoles) {
            // accountRoles
            final List<Role> role = roleDao.getRoleById(idRole);
            if (role.size() != 0) {
                accountRoles.add(role.get(0));
            }
        }
        return accountRoles;
    }

}
