package org.jmatrices.dbl.measure;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;
import org.jmatrices.dbl.decomposition.CholeskyDecomposition;
import org.jmatrices.dbl.decomposition.LUDecomposition;
import org.jmatrices.dbl.transformer.MatrixTransformer;

/**
 * MatrixProperty
 * <p>
 * Given a matrix <strong> M -yields-> value</strong> ,where value is boolean (true or false).
 * </p>
 * <p>
 * All operations on a matrix fitting this pattern can be found here!
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 08.03.2004
 * Time: 23:15:14
 */
public final class MatrixProperty {

    /**
     * Determines whether or not a matrix is a Column Vector
     *
     * @param m
     * @return true iff m has only one column
     */
    public static boolean isColumnVector(Matrix m) {
        return m.cols() == 1;
    }

    /**
     * Determines whether or not a matrix is a Row Vector
     *
     * @param m
     * @return true iff m has only one row
     */
    public static boolean isRowVector(Matrix m) {
        return m.rows() == 1;
    }

     /**
     * Determines whether or not a matrix is a Row or a Column Vector
     *
     * @param m
     * @return true iff m has only one row
     */
    public static boolean isVector(Matrix m) {
        return (m.cols() ==1 || m.rows() == 1);
    }

    /**
     * Determines whether or not a matrix is a <strong>square</strong> matrix.
     *
     * @param m
     * @return true iff m has as many columns as it has rows
     */
    public static boolean isSquare(Matrix m) {
        return m.rows() == m.cols();
    }

    /**
     * Determines whether or not a matrix is <strong>Symmetric</strong>
     *
     * @param m
     * @return true iff A' = A
     */
    public static boolean isSymmetric(Matrix m) {
        return MatricesMeasure.areEqual(m, MatrixTransformer.transpose(m));
    }

    /**
     * Determines whether or not a matrix is <strong>SkewSymmetric</strong>
     *
     * @param m
     * @return true if A' = -A
     */
    public static boolean isSkewSymmetric(Matrix m) {
        return MatricesMeasure.areEqual(MatrixTransformer.transpose(m), MatrixTransformer.negate(m));
    }

    /**
     * Determines whether or not a matrix is <strong>Idempotent</strong>
     *
     * @param m
     * @return true if A = AA = AAA = AAAA...  or A = A<sup>2</sup> = A<sup>3</sup>= A<sup>4</sup> ..
     */
    public static boolean isIdempotent(Matrix m) {
        return MatricesMeasure.areEqual(m, MatrixTransformer.pow(m, 2));
    }

    //magic square - a square matrix of n rows and columns; the first n-squared integers are arranged in the cells of the matrix is such a way that the sum of any row or column or diagonal is the same
    /**
     * <font color="blue">todo implement</font>
     *
     * @param m
     * @return
     */
    public static boolean isMagicSquare(Matrix m) {
        throw new UnsupportedOperationException("to be implemented");
    }


    /**
     * Determines whether or not a square matrix is <strong>Singular</strong> i.e has no inverse.
     *
     * @param m Matrix
     * @return true iff A<sup>-1</sup> exists
     */
    public static boolean isSingular(Matrix m) {
        return new LUDecomposition(m).isSingular();
    }

    /**
     * Determines whether or not a square matrix is a <strong>Diagonal</strong>  matrix
     *
     * @param m Matrix
     * @return true iff a<sub>i</sub><sub>j</sub>=0 , for all i <> j
     * @see MatrixProperty#isScalar(Matrix)
     */
    public static boolean isDiagonal(Matrix m) {
        if (isSquare(m)) {
            for (int row = 1; row <= m.rows(); row++) {
                for (int col = 1; col <= m.cols(); col++) {
                    if (row != col && m.get(row, col) != 0)
                        return false;
                }
            }
            return true;
        } else
            return false;
    }

    /**
     * Determines whether or not a diagonal matrix is an <strong>Identity</strong> matrix
     *
     * @param m Matrix
     * @return true iff a<sub>i</sub><sub>j</sub>=0 , for all i <> j  and a<sub>i</sub><sub>j</sub>=1 , for all i = j
     */
    public static boolean isIdentity(Matrix m) {
        return isSquare(m) && MatricesMeasure.areEqual(m, MatrixFactory.getIdentityMatrix(m.rows(), null));
    }

    /**
     * Determines whether or not a diagonal matrix is an <strong>Unit</strong> matrix or an <strong>Identity</strong> matrix
     *
     * @param m Matrix
     * @return true iff a<sub>i</sub><sub>j</sub>=0 , for all i <> j  and a<sub>i</sub><sub>j</sub>=1 , for all i = j
     */
    public static boolean isUnit(Matrix m) {
        return isIdentity(m);
    }

    /**
     * Determines whether or not a matrix is upper triangular in form
     *
     * @param m
     * @return true if matrix is upper triangular in form
     */
    public static boolean isUpperTriangular(Matrix m) {
        return MatricesMeasure.areEqual(MatrixTransformer.extractUpperTriangular(m,0),m);
    }

    /**
     * Determines whether or not a matrix is lower triangular in form
     *
     * @param m
     * @return true if matrix is lower triangular in form
     */
    public static boolean isLowerTriangular(Matrix m) {
        return MatricesMeasure.areEqual(MatrixTransformer.extractLowerTriangular(m,0),m);
    }

    /**
     * Determines whether or not a square matrix is a <strong>Scalar</strong>  matrix
     * <p/>
     * scalar matrix - a diagonal matrix in which all of the diagonal elements are areEqual
     * </p>
     * //todo find a better way?
     *
     * @param m Matrix
     * @return true iff a<sub>i</sub><sub>j</sub>=0 , for all i <> j  and a<sub>i</sub><sub>j</sub>=c , for all i = j
     * @see MatrixProperty#isDiagonal(Matrix)
     */
    public static boolean isScalar(Matrix m) {
        //we could have done this in many ways ..
        double tmp = 0.0;
        boolean firstIter = true;
        if (isSquare(m)) {
            for (int row = 1; row <= m.rows(); row++) {
                for (int col = 1; col <= m.cols(); col++) {
                    if (row != col && m.get(row, col) != 0)
                        return false;
                    else if (row == col) {
                        if (firstIter) {
                            tmp = m.get(row, col);
                            firstIter = false;
                        } else {
                            if (tmp != m.get(row, col))
                                return false;
                        }
                    }
                }
            }
            return true;
        } else
            return false;
    }


    //Not sure but I hope it does what it says!
    public static boolean isSymetricPositiveDefinite(Matrix m) {
        return new CholeskyDecomposition(m).isSPD();
    }

    /**
     * <font color="blue">todo implement</font>
     *
     * @param m
     * @return
     */
    public static boolean isDefinitePositive(Matrix m) {
        throw new UnsupportedOperationException("to be implemented");
    }

    /**
     * <font color="blue">todo implement</font>
     *
     * @param m
     * @return
     */
    public static boolean isSemiDefinitePositive(Matrix m) {
        throw new UnsupportedOperationException("to be implemented");
    }

    /**
     * <font color="blue">todo implement</font>
     */
    public class MatrixBandwidth {
        //check
        //http://www.cs.ut.ee/~toomas_l/linalg/lin1/node13.html
    }

    private MatrixProperty() {
    }
}

/**
 *  Jmatrices - Matrix Library
    Copyright (C) 2004  Piyush Purang

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library, see License.txt; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */