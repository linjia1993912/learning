package learning.readinglist.jdbc.dao;

import learning.readinglist.jdbc.entity.AccountEntity;

import java.util.List;

/**
 * @Description: jdbc访问mysql
 * DAO层
 * @Author: linJia
 * @Date: 2020/9/2 15:07
 */
public interface IAccountDAO {

    int add(AccountEntity account);

    int update(AccountEntity account);

    int delete(int id);

    AccountEntity findAccountById(int id);

    List<AccountEntity> findAccountList();
}
