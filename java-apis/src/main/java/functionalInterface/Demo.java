package functionalInterface;

/**
 * @program: my-design-patterns
 * @description: 函数式接口的使用：一般可以作为方法的参数和返回值类型使用
 * @author: Rui.Zhou
 * @create: 2019-07-01 10:02
 **/
public class Demo {

    //定义一个方法，参数使用函数式接口
    public static void show(MyFunctionalInterface myInter){
        myInter.method();
    }

    public static void main(String[] args) {
        //调用show方法，方法的参数是一个接口，可以传接口的实现类
        show(new MyFunctionalInterfaceImpl());

        //调用show方法，方法的参数是一个接口，可以传接口的匿名内部类
        show(new MyFunctionalInterface() {
            @Override
            public void method() {
                System.out.println("使用匿名内部类重写接口中的方法");
            }
        });

        //调用show方法，方法的参数是一个函数式接口，所以可以传递Lambda表达式


        show(()->{
            System.out.println("使用lambda表达式重写接口中的抽象方法");
        });

        //简化lambda表达式
        show(()-> System.out.println("使用lambda表达式重写接口中的抽象方法"));
    }


}
