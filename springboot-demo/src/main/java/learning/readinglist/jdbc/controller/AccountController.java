package learning.readinglist.jdbc.controller;

import learning.readinglist.jdbc.entity.AccountEntity;
import learning.readinglist.jdbc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:构建一组restful api来展示
 * jdbc访问mysql
 * @Author LinJia
 * @Date 2020/9/2
 **/
@RestController
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping(value = "/list")
    public List<AccountEntity> getAccounts(){
        return accountService.findAccountList();
    }

    @GetMapping(value = "/{id}")
    public AccountEntity getAccountById(@PathVariable("id") int id){
        return accountService.findAccountById(id);
    }

    @PutMapping(value = "/{id}")
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
                                 @RequestParam(value = "money" ,required = true)double money){
        AccountEntity account=new AccountEntity();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

    @PostMapping(value = "/add")
    public String postAccount( @RequestParam(value = "name")String name,
                                @RequestParam(value = "money" )double money){
        AccountEntity account=new AccountEntity();
        account.setMoney(money);
        account.setName(name);
        int t= accountService.add(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

}
