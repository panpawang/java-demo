package org.jmatrices.dbl;

/**
 * MuttableMatrix represents a matrix who's structure is muttable.
 * <br/>
 * This is a strict no no. The reason it exists is because we need it for better handling different implementations of  
 * <p>Author: purangp</p>
 * Date: 29.04.2004
 * Time: 20:02:38
 */
interface MuttableMatrix extends Matrix{
    void setRows(int rows);
    void setCols(int cols);
}
