package methodRefence;

/**
 * @program: my-design-patterns
 * @description: 通过对象名引用成员方法，使用前提是对象名是已经存在，成员方法也存在，就可以使用对象名引用成员方法
 * @author: Rui.Zhou
 * @create: 2019-07-10 17:29
 **/
public class Demo02MethodRefObject {
    //定义一个方法，参数传递printable

    public static void printString(Printable printable){

        printable.print("hello");
    }



    public static void main(String[] args) {

        //调用printString方法
        printString(s -> new MethodRefObject().printUpperCaseString(s));



        //使用方法引用优化lambda
        //对象已经存在
        //成员方法也存在
        //所以可以使用对象名来引用成员方法

        printString(new MethodRefObject()::printUpperCaseString);
    }
}
