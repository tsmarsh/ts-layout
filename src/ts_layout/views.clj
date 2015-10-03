(ns ts-layout.views
  (:require [hiccup.core :as h]))

(def nav (h/html
          [:form {:action "/boxes/add" :method "POST"}
           [:input {:name "url"}]
           [:input {:type "submit" :value "Add"}]]))

(defn page [content] (h/html
                      [:head [:title "Dashboard"]]
                      [:body
                       nav
                       content]))

(defn draw-box [idx url]
  (h/html [:div {:class :box}
           [:a {:href (str "/boxes/" idx "/up")} "Up"]
           [:a {:href (str "/boxes/" idx "/down")} "Down"]
           [:a {:href (str "/boxes/" idx "/delete")} "Delete"]
           [:iframe {:src url}]]))
