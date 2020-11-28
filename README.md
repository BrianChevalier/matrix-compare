# Matrix Compare

A comparison of different languages and to do common linear algebraic operations between them. Inspired by [Numpy for MATLAB users](https://numpy.org/doc/stable/user/numpy-for-matlab-users.html). Visual site available [here](https://brianchevalier.github.io/matrix-compare/). Pull requests welcome!

## Development

    make dev

Each example is stored under `src/matrix_compare/examples.cljs` in a list of maps, where each maps looks like the following:

```
{:description "Create a new array"     ; description of the example
 :core.matrix {:in  "(array [0 1 2])"  ; the example input
               :out "[0 1 2]"          ; output of the example
               :fn :core.matrix/array} ; namespaced key, used in core.cljs to create a documentation url
 :numpy      {:in "np.array([0, 1, 2])"
               :out "array([0, 1, 2])"
               :fn :numpy/array}}
```

## Deploying

    make deploy