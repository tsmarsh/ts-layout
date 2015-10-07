(ns ts-layout.core
  (:require [compojure.core :refer :all]
            [compojure.coercions :refer :all]
            [compojure.route :as route] 
            [ring.util.response :as rr]
            [ring.util.codec :as rc]
            [org.httpkit.server :as hk]
            [ts-layout.model :as m]
            [ts-layout.views :as v]))

(def boxes (atom ["/1" "/2"]))

(defn persist! [val]
  (reset! boxes val))

(defn reload []
  (rr/redirect "/boxes"))

(defn get-form-param [body param]
  (get (rc/form-decode body) param))

(defroutes app
  (context "/boxes/:index" [index :<< as-int]
           (GET "/up" [] (do 
                           (persist! (m/push-box-up @boxes index))
                           (reload)))
           (GET "/down" [] (do 
                             (persist! (m/push-box-down @boxes index))
                             (reload)))
           (GET "/delete" [] (do
                               (persist!  (m/delete-box @boxes index))
                               (reload))))
  (POST "/boxes/add"
        {body-stream :body} (let [body (slurp body-stream)]
                              (persist!
                               (m/add-box @boxes (get-form-param body "url"))))
        (reload))
  (GET "/boxes" []  (v/page (map-indexed v/draw-box @boxes)))
  (GET "/:number" [number :<< as-int]
       (v/numero number))
  (GET "/" [] (rr/redirect "/boxes"))
  (route/resources "/public")
  (route/not-found (v/missing)))

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
