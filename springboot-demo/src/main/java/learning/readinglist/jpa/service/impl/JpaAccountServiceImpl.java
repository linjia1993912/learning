package learning.readinglist.jpa.service.impl;

import learning.readinglist.jpa.dao.JpaAccountDao;
import learning.readinglist.jpa.entity.Account;
import learning.readinglist.jpa.service.IJpaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author LinJia
 * @Date 2020/9/2
 **/
@Service
public class JpaAccountServiceImpl implements IJpaAccountService {

    @Autowired
    private JpaAccountDao accountDao;

    @Override
    public List<Account> getAccounts() {
        return accountDao.findAll();
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return accountDao.findById(id);
    }

    @Override
    public String updateAccount(Account account) {
        Account account1 = accountDao.saveAndFlush(account);
        return account1.toString();
    }

    @Override
    public String postAccount(Account account) {
        Account account1 = accountDao.save(account);
        return account1.toString();
    }
}
