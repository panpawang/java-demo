package org.jmatrices.dbl;

/**
 * MatrixVectorImpl
 * <p>
 * <font color="blue">
 * todo - open thought!
 * </font>
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 21:57:18
 */
public class MatrixVectorImpl implements MatrixVector {
    int cols, rows;

    Vector[] rowVectors;
    Vector[] columnVectors;

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public double get(int row, int col) {
        return 0;
    }

    public void set(int row, int col, double value) {
    }

    public Vector getRowVector(int row) {
        return null;
    }

    public Vector getColumnVector(int col) {
        return null;
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