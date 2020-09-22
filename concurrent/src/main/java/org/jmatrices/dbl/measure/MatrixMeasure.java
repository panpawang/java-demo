package org.jmatrices.dbl.measure;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.decomposition.LUDecomposition;
import org.jmatrices.dbl.decomposition.SingularValueDecomposition;
import org.jmatrices.dbl.rowcoltr.ColumnTransformer;
import org.jmatrices.dbl.rowcoltr.RowTransformer;
import org.jmatrices.dbl.transformer.MatrixEBETransformation;
import org.jmatrices.dbl.transformer.MatrixEBETransformer;
import org.jmatrices.dbl.transformer.MatrixTransformer;


/**
 * MatrixMeasure provides important measures associated with a matrix
 * <p/>
 * Given a matrix <strong> M -yields-> value</strong> ,where value is a number.
 * </p>
 * <p/>
 * All operations on a matrix fitting this pattern can be found here!
 * </p>
 * <p/>
 * Author: purangp
 * </p>
 * Date: 08.03.2004
 * Time: 00:09:12
 */
public final class MatrixMeasure {
    //classical measures
    /**
     * Gets the getDeterminant of a non-singular square matrix.
     * <br/>todo is the observation about non-singularity true?
     *
     * @param m Matrix
     * @return detrminant
     */
    public static double getDeterminant(Matrix m) {
        return (new LUDecomposition(m)).det();
    }

    /**
     * Gets the trace of the matrix
     *
     * @param m Matrix
     * @return trace
     */
    public static double getTrace(Matrix m) {
        return ColumnTransformer.sum(MatrixTransformer.diagonal(m)).get(1, 1);
    }

    /**
     * Gets the rank of the matrix
     *
     * @param m Matrix
     * @return rank
     */
    public static int getRank(Matrix m) {
        return (new SingularValueDecomposition(m)).rank();
    }

    //todo do we really need get suffix? as we haven't used it any where else .. that doesn't seem very consistent!!!
    //reasons for .. because it is a gettable property of a matrix
    /**
     * Gets the maximum value occuring in the matrix
     *
     * @param m Matrix
     * @return maximum value
     */
    public static double getMax(Matrix m) {
        return (RowTransformer.max(ColumnTransformer.max(m))).get(1, 1);
    }

    /**
     * Gets the minimum value occuring in the matrix
     *
     * @param m Matrix
     * @return minimum value
     */
    public static double getMin(Matrix m) {
        return (RowTransformer.min(ColumnTransformer.min(m))).get(1, 1);
    }

    /**
     * Gets the sum of all values
     *
     * @param m Matrix
     * @return sum of all values
     */
    public static double getSum(Matrix m) {
        return (RowTransformer.sum(ColumnTransformer.sum(m))).get(1, 1);
    }

    /**
     * Gets the product of all values
     *
     * @param m Matrix
     * @return product of all values
     */
    public static double getProduct(Matrix m) {
        return (RowTransformer.product(ColumnTransformer.product(m))).get(1, 1);
    }

    /**
     * Gets the mean or average of all the values
     * <p>adjustment- calculates the mean by dividing the sum of all values by (n-1) instead of n,
     * which is the number of elements in the matrix</p>
     *
     * @param m          Matrix
     * @param adjustment true or false
     * @return adjusted or unadjusted mean of all values
     */
    public static double getMean(Matrix m, boolean adjustment) {
        double prod = m.rows() * m.cols();
        if (adjustment)
            return ((prod / (prod - 1)) * getMean(m, false));
        else
            return RowTransformer.mean(ColumnTransformer.mean(m, false), false).get(1, 1);
    }


    /**
     * Gets the maximum column sum
     *
     * @param m Matrix
     * @return maximum column sum
     */
    public static double getNorm1(Matrix m) {
        return RowTransformer.max(ColumnTransformer.sum(MatrixEBETransformer.ebeTransform(m, new MatrixEBETransformation() {
            public double transform(double element) {
                return Math.abs(element);
            }
        }))).get(1, 1);
    }

    /**
     * Gets the maximum singular value
     *
     * @param m Matrix
     * @return maximum singular value
     */
    public static double getNorm2(Matrix m) {
        return new SingularValueDecomposition(m).norm2();
    }


    /**
     * Gets the maximum row sum
     *
     * @param m Matrix
     * @return maximum row sum
     */
    public static double getNormInfinity(Matrix m) {
        return ColumnTransformer.max(RowTransformer.sum(MatrixEBETransformer.ebeTransform(m, new MatrixEBETransformation() {
            public double transform(double element) {
                return Math.abs(element);
            }
        }))).get(1, 1);
    }

    /**
     * Gets the sqrt of sum of squares of all elements.
     *
     * @param m Matrix
     * @return sqrt of sum of squares of all elements.
     */
    public static double getNormFrobenius(Matrix m) {
        return Math.sqrt(MatrixMeasure.getSum(MatrixEBETransformer.ebeTransform(m, new MatrixEBETransformation() {
            public double transform(double element) {
                return element * element;
            }
        })));
        //Math.sqrt(ColumnTransformer.sum(diag(multiply(t(m),m))).get(1,1))
    }

    /**
     * Gets the ratio of largest to smallest singular value.
     *
     * @param m Matrix
     * @return ratio of largest to smallest singular value.
     */
    public static double getCondition(Matrix m) {
        return new SingularValueDecomposition(m).cond();
    }


    /**
     * Gets the length of the matrix, which is treated to be the maximum dimension.
     *
     * @param m
     * @return Math.max(m.rows(),m.cols())
     */
    public static int length(Matrix m) {
        return Math.max(m.rows(), m.cols());
    }

    /**
     * Gets the breadth of the matrix, which is treated to be the minimum dimension.
     *
     * @param m
     * @return Math.min(m.rows(),m.cols())
     */
    public static int breadth(Matrix m) {
        return Math.min(m.rows(), m.cols());
    }

    /**
     * private constructor to disallow object creation or extensions
     */
    private MatrixMeasure() {
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