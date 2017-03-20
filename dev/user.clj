(ns user
  (:require [clj-time.core :as core :refer :all :rename {second clj-second
                                                         extend clj-extend}]
            [clj-time.format :as format :refer :all]
            [clj-time.coerce :as coerce])
  (:import (org.joda.time LocalDate)
           (org.joda.time.format PeriodFormat)))

(def local-date-formatter (formatter-local "EEEE, MMMM d, yyyy"))

(defn qf [dt]
  (let [[f fmt] (if (instance? LocalDate dt)
                  [unparse-local-date local-date-formatter]
                  [unparse (formatter :rfc822)])]
    (f fmt dt)))

(defn from-today [& ps]
  (qf (apply (partial plus (today)) ps)))

(defn ago-today [& ps]
  (qf (apply (partial minus (today)) ps)))

(defn time-pass [start end]
  (let [[start end] (sort [(coerce/to-date-time start)
                           (coerce/to-date-time end)])
        period (-> (interval start end) (.toPeriod))
        fmt (PeriodFormat/wordBased)]
    (.print fmt period)))
