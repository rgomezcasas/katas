(ns the-buffer.core
  (:use clojure.java.io))

(defn- parse-bigint [s]
  (BigInteger. (re-find  #"\d+" s )))

(defn- append-to-file [string file-name]
  (with-open [writer-handler (writer file-name :append true)]
    (.write writer-handler (str string "\n"))))

(defn calculate [total-urinals]
  (quot (+ 1 total-urinals) 2))

(defn calculate-from-file [input-file output-file]
  (with-open [reader-handler (reader input-file)]
    (doseq [line (next (line-seq reader-handler))]
      (append-to-file (str (calculate (parse-bigint line))) output-file))))
