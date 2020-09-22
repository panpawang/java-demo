package org.jmatrices.dbl.rowcoltr;

import org.jmatrices.dbl.Matrix;

/**
 * ColumnTransformation
 * <p>
 * <font color="red">this is an open thought of trying to make column operations eleganter and perhaps faster </font>
 * Check out the "see also" section
 * </p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 07.03.2004
 * Time: 20:48:58
 *
 * @see ColumnTransformer#applyColumnOperation(Matrix, ColumnTransformation)
 */
public interface ColumnTransformation {
    public double apply(Matrix d);
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
