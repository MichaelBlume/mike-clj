(ns mike
  (:require [clojure.test :refer [run-tests]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [cemerick.pomegranate :refer [add-dependencies]]))

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
  (refresh)
  (apply run-tests (all-test-ns)))

(def repos
  (merge
    cemerick.pomegranate.aether/maven-central
    {"clojars" "http://clojars.org/repo"}))

(defn add-dep* [d]
  (add-dependencies
    :coordinates [d]
    :repositories repos))

(defmacro add-dep [d]
  `(add-dep* (quote ~d)))
