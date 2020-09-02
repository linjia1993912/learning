package learning.readinglist.jpa.service;

import learning.readinglist.jpa.entity.Account;

import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: linJia
 * @Date: 2020/9/2 15:35
 */
public interface IJpaAccountService {
    List<Account> getAccounts();

    Optional<Account> getAccountById(int id);

    String updateAccount(Account account);

    String postAccount(Account account);
}
