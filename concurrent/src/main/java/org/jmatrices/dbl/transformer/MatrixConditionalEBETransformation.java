package org.jmatrices.dbl.transformer;

/**
 * MatrixConditionalEBETransformation
 * <p>
 * Encapsulates a conditional operation that can be performed on each element of a matrix
 * depending on their position
 * </p>
 * <p>
 * <strong>Usage:</strong>
 * <pre>
 * public static Matrix getScalarMatrix(int dim, final double scalar) {
 *       Matrix m = MatrixFactory.getMatrix(dim, dim);
 *       return MatrixEBETransformer.ebeTransform(m, new MatrixConditionalEBETransformation() {
 *           public double transformer(int row, int col, double element) {
 *               if (row == col)
 *                   return scalar;
 *               return
 *                       element;
 *           }
 *       });
 *   }
 * </pre>
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 12.03.2004
 * Time: 20:20:20
 */
public interface MatrixConditionalEBETransformation {
    /**
     * Transforms an elements value into another value
     *
     * @param row     row to which the element belongs
     * @param col     column to which the element belongs
     * @param element value of the element at row,col
     * @return transformed value
     */
    double transform(int row, int col, double element);
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
