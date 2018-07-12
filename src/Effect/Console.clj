(ns Effect.Console._foreign)

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
