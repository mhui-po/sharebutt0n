(ns memefactory.server.contract.meme
  (:require
    [district.server.smart-contracts :refer [contract-call instance contract-address]]
    [memefactory.shared.contract.meme :refer [parse-load-meme]]
    [district.shared.error-handling :refer [try-catch try-catch-throw]]))

(defn mint [contract-addr & [amount opts]]
  (try-catch-throw
   (contract-call [:meme contract-addr] :mint (or amount 0) (merge {:gas 6000000} opts))))

(defn load-meme [contract-addr]
  (parse-load-meme contract-addr (contract-call (instance :meme contract-addr) :load-meme)))

(defn transfer-deposit [contract-addr & [opts]]
  (try-catch-throw
   (contract-call (instance :meme contract-addr) :transfer-deposit (merge {:gas 300000} opts))))
