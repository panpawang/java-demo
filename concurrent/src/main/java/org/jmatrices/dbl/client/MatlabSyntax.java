package org.jmatrices.dbl.client;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;
import org.jmatrices.dbl.decomposition.*;
import org.jmatrices.dbl.measure.MatricesMeasure;
import org.jmatrices.dbl.measure.MatrixMeasure;
import org.jmatrices.dbl.operator.MatrixEBEOperation;
import org.jmatrices.dbl.operator.MatrixOperator;
import org.jmatrices.dbl.rowcoltr.ColumnTransformer;
import org.jmatrices.dbl.transformer.MatrixEBETransformation;
import org.jmatrices.dbl.transformer.MatrixEBETransformer;
import org.jmatrices.dbl.transformer.MatrixTransformer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * MatlabSyntax
 * <p>Author: purangp</p>
 * Date: 30.04.2004
 * Time: 14:49:39
 */
public class MatlabSyntax {
    /**
     * Creation
     */
    public static Matrix create(String src) {
        return MatrixParser.parseMatlabMatrix(src);
    }

    public static Matrix rand(int rows, int cols) {
        return MatrixFactory.getRandomMatrix(rows, cols, null);
    }

    public static Matrix magic(int dim) {
        throw new UnsupportedOperationException("tobe implemented");
    }

    public static Matrix pascal(int dim) {
        throw new UnsupportedOperationException("tobe implemented");
    }

    public static Matrix zeros(int rows, int cols) {
        return MatrixFactory.getMatrix(rows, cols, null);
    }

    public static Matrix zeros(int dim) {
        return zeros(dim, dim);
    }

    public static Matrix ones(int rows, int cols) {
        return MatrixFactory.getMatrix(rows, cols, null, 1);
    }

    public static Matrix ones(int dim) {
        return ones(dim, dim);
    }

    public static Matrix eye(int rows, int cols) {
        if (rows == cols)
            return MatrixFactory.getIdentityMatrix(rows, null);
        boolean rowsAreBigger = (rows > cols);     //vertical concatenation
        if (rowsAreBigger)
            return MatrixOperator.verticalConcatenation(MatrixFactory.getIdentityMatrix(cols, null), MatrixFactory.getMatrix(rows - cols, cols, null));
        else
            return MatrixOperator.horizontalConcatenation(MatrixFactory.getIdentityMatrix(rows, null), MatrixFactory.getMatrix(rows, cols - rows, null));
    }

    /**
     * Operators
     */
    public static Matrix neg(Matrix a) {
        return MatrixTransformer.negate(a);
    }

    public static Matrix powElem(Matrix a, final double s) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return Math.pow(element, s);
            }
        });
    }

    public static Matrix powElem(Matrix a, Matrix b) {
        return MatrixOperator.applyEBEOperation(a, b, new MatrixEBEOperation() {
            public double apply(double a, double b) {
                return Math.pow(a, b);
            }
        });
    }

    public static Matrix pow(Matrix a, int s) {
        return MatrixTransformer.pow(a, s);
    }

    public static Matrix add(Matrix a, Matrix b) {
        return MatrixOperator.add(a, b);
    }

    public static Matrix add(Matrix a, final double scalar) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return element + scalar;
            }
        });
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        return MatrixOperator.subtract(a, b);
    }

    public static Matrix subtract(Matrix a, final double scalar) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return element - scalar;
            }
        });
    }

    public static Matrix subtract(final double scalar, Matrix a) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return scalar - element;
            }
        });
    }

    public static double dotprod(Matrix a, Matrix b) {
        return MatricesMeasure.dotProduct(a, b);
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        return MatrixOperator.multiply(a, b);
    }

    public static Matrix multiplyEBE(Matrix a, Matrix b) {
        return MatrixOperator.multiplyEBE(a, b);
    }

    public static Matrix multiply(Matrix a, final double scalar) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return scalar * element;
            }
        });
    }

    public static Matrix kron(Matrix a, Matrix b) {
        return MatrixOperator.kroneckerProduct(a, b);
    }

    public static Matrix hdp(Matrix a, Matrix b) {
        return MatrixOperator.horizontalDirectProduct(a, b);
    }

    public static Matrix solve(Matrix a, Matrix b) {
        return MatrixOperator.solve(a, b);
    }

    public static Matrix divide(Matrix a, Matrix b) {
        return MatrixOperator.divideEBE(a, b);
    }

    public static Matrix divide(Matrix a, final double scalar) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return element / scalar;
            }
        });
    }

    public static Matrix divide(final double scalar, Matrix a) {
        return MatrixEBETransformer.ebeTransform(a, new MatrixEBETransformation() {
            public double transform(double element) {
                return scalar / element;
            }
        });
    }

    public static Matrix t(Matrix m) {
        return MatrixTransformer.transpose(m);
    }

    public static Matrix inv(Matrix m) {
        return MatrixTransformer.inverse(m);
    }

    public static Matrix horzcat(Matrix a, Matrix b) {
        return MatrixOperator.horizontalConcatenation(a, b);
    }

    public static Matrix vertcat(Matrix a, Matrix b) {
        return MatrixOperator.verticalConcatenation(a, b);
    }

    public static Matrix blkdiag(ArrayList matrices) {
        Iterator iter = matrices.iterator();
        Matrix tmpMatrix = null;
        while (iter.hasNext()) {
            Matrix matrix = (Matrix) iter.next();
            int rows_m = matrix.rows(), cols_m = matrix.cols(), rows_tmp = 0, cols_tmp = 0;
            if (tmpMatrix != null) {
                rows_tmp = tmpMatrix.rows();
                cols_tmp = tmpMatrix.cols();
                Matrix rightMatrix = MatrixFactory.getMatrix(rows_tmp, cols_m, tmpMatrix, 0);
                Matrix leftMatrix = MatrixFactory.getMatrix(rows_m, cols_tmp, tmpMatrix, 0);
                Matrix upperMatrix = horzcat(tmpMatrix, rightMatrix);
                Matrix lowerMatrix = horzcat(leftMatrix, matrix);
                tmpMatrix = vertcat(upperMatrix, lowerMatrix);
            } else {
                tmpMatrix = matrix;
            }
        }
        return tmpMatrix;
    }

    /**
     * Methods
     */

    public static double rank(Matrix m) {
        return MatrixMeasure.getRank(m);
    }

    public static double det(Matrix m) {
        return MatrixMeasure.getDeterminant(m);
    }

    public static double trace(Matrix m) {
        return MatrixMeasure.getTrace(m);
    }

    public static double norm(Matrix m) {
        return MatrixMeasure.getNorm2(m);
    }

    public static double norm_1(Matrix m) {
        return MatrixMeasure.getNorm1(m);
    }

    public static double normInf(Matrix m) {
        return MatrixMeasure.getNormInfinity(m);
    }

    public static double normFro(Matrix m) {
        return MatrixMeasure.getNormFrobenius(m);
    }

    public static double cond(Matrix m) {
        return MatrixMeasure.getCondition(m);
    }

    public static Matrix diag(Matrix m) {
        return MatrixTransformer.diagonal(m);
    }

    public static Matrix diagEx(Matrix m, int offset) {
        return MatrixTransformer.extractDiagonal(m, offset);
    }

    public static Matrix diagEm(Matrix m, int offset) {
        return MatrixTransformer.embedDiagonal(m, offset);
    }

    public static int length(Matrix m) {
        return MatrixMeasure.length(m);
    }

    public static double sumElem(Matrix m) {
        return MatrixMeasure.getSum(m);
    }

    public static Matrix sumCol(Matrix m) {
        return ColumnTransformer.sum(m);
    }

    public static double prodElem(Matrix m) {
        return MatrixMeasure.getProduct(m);
    }

    public static Matrix prodCol(Matrix m) {
        return ColumnTransformer.product(m);
    }

    public static double maxElem(Matrix m) {
        return MatrixMeasure.getMax(m);
    }

    public static Matrix maxCol(Matrix m) {
        return ColumnTransformer.max(m);
    }

    public static double minElem(Matrix m) {
        return MatrixMeasure.getMin(m);
    }

    public static Matrix minCol(Matrix m) {
        return ColumnTransformer.min(m);
    }

    public static double meanElem(Matrix m) {
        return MatrixMeasure.getMean(m, false);
    }

    public static Matrix meanCol(Matrix m) {
        return ColumnTransformer.mean(m, false);
    }

    public static Matrix cumprod(Matrix m) {
        return MatrixTransformer.cumulativeColumnProduct(m);
    }

    public static Matrix cumsum(Matrix m) {
        return MatrixTransformer.cumulativeColumnSum(m);
    }

    public static Matrix triu(Matrix m, int offset) {
        return MatrixTransformer.extractUpperTriangular(m, offset);
    }

    public static Matrix tril(Matrix m, int offset) {
        return MatrixTransformer.extractLowerTriangular(m, offset);
    }

    /**
     * Deocompositions
     */
    public static Matrix[] lu(Matrix m) {
        Matrix[] result = new Matrix[3];
        LUDecomposition lu = new LUDecomposition(m);
        result[0] = lu.getL();
        result[1] = lu.getU();
        result[2] = lu.getPivotMatrix();
        return result;
    }

    public static Matrix[] qr(Matrix m) {
        Matrix[] result = new Matrix[3];
        QRDecomposition qr = new QRDecomposition(m);
        result[0] = qr.getQ();
        result[1] = qr.getR();
        result[2] = qr.getH();
        return result;
    }

    public static Matrix[] eig(Matrix m) {
        Matrix[] result = new Matrix[2];
        EigenvalueDecomposition eig = new EigenvalueDecomposition(m);
        result[0] = eig.getV();
        result[1] = eig.getD();
        return result;
    }

    public static Matrix[] chol(Matrix m) {
        Matrix[] result = new Matrix[2];
        CholeskyDecomposition chol = new CholeskyDecomposition(m);
        result[0] = chol.getL();
        Matrix p = MatrixFactory.getMatrix(1, 1, null);
        if (chol.isSPD())
            p.set(1, 1, 0);
        else
            p.set(1, 1, 1);
        result[1] = p;
        return result;
    }

    public static Matrix[] svd(Matrix m) {
        Matrix[] result = new Matrix[3];
        SingularValueDecomposition svd = new SingularValueDecomposition(m);
        result[0] = svd.getU();
        result[1] = svd.getS();
        result[2] = svd.getV();
        return result;
    }

    public static String help() {
        String str = "------------------------Legend for vars------------------------\n";
        str += "s (scalar) \t v,w (vectors) \t A,B (Matrices)\n";
        str +="------------------------Matrix creation------------------------\n";
        str +="\t[1,2,3;4,5,6]\t\tcreate(\"[1,2,3;4,5,6]\")\n";
        str +="\trand(2,3)\t\trand(2,3)\n";
        str +="\tzeros(4,5)\t\tzeros(4,5)\n";
        str +="\tones(4,5)\t\tones(4,5)\n";
        str +="\teye(3,4)\t\teye(3,4)\n";
        str +="\n";
        str +="------------------------Matrix operators------------------------\n";
        str +="\tA(1,2)\t\tA.get(1,2)\n";
        str +="\tA(1,2)=3.5\t\tA.set(1,2,3.5)\n";
        str +="\t[m,n] = size(A)\t\tm=A.rows(),n=A.cols()\n";
        str +="\tA(:,j)\t\tA.getColumn(j)\n";
        str +="\tA(i,:)\t\tA.getRow(i)\n";
        str +="\n";
        str +="\t-A\t\tneg(A)\n";
        str +="\tA+B\t\tadd(A,B)\n";
        str +="\ts+A\t\tadd(A,s)\n";
        str +="\n";
        str +="\tA-B\t\tsubtract(A,B)\n";
        str +="\tA-s\t\tsubtract(A,s)\n";
        str +="\ts-A\t\tsubtract(s,A)\n";
        str +="\n";
        str +="\tv*w\t\tdotprod(v,w)\n";
        str +="\tA*B\t\tmultiply(A,B)\n";
        str +="\tA.*B\t\tmultiplyEBE(A,B)\n";
        str +="\ts*A\t\tmultiply(A,s)\n";
        str +="\tkron(A,B)\t\tkron(A,B)\n";
        str +="\thorizontal direct product\t\thdp(A,B)\n";
        str +="\n";
        str +="\tA^s\t\tpow(A,s)\n";
        str +="\tA.^s\t\tpowElem(A,s)\n";
        str +="\tA.^B\t\tpowElem(A,B)\n";
        str +="\n";
        str +="\tA/B\t\tsolve(A,B)\n";
        str +="\n";
        str +="\tA./B\t\tdivide(A,B)\n";
        str +="\ts./B\t\tdivide(s,B)\n";
        str +="\tB./s\t\tdivide(B,s)\n";
        str +="\n";
        str +="\tA'\t\tt(A)\n";
        str +="\t\t\t\n";
        str +="\thorzcat(A,B)\t\thorzcat(A,B)\n";
        str +="\tvertcat(A,B)\t\tvertcat(A,B)\n";
        str +="\tblkdiag(A,B,C,...)\t\tblkdiag(list)\t list of matrices, ArrayList can be used\n";
        str +="\n";
        str +="------------------------Matrix functions------------------------\n";
        str +="\trank(A)\t\trank(A)\n";
        str +="\tdet(A)\t\tdet(A)\n";
        str +="\tinv(A)\t\tinv(A)\n";
        str +="\ttrace(A)\t\ttrace(A)\n";
        str +="\tnorm(A)\t\tnorm(A)\n";
        str +="\tnorm(A,1)\t\tnorm_1(A)\n";
        str +="\tnorm(A,inf)\t\tnormInf(A)\n";
        str +="\tnorm(A,'fro')\t\tnormFro(A)\n";
        str +="\tcond(A)\t\tcond(A)\n";
        str +="\tlength(A)\t\tlength(A)\n";
        str +="\n";
        str +="\tsum(v)\t\tsumElem(v)\n";
        str +="\tsum(A)\t\tsumCol(A)\n";
        str +="\tprod(v)\t\tprodElem(v)\n";
        str +="\tprod(A)\t\tprodCol(A)\n";
        str +="\tmax(v)\t\tmaxElem(v)\n";
        str +="\tmax(A)\t\tmaxCol(A)\n";
        str +="\tmin(v)\t\tminElem(v)\n";
        str +="\tmin(A)\t\tminCol(A)\n";
        str +="\tmean(v)\t\tmeanElem(v)\n";
        str +="\tmean(A)\t\tmeanCol(A)\n";
        str +="\n";
        str +="\tdiag(A)\t\tdiag(A)\t extracts the main diagonal from a SQUARE matrix\n";
        str +="\tdiag(v,offset)\t\tdiagEm(v,offset)\tputs the vector in a diagonal of a zeros matrix\n";
        str +="\tdiag(A,offset)\t\tdiagEx(A,offset) \t extracts the diagonal from the matrix and returns it as a column vector\n";
        str +="\n";
        str +="\tcumprod(A)\t\tcumprod(A)\n";
        str +="\tcumsum(A)\t\tcumsum(A)\n";
        str +="\n";
        str +="\ttriu(A,offset)\t\ttriu(A,offset)\n";
        str +="\ttril(A,offset)\t\ttril(A,offset)\n";
        str +="\n";
        str +="------------------------Matrix decompositions------------------------\n";
        str +="\t[L,U,P] = LU(A)\t\tMatrix[]=lu(A)\n";
        str +="\t[Q,R,H] = QR(A)\t\tMatrix[]=qr(A)\n";
        str +="\t[V,D]=eig(A)\t\tMatrix[]=eig(A)\n";
        str +="\t[L,P]=chol(A)\t\tMatrix[]=chol(A)\n";
        str +="\t[U,S,V] = SVD(A)\t\tMatrix[]=svd(A)\n";
        str +="\t\t\t\n";


        return str;

    }
   /*
    public static void main(String[] args) {
        //testEye();
        //testKron();
        //testHDP();
        //testNorms();
        help();
        testBlkDiag();
    }

    private static void testNorms() {
        Matrix v = create("[2,0,-1]");
        Matrix m = rand(3, 2);
        testNorms(v);
        testNorms(m);
    }

    private static void testNorms(Matrix m) {
        System.out.println("norm ->" + norm(m));
        System.out.println("norm 1 ->" + norm_1(m));
        System.out.println("norm Inf ->" + normInf(m));
        System.out.println("norm Forb ->" + normFro(m) + " [OR]" + Math.sqrt(ColumnTransformer.sum(diag(multiply(t(m), m))).get(1, 1)));
        System.out.println("--------------------------");
    }

    private static void testEye() {
        System.out.println(eye(3, 2));
        System.out.println("-----------");
        System.out.println(eye(2, 3));
    }

    private static void testBlkDiag() {
        ArrayList list = new ArrayList();
        list.add(create("[1,2;3,4]"));
        list.add(create("[4,5,6;7,8,9]"));
        list.add(create("[10,11,12,13;14,15,16,17;18,19,20,21]"));
        System.out.println(blkdiag(list));
    }

    private static void testKron() {
        Matrix a = create("[1,2;3,4]");
        Matrix b = create("[4,5,6;7,8,9]");

        System.out.println(kron(a, b));
    }

    private static void testHDP() {
        Matrix c = create("[1,2;3,4]");
        Matrix d = create("[5,6;7,8]");
        System.out.println(hdp(c, d));
    }
    */
}

