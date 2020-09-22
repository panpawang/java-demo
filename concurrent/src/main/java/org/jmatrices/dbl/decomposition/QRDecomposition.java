package org.jmatrices.dbl.decomposition;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;

/**
 * QRDecomposition
 * <P>
 * For an m-by-n matrix A with m >= n, the QR decomposition is an m-by-n
 * orthogonal matrix Q and an n-by-n upper triangular matrix R so that
 * A = Q*R.
 * </p>
 * <P>
 * The QR decompostion always exists, even if the matrix does not have
 * full rank, so the constructor will never fail.  The primary use of the
 * QR decomposition is in the least squares solution of nonsquare systems
 * of simultaneous linear equations.  This will fail if isFullRank()
 * returns false
 * </p>
 * <p><font color="red">
 * The code is basically JAMA code with modifications made to fit in the scheme of things.
 * </font></p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 13.03.2004
 * Time: 13:51:42
 */
public class QRDecomposition {

/* ------------------------
   Class variables
 * ------------------------ */

    /**
     * Array for internal storage of decomposition.
     *
     * @serial internal array storage.
     */
    private double[][] QR;

    /**
     * Row and column dimensions.
     *
     * @serial column dimension.
     * @serial row dimension.
     */
    private int m, n;

    /**
     * Array for internal storage of diagonal of R.
     *
     * @serial diagonal of R.
     */
    private double[] Rdiag;

    private Matrix hint;

/* ------------------------
   Constructor
 * ------------------------ */

    /**
     * QR Decomposition, computed by Householder reflections.
     *
     * @param matrix Rectangular matrix
     *          Structure to access R and the Householder vectors and compute Q.
     */

    public QRDecomposition(Matrix matrix) {
        hint = matrix;
        // Initialize.
        QR = matrix.get();        //changed
        m = matrix.rows(); //changed
        n = matrix.cols();  //changed
        Rdiag = new double[n];

        // Main loop.
        for (int k = 0; k < n; k++) {
            // Compute 2-norm of k-th column without under/overflow.
            double nrm = 0;
            for (int i = k; i < m; i++) {
                nrm = Util.hypot(nrm, QR[i][k]);
            }

            if (nrm != 0.0) {
                // Form k-th Householder vector.
                if (QR[k][k] < 0) {
                    nrm = -nrm;
                }
                for (int i = k; i < m; i++) {
                    QR[i][k] /= nrm;
                }
                QR[k][k] += 1.0;

                // Apply transformer to remaining columns.
                for (int j = k + 1; j < n; j++) {
                    double s = 0.0;
                    for (int i = k; i < m; i++) {
                        s += QR[i][k] * QR[i][j];
                    }
                    s = -s / QR[k][k];
                    for (int i = k; i < m; i++) {
                        QR[i][j] += s * QR[i][k];
                    }
                }
            }
            Rdiag[k] = -nrm;
        }
    }

/* ------------------------
   Public Methods
 * ------------------------ */

    /**
     * Is the matrix full rank?
     *
     * @return true if R, and hence A, has full rank.
     */

    public boolean isFullRank() {
        for (int j = 0; j < n; j++) {
            if (Rdiag[j] == 0)
                return false;
        }
        return true;
    }

    /**
     * Return the Householder vectors
     *
     * @return Lower trapezoidal matrix whose columns define the reflections
     */

    public Matrix getH() {
        double[][] H = new double[m][n];    //changed
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    H[i][j] = QR[i][j];
                } else {
                    H[i][j] = 0.0;
                }
            }
        }
        return MatrixFactory.getMatrix(m, n, hint, H);  //changed
    }

    /**
     * Return the upper triangular factor
     *
     * @return R
     */

    public Matrix getR() {
        double[][] R = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    R[i][j] = QR[i][j];
                } else if (i == j) {
                    R[i][j] = Rdiag[i];
                } else {
                    R[i][j] = 0.0;
                }
            }
        }
        return MatrixFactory.getMatrix(n, n, hint, R); //changed
    }

    /**
     * Generate and return the (economy-sized) orthogonal factor
     *
     * @return Q
     */

    public Matrix getQ() {
        double[][] Q = new double[m][n];
        for (int k = n - 1; k >= 0; k--) {
            for (int i = 0; i < m; i++) {
                Q[i][k] = 0.0;
            }
            Q[k][k] = 1.0;
            for (int j = k; j < n; j++) {
                if (QR[k][k] != 0) {
                    double s = 0.0;
                    for (int i = k; i < m; i++) {
                        s += QR[i][k] * Q[i][j];
                    }
                    s = -s / QR[k][k];
                    for (int i = k; i < m; i++) {
                        Q[i][j] += s * QR[i][k];
                    }
                }
            }
        }
        return MatrixFactory.getMatrix(m, n, hint, Q);
    }

    /**
     * Least squares solution of A*X = B
     *
     * @param B A Matrix with as many rows as A and any number of columns.
     * @return X that minimizes the two norm of Q*R*X-B.
     * @throws IllegalArgumentException Matrix row dimensions must agree.
     * @throws RuntimeException         Matrix is rank deficient.
     */

    public Matrix solve(Matrix B) {
        if (B.rows() != m) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (!this.isFullRank()) {
            throw new RuntimeException("Matrix is rank deficient.");
        }

        // Copy right hand side
        int nx = B.cols();
        double[][] X = B.get();        //changed

        // Compute Y = transpose(Q)*B
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < nx; j++) {
                double s = 0.0;
                for (int i = k; i < m; i++) {
                    s += QR[i][k] * X[i][j];
                }
                s = -s / QR[k][k];
                for (int i = k; i < m; i++) {
                    X[i][j] += s * QR[i][k];
                }
            }
        }
        // Solve R*X = Y;
        for (int k = n - 1; k >= 0; k--) {
            for (int j = 0; j < nx; j++) {
                X[k][j] /= Rdiag[k];
            }
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < nx; j++) {
                    X[i][j] -= X[k][j] * QR[i][k];
                }
            }
        }
        //return (new Matrix(X, n, nx).getMatrix(0, n - 1, 0, nx - 1));
        return MatrixFactory.getMatrix(B.rows(), B.cols(), hint, X).getSubMatrix(1, 1, n, nx); //changed  taking care of 1,1 (index start) and differences between getMatrix and getSubMatrix
    }
}
