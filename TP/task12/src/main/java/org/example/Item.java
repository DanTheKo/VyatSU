package org.example;

import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;

@Entity
@Table(name = "item")

public class Item
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private int value;
    @Column(name = "valueNew")
    @OptimisticLock(excluded = true)
    private int valueNew;

    public Item()
    {
        value = 0;
        valueNew = 0;
    }



    @Version
    long version;
    public void increment()
    {
        value++;
    }
    public void increment2()
    {
        valueNew++;
    }
    public int getValue()
    {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValueNew() {
        return valueNew;
    }

    public void setValueNew(int valueNew) {
        this.valueNew = valueNew;
    }
}
