(ns matrix-compare.examples)

(def examples
  [{:description "Importing"
    :core.matrix {:in "(:require\n[clojure.core.matrix :as m]\n[clojure.core.matrix.linear :as lin]\n[clojure.core.matrix.operators :as op])"
                  :out ""}
    :numpy {:in "import numpy as np\nimport numpy.linalg as lin\nimport scipy"
            :out ""}
    :MATLAB {:in ""
             :out ""}}

   ;; Constructors
   "Constructors"
   {:description "Array and matrix construction"
    :MATLAB {:in "[0 1 2;\n 3 4 5]"}
    :numpy {:in "np.array([[0, 1, 2],\n          [3, 4, 5]])"
            :fn :numpy/array}
    :core.matrix {:in "(m/array [[0 1 2]\n          [3 4 5]])"
                  :fn :core.matrix/array}}

   {:description "Identity Matrix"
    :MATLAB {:in "eye(3)"
             :fn :MATLAB/eye}
    :numpy {:in "np.eye(3)"
            :fn :numpy/eye}
    :core.matrix {:in "(m/identity-matrix 2)"
                  :fn :core.matrix/identity-matrix}}
   {:description "Zeros"
    :MATLAB {:in "zeros(rows, cols)"}
    :numpy {:in "np.zeros((rows, cols))"
            :fn :numpy/zeros}
    :core.matrix {:in "(m/zero-array [rows cols])"
                  :fn :core.matrix/zero-array}}
   {:description "Create a square diagonal matrix"
    :MATLAB {:in "diag(a)"
             :fn :MATLAB/diag}
    :numpy {:in "np.diag(a)"
            :fn :numpy/diag}
    :core.matrix {:in "(m/diagonal-matrix a)"
                  :fn :core.matrix/diagonal-matrix}
    :math.js {:in "math.diag(a)"
              :fn :math.js/diag}}
   {:description "Get the main diagonal of a matrix as a vector"
    :MATLAB {:in "diag(A)"
             :fn :MATLAB/diag}
    :numpy {:in "np.diag(A)"
            :fn :numpy/diag}
    :core.matrix {:in "(m/main-diagonal A)"
                  :fn :core.matrix/main-diagonal}
    :math.js {:in "math.diag(A)"
              :fn :math.js/diag}}
   {:description "Create matrix from blocks"
    :MATLAB {:in "[a b; c d]"}
    :numpy {:in "np.block([[a, b], [c, d]])"
            :fn :numpy/block}}
   {:description "Compute the elements of a matrix given i, j"
    :MATLAB {}
    :numpy {}
    :core.matrix {:in "(m/compute-matrix shape (fn [i j]))"
                  :fn :core.matrix/compute-matrix}}
   {:description "Concatenate arrays"
    :MATLAB {:in "[a b c]"}
    :numpy {:in "np.concatenate([a, b, c])"
            :fn :numpy/concatenate}
    :core.matrix {:in "(m/join a b c)"
                  :fn :core.matrix/join}}

   ;; Operators
   "Operators"
   {:description "Addition"
    :MATLAB {:in  "a + b"
             :fn :MATLAB/plus}
    :numpy {:in "a + b"
            :fn :numpy/add}
    :core.matrix {:in  "(op/+ a b)"
                  :fn :core.matrix.operators/+}}
   {:description "Subtraction"
    :MATLAB {:in "a - b"
             :out ""
             :fn :MATLAB/minus}
    :numpy   {:in "a - b"
              :out ""
              :fn :numpy/subtract}
    :core.matrix {:in "(op/- a b)"
                  :out ""
                  :fn :core.matrix.operators/-}}
   {:description "Element-wise multiplication"
    :MATLAB {:in "a.*b"
             :fn :MATLAB/times}
    :numpy {:in "a * b"}
    :core.matrix {:in "(op/* a b)"
                  :fn :core.matrix.operators/*}
    :math.js {:in "math.dotMultiply(a, b)"
              :fn :math.js/dotMultiply}}

   {:description "Matrix Multiplication"
    :MATLAB {:in "a * b"
             :fn :MATLAB/mtimes}
    :numpy {:in "np.matmul(a, b)"
            :fn :numpy/matmul}
    :core.matrix {:in "(m/mmul a b)"
                  :fn :core.matrix/mmul}}
   {:description "Dot product"
    :MATLAB {:in "dot(a, b)"
             :fn :MATLAB/dot}
    :numpy {:in "np.dot(a, b)"
            :fn :numpy/dot}
    :core.matrix {:in "(m/dot a b)"
                  :fn :core.matrix/dot}
    :math.js {:in "math.dot(a, b)"
              :fn :math.js/dot}}
   {:description "Cross product"
    :MATLAB {:in "cross(a, b)"
             :fn :MATLAB/cross}
    :numpy {:in "np.cross(a, b)"
            :fn :numpy/cross}
    :math.js {:in "math.cross(a, b)"
              :fn :math.js/cross}
    :core.matrix {:in "(m/cross a b)"
                  :fn :core.matrix/cross}}

   {:description "Transpose"
    :MATLAB {:in "a'"
             :out ""
             :fn :MATLAB/transpose}
    :numpy   {:in "a.T"
              :out ""
              :fn :numpy/transpose}
    :core.matrix {:in "(m/transpose a)"
                  :out ""
                  :fn :core.matrix/transpose}
    :math.js {:in "math.transpose(a)"
              :fn :math.js/transpose}}

   ;; Linear algebra
   "Linear Algebra"
   {:description "Compute the linear algebraic norm"
    :MATLAB {:in "norm(a)"
             :out ""
             :fn :MATLAB/norm}
    :numpy   {:in "np.linalg.norm(a)"
              :out ""
              :fn :numpy.linalg/norm}
    :core.matrix {:in "(lin/norm a)"
                  :out ""
                  :fn :core.matrix.linear/norm}}
   {:description "Compute the inverse"
    :MATLAB {:in "inv(a)"
             :fn :MATLAB/inv}
    :numpy {:in "lin.inv(a)"
            :fn :numpy.linalg/inv}
    :core.matrix {:in "(m/inverse a)"
                  :fn :core.matrix/inverse}}
   {:description "Compute the determinant"
    :MATLAB {:in "det(A)"
             :fn :MATLAB/det}
    :numpy {:in "lin.det(A)"
            :fn :numpy.linalg/det}
    :core.matrix {:in "(m/det A)"
                  :fn :core.matrix/det}}
   {:description "Eigen"
    :MATLAB {:in "[V, D] = eig(a)"
             :fn :MATLAB/eig}
    :numpy {:in "D, V = lin.eig(a)"
            :fn :numpy.linalg/eig}
    :core.matrix {:in "(lin/eigen a)"
                  :fn :core.matrix.linear/eigen}
    :math.js {:in "math.eigs(a)"
              :fn :math.js/eigs}}
   {:description "Generalized Eigenvalue problem"
    :numpy {:in "D, V = scipy.linalg.eig(a, b)"
            :fn :scipy.linalg/eig}
    :MATLAB {:in "V, D = eig(a, b)"
             :fn :MATLAB/eig}}
   {:description "Solve"
    :MATLAB {:in "x = A\\b"
             :fn :MATLAB/mldivide}
    :numpy {:in "x = lin.solve(A, b)"
            :fn :numpy.linalg/solve}
    :core.matrix {:in "(lin/solve A b)"
                  :fn :core.matrix.linear/solve}
    :math.js {:in "math.lusolve(A, b)"
              :fn :math.js/lusolve}}
   {:description "LU Decomposition"
    :MATLAB {:in "[L, U] = lu(A)\n[L, U, P] = lu(A)"
             :fn :MATLAB/lu}
    :numpy {:in "L, U = scipy.linalg.lu(A)"
            :fn :scipy.linalg/lu}
    :core.matrix {:in "(m/lu A)"
                  :out "{:L [] :U [] :P []}"
                  :fn :core.matrix.linear/lu}}

   {:description "Get the shape of a matrix/array"
    :MATLAB {:in "a + b"
             :out ""}
    :numpy {:in "a.shape"
            :out "[3 2]"}
    :core.matrix {:in "(m/shape [[1 2]\n[3 4]\n[5 6]])"
                  :out "[3 2]"
                  :fn :core.matrix/shape}}

   ;; Indexing
   "Indexing and Slicing"
   {:description "Get second row"
    :MATLAB {:in "a(2, :)"}
    :numpy {:in "a[1, :]"}
    :core.matrix {:in "(m/get-row a 1)"
                  :fn :core.matrix/get-row}}
   {:description "Get second column"
    :MATLAB {:in "a(:, 2)"}
    :numpy {:in "a[:, 2]"}
    :core.matrix {:in "(m/get-column a 1)"
                  :fn :core.matrix/get-column}}
   {:description "Get an element from an array"
    :core.matrix {:in "(m/mget 1 2)"
                  :fn :core.matrix/mget}}
   {:description "Select"
    :MATLAB {:in "a([2,4,5],[1,3])"}
    :numpy {:in "a[np.ix_([1, 3, 4], [0, 2])]"
            :fn :numpy/ix_}
    :core.matrix {:in "(m/select a [1 3 4] [0 2])"
                  :fn :core.matrix/select}}
   {:description "Set scalar value at an index"
    :MATLAB {:in "a() = "}
    :numpy {:in "a() = "}
    :core.matrix {:in "(m/mset a [])"
                  :fn :core.matrix/mset}}
   {:description "Set selection"
    :MATLAB {:in "a() = "}
    :core.matrix {:in "(m/set-selection a [] [])"
                  :fn :core.matrix/set-selection}}

   ;; Other relevant language features
   "Other relevant language features"
   {:description "Anonymous function"
    :core.matrix {:in "(fn [a b] (+ a b))"
                  :fn :clojure.core/fn}
    :numpy {:in "lambda a, b: a + b"
            :fn :python.control-flow/lambda-expressions}
    :MATLAB {:in "@(a, b) a + b"
             :fn :MATLAB/function_handle}}
   {:description "Function"
    :MATLAB {:in "function [c] = func(a, b)\n    c = a + b;\nend"
             :fn :MATLAB/function}
    :numpy {:in "def func(a, b):\n    return a + b"
            :fn :python.control-flow/defining-functions}
    :core.matrix {:in "(defn func [a b]\n    (+ a b))"
                  :fn :clojure.core/defn}}
   {:description "List comprehension"
    :numpy {:in "[i for i in range(n)]"
            :fn :python.data-structures/list-comprehensions}
    :core.matrix {:in "(for [i (range n)] i)"
                  :fn :clojure.core/for}}])
