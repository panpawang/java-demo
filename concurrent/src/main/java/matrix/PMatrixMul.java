package matrix;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;
import org.jmatrices.dbl.operator.MatrixOperator;

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
public class PMatrixMul {
    public static final int granularity=3;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Matrix m1=MatrixFactory.getRandomIntMatrix(100, 100, null);
        Matrix m2=MatrixFactory.getRandomIntMatrix(100, 100, null);
        Matrix sr=MatrixOperator.multiply(m1, m2);
        MatrixMulTask task=new MatrixMulTask(m1,m2,null);
        ForkJoinTask<Matrix> result = forkJoinPool.submit(task);
        Matrix pr=result.get();
        //检查和串行计算结果是否一致 使用-ea开启断言
        assert sr.toString().equals(pr.toString());
//        System.out.println(pr);
    }
}
