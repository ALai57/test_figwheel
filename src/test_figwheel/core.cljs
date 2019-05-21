(ns test-figwheel.core
  (:require [sablono.core :as sab]))

(.log js/console "Hi nowhere!")

(def app-state (atom { :likes 0 }))

(defn like-app [data]
  (sab/html [:div
             [:h1 "My app's quantified popularity: " (:likes @data)]
             [:div [:a {:href "#"
                        :onClick #(swap! data update-in [:likes] inc)}
                    "Thumbs up"]]]))

(defn render! []
  (.render js/ReactDOM
           (like-app app-state)
           (.getElementById js/document "app")))

(add-watch app-state :on-change (fn [_ _ _ _] (render!)))

(render!)
