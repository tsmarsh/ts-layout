(ns ts-layout.util)

(defn drop-nth [v idx]
  (concat (take idx v) (subvec (into [] v)
                               (min (inc idx)
                                    (count v)))))

(defn swap [v i1 i2]
  (let [v' (into [] v)
        i1' (mod i1 (count v))
        i2' (mod i2 (count v))
        i1'' (min i1' i2')
        i2'' (max i1' i2')]
    (assoc v' i2'' (v' i1'') i1'' (v' i2''))))
