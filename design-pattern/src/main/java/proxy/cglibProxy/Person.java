package proxy.cglibProxy;

public class Person {

    private String name;

    public Person(){}

    public Person(String name){
        this.name = name;
    }

    public void SayName(){
        System.out.println("my name is ："+this.name);
    }
}
