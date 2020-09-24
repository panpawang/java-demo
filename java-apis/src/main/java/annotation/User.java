package annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class User {

    @AddAnnotation(arrays = {"1"},userId = 2,userName = "张三")
    public void add(){}

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = Class.forName("annotation.User");

        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {

            AddAnnotation declaredAnnotation = declaredMethod.getDeclaredAnnotation(AddAnnotation.class);

            if(declaredAnnotation == null){
                //该方法没注解
                continue;
            }
            int i = declaredAnnotation.userId();
            String s = declaredAnnotation.userName();
            String[] arrays = declaredAnnotation.arrays();
            System.out.println(i + s + Arrays.toString(arrays));
        }
    }
}
