package org.jmatrices.dbl.rowcoltr;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.transformer.MatrixTransformer;

/**
 * RowTransformer  captures the operations that can be carried out on rows of a matrix
 * <p>
 * Given a matrix of dimension mxn <strong> M -yields-> C</strong> ,where C is a column vector of dimension mx1.
 * </p>
 * <p>
 * All operations on a matrix fitting this pattern can be found here!
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 20:37:21
 */
public final class RowTransformer {
    /**
     * Sums up all the rows and returns them as a column vector
     *
     * @param m Matrix
     * @return c(mx1) column vector with row sums
     */
    public static Matrix sum(Matrix m) {
        return MatrixTransformer.transpose(ColumnTransformer.sum(MatrixTransformer.transpose(m)));
    }

    /**
     * Multiplies up all the elements in a row and returns them as a column vector
     *
     * @param m Matrix
     * @return c(mx1) column vector with row products
     */
    public static Matrix product(Matrix m) {
        return MatrixTransformer.transpose(ColumnTransformer.product(MatrixTransformer.transpose(m)));
    }

    /**
     * Gets the means of  all the elements in a row and returns them as a column vector
     * <p>adjustment- calculates the mean by dividing the sum of all values by (n-1) instead of n,
     * which is the number of elements in the row</p>
     *
     * @param m          Matrix
     * @param adjustment true or false
     * @return c(mx1) column vector with row means
     */
    public static Matrix mean(Matrix m, boolean adjustment) {
        return MatrixTransformer.transpose(ColumnTransformer.mean(MatrixTransformer.transpose(m), adjustment));
    }

    /**
     * Gets the maximum element in a row and returns the selected ones as a column vector
     *
     * @param m Matrix
     * @return c(mx1) column vector with row's maximum values
     */
    public static Matrix max(Matrix m) {
        return MatrixTransformer.transpose(ColumnTransformer.max(MatrixTransformer.transpose(m)));
    }

    /**
     * Gets the minimum element in a column and returns the selected as a row vector
     *
     * @param m Matrix
     * @return c(mx1) column vector with rows's minimum values
     */
    public static Matrix min(Matrix m) {
        return MatrixTransformer.transpose(ColumnTransformer.min(MatrixTransformer.transpose(m)));
    }


    private RowTransformer() {
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