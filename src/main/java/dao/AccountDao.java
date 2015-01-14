package dao;

import entity.Account;
import entity.Role;

import java.util.List;

public interface AccountDao {

    Account getAccountByLogin(String login);

    List<Role> getAccountRoles(int idAccount);

}
