(ns ts-layout.model
  (:require [ts-layout.util :as u]))

(defn push-box-up [boxes idx]
   (u/swap boxes (dec idx) idx))

(defn push-box-down [boxes idx]
  (u/swap boxes idx (inc idx)))

(defn delete-box [boxes idx]
  (u/drop-nth boxes idx))

(defn add-box [boxes url]
  (conj boxes url))
