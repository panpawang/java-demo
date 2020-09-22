package org.jmatrices.dbl.transformer;

/**
 * MatrixEBETransformation
 * <p>
 * Encapsulates an operation that can be performed on each element of a matrix
 * </p>
 * <p>
 * Meant for scalar ebe transformations or ebe function application.
 * </p>
 * <p>
 * <b>Examples:</b>  As annonymous classes ..
 * <pre>
 * private static Matrix scalarAddition(Matrix m, final double s) {
 *       return MatrixEBETransformer.ebeTransform(m, new MatrixEBETransformation() {
 *           public double transformer(double element) {
 *               return element + s; //could be +, *, -, /
 *           }
 *       });
 *   }
 * </pre>
 * <pre>
 * // unary minus or negation
 * new MatrixEBETransformation() {
 *      double transformer(double element) {
 *          return -element;
 *      }
 * };
 * </pre>
 * <pre>
 * //scalar multiplication
 * new MatrixEBETransformation() {
 *      double transformer(double element) {
 *          return 2 * element;
 *      }
 * };
 * </pre>
 * <pre>
 * // function application
 * new MatrixEBETransformation() {
 *      double transformer(double element) {
 *          return Math.sin(element);
 *      }
 * };
 * </pre>
 * </p>
 * <font color="red">
 * This interface may be dropped in the future in favour of the conditional one
 * as that one essentially allows for everything that this interface allows ..
 * or we might drop the MatrixConditionalEBETransformation,
 * replacing the method here with the one in MatrixConditionalEBETransformation
 * </font>
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 15:54:31
 *
 * @see MatrixEBETransformer
 */
public interface MatrixEBETransformation {
    /**
     * Transforms an elements value into another value
     * 
     * @param element value of the element
     * @return transformed value
     */
    double transform(double element);


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
