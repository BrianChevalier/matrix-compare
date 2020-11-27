(ns matrix-compare.examples)

(def examples
  [{:description "Importing"
    :core.matrix {:in "(:require\n[clojure.core.matrix :as m])\n[clojure.core.matrix.linear :as lin]"
                  :out ""}
    :numpy {:in "import numpy as np\nimport numpy.linalg as lin"
            :out ""}
    :MATLAB {:in ""
             :out ""}}

   ;; Constructors
   {:description "Array and matrix construction"
    :MATLAB {:in "[0 1 2;\n 3 4 5]"}
    :numpy {:in "np.array([[0, 1, 2],\n          [3, 4, 5]])"}
    :core.matrix {:in "(array [[0 1 2]\n        [3 4 5]])"}}

   {:description "Identity Matrix"
    :MATLAB {:in "eye(3)"
             :fn :MATLAB/eye}
    :numpy {:in "np.eye(3)"
            :fn :numpy/eye}
    :core.matrix {:in "(m/identity-matrix 2)"
                  :out "[[1 1]\n [1 1]]"
                  :fn :core.matrix/identity-matrix}}
   {:description "Create a square diagonal matrix"
    :MATLAB {:in "diag([0 1 2])"
             :fn :MATLAB/diag}
    :numpy {:in "np.diag(np.array([0, 1, 2]))"
            :fn :numpy/diag}
    :core.matrix {:in "(diagonal-matrix )"
                  :fn :core.matrix/diagonal-matrix}}

   ;; Operators
   {:description "Addition"
    :MATLAB {:in  "[1 2 3] + [4 5 6]"
             :out "[5 7 9]"
             :fn :MATLAB/plus}
    :numpy {:in "[1, 2, 3] + [4, 5, 6]"
            :out ""
            :fn :numpy/add}
    :core.matrix {:in  "(+ [1 2 3] [4 5 6])"
                  :out "[5 7 9]"
                  :fn :core.matrix.operators/+}}
   {:description "Subtraction"
    :MATLAB {:in "a - b"
             :out ""
             :fn :MATLAB/minus}
    :numpy   {:in "a - b"
              :out ""
              :fn :numpy/subtract}
    :core.matrix {:in "(- a b)"
                  :out ""
                  :fn :core.matrix.operators/-}}
   {:description "Element-wise multiplication"
    :MATLAB {:in "a.*b"
             :fn :MATLAB/times}
    :numpy {:in "a * b"}
    :core.matrix {:in "(* a b)"
                  :fn :core.matrix.operators/*}}

   {:description "Matrix Multiplication"
    :MATLAB {:in "[0 1 2]*[0; 1; 2]"
             :out "5"
             :fn :MATLAB/mtimes}
    :numpy {:in "np.matmul()"
            :fn :numpy/matmul}
    :core.matrix {:in "(m/mmul )"
                  :out ""
                  :fn :core.matrix/mmul}}

   {:description "Transpose"
    :MATLAB {:in "a'"
             :out ""
             :fn :MATLAB/transpose}
    :numpy   {:in "a.T"
              :out ""
              :fn :numpy/transpose}
    :core.matrix {:in "(m/transpose a)"
                  :out ""
                  :fn :core.matrix/transpose}}

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

   {:description "Get the shape of a matrix/array"
    :MATLAB {:in "a + b"
             :out ""}
    :numpy {:in "a.shape"
            :out "[3 2]"}
    :core.matrix {:in "(shape [[1 2]\n[3 4]\n[5 6]])"
                  :out "[3 2]"
                  :fn :core.matrix/shape}}])
