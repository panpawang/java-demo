package org.jmatrices.dbl.rowcoltr;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;

/**
 * ColumnTransformer captures the operations that can be carried out on columns of a matrix
 * <p>
 * Given a matrix of dimension mxn <strong> M -yields-> R</strong> ,where R is a row vector of dimension 1xn.
 * </p>
 * <p>
 * All operations on a matrix fitting this pattern can be found here!
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 20:46:52
 */
public final class ColumnTransformer {
    /**
     * <p/>
     * <font color="red">this is an open thought of trying to make column operations eleganter and perhaps faster </font>
     * Check out the "see also" section
     * <p/>
     *
     * @param m
     * @param co
     * @return
     * @see ColumnTransformation
     */
    public static Matrix applyColumnOperation(Matrix m, ColumnTransformation co) {
        throw new UnsupportedOperationException("to be implemented");
    }

    /**
     * Sums up all the columns and returns them as a row vector
     *
     * @param m Matrix
     * @return r(1xn) row vector with column sums
     */
    public static Matrix sum(Matrix m) {
        int rows_c = m.rows(), cols_c = m.cols();
        Matrix d;
        if (rows_c == 1) {
            return m;
        } else {
            d = MatrixFactory.getMatrix(1, cols_c,m);
            for (int col = 1; col <= cols_c; col++) {
                double sumCol = 0D;
                for (int row = 1; row <= rows_c; row++) {
                    sumCol += m.get(row, col);
                }
                d.set(1, col, sumCol);
            }
        }
        return d;
    }

    /**
     * Multiplies up all the elements in a column and returns them as a row vector
     *
     * @param m Matrix
     * @return r(1xn) row vector with column product
     */
    public static Matrix product(Matrix m) {
        int rows_c = m.rows(), cols_c = m.cols();
        Matrix d;
        if (rows_c == 1) {
            return m;
        } else {
            d = MatrixFactory.getMatrix(1, cols_c, m);
            for (int col = 1; col <= cols_c; col++) {
                double prodCol = 1D;
                for (int row = 1; row <= rows_c; row++) {
                    prodCol = prodCol * m.get(row, col);
                }
                d.set(1, col, prodCol);
            }
        }
        return d;
    }

    /**
     * Gets the means of  all the elements in a column and returns them as a row vector
     * <p>adjustment- calculates the mean by dividing the sum of all values by (n-1) instead of n,
     * which is the number of elements in the colum</p>
     *
     * @param m          Matrix
     * @param adjustment true or false
     * @return r(1xn) row vector with column means
     */
    public static Matrix mean(Matrix m, boolean adjustment) {
        int rows_c = m.rows(), cols_c = m.cols(), den = rows_c;
        Matrix d;
        if (adjustment)
            den = den - 1;
        if (rows_c == 1) {
            return m;
        } else {
            d = MatrixFactory.getMatrix(1, cols_c, m);
            for (int col = 1; col <= cols_c; col++) {
                double sumCol = 0D;
                for (int row = 1; row <= rows_c; row++) {
                    sumCol += m.get(row, col);
                }
                d.set(1, col, sumCol / den);
            }
        }
        return d;
    }

    /**
     * Gets the maximum element in a column and returns the selected as a row vector
     *
     * @param m Matrix
     * @return r(1xn) row vector with column's maximum values
     */
    public static Matrix max(Matrix m) {
        int rows_c = m.rows(), cols_c = m.cols();
        Matrix d;
        if (rows_c == 1) {
            return m;
        } else {
            d = MatrixFactory.getMatrix(1, cols_c, m);
            for (int col = 1; col <= cols_c; col++) {
                double max = 0D;
                boolean firstiter = true;
                for (int row = 1; row <= rows_c; row++) {
                    double tmp = m.get(row, col);
                    if (!firstiter) {
                        max = Math.max(tmp, max);
                    } else {
                        max = tmp;
                        firstiter = false;
                    }
                }
                d.set(1, col, max);
            }
        }
        return d;
    }

    /**
     * Gets the minimum element in a column and returns the selected as a row vector
     *
     * @param m Matrix
     * @return r(1xn) row vector with column's minimum values
     */
    public static Matrix min(Matrix m) {
        int rows_c = m.rows(), cols_c = m.cols(), den = rows_c;
        Matrix d;
        if (rows_c == 1) {
            return m;
        } else {
            d = MatrixFactory.getMatrix(1, cols_c, m);
            for (int col = 1; col <= cols_c; col++) {
                double min = 0D;
                boolean firstiter = true;
                for (int row = 1; row <= rows_c; row++) {
                    double tmp = m.get(row, col);
                    if (!firstiter) {
                        min = Math.min(tmp, min);
                    } else {
                        min = tmp;
                        firstiter = false;
                    }
                }
                d.set(1, col, min);
            }
        }
        return d;
    }

    private ColumnTransformer() {
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
