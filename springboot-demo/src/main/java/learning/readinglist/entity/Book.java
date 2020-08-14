package learning.readinglist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description:书的Book类
 * JPA实体
 * @Author LinJia
 * @Date 2020/6/2
 **/

@Entity
public class Book {
    @Id //唯一标识
    @GeneratedValue //自增
    private Long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;
}
