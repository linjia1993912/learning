package learning.readinglist.service;

import learning.readinglist.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:将Book对象持久化到数据库，因为用了Spring Data JPA，最简单的就是定义一个接口，
 * 扩展一下Spring Data JPA的JpaRepository接口
 * JpaRepository是个泛型接口，有两个参数，仓库操作的领域对象类型，以及ID属性的类型
 * ReadingListRepository继承了JpaRepository 18个常用持久化操作的方法
 * @Author: linJia
 * @Date: 2020/6/2 15:10
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
