package org.example.animals;

import org.example.Column;
import org.example.Table;

@Table(title = "WhiteBear2")
public class WhiteBear{


    //@Column
    private String name;
    @Column
    private String age;
    @Column
    protected FurType furType;
    @Column
    protected int maxRunDist;
    //@Column
    protected int maxSwimDist;

    public WhiteBear(String Name, String Age, int MaxRunDist, int MaxSwimDist, FurType furType) {
        this.name = Name;
        this.age = Age;
        this.maxRunDist = MaxRunDist;
        this.maxSwimDist = MaxSwimDist;
        this.furType = furType;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }

//    public int getAge(){
//        return age;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
