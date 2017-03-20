When I need to do some quick date-time calculations.

```
$ lein repl

# 7 months and 13 days from today
user=> (from-today (months 7) (days 13))
"Wednesday, November 1, 2017"

# 1 year and 30 days ago from today
user=> (ago-today (years 1) (days 30))
"Thursday, February 18, 2016"

user=> (time-pass "2011-11-11" (today))
"5 years, 4 months, 1 week and 2 days"

# the arguments can be passed in any order
user=> (time-pass (today) "2011-11-11")
"5 years, 4 months, 1 week and 2 days"

user=> (time-pass "2011-11-11" (local-date 2009 9 9))
"2 years, 2 months and 2 days"

# clj-time.core and clj-time.format are in the user namespace so go nuts
user=> (plus (year-month 2010 1) (years 50))
#object[org.joda.time.YearMonth 0x711d0a61 "2060-01"]

# for all other clj-time namespaces
user=> (require '[clj-time.predicates :as pr])
nil
user=> (pr/weekday? (today))
false
```