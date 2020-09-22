package org.jmatrices.dbl.decomposition;

import org.jmatrices.dbl.Matrix;
import org.jmatrices.dbl.MatrixFactory;

/**
 * LUDecomposition
 * <P>
 * For an m-by-n matrix A with m >= n, the LU decomposition is an m-by-n
 * unit lower triangular matrix L, an n-by-n upper triangular matrix U,
 * and a permutation vector piv of length m so that A(piv,:) = L*U.
 * If m < n, then L is m-by-m and U is m-by-n.
 * </p>
 * <P>
 * The LU decompostion with pivoting always exists, even if the matrix is
 * singular, so the constructor will never fail.  The primary use of the
 * LU decomposition is in the solution of square systems of simultaneous
 * linear equations.  This will fail if isNonsingular() returns false.
 * </p>
 * <p>
 * http://www.hku.hk/cc/sp2/software/hpf/Course/HTMLQuestionsnode65.html
 * http://csep10.phys.utk.edu/guidry/phys594/lectures/linear_algebra/lanotes/node3.html
 * </p>
 * <p><font color="red">
 * The code is basically JAMA code with modifications made to fit in the scheme of things.
 * </font></p>
 * <p>
 * Author: purangp
 * </p>
 * Date: 08.03.2004
 * Time: 23:37:48
 */
public class LUDecomposition {
/* ------------------------
   Class variables
 * ------------------------ */

    /**
     * Array for internal storage of decomposition.
     *
     * @serial internal array storage.
     */
    private double[][] LU;

    /**
     * Row and column dimensions, and pivot sign.
     *
     * @serial column dimension.
     * @serial row dimension.
     * @serial pivot sign.
     */
    private int m, n, pivsign;

    /**
     * Internal storage of pivot vector.
     *
     * @serial pivot vector.
     */
    private int[] piv;

    private Matrix hint;

/* ------------------------
   Constructor
 * ------------------------ */
    /**
     * LU Decomposition
     *
     * @param a Rectangular matrix
     */
    public LUDecomposition(Matrix a) {   //renamed A to a
        hint = a;
        // Use a "left-looking", dot-product, Crout/Doolittle algorithm.

        LU = a.get();     //changed
        m = a.rows();
        n = a.cols();
        piv = new int[m];
        for (int i = 0; i < m; i++) {
            piv[i] = i + 1;    //changed so as to begin the indices from 1 and not 0
            //either we could do it here or we could have adjusted it later
        }
        pivsign = 1;
        double[] LUrowi;
        double[] LUcolj = new double[m];

        // Outer loop.

        for (int j = 0; j < n; j++) {

            // Make a copy of the j-th column to localize references.

            for (int i = 0; i < m; i++) {
                LUcolj[i] = LU[i][j];
            }

            // Apply previous transformations.

            for (int i = 0; i < m; i++) {
                LUrowi = LU[i];

                // Most of the time is spent in the following dot product.

                int kmax = Math.min(i, j);
                double s = 0.0;
                for (int k = 0; k < kmax; k++) {
                    s += LUrowi[k] * LUcolj[k];
                }

                LUrowi[j] = LUcolj[i] -= s;
            }

            // Find pivot and exchange if necessary.

            int p = j;
            for (int i = j + 1; i < m; i++) {
                if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p])) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < n; k++) {
                    double t = LU[p][k];
                    LU[p][k] = LU[j][k];
                    LU[j][k] = t;
                }
                int k = piv[p];
                piv[p] = piv[j];
                piv[j] = k;
                pivsign = -pivsign;
            }

            // Compute multipliers.

            if (j < m & LU[j][j] != 0.0) {
                for (int i = j + 1; i < m; i++) {
                    LU[i][j] /= LU[j][j];
                }
            }
        }
    }

/* ------------------------
   Public Methods
 * ------------------------ */
    /**
     * Is the matrix nonsingular?
     *
     * @return true if U, and hence A, is nonsingular.
     */
    public boolean isNonsingular() {
        for (int j = 0; j < n; j++) {
            if (LU[j][j] == 0)
                return false;
        }
        return true;
    }

    //added this method
    public boolean isSingular() {
        return !isNonsingular();
    }

    /**
     * Return lower triangular factor
     *
     * @return L
     */
    public Matrix getL() {
        double[][] L = new double[m][n]; //changed
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = LU[i][j];
                } else if (i == j) {
                    L[i][j] = 1.0;
                } else {
                    L[i][j] = 0.0;
                }
            }
        }
        return MatrixFactory.getMatrix(m, n, hint, L);     //changed
    }

    /**
     * Return upper triangular factor
     *
     * @return U
     */

    public Matrix getU() {
        double[][] U = new double[m][n]; //changed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) {
                    U[i][j] = LU[i][j];
                } else {
                    U[i][j] = 0.0;
                }
            }
        }
        return MatrixFactory.getMatrix(m, n, hint, U);
    }

    /**
     * Return pivot permutation vector
     *
     * @return piv
     */

    public int[] getPivot() {
        int[] p = new int[m];
        for (int i = 0; i < m; i++) {
            p[i] = piv[i];
        }
        return p;
    }


    /**
     * Return pivot permutation vector as a one-dimensional double array
     *
     * @return (double) piv
     */

    public double[][] getDoublePivot() {       //changed to suit our needs
        double[][] vals = new double[m][1];
        for (int i = 0; i < m; i++) {
            vals[i][0] = (double) piv[i];
        }
        return vals;
    }


    /**
     * Return pivot permutation vector
     *
     * @return piv
     */

    public Matrix getPivotMatrix() {
        double[][] dblPivot = getDoublePivot();
        return MatrixFactory.getMatrix(dblPivot.length, 1, hint, dblPivot);
    }

    /**
     * Determinant
     *
     * @return det(A)
     * @throws IllegalArgumentException Matrix must be square
     */

    public double det() {
        if (m != n) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        double d = (double) pivsign;
        for (int j = 0; j < n; j++) {
            d *= LU[j][j];
        }
        return d;
    }

    /**
     * Solve A*X = B
     *
     * @param B A Matrix with as many rows as A and any number of columns.
     * @return X so that L*U*X = B(piv,:)
     * @throws IllegalArgumentException Matrix row dimensions must agree.
     * @throws RuntimeException         Matrix is singular.
     */

    public Matrix solve(Matrix B) {
        if (B.rows() != m) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (!this.isNonsingular()) {
            throw new RuntimeException("Matrix is singular.");
        }

        // Copy right hand side with pivoting
        int nx = B.cols();
        Matrix Xmat = B.getSubMatrix(piv, 0 + 1, nx - 1 + 1);//changed to reflect indices from 1,1   //piv must have indices begining from 1
        //Matrix Xmat = B.getSubMatrix(getAdjustedPivot(),0+1,nx-1+1);//if we wouldn't have changed piv earlier we could have done that in a method named adjustedPivot .. I tested it and it was working!
        double[][] X = Xmat.get();

        // Solve L*Y = B(piv,:)
        for (int k = 0; k < n; k++) {
            for (int i = k + 1; i < n; i++) {
                for (int j = 0; j < nx; j++) {
                    X[i][j] -= X[k][j] * LU[i][k];
                }
            }
        }
        // Solve U*X = Y;
        for (int k = n - 1; k >= 0; k--) {
            for (int j = 0; j < nx; j++) {
                X[k][j] /= LU[k][k];
            }
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < nx; j++) {
                    X[i][j] -= X[k][j] * LU[i][k];
                }
            }
        }
        return MatrixFactory.getMatrix(Xmat.rows(), Xmat.cols(), hint,  X);
    }



/* ------------------------
   Temporary, experimental code.
   ------------------------ *\

   \** LU Decomposition, computed by Gaussian elimination.
   <P>
   This constructor computes L and U with the "daxpy"-based elimination
   algorithm used in LINPACK and MATLAB.  In Java, we suspect the dot-product,
   Crout algorithm will be faster.  We have temporarily included this
   constructor until timing experiments confirm this suspicion.
   <P>
   @param  A             Rectangular matrix
   @param  linpackflag   Use Gaussian elimination.  Actual value ignored.
   @return               Structure to access L, U and piv.
   *\

   public LUDecomposition (Matrix A, int linpackflag) {
      // Initialize.
      LU = A.getArrayCopy();
      m = A.getRowDimension();
      n = A.getColumnDimension();
      piv = new int[m];
      for (int i = 0; i < m; i++) {
         piv[i] = i;
      }
      pivsign = 1;
      // Main loop.
      for (int k = 0; k < n; k++) {
         // Find pivot.
         int p = k;
         for (int i = k+1; i < m; i++) {
            if (Math.abs(LU[i][k]) > Math.abs(LU[p][k])) {
               p = i;
            }
         }
         // Exchange if necessary.
         if (p != k) {
            for (int j = 0; j < n; j++) {
               double t = LU[p][j]; LU[p][j] = LU[k][j]; LU[k][j] = t;
            }
            int t = piv[p]; piv[p] = piv[k]; piv[k] = t;
            pivsign = -pivsign;
         }
         // Compute multipliers and eliminate k-th column.
         if (LU[k][k] != 0.0) {
            for (int i = k+1; i < m; i++) {
               LU[i][k] /= LU[k][k];
               for (int j = k+1; j < n; j++) {
                  LU[i][j] -= LU[i][k]*LU[k][j];
               }
            }
         }
      }
   }

\* ------------------------
   End of temporary code.
 * ------------------------ */

}
