package org.jmatrices.dbl;

import org.jmatrices.dbl.transformer.MatrixConditionalEBETransformation;
import org.jmatrices.dbl.transformer.MatrixEBETransformation;
import org.jmatrices.dbl.transformer.MatrixEBETransformer;

import java.util.Iterator;
import java.util.List;

/**
 * MatrixFactory is one-stop shop for creating matrices.
 * <ol>
 * <li>Rectangular(Square, Vector) matrices, initialized to 0.0, scalar or array of values</li>
 * <li>Scalar Matrices including Identity matrix</li>
 * </ol>
 * <p/>
 * <font color="blue">
 * todo how to provide a mechanism to change implementations (from LightMatrixImpl to HeavyMatrixImpl), intelligent choice or user's choice?
 * <br/>todo consider deserialization of matrices from ascii, mathml, xml files.
 * <br/>todo serialization? through matrix interface ?
 * </font>
 * </p><p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 15:56:59
 */
public class MatrixFactory {
    /**
     * Please refer to the java doc of EmptyMatrix to see the reason for deprecation
     *
     * @return
     * @deprecated
     */
    public static Matrix getEmptyMatrix() {
        return new EmptyMatrix();
    }

    /**
     * Gets a matrix of the asked dimensions.
     * <p/>
     * All elements are set to 0.0
     *
     * @param rows number of rows in the matrix (> 1)
     * @param cols number of columns in the matrix (> 1)
     * @param hint acts as a hint for the right implementation to use
     * @return Matrix of the given dimensions
     */
    public static Matrix getMatrix(int rows, int cols, Matrix hint) {
        if (hint instanceof HeavyMatrixImpl)
            return new HeavyMatrixImpl(rows, cols);
        return new LightMatrixImpl(rows, cols);
    }

    /**
     * Gets a matrix of the asked dimensions, filled with random values
     * <p/>
     *
     * @param rows number of rows in the matrix (> 1)
     * @param cols number of columns in the matrix (> 1)
     * @param hint acts as a hint for the right implementation to use
     * @return Matrix of the given dimensions
     */
    public static Matrix getRandomMatrix(int rows, int cols, Matrix hint) {
        return MatrixEBETransformer.ebeTransform(getMatrix(rows, cols, hint), new MatrixEBETransformation() {
            public double transform(double element) {
                return Math.random();
            }
        });
    }
    
    public static Matrix getRandomIntMatrix(int rows, int cols, Matrix hint) {
        return MatrixEBETransformer.ebeTransform(getMatrix(rows, cols, hint), new MatrixEBETransformation() {
            public double transform(double element) {
                return (int)(Math.random()*100);
            }
        });
    }
    

    /**
     * Gets a matrix of the asked dimensions.
     * <p/>
     * All elements are set to scalar.
     *
     * @param rows   number of rows in the matrix (> 1)
     * @param cols   number of columns in the matrix (> 1)
     * @param hint   acts as a hint for the right implementation to use
     * @param scalar initial value of the elements
     * @return Matrix of the given dimensions and value
     */
    public static Matrix getMatrix(int rows, int cols, Matrix hint, double scalar) {
        return scalarAddition(getMatrix(rows, cols, hint), scalar);
    }

    /**
     * Gets a matrix of the asked dimensions.
     * <p/>
     * All elements are set to values in the passed array.
     *
     * @param rows   number of rows in the matrix (> 1)
     * @param cols   number of columns in the matrix (> 1)
     * @param hint   acts as a hint for the right implementation to use
     * @param values initial value of the elements
     * @return Matrix of the given dimensions and values
     */
    public static Matrix getMatrix(int rows, int cols, Matrix hint, double values[][]) {
        Matrix m = getMatrix(rows, cols, hint);
        return populateElements(m, values);
    }

    /**
     * Gets an Identity matrix
     *
     * @param dim  dimension of the square matrix
     * @param hint acts as a hint for the right implementation to use
     * @return Square matrix with the diagonal elements set to 1.
     */
    public static Matrix getIdentityMatrix(int dim, Matrix hint) {
        return getScalarMatrix(dim, hint, 1);
    }

    /**
     * Gets a scalar matrix.
     *
     * @param dim    dimension of the square matrix
     * @param hint   acts as a hint for the right implementation to use
     * @param scalar the value the main diagonal elements have to be set to
     * @return Square matrix with the diagonal elements set to scalar value.
     */
    public static Matrix getScalarMatrix(int dim, Matrix hint, final double scalar) {
        Matrix m = MatrixFactory.getMatrix(dim, dim, hint);
        return MatrixEBETransformer.ebeTransform(m, new MatrixConditionalEBETransformation() {
            public double transform(int row, int col, double element) {
                if (row == col)
                    return scalar;
                return
                        element;
            }
        });
    }

    /**
     * Gets a column vector with list values composing the vector's values.
     * <br/>
     * <b>Note:</b> will throw an <code>IllegalArgumentException</code> if <code>list.size()<=0</code>
     *
     * @param list list containing the <code>Double</code> values that will be used to compose the vector
     * @param hint acts as a hint for the right implementation to use
     * @return column vector
     */
    public static Matrix getMatrix(List list, Matrix hint) {
        if (list.size() <= 0)
            throw new IllegalArgumentException("Array list size should atleast be 1");
        // return MatrixFactory.getEmptyMatrix();
        Matrix cv = getMatrix(list.size(), 1, hint);
        Iterator iter = list.iterator();
        int row = 1;
        while (iter.hasNext()) {
            Double o = (Double) iter.next();
            cv.set(row, 1, o.doubleValue());
            row++;
        }
        return cv;
    }

    /**
     * PRIVATE MEMBERS
     */


    private MatrixFactory() {
    }

    /**
     * todo perhaps should be moved to transformer package and made public!
     *
     * @param m
     * @param s
     * @return
     */
    private static Matrix scalarAddition(Matrix m, final double s) {
        return MatrixEBETransformer.ebeTransform(m, new MatrixEBETransformation() {
            public double transform(double element) {
                return element + s; //could be +, *, -, /
            }
        });
    }

    private static Matrix populateElements(Matrix m, double[][] elems) {
        //todo - performance hit but better management of code - any better solution?!
        if (checkConformity(elems, m.rows(), m.cols())) {
            for (int i = 0; i < elems.length; i++) {
                for (int j = 0; j < elems[i].length; j++) {
                    m.set(i + 1, j + 1, elems[i][j]);
                }
            }
            return m;
        } else
            throw new IllegalArgumentException("Array to initialise doesn't conform with matrix dimensions");
    }


    private static boolean checkConformity(double[][] elems, int rows, int cols) {
        if (elems.length != rows) {
            return false;
        } else {
            for (int row = 0; row < elems.length; row++) {
                if (elems[row].length != cols) return false;
            }
        }
        return true;
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