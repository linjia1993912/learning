package learning.readinglist.jdbc.service.impl;

import learning.readinglist.jdbc.dao.IAccountDAO;
import learning.readinglist.jdbc.entity.AccountEntity;
import learning.readinglist.jdbc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: jdbc访问mysql具体实现类
 * service
 * @Author LinJia
 * @Date 2020/9/2
 **/
@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountDAO accountDAO;

    @Override
    public int add(AccountEntity account) {
        return accountDAO.add(account);
    }

    @Override
    public int update(AccountEntity account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public AccountEntity findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<AccountEntity> findAccountList() {
        return accountDAO.findAccountList();
    }
}
