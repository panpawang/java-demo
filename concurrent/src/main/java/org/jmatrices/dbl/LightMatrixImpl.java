package org.jmatrices.dbl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * LightMatrixImpl
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 16:06:06
 */
class LightMatrixImpl implements Matrix {
    protected int rows, cols;
    protected double[][] rowView;

    protected LightMatrixImpl() {
    }

    public LightMatrixImpl(int rows, int cols) {
        if (rows < 1 || cols < 1)
            throw new IllegalArgumentException("Rows and/or Columns can't be less than 1");
        else {
            this.rows = rows;
            this.cols = cols;
            rowView = new double[rows][cols];
        }
    }

    /**
     * Counts from 1
     *
     * @return
     */
    public int rows() {
        return rows;
    }

    /**
     * counts from 1
     *
     * @return
     */
    public int cols() {
        return cols;
    }

    /**
     * @param row
     * @param col
     * @param value
     */
    public void set(int row, int col, double value) {
        rowView[row - 1][col - 1] = value;
    }

    /**
     * @param row
     * @param col
     * @return
     */
    public double get(int row, int col) {
        return rowView[row - 1][col - 1];
    }


    //todo should we return arrays or matrices?  we don't have to return arrays as get returns the rowView array perhaps we should move these to transformer package!!!
    //
    public Matrix getRow(int row) {
        return getRowMatrix(cols, rowView[row-1]);
    }

    protected Matrix getRowMatrix(int cols, double[] elems) {
        if (elems.length != cols) throw new IllegalArgumentException("Length of elems and cols don't conform");
        Matrix lm = new LightMatrixImpl(1, cols);
        for (int elem = 0; elem < elems.length; elem++) {
            lm.set(1, elem + 1, elems[elem]);  //add 1 to elem as set expects index starting from 1,1
        }
        return lm;
    }

    public Matrix getColumn(int j) {
        double[] column = new double[rows];
        for (int row = 0; row < rowView.length; row++) {
            for (int col = 0; col < rowView[row].length; col++) {
                if (col == j)
                    column[j] = rowView[row][col];
            }
        }
        return getColumnMatrix(rows, column);
    }

    protected Matrix getColumnMatrix(int rows, double[] elems) {
        if (elems.length != rows) throw new IllegalArgumentException("Length of elems and cols don't conform");
        Matrix lm = new LightMatrixImpl(rows, 1);
        for (int elem = 0; elem < elems.length; elem++) {
            lm.set(elem + 1, 1, elems[elem]);//add 1 to elem as set expects index starting from 1,1
        }
        return lm;
    }

    /**
     * @return
     */
    public double[][] get() {
        double[][] store = new double[rows][cols];
        for (int i = 0; i < rowView.length; i++) {
            for (int j = 0; j < rowView[i].length; j++) {
                store[i][j] = rowView[i][j];
            }
        }
        return store;
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
        return getSubMatrix(new LightMatrixImpl(rowF - rowI + 1, colF - colI + 1), rowI, colI, rowF, colF);
    }

    //The reason we implement this way so as to allow each matrix implementation to use themeselves as the matrices
    //and yet we can share the code between
    protected Matrix getSubMatrix(Matrix m, int rowI, int colI, int rowF, int colF) {
        int rows_m = m.rows(), cols_m = m.cols();
        if (rows_m > rows() || cols_m > cols)
            throw new IllegalArgumentException("The submatrix being extracted violates dimension constraints");
        else {
            //for (int row = rowI, rowm = 1; row <= rowF; row++, rowm++) {
            for (int row = rowI; row <= rowF; row++) {
                //for (int col = colI, colm = 1; col <= colF; col++, colm++) {
                for (int col = colI; col <= colF; col++) {
                    //m.set(rowm,colm,get(row,col));
                    m.set(row - rowI + 1, col - colI + 1, get(row, col));
                }
            }
        }
        return m;
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
        return getSubMatrix(new LightMatrixImpl(r.length, c.length), r, c);
    }

    protected Matrix getSubMatrix(Matrix m, int[] r, int[] c) {
        for (int row = 1; row <= r.length; row++) {
            for (int col = 1; col <= c.length; col++) {
                m.set(row, col, get(r[row - 1], c[col - 1]));
            }
        }
        return m;
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
        return getSubMatrix(new LightMatrixImpl(rowF - rowI + 1, c.length), rowI, rowF, c);
    }

    protected Matrix getSubMatrix(Matrix m, int rowI, int rowF, int[] c) {
        if (m.rows() > rows())
            throw new IllegalArgumentException("The submatrix being extracted violates dimension constraints");
        else {
            for (int row = rowI; row <= rowF; row++) {
                for (int col = 1; col <= c.length; col++) {
                    m.set(row - rowI + 1, col, get(row, c[col - 1]));
                }
            }
        }
        return m;
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
        return getSubMatrix(new LightMatrixImpl(r.length, colF - colI + 1), r, colI, colF);
    }

    public Matrix getSubMatrix(Matrix m, int[] r, int colI, int colF) {
        if (m.cols() > cols())
            throw new IllegalArgumentException("The submatrix being extracted violates dimension constraints");
        else {
            for (int row = 1; row <= r.length; row++) {
                for (int col = colI; col <= colF; col++) {
                    m.set(row, col - colI + 1, get(r[row - 1], col));
                }
            }
        }
        return m;
    }

     public String toString() {
        //todo make this configurable!!
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(8);
        format.setMinimumFractionDigits(2);
        format.setGroupingUsed(false);
        StringBuffer matrix = new StringBuffer();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                matrix.append(format.format(this.get(row, col)) + " ");
                //matrix.append(Math.round(this.get(row, col)) + " ");
            }
            matrix.append("\n");
        }
        return matrix.toString();
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