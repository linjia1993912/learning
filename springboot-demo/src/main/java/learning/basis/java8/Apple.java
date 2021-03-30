package learning.basis.java8;

import java.math.BigDecimal;

/**
 * @author LinJia
 * @description: 模拟实体类
 * @date 2021/3/30
 */
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", num=" + num +
                '}';
    }
}