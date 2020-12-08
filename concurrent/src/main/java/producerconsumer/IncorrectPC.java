package producerconsumer;

class Q {
    int n;
    boolean valueSet = false;

    synchronized int get() {

        while (!valueSet){

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Got : " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        this.n = n;
        valueSet =true;
        System.out.println("Put: " + n);
        notify();
    }
}

class Producer1 implements Runnable {
    Q q;
    Thread t;

    Producer1(Q q) {
        this.q = q;
        t = new Thread(this, "Producer");
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}

class Consumer1 implements Runnable{
    Q q;
    Thread t;

    Consumer1(Q q){
        this.q = q;
        t = new Thread(this,"Consumer");
    }


    @Override
    public void run() {
        while (true){
            q.get();
        }
    }
}

public class IncorrectPC {

    public static void main(String[] args) {
        Q q = new Q();

        Producer1 p = new Producer1(q);
        Consumer1 c = new Consumer1(q);
        p.t.start();
        c.t.start();

        System.out.println("stop.");
    }
}


