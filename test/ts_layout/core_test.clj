(ns ts-layout.core-test
  (:require [clojure.test :refer :all]
            [ts-layout.core :refer :all]))

(defn request [resource web-app method & params]
   (web-app {:request-method method :uri resource :params (first params)}))

(defn get-it [resource web-app & params]
  (apply (partial request resource web-app :get) params))

(defn post-it [resource web-app & params]
  (apply (partial request resource web-app :post) params))

(deftest root
  (let [response (get-it "/" app)]
    (is (= 302 (:status response)))
    (is (= {"Location" "/boxes"} (:headers response)))))

(deftest boxen
  (let [response (get-it "/boxes" app)]
    (is (= 200 (:status response)))))

(deftest up
  (let [response (get-it "/boxes/0/up" app)]
    (is (= 302 (:status response)))
    (is (= {"Location" "/boxes"} (:headers response)))))

(deftest down
  (let [response (get-it "/boxes/0/down" app)]
    (is (= 302 (:status response)))
    (is (= {"Location" "/boxes"} (:headers response)))))

(deftest delete
  (let [response (get-it "/boxes/0/delete" app)]
    (is (= 302 (:status response)))
    (is (= {"Location" "/boxes"} (:headers response)))))
