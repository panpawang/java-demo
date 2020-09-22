package org.jmatrices.dbl.operator;

/**
 * MatrixEBEOperation
 * <p>
 * Encapsulates an operation that can be performed on elements at the same position in
 * two matrices.
 * </p>
 * <p>
 * <strong>Usage:</strong> from {@link MatrixOperator#add(org.jmatrices.dbl.Matrix, org.jmatrices.dbl.Matrix)}
 * <pre>
 * public static Matrix add(Matrix a, Matrix b) {
 *       return applyEBEOperation(a, b, new MatrixEBEOperation() {
 *           public double apply(double a, double b) {
 *               return a + b;
 *           }
 *       });
 *   }
 * </pre>
 * </p>
 * Check out the "See Also" list to find some more methods that use this method
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 19:17:46
 *
 * @see MatrixOperator
 * @see MatrixOperator#subtract(org.jmatrices.dbl.Matrix, org.jmatrices.dbl.Matrix)
 * @see MatrixOperator#multiplyEBE(org.jmatrices.dbl.Matrix, org.jmatrices.dbl.Matrix)
 * @see MatrixOperator#divideEBE(org.jmatrices.dbl.Matrix, org.jmatrices.dbl.Matrix)
 */
public interface MatrixEBEOperation {
    /**
     * Applies the operation to the elements
     * <p>
     * <font color="blue">
     * todo - perhaps we should provide row, col for better control!  
     * </font>
     * </p>
     * @param a element of a matrix
     * @param b element of a matrix
     * @return result of application of an operation
     */
    double apply(double a, double b);
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
