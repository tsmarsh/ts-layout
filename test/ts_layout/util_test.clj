(ns ts-layout.util-test
  (:require [ts-layout.util :refer :all]
            [clojure.test :refer :all]))

(deftest droppings
  (testing "can remove first" 
    (is (= [:b :c] (drop-nth [:a :b :c] 0))))
  (testing "can remove last"
    (is (= [:a :b] (drop-nth [:a :b :c] 2))))
  (testing "can remove the middle"
    (is (= [:a :c] (drop-nth [:a :b :c] 1)))))

(deftest swappings
  (testing "can swap"
    (is (= [:b :a] (swap [:a :b] 0 1)))
    (is (= [:b :a] (swap [:a :b] 1 0)))
    (is (= [:c :b :a] (swap [:a :b :c] -1 0)))))
