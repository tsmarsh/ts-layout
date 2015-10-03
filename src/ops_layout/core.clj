(ns ops-layout.core
  (:require [compojure.core :refer :all]
            [compojure.coercions :refer :all]
            [compojure.route :as route]
            [hiccup.core :as h]
            [ring.util.response :as rr]
            [org.httpkit.server :as hk]))

(def boxes (atom ["/1" "/2"]))

(defn draw-box [idx url]
  (h/html [:div {:class :box}
           [:a {:href (str "/boxes/" idx "/up")} "Up"]
           [:a {:href (str "/boxes/" idx "/down")} "Down"]
           [:iframe {:src url}]]))

(defn swap [v i1 i2] 
  (assoc v i2 (v i1) i1 (v i2)))

(defn push-box-up [idx]
   (reset! boxes (swap @boxes (dec idx) idx)))

(defn push-box-down [idx]
  (reset! boxes (swap @boxes idx (inc idx))))

(defroutes app
  (context "/boxes/:index" [index :<< as-int]
           (GET "/up" [] (do 
                           (push-box-up index)
                           (rr/redirect "/boxes")))
           (GET "/down" [] (do 
                             (push-box-down index)
                             (rr/redirect "/boxes"))))
  (GET "/boxes" []  (map-indexed draw-box @boxes))
  (GET "/:number" [number] (h/html [:h1 {:class "number"} number]))
  (GET "/" [] (rr/redirect "/boxes"))
  (route/not-found (h/html [:h1 "Dumbass"])))

(defonce server (atom nil))

(def port (atom 8888))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)
    (println "Server stopped")))

(defn start-server []
  (reset! server (hk/run-server #'app {:port @port}))
  (println "Server running on" @port))
