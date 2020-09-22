package prime;


public class FindPrime implements Runnable {
    
    int myPrime;
    
    public FindPrime(int prime){
        this.myPrime=prime;
        FindPrimeMain.threadCount.incrementAndGet();
    }
    @Override
    public void run() {
       out:while(true){
           for(int i=myPrime+1;i<FindPrimeMain.COUNT;i++){
               if(PrimeUtil.isPrime(i)){
                   //submit
                   FindPrimeMain.pool.execute(new FindPrime(i));
                   break out;
               }
           }
       }
       int k=0;
       for(int i=myPrime;(k=i*myPrime)<FindPrimeMain.COUNT;i++){
           FindPrimeMain.bitset.set(k,false);
       }
       FindPrimeMain.threadCount.decrementAndGet();
    }
}
