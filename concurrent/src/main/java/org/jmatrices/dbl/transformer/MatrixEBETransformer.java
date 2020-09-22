package org.jmatrices.dbl.transformer;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;

/**
 * MatrixEBETransformer is responsible for applying the <code>MatrixEBETransformation</code> to each element in a matrix
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 15:54:06
 *
 * @see MatrixEBETransformation
 * @see MatrixConditionalEBETransformation
 */
public final class MatrixEBETransformer {
    /**
     * Performs a ebeTransform on a matrix
     *
     * @param m     Matrix
     * @param mebet MatrixEBETransformation object responsible for the transformer
     * @return transformed matrix
     */
    public static Matrix ebeTransform(Matrix m, MatrixEBETransformation mebet) {
        int rows = m.rows(), cols = m.cols();
        Matrix transformed = MatrixFactory.getMatrix(rows, cols, m);
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                transformed.set(row, col, mebet.transform(m.get(row, col)));
            }
        }
        return transformed;
    }

    /**
     * Performs a ebeTransform on a matrix
     *
     * @param m      Matrix to be transformed
     * @param mcebet MatrixConditionalEBETransformation object responsible for the transformer
     * @return transformed matrix
     */
    public static Matrix ebeTransform(Matrix m, MatrixConditionalEBETransformation mcebet) {
        int rows = m.rows(), cols = m.cols();
        Matrix transformed = MatrixFactory.getMatrix(rows, cols, m);
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                transformed.set(row, col, mcebet.transform(row, col, m.get(row, col)));
            }
        }
        return transformed;
    }

    private MatrixEBETransformer() {
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
