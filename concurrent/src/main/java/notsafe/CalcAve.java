
package notsafe;

public class CalcAve {
    
    public static void aveInt(){
        int v1=1073741827;
        int v2=1431655768;
        System.out.println("v1="+v1);
        System.out.println("v2="+v2);
        int ave=(v1+v2)/2;
        System.out.println("ave="+ave);
    }
    
    public static void aveInt2(){
        int v1=1073741827;
        int v2=1431655768;
        System.out.println("v1="+v1);
        System.out.println("v2="+v2);
        int ave= (v1 & v2) + ((v1 ^ v2) >> 1);
        System.out.println("ave="+ave);
    }

    public static void main(String[] args) {
        aveInt();
        aveInt2();
    }
}
