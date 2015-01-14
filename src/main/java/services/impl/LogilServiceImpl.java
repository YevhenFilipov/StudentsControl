package services.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import entity.Account;
import entity.Role;
import exeptions.InvalidDataException;
import services.LoginService;

import java.util.LinkedList;
import java.util.List;

public class LogilServiceImpl implements LoginService {

    private AccountDao accountDao = new AccountDaoImpl();

    @Override
    public Account login(String login, String password, int role) throws InvalidDataException {
        final Account accountByLogin = accountDao.getAccountByLogin(login);
        if (!password.equals(accountByLogin.getPassword())) {
            accountByLogin.setId(0);
        }
        final List<Role> roles = accountByLogin.getRoles();
        final LinkedList<Integer> rolesFromAccount = new LinkedList<Integer>();
        for (Role currentRole : roles) {
            rolesFromAccount.add(currentRole.getId());
        }
        if (!rolesFromAccount.contains(role))
            accountByLogin.setId(0);
        if (accountByLogin.getId() == 0)
            throw new InvalidDataException("account");
        return accountByLogin;
    }

}
