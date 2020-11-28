(ns matrix-compare.core
    (:require
     [matrix-compare.examples :as ex]
     [matrix-compare.components :as c]
     [reagent.core :as r]
     [reagent.dom :as d]))

(defonce state
  (r/atom {:MATLAB      true
           :core.matrix true
           :numpy       true
           :math.js     true}))

(def titles
  {:MATLAB      "MATLAB"
   :core.matrix "Clojure (clojure.core.matrix)"
   :numpy       "Python (NumPy)"
   :math.js     "JavaScript (math.js)"})

(def libs
  [:core.matrix :MATLAB :numpy :math.js])

(defn item
  [item ks]
  (cond
    (map? item)
    [:tr
     [:td c/table-border (:description item)]
     (for [k ks]
       (when (k @state)
         [c/code-example item k]))]

    (string? item)
    [:tr {:style
          {:background-color "#3B4252"
           :color "#D8DEE9"}}
     [:td {:colSpan "10"}
      [:h3 item]]]))

(defn checkbox [k]
  [:div {:style {:margin "0 auto"}}
   [:input {:type :checkbox
            :id k
            :name k
            :checked (k @state)
            :on-change #(swap! state assoc k (not (k @state)))}]
   [:label {:for k}] (name k)])

(defn checkboxes [ks]
  [:div {:style
         {:margin "0 auto"
          :max-width "500px"
          :display :flex
          :flex-direction :horizontal}}
   (for [k ks]
     [checkbox k])])

(defn home-page []
  (fn []
    (let [selected-keys (filter #(-> @state %) libs)]
      [:center
      [c/title]
      [checkboxes libs]
      [:table c/table-border
       [:tr
        [:td c/table-border "Description"]
        [c/headers titles selected-keys]]
       (for [example ex/examples]
         [item example selected-keys])]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
