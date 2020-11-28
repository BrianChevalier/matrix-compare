(ns matrix-compare.core
    (:require
     [matrix-compare.examples :as ex]
     [reagent.dom :as d]))

;; -------------------------
;; Views

(defn doc-url
  "Create a url to various documentation sites
   Dispatches based on a namespace qualified key where
   the namespace chooses the base url and the name is
   used in the url"
  [fn-key]
  (if (qualified-keyword? fn-key)
    (let [namespace (namespace fn-key)
          name      (name fn-key)]
      (case namespace
        "core.matrix"            (str "https://mikera.github.io/core.matrix/doc/clojure.core.matrix.html#var-" name)
        "core.matrix.linear"     (str "https://mikera.github.io/core.matrix/doc/clojure.core.matrix.linear.html#var-" name)
        "core.matrix.operators"  (str "https://mikera.github.io/core.matrix/doc/clojure.core.matrix.operators.html#var-" name)
        "clojure.core"           (str "https://clojuredocs.org/clojure.core/" name)
        "numpy"                  (str "https://numpy.org/doc/stable/reference/generated/numpy." name ".html")
        "numpy.linalg"           (str "https://numpy.org/doc/stable/reference/generated/numpy.linalg." name ".html")
        "scipy.linalg"           (str "https://docs.scipy.org/doc/scipy/reference/generated/scipy.linalg." name ".html")
        "python.control-flow"    (str "https://docs.python.org/3/tutorial/controlflow.html#" name)
        "python.data-structures" (str "https://docs.python.org/3/tutorial/datastructures.html#" name)
        "MATLAB"                 (str "https://www.mathworks.com/help/matlab/ref/" name ".html")
        "math.js"                (str "https://mathjs.org/docs/reference/functions/" name ".html")
        ""))
    nil))

(def table-border
  {:style
   {:border "2px solid #4C566A"
    :border-collapse :collapse
    :position :relative}})

(defn input
  [content]
  [:div
   ;;[:code ""]
   [:pre [:code (:in content)]]])

(defn output
  [content]
  [:div
   ;;[:code ""]
   [:pre [:code (:out content)]]])

(defn code-example [item lang]
  [:td table-border
   (when-let [url (-> item lang :fn doc-url)]
     [:a {:href url}
      [:div {:style {:position :absolute
                     :width "20px"
                     :top "0px"
                     :right "0px"
                     :background-color "#5E81AC"
                     :font-style :italic
                     :font-weight :bold
                     :color :white
                     :font-size "0.8rem"
                     :text-align :center}} "i"]])
   [input  (-> item lang)]
   [output (-> item lang)]])

(defn item
  [item]
  (cond
    (map? item)
    [:tr
     [:td table-border (:description item)]
     [code-example item :core.matrix]
     [code-example item :numpy]
     [code-example item :MATLAB]
     [code-example item :math.js]]

    (string? item)
    [:tr {:style
          {:background-color "#3B4252"
           :color "#D8DEE9"}}
     [:td {:colSpan "10"}
      [:h3 item]]]))

(defn home-page []
  (fn []
    [:center
     [:h1 "matrix-compare"]
     [:p {:style {:max-width "500px"}}
      "A comparison of various array & matrix programming 
       languages/libraries and how to accomplish similar tasks. Inspired by "
      [:a {:href "https://numpy.org/doc/stable/user/numpy-for-matlab-users.html"} "NumPy for MATLAB users"] ". "
      [:a {:href "https://github.com/BrianChevalier/matrix-compare"} "Pull requests"] " welcome!"]
     [:table table-border
      [:tr
       [:td table-border "Description"]
       [:td table-border "Clojure (clojure.core.matrix)"]
       [:td table-border  "Python (NumPy)"]
       [:td table-border "MATLAB"]
       [:td table-border "JavaScript (math.js)"]]
      (for [example ex/examples]
        (item example))]]))

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
