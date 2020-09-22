package pool;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;

public class MoreExecutorsDemo {
    public static void main(String[] args) {
        Executor exceutor = MoreExecutors.directExecutor();
        exceutor.execute(() -> System.out.println("I am running in " + Thread.currentThread().getName()));
    }
}
