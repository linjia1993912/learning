package learning.readinglist.jdbc.service;

import learning.readinglist.jdbc.entity.AccountEntity;

import java.util.List;

/**
 * @Description:jdbc访问mysql
 * service层
 * @Author LinJia
 * @Date 2020/9/2
 **/
public interface IAccountService {
    int add(AccountEntity account);

    int update(AccountEntity account);

    int delete(int id);

    AccountEntity findAccountById(int id);

    List<AccountEntity> findAccountList();
}
