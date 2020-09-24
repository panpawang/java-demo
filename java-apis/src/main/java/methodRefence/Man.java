package methodRefence;

/**
 * @program: my-design-patterns
 * @description:
 * @author: Rui.Zhou
 * @create: 2019-07-11 16:58
 **/
public class Man extends Human {

    @Override
    public void sayHello() {
        System.out.println("hi");
    }

    //定义一个方法，参数传递greetable

    public void method(Greetable greetable){

        greetable.greet();
    }

    public void show(){

//        method(() -> {
//            Human human = new Human();
//            human.sayHello();
//        });

//        method(()->super.sayHello());

        /*
        使用super引用父类的成员方法
         */

        method(super::sayHello);
    }

    public static void main(String[] args) {
        Man man = new Man();

        man.show();
    }

}
