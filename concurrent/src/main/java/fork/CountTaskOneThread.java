package fork;

public class CountTaskOneThread {

    public static void main(String[] args) {
        long sum=0;
        for(long i=1;i<=2000000000L;i++){
            sum+=i;
        }
        System.out.println(sum);
    }

}
