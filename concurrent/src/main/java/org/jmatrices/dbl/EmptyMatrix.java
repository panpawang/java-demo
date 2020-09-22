package org.jmatrices.dbl;

/**
 * EmptyMatrix
 * //todo is this concept worth thinking about? there must be a way to check for emptiness isEmpty()
 * //todo It breaks object encapsulation. We willhave to add another method in Matrix interface, isEmpty, to detect and deal with emptiness
 * //todo I would suggest throwing IllegalArgumentException where ever an empty matrix might result. As in MatrixTransformer.extractDiagonal
 * <p>Author: purangp</p>
 * Date: 29.04.2004
 * Time: 03:28:16
 */
class EmptyMatrix implements Matrix {
    private final int rows=0;
    private final int cols=0;
    /**
     * Gets the number of rows in the matrix
     * <p/>
     * Counts from 1
     *
     * @return number of rows in the matrix
     */
    public int rows() {
        return rows;
    }

    /**
     * Gets the number of columns in the matrix
     * <p/>
     * counts from 1
     *
     * @return number of columns in the matrix
     */
    public int cols() {
        return cols;
    }

    /**
     * Sets an element at the given position to a new value
     *
     * @param row   row in which the element occurs
     * @param col   column in which the element occurs
     * @param value the new value to be set
     */
    public void set(int row, int col, double value) {

    }

    /**
     * Gets the value of the element at the given row and column
     *
     * @param row row in which the element occurs
     * @param col column in which the element occurs
     * @return value of the element
     */
    public double get(int row, int col) {
        return Double.NaN;
    }

    /**
     * Gets the entire row as a matrix
     *
     * @param row row asked for
     * @return Matrix containing the row
     */
    public Matrix getRow(int row) {
        return new EmptyMatrix();
    }

    /**
     * Gets the entire column as a matrix
     *
     * @param col column asked for
     * @return Matrix containing the column
     */
    public Matrix getColumn(int col) {
        return new EmptyMatrix();
    }

    /**
     * Gets a <strong>copy</strong> of the elements as a 2D array.
     * <p/>
     * Copy signifies the fact that any modifications made on the copy will not affect the Source matrix!
     *
     * @return copy of all elements as a 2D array
     */
    public double[][] get() {
        return null;
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
        return new EmptyMatrix();
    }

    /**
     * Get a submatrix.
     *
     * @param r Array of row indices.
     * @param c Array of column indices.
     * @return A(r(:),c(:))
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    public Matrix getSubMatrix(int[] r, int[] c) {
        return new EmptyMatrix();
    }

    /**
     * Get a submatrix.
     *
     * @param rowI Initial row index
     * @param rowF Final row index
     * @param c    Array of column indices.
     * @return A(i0:i1,c(:))
     * @throws ArrayIndexOutOfBoundsException Submatrix indices
     */

    public Matrix getSubMatrix(int rowI, int rowF, int[] c) {
        return new EmptyMatrix();
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
        return new EmptyMatrix();
    }

    /**
     * Returns a string representation of the object. In general, the
     * <code>toString</code> method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The <code>toString</code> method for class <code>Object</code>
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `<code>@</code>', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    public String toString() {
        return "Empty Matrix!!";
    }

}
