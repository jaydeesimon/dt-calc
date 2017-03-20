(ns user
  (:require [clj-time.core :refer :all]
            [clj-time.format :refer :all])
  (:import (org.joda.time LocalDate Interval)
           (org.joda.time.format PeriodFormatterBuilder)))

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

(defn format-period [start end]
  (let [[start end] (sort [start end])
        period (-> (interval start end) (.toPeriod))
        fmt (-> (PeriodFormatterBuilder.)
                (.printZeroAlways)
                (.appendYears)
                (.appendSuffix " year" " years")
                (.appendSeparator ", ")
                (.appendMonths)
                (.appendSuffix " month" " months")
                (.appendSeparator ", ")
                (.appendDays)
                (.appendSuffix " day" " days")
                (.toFormatter))]
    (.print fmt period)))
