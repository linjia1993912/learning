package learning.readinglist.jpa.dao;

import learning.readinglist.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 数据访问层
 * 通过编写一个继承自 JpaRepository 的接口就能完成数据访问,其中包含了几本的单表查询的方法，非常的方便。值得注意的是，
 * 这个Account 对象名，而不是具体的表名，另外Interger是主键的类型，一般为Integer或者Long
 * @Author: linJia
 * @Date: 2020/9/2 15:33
 */
public interface JpaAccountDao extends JpaRepository<Account,Integer> {

}
