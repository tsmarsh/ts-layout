(ns ts-layout.views
  (:require [hiccup.core :as h]))

(def nav (h/html
          [:form {:action "/boxes/add" :method "POST"}
           [:input {:name "url"}]
           [:input {:type "submit" :value "Add"}]]))

(defn page [content] (h/html
                      [:head [:title "Dashboard"]
                       [:link {:rel "stylesheet" :href "/public/layout.css"}]]
                      [:body
                       nav
                       content]))

(defn missing []
  (h/html [:h1 "Dumbass"]))

(defn draw-box [idx url]
  (h/html [:div {:class :box}
           [:a {:href (str "/boxes/" idx "/up")} "Up"]
           [:a {:href (str "/boxes/" idx "/down")} "Down"]
           [:a {:href (str "/boxes/" idx "/delete")} "Delete"]
           [:iframe {:src url}]]))

(defn numero [number]
  (h/html
        [:head [:title (str "Number: " number)]]
        [:body [:h1 {:class "number"} number]]))
