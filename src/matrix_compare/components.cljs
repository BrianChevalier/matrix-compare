(ns matrix-compare.components
  (:require [matrix-compare.docs :refer [doc-url]]))

(defn title []
  [:<>
   [:h1 "matrix-compare"]
   [:p {:style {:max-width "500px"}}
    "A comparison of various array & matrix programming 
       languages/libraries and how to accomplish similar tasks. Inspired by "
    [:a {:href "https://numpy.org/doc/stable/user/numpy-for-matlab-users.html"} "NumPy for MATLAB users"] ". "
    [:a {:href "https://github.com/BrianChevalier/matrix-compare"} "Pull requests"] " welcome!"]])

(defn input
  [content]
  [:div
   [:pre [:code (:in content)]]])

(defn output
  [content]
  [:div
   [:pre [:code (:out content)]]])

(def table-border
  {:style
   {:border "2px solid #4C566A"
    :border-collapse :collapse
    :position :relative}})

(defn code-example [item lang]
  [:td table-border
   (when-let [url (-> item lang :fn doc-url)]
     [:a {:href url}
      [:div {:style
             {:position :absolute
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

(defn headers [titles ks]
  [:<>
   (for [k ks]
     [:td table-border (k titles)])])