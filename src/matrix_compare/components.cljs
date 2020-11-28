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

(defn popover [title message]
  [:div.popover_wrapper
   [:a {:href "#"}
    [:div.popover_title title]]
   [:div.popover_content
    [:div.popover_message message]]])

(def info-button
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
          :text-align :center}} "i"])

#_(defn note-button [note]
  [:div {:style
         {:position :absolute
          :width "20px"
          :height "20px"
          :top "20px"
          :right "0px"
          :background-color "#5E81AC"
          :font-weight :bold
          :color :white
          :font-size "0.8rem"
          :text-align :center}}
   [popover "note" note]])

(def note-icon 
  [:svg {:preserveAspectRatio "none" :y "0px", :xml:space "preserve", :width "20px", :height "20px", :viewbox "0 0 512 512", :xmlns "http://www.w3.org/2000/svg", :xmlns:xlink "http://www.w3.org/1999/xlink", :id "Layer_1", :x "0px", :version "1.1", :enable-background "new 0 0 512 512"}
   [:g {:transform "scale(0.03)"}
    [:path {:fill "white" :stroke "white" :d "M448,0H64C46.328,0,32,14.313,32,32v448c0,17.688,14.328,32,32,32h288l128-128V32C480,14.313,465.688,0,448,0z M352,466.75\n\tV384h82.75L352,466.75z M448,352h-96c-17.688,0-32,14.313-32,32v96H64V32h384V352z M96,112c0-8.844,7.156-16,16-16h288\n\tc8.844,0,16,7.156,16,16s-7.156,16-16,16H112C103.156,128,96,120.844,96,112z M96,208c0-8.844,7.156-16,16-16h288\n\tc8.844,0,16,7.156,16,16s-7.156,16-16,16H112C103.156,224,96,216.844,96,208z M96,304c0-8.844,7.156-16,16-16h288\n\tc8.844,0,16,7.156,16,16s-7.156,16-16,16H112C103.156,320,96,312.844,96,304z"}]]])

(defn code-example [item lang]
  (case (:type item)

    :text
    [:td table-border
     [:p (-> item lang :text)]]

    [:td table-border
     (when-let [url (-> item lang :fn doc-url)]
       [:a {:href url :target "_blank"} info-button])
     (when-let [note (-> item lang :note)]
       [popover note-icon note])
     [input  (-> item lang)]
     [output (-> item lang)]]))

(defn headers [titles ks]
  [:<>
   (for [k ks]
     [:td table-border (k titles)])])