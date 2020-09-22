package org.jmatrices.dbl;

/**
 * HeavyMatrixImpl
 * <p>
 * todo perhaps it is better to implement it through delegation rather than extending LightMatrixImpl
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 10.03.2004
 * Time: 16:06:08
 */
class HeavyMatrixImpl extends LightMatrixImpl implements Matrix {

    private double[][] columnView;

    protected HeavyMatrixImpl() {

    }

    public HeavyMatrixImpl(int rows, int cols) {
        super(rows, cols);
        columnView = new double[cols][rows];
    }

    /**
     * @param i
     * @param j
     * @param value
     */
    public void set(int i, int j, double value) {
        super.set(i, j, value);
        columnView[j - 1][i - 1] = value;
    }

    public Matrix getRow(int i) {
        return getRowMatrix(cols, rowView[i - 1]);
    }

    //todo there is just one line different between this method and the method it overerides
    protected Matrix getRowMatrix(int cols, double[] elems) {
        if (elems.length != cols) throw new IllegalArgumentException("Length of elems and cols don't conform");
        Matrix hm = new HeavyMatrixImpl(1, cols);     //the only difference
        for (int elem = 0; elem < elems.length; elem++) {
            hm.set(1, elem + 1, elems[elem]);  //add 1 to elem as set expects index starting from 1,1
        }
        return hm;
    }

    public Matrix getColumn(int j) {
        return getColumnMatrix(rows, columnView[j - 1]);
    }

    //todo there is just one line different between this method and the method it overerides
    protected Matrix getColumnMatrix(int rows, double[] elems) {
        if (elems.length != rows) throw new IllegalArgumentException("Length of elems and cols don't conform");
        Matrix hm = new HeavyMatrixImpl(rows, 1); //the only difference
        for (int elem = 0; elem < elems.length; elem++) {
            hm.set(elem + 1, 1, elems[elem]);//add 1 to elem as set expects index starting from 1,1
        }
        return hm;
    }

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
    public Matrix getSubMatrix(int rowI, int colI, int rowF, int colF) {
        return getSubMatrix(new HeavyMatrixImpl(rowF - rowI + 1, colF - colI + 1), rowI, colI, rowF, colF);
    }

    /**
     * Get a submatrix.
     *
     * @param r Array of row indices.
     * @param c Array of column indices.
     * @return A(r(:),c(:))
     * @throws ArrayIndexOutOfBoundsException
     */

    public Matrix getSubMatrix(int[] r, int[] c) {
        return getSubMatrix(new HeavyMatrixImpl(r.length, c.length), r, c);
    }

    /**
     * Get a submatrix.
     *
     * @param rowI Initial row index
     * @param rowF Final row index
     * @param c    Array of column indices.
     * @return A(i0:i1,c(:))
     * @throws IllegalArgumentException
     * @throws ArrayIndexOutOfBoundsException
     */

    public Matrix getSubMatrix(int rowI, int rowF, int[] c) {
        return getSubMatrix(new HeavyMatrixImpl(rowF - rowI + 1, c.length), rowI, rowF, c);
    }

    /**
     * Get a submatrix.
     *
     * @param r    Array of row indices.
     * @param colI Initial column index
     * @param colF Final column index
     * @return A(r(:),j0:j1)
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    public Matrix getSubMatrix(int[] r, int colI, int colF) {
        return getSubMatrix(new HeavyMatrixImpl(r.length, colF - colI + 1), r, colI, colF);
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