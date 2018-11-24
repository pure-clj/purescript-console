(ns Effect.Console._foreign
  (:refer-clojure :exclude [time]))

(defn log [s]
  (fn [& _]
    (println s)
    {}))

(def warn log)
(def info log)

(defn error [s]
  (fn [& _]
    (binding [*out* *err*]
      (println s))
    {}))

(def ^:private timers (atom {}))

(defn time [s]
  (fn [& _]
    (swap! timers assoc s (System/currentTimeMillis))
    {}))

(defn timeEnd [s]
  (fn [& _]
    (let [start (get @timers s)
          elapsed (- (System/currentTimeMillis) start)]
      (swap! timers dissoc s)
      (println (format "%s: %sms" s elapsed))
      {})))
