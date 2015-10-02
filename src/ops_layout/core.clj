(ns ops-layout.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :as h]
            [org.httpkit.server :as hk]))

(def boxes ["/1" "/2"])

(defn draw-box [url]
  (h/html [:iframe {:src url}]))

(defroutes app
  (GET "/:number" [number] (h/html [:h1 {:class "number"} number]))
  (GET "/" []  (map draw-box boxes))
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
