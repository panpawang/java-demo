package matrix;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * A*B
    A=(A1,A2)T
    B=(B1,B2)
    
    A*B=(A1*B1  A1*B2)
        (A2*B1  A2*B2)
 * 
 * @author Geym
 *
 */
public class PMatrixMulOnly {
    public static final int granularity=3;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Matrix m1=MatrixFactory.getRandomIntMatrix(300, 300, null);
        Matrix m2=MatrixFactory.getRandomIntMatrix(300, 300, null);
        MatrixMulTask task=new MatrixMulTask(m1,m2,null);
        ForkJoinTask<Matrix> result = forkJoinPool.submit(task);
        Matrix pr=result.get();
        System.out.println(pr);
    }
}
