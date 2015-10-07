(ns ts-layout.core-test
  (:require [clojure.test :refer :all]
            [ts-layout.core :refer :all]))

(defn request [resource web-app method & params]
   )

(defn get-it [resource web-app & params]
  (web-app {:request-method :get :uri resource :params (first params)}))

(defn post-it [resource web-app body]
  (web-app {:request-method :post :uri resource :body (java.io.ByteArrayInputStream. (.getBytes body))}))

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

(deftest add
  (let [response (post-it "/boxes/add" app "url=floop")]
    (is (= 302 (:status response)))
    (is (= {"Location" "/boxes"} (:headers response)))))
