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
        "core.matrix"           (str "https://mikera.github.io/core.matrix/doc/clojure.core.matrix.html#var-" name)
        "core.matrix.linear"    (str "https://mikera.github.io/core.matrix/doc/clojure.core.matrix.linear.html#var-" name)
        "core.matrix.operators" (str "https://mikera.github.io/core.matrix/doc/clojure.core.matrix.operators.html#var-" name)
        "numpy"                 (str "https://numpy.org/doc/stable/reference/generated/numpy." name ".html")
        "numpy.linalg"          (str "https://numpy.org/doc/stable/reference/generated/numpy.linalg." name ".html")
        "MATLAB"                (str "https://www.mathworks.com/help/matlab/ref/" name ".html")
        ""))
    nil))

(def table-border
  {:style
   {:border "1px solid black"
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
                     :background-color "black"
                     :font-style :italic
                     :font-weight :bold
                     :color :white
                     :font-size "0.8rem"
                     :text-align :center}} "i"]])
   [input  (-> item lang)]
   [output (-> item lang)]])

(defn item
  [item]
  [:tr
   [:td table-border (:description item)]
   [code-example item :core.matrix]
   [code-example item :numpy]
   [code-example item :MATLAB]])

(defn home-page []
  (fn []
    [:center
     [:table table-border
      [:tr
       [:td table-border "Description"]
       [:td table-border "Clojure (clojure.core.matrix)"]
       [:td table-border  "Python (NumPy)"]
       [:td table-border "MATLAB"]]
      (for [example ex/examples]
        (item example))]]))

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
