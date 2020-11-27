(ns matrix-compare.examples)

(def examples
  [{:description "Importing"
    :Clojure {:in "(:require\n[clojure.core.matrix :as m])\n[clojure.core.matrix.linear :as lin]"
              :out ""}
    :NumPy {:in "import numpy as np\nimport numpy.linalg as lin"
            :out ""}
    :MATLAB {:in ""
             :out ""}}

   ;; Constructors
   {:description "Array and matrix construction"
    :MATLAB {:in "[0 1 2;\n 3 4 5]"}
    :NumPy {:in "np.array([[0, 1, 2],\n          [3, 4, 5]])"}
    :Clojure {:in "(array [[0 1 2]\n        [3 4 5]])"}}

   {:description "Identity Matrix"
    :MATLAB {:in "eye(3)"
             :fn :MATLAB/eye}
    :NumPy {:in "np.eye(3)"
            :fn :numpy/eye}
    :Clojure {:in "(m/identity-matrix 2)"
              :out "[[1 1]\n [1 1]]"
              :fn :core.matrix/identity-matrix}}
   {:description "Create a square diagonal matrix"
    :MATLAB {:in "diag([0 1 2])" :fn :MATLAB/diag}
    :NumPy {:in "np.diag(np.array([0, 1, 2]))" :fn :numpy/diag}}

   ;; Operators
   {:description "Addition"
    :MATLAB {:in  "[1 2 3] + [4 5 6]"
             :out "[5 7 9]"}
    :NumPy {:in "[1, 2, 3] + [4, 5, 6]"
            :out ""}
    :Clojure {:in  "(+ [1 2 3] [4 5 6])"
              :out "[5 7 9]"}}

   {:description "Subtraction"
    :MATLAB {:in "a - b"
             :out ""}
    :NumPy   {:in "a - b" :out ""}
    :Clojure {:in "(- a b)" :out ""}}

   {:description "Matrix Multiplication"
    :MATLAB {:in "[0 1 2]*[0; 1; 2]"
             :out "5"}
    :Clojure {:in "(m/mmul )"
              :out ""
              :fn :core.matrix/mmul}}

   {:description "Transpose"
    :MATLAB {:in "a'"  :out ""
             :fn :MATLAB/transpose}
    :NumPy   {:in "a.T" :out "" :fn :numpy/transpose}
    :Clojure {:in "(m/transpose a)"
              :out ""
              :fn :core.matrix/transpose}}

   {:description "Inverse"
    :MATLAB
    {:in "inv(a)"  :out ""}
    :NumPy   "np.linalg.norm(a)"
    :Clojure "(lin/norm a)"}

   {:description "Shape"
    :MATLAB {:in "a + b"
             :out ""}
    :NumPy {:in "a.shape"
            :out "[3 2]"}
    :Clojure {:in "(shape [[1 2]\n[3 4]\n[5 6]])"
              :out "[3 2]"}}])
