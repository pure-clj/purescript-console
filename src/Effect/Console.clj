(ns Effect.Console._foreign)

(defn log [s]
  (fn []
    (println s)
    {}))

(def warn log)
(def info log)

(defn error [s]
  (fn []
    (binding [*out* *err*]
      (println s))
    {}))
