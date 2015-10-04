(ns ts-layout.core-test
  (:require [clojure.test :refer :all]
            [ts-layout.core :refer :all]))

(defn request [resource web-app & params]
   (web-app {:request-method :get :uri resource :params (first params)}))

(deftest root
  (let [response (request "/" app)]
    (is (= 302 (:status response)))
    (is (= {"Location" "/boxes"} (:headers response)))))
