(ns mike
  (:require [clojure.test :refer [run-tests]]))

; Stuff I always want

(defn has-tests
  [ns]
  (->>
    ns
    the-ns
    ns-interns
    vals
    (some (comp :test meta))
    boolean))

(defn all-test-ns []
  (filter has-tests (all-ns)))

(defn run-all-tests []
  (apply run-tests (all-test-ns)))
