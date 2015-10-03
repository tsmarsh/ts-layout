(ns ts-layout.views-test
  (:require [ts-layout.views :refer :all]
            [clojure.test :refer :all]))

(deftest furniture
  (is (= "<head><title>Dashboard</title></head><body><form action=\"/boxes/add\" method=\"POST\"><input name=\"url\" /><input type=\"submit\" value=\"Add\" /></form>foo</body>" (page "foo"))))
