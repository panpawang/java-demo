package org.jmatrices.dbl;
/**
 * Matrix represents a <strong>structurally immutable</strong> matrix of numbers(double) with <strong>index begining at 1</strong>.
 * <p>
 * The only way to change the elements in a matrix is through {@link #set(int, int, double)}
 * </p><p>
 * A good convention to follow is to use <code>row=1;row<=rows();row++(col=1;col<=cols();coll++)</code>  as indices while looping over matrix elements.
 * </p><p>
 * And use the normal <code>i,j,k</code> etc. to iterate over arrays!
 * </p><p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 15:52:02
 */
public interface Matrix extends java.io.Serializable {
    /**
     * Gets the number of rows in the matrix
     * <p/>
     * Counts from 1
     *
     * @return number of rows in the matrix
     */
    int rows();

    /**
     * Gets the number of columns in the matrix
     * <p/>
     * counts from 1
     *
     * @return number of columns in the matrix
     */
    int cols();

    // GETS AND SETS
    /**
     * Sets an element at the given position to a new value
     *
     * @param row   row in which the element occurs
     * @param col   column in which the element occurs
     * @param value the new value to be set
     */
    void set(int row, int col, double value);

    /**
     * Gets the value of the element at the given row and column
     *
     * @param row row in which the element occurs
     * @param col column in which the element occurs
     * @return value of the element
     */
    double get(int row, int col);

    //todo should we return arrays or matrices?  we don't have to return arrays as get returns the store array perhaps we should move these to transformer package!!!
    /**
     * Gets the entire row as a matrix
     *
     * @param row row asked for
     * @return Matrix containing the row
     */
    Matrix getRow(int row);

    /**
     * Gets the entire column as a matrix
     *
     * @param col column asked for
     * @return Matrix containing the column
     */
    Matrix getColumn(int col);

    /**
     * Gets a <strong>copy</strong> of the elements as a 2D array.
     * <p/>
     * Copy signifies the fact that any modifications made on the copy will not affect the Source matrix!
     *
     * @return copy of all elements as a 2D array
     */
    double[][] get();

    //COPY

    //Matrix copy();
    //Matrix subMatrix(int initialRow, int initialCol, int finalRow, int finalCol);
    //Matrix subMatrix(int initialRow, int initialCol);

    /**
     * Get a submatrix.
     *
     * @param rowI Initial row index
     * @param colI Initial column index
     * @param rowF Final row index
     * @param colF Final column index
     * @return A(rowI:rowF,colI:colF)
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    public Matrix getSubMatrix(int rowI, int colI, int rowF, int colF);

    /**
     * Get a submatrix.
     *
     * @param r Array of row indices.
     * @param c Array of column indices.
     * @return A(r(:),c(:))
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    public Matrix getSubMatrix(int[] r, int[] c);

    /**
     * Get a submatrix.
     *
     * @param rowI Initial row index
     * @param rowF Final row index
     * @param c    Array of column indices.
     * @return A(i0:i1,c(:))
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    Matrix getSubMatrix(int rowI, int rowF, int[] c);

    /**
     * Get a submatrix.
     *
     * @param r    Array of row indices.
     * @param colI Initial column index
     * @param colF Final column index
     * @return A(r(:),j0:j1)
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    Matrix getSubMatrix(int[] r, int colI, int colF);
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