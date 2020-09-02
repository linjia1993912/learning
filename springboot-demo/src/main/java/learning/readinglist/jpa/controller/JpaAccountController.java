package learning.readinglist.jpa.controller;

import learning.readinglist.jpa.entity.Account;
import learning.readinglist.jpa.service.IJpaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: JPA访问mysql
 * @Author LinJia
 * @Date 2020/9/2
 **/
@RestController
@RequestMapping("/jpa")
public class JpaAccountController {

    @Autowired
    private IJpaAccountService iAccountService;

    @GetMapping(value = "/findAll")
    public List<Account> findAll() {
        return iAccountService.getAccounts();
    }

    @GetMapping(value = "/{id}")
    public Account getAccountById(@PathVariable("id") int id) {
        return iAccountService.getAccountById(id).get();
    }

    @PutMapping(value = "/{id}")
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        return iAccountService.updateAccount(account);
    }

    @PostMapping(value = "/add")
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        return iAccountService.postAccount(account);

    }

}
