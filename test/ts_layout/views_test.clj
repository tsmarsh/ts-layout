(ns ts-layout.views-test
  (:require [ts-layout.views :refer :all]
            [clojure.test :refer :all]))

(deftest furniture
  (is (=  "<head><title>Dashboard</title><link href=\"/public/layout.css\" rel=\"stylesheet\" /></head><body><form action=\"/boxes/add\" method=\"POST\"><input name=\"url\" /><input type=\"submit\" value=\"Add\" /></form>foo</body>"
 (page "foo"))))

(deftest box
  (is (= "<div class=\"box\"><a href=\"/boxes/0/up\">Up</a><a href=\"/boxes/0/down\">Down</a><a href=\"/boxes/0/delete\">Delete</a><iframe src=\"/1\"></iframe></div>"
         (draw-box 0 "/1"))))

(deftest four-oh-four
  (is (= "<h1>Dumbass</h1>"
         (missing))))

(deftest dummy
  (is (= "<head><title>Number: 0</title></head><body><h1 class=\"number\">0</h1></body>"
         (numero 0))))
