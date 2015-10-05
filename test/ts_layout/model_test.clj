(ns ts-layout.model-test
  (:require [ts-layout.model :refer :all]
            [clojure.test :refer :all]))

(deftest up
  (testing "head wraps to tail"
    (is (= [:b :a] (push-box-up [:a :b] 0))))
  (testing "moves things up"
    (is (= [:b :a :c] (push-box-up [:a :b :c] 1)))))

(deftest down
  (testing "tail wraps to head"
    (is (= [:b :a] (push-box-down [:a :b] 1))))
  (testing "moves things down"
    (is (= [ :a :c :b] (push-box-down [:a :b :c] 1)))))

(deftest delete
  (testing "empties the seq"
    (is (= [] (delete-box [:a] 0))))
  (testing "deletes an entry"
    (is (= [:a] (delete-box [:a :b] 1)))
    (is (= [:b] (delete-box [:a :b] 0)))))

(deftest add
  (testing "can add to empty"
    (is (= [:a] (add-box [] :a))))
  (testing "can add"
    (is (= [:a :b] (add-box [:a] :b)))))
