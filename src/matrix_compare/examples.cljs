(ns matrix-compare.examples)

(def examples
  [{:description "Importing"
    :core.matrix {:in "(ns your.namespace\n  (:refer-clojure :exclude [- + *])\n  (:require\n    [clojure.core.matrix :as m]\n    [clojure.core.matrix.linear :as lin]\n    [clojure.core.matrix.operators :refer [- + *]]))"
                  :fn :clojure.core/ns
                  :note "The clojure.core -, +, and * operators are being replaced here with the core.matrix operators, however, you could instead use core.matrix/add, etc."}
    :numpy {:in "import numpy as np\nimport numpy.linalg as lin\nimport scipy"}
    :MATLAB {:in ""}
    :julia {:in "using LinearAlgebra"}}
   {:type :text
    :description "Indexing"
    :core.matrix {:text "zero-based indexing"}
    :MATLAB {:text "one-based indexing"}
    :numpy {:text "zero-based indexing"}
    :math.js {:text "zero-based indexing"}}
   {:type :text
    :description "Value semantics"
    :MATLAB {:text [:span "Pass-by-value sematics. When a function takes an input, 
                    it cannot mutate the array in place. "
                    [:a {:href "https://www.mathworks.com/help/matlab/matlab_prog/avoid-unnecessary-copies-of-data.html"} "Read more."]]}
    :core.matrix {:text "Pass-by-value by default. All operations create a 'new' arrays, unless using mutable views and mutating functions.
                         Varies by implementation."}
    :numpy {:text "Pass-by-reference semantics. Array slices are mutable views of an array. To copy an array use np.copy(A)."}}

   ;; Constructors
   "Array Creation"
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
    :core.matrix {:in "(m/identity-matrix 3)"
                  :fn :core.matrix/identity-matrix}
    :math.js {:in "math.identity(n)"
              :fn :math.js/identity}}
   {:description "Zeros"
    :MATLAB {:in "zeros(rows, cols)"}
    :numpy {:in "np.zeros((rows, cols))"
            :fn :numpy/zeros}
    :core.matrix {:in "(m/zero-array [rows cols])"
                  :fn :core.matrix/zero-array}}
   {:description "Ones Matrix"
    :MATLAB {:in "ones(n, m)"
             :fn :MATLAB/ones}
    :numpy {:in "np.ones((n, m))"
            :fn :numpy/ones}
    :math.js {:in "math.ones(n, m)"
              :fn :math.js/ones}}
   {:description "Evenly spaced array from a to b with n points"
    :numpy {:in "np.linspace(a, b, n)"
            :fn :numpy/linspace}
    :MATLAB {:in "linspace(a, b, n)"
             :fn :MATLAB/linspace}}
   {:description "Create array from a to b spaced by dx"
    :numpy {:in "np.arange(a, b, dx)"
            :fn :numpy/arange}
    :MATLAB {:in "a:dx:b"
             :fn :MATLAB/colon}
    :core.matrix {:in "(m/matrix (range a b dx))"}}
   {:description "Create a square diagonal matrix, with the vector a on the main diagonal"
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
   {:description "Compute the elements of a matrix given i, j with given shape"
    :MATLAB {}
    :numpy {:in "np.fromfunction(lambda i, j: i*j, shape)"
            :fn :numpy/fromfunction}
    :core.matrix {:in "(m/compute-matrix shape (fn [i j] (* i j)))"
                  :fn :core.matrix/compute-matrix}}

   "Combining Arrays"
   {:description "Concatenate arrays"
    :MATLAB {:in "[a b c]"}
    :numpy {:in "np.concatenate([a, b, c])"
            :fn :numpy/concatenate}
    :core.matrix {:in "(m/join a b c)"
                  :fn :core.matrix/join}}
   {:description "Create matrix from blocks"
    :MATLAB {:in "[a b; c d]"}
    :numpy {:in "np.block([[a, b], [c, d]])"
            :fn :numpy/block}}
   {:description "Vertically stack arrays (row-wise)"
    :numpy {:in "np.vstack([a, b])"
            :fn :numpy/vstack}}
   {:description "Horizontally stack arrays (column-wise)"
    :numpy {:in "np.hstack([a, b])"
            :fn :numpy/hstack}}

   "Metadata"
   {:description "Get the shape of a matrix, A"
    :MATLAB {:in "size(A)"
             :fn :MATLAB/size}
    :numpy {:in "np.shape(A)"
            :fn :numpy/shape}
    :core.matrix {:in "(m/shape A)"
                  :fn :core.matrix/shape}}
   {:description "Get number of rows in matrix, A"
    :core.matrix {:in "(m/row-count A)"
                  :fn :core.matrix/row-count}
    :MATLAB {:in "size(A, 1)"
             :fn :MATLAB/size}
    :numpy {:in "np.shape(A)[0]"
            :fn :numpy/shape}}
   {:description "Get number of columns in matrix, A"
    :core.matrix {:in "(m/column-count A)"
                  :fn :core.matrix/column-count}
    :numpy {:in "np.shape(A)[1]"
            :fn :numpy/shape}
    :MATLAB {:in "size(A, 2)"
             :fn :MATLAB/size}}
   {:description "Dimensionality"
    :core.matrix {:in "(m/dimensionality A)"
                  :fn :core.matrix/dimensionality}}
   {:description "Get size of specified dimension in matrix A"
    :core.matrix {:in "(m/dimension-count A dim)"
                  :fn :core.matrix/dimension-count}}

   ;; Operators
   "Operators"
   {:description "Broadcasting"
    :type :text
    :core.matrix {:text [:span [:a {:href "https://github.com/mikera/core.matrix/wiki/Broadcasting"} "Broadcasting similar to NumPy"]]}
    :MATLAB {:text [:span "Implicit Expansion"]
             :note "Implicit expansion is not very well documented."}
    :numpy {:text [:span [:a {:href "https://numpy.org/doc/stable/user/theory.broadcasting.html#array-broadcasting-in-numpy"} "Array broadcasting"]]}}
   {:description "Addition"
    :MATLAB {:in  "a + b"
             :fn :MATLAB/plus}
    :numpy {:in "a + b"
            :fn :numpy/add}
    :core.matrix {:in  "(+ a b)"
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
   {:description "Trace (sum of diagonal) of A"
    :core.matrix {:in "(m/trace A)"
                  :fn :core.matrix/trace}
    :numpy {:in "np.trace(A)"
            :fn :numpy/trace}
    :MATLAB {:in "trace(A)"
             :fn :MATLAB/trace}
    :julia {:in "tr(A)"
            :fn :julia.LinearAlgebra/tr}}

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
    :numpy   {:in "lin.norm(a)"
              :out ""
              :fn :numpy.linalg/norm}
    :core.matrix {:in "(lin/norm a)"
                  :out ""
                  :fn :core.matrix.linear/norm}}
   {:description "Compute the inverse of A"
    :MATLAB {:in "inv(A)"
             :fn :MATLAB/inv}
    :numpy {:in "lin.inv(A)"
            :fn :numpy.linalg/inv}
    :core.matrix {:in "(m/inverse A)"
                  :fn :core.matrix/inverse}
    :julia {:in "inv(A)"}}
   {:description "Compute the determinant"
    :MATLAB {:in "det(A)"
             :fn :MATLAB/det}
    :numpy {:in "lin.det(A)"
            :fn :numpy.linalg/det}
    :core.matrix {:in "(m/det A)"
                  :fn :core.matrix/det}
    :julia {:in "det(A)"
            :fn :julia.LinearAlgebra/det}}
   {:description "Eigen"
    :MATLAB {:in "[V, D] = eig(a)"
             :fn :MATLAB/eig}
    :numpy {:in "D, V = lin.eig(a)"
            :fn :numpy.linalg/eig}
    :core.matrix {:in "(lin/eigen a)"
                  :out "{:Q [] :A []}"
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

   ;; Indexing
   "Getting and Setting elements and selections of an array"
   {:description "Guides"
    :type :text
    :MATLAB {:text [:a {:href "https://www.mathworks.com/help/matlab/math/array-indexing.html"} "Array Indexing in MATLAB"]}
    :numpy {:text [:a {:href "https://numpy.org/doc/stable/user/basics.indexing.html"} "NumPy Indexing Basics"]}}
   {:description "Get second row"
    :MATLAB {:in "a(2, :)"}
    :numpy {:in "a[1, :]"}
    :core.matrix {:in "(m/get-row a 1)"
                  :fn :core.matrix/get-row}}
   {:description "Get second column"
    :MATLAB {:in "a(:, 2)"}
    :numpy {:in "a[:, 1]"}
    :core.matrix {:in "(m/get-column a 1)"
                  :fn :core.matrix/get-column}}
   {:description "Get the element from row 2, column 3"
    :MATLAB {:in "a(2, 3)"}
    :numpy {:in "a[1, 2]"}
    :core.matrix {:in "(m/mget a 1 2)"
                  :fn :core.matrix/mget}}
   {:description "Set the value at row 2, column 3 to 1"
    :MATLAB {:in "a(2, 3) = 1"}
    :numpy {:in "a[1, 2] = 1"}
    :core.matrix {:in "(m/mset a 1 2 1)"
                  :fn :core.matrix/mset}}
   {:description "Get last element in vector"
    :numpy {:in "a[-1]"}
    :MATLAB {:in "a(end)"}
    :core.matrix {:in "(m/select a :last)"}}
   {:description "Select elements that are the cartesian product of the indicies given"
    :MATLAB {:in "a([2 4 5], [1 3])"}
    :numpy {:in "a[np.ix_([1, 3, 4], [0, 2])]"
            :fn :numpy/ix_}
    :core.matrix {:in "(m/select a [1 3 4] [0 2])"
                  :fn :core.matrix/select}}
   {:description "Set selection"
    :MATLAB {:in "K(rows, cols) = ke"}
    :core.matrix {:in "(m/set-selection K rows cols ke)"
                  :fn :core.matrix/set-selection}
    :numpy {:in "K[np.ix_(rows, cols)] = ke"}}
   {:description "Set row i of matrix A with vector row"
    :core.matrix {:in "(m/set-row A i row)"
                  :fn :core.matrix/set-row}
    :numpy {:in "A[i, :] = row"}
    :MATLAB {:in "A(i, :) = row;"}}
   {:description "Multiply row i of matrix, A, by factor"
    :core.matrix {:in "(m/multiply-row A i factor)"
                  :fn :core.matrix/multiply-row}
    :numpy {:in "A[i, :] *= factor"}
    :MATLAB {:in "A(i, :) = A(i, :) * factor;"}}
   {:description "Swap row i and j in matrix A"
    :core.matrix {:in "(m/swap-rows A i j)"
                  :fn :core.matrix/swap-rows}
    :MATLAB {:in "A([i j], :) = A([j i], :)"}
    :numpy {:in "A[[i, j], :] = A[[j, i], :]"}}
   {:description "Set column i of matrix A with vector column"
    :core.matrix {:in "(m/set-column A i column)"
                  :fn :core.matrix/set-column}
    :numpy {:in "A[:, i] = column"}
    :MATLAB {:in "A(:, i) = column;"}}
   {:description "Selection+="
    :MATLAB {:in "K(row, cols) = K(rows, cols) + ke;"}
    :numpy {:in "K[np.ix_(rows, cols)] += ke"}
    :core.matrix {:in "(m/set-selection K rows cols\n  (+ (m/select K rows cols) ke))"}}

   "Aggregating data about an array"
   {:description "Sum all elements of matrix, A"
    :numpy {:in "np.sum(A)" :fn :numpy/sum}
    :core.matrix {:in "(m/esum A)" :fn :core.matrix/esum}
    :MATLAB {:in "sum(A, 'all')" :fn :MATLAB/sum}}
   {:description "Sum over rows of matrix, A"
    :numpy {:in "np.sum(A, axis=0)" :fn :numpy/sum}
    :core.matrix {:in "(reduce + (m/slices A))"}
    :MATLAB {:in "sum(A)" :fn :MATLAB/sum}}
   {:description "Sum over columns of matrix, A"
    :numpy {:in "np.sum(A, axis=1)" :fn :numpy/sum}
    :core.matrix {:in "(reduce + (m/slices A 1))"}
    :MATLAB {:in "sum(A, 2)" :fn :MATLAB/sum}}
   
   {:description "Minimum of matrix, A"
    :numpy {:in "np.amin(A)" :fn :numpy/amin}
    :core.matrix {:in "(m/emin A)" :fn :core.matrix/emin}
    :MATLAB {:in "min(min(A))" :fn :MATLAB/min}}
   
   {:description "Maximum of matrix, A"
    :numpy {:in "np.amax(A)" :fn :numpy/amax}
    :core.matrix {:in "(m/emax A)" :fn :core.matrix/emax}
    :MATLAB {:in "max(max(A))"} :fn :MATLAB/max}

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
             :fn :MATLAB/function
             :note "File-local functions must be at the end of the file. 
                    For a function to be used in another file, the function 
                    name must match the file name exactly."}
    :numpy {:in "def func(a, b):\n    return a + b"
            :fn :python.control-flow/defining-functions}
    :core.matrix {:in "(defn func [a b]\n    (+ a b))"
                  :fn :clojure.core/defn}}
   {:description "List comprehension"
    :numpy {:in "[i for i in range(n)]"
            :fn :python.data-structures/list-comprehensions}
    :core.matrix {:in "(for [i (range n)] i)"
                  :fn :clojure.core/for}}
   {:description "Call function on each element in a collection"
    :core.matrix {:in "(map (fn [v] v) coll)"
                  :fn :clojure.core/map}
    :numpy {:in "map(lambda v: v, coll)"}}
   {:description "Dictionaries/maps (Associative arrays)"
    :numpy {:in "{'key': 'value'}"}
    :MATLAB {:in "containers.Map({'key'}, {'value'})"
             :fn :MATLAB/containers.map
             :note "Not very widely used"}
    :core.matrix {:in "{:key 'value'}"
                  :fn :clojure.core/hash-map}}])
