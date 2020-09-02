package learning.readinglist.jpa.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @Description:
 * @Author LinJia
 * @Date 2020/9/2
 **/
@Entity //通过@Entity 表明是一个映射的实体类
public class Account {

    // @Id表明id,注意不要导错包
    @Id
    //@GeneratedValue 字段自动生成,主键自增
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
