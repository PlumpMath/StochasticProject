(ns maximileiningen.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as ar]
            [clojure.core.logic.fd :as fd]
            [clojure.core.matrix.operators :as mo])
  (:gen-class))

(def max-delta "Depends on the viewpoint: either temperature, speed or
  max distance a thing can move between two frames."
  80)

(def new-delta #(- (rand (inc (* 2 max-delta))) max-delta))

(defn setup []
  ;; Set frame rate (frames per second).
  (q/frame-rate 100)
  ;; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ;; setup function returns initial state. It contains
  ;; circle color and position.
  {:color 0
   :position [0 0 0]
   :angle 0
   :navigation-3d {:position [0 0 250]
                   :straight [0.0 0.0 -1.0]
                   :up [0.0 1.0 0.0]}})

(defn update-state [state]
  (assoc state
         ;; Update sketch state by changing circle color and position.
         :color (mod (+ (:color state) 0.7) 255)
         :position (mo/+ (vec (repeatedly 3 (comp #(/ % 100) new-delta)))
                         (:position state))))

(defn draw-state [state]
  ;; Clear the sketch by filling it with light-grey color.
  (q/background 240)
  (q/no-stroke)
  ;; Set circle color.
  (q/fill (:color state) 255 255)
  ;; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        [x y z] (:position state)]
    ;; Move origin point to the center of the sketch.
    (q/with-translation [[x y z]]
      ;; Draw the circle.
      (q/sphere 80))))

(defn -main [& args]
  (q/defsketch maximileiningen2
    :title "You spin my circle right round"
    :size [500 500]
    :settings #(q/smooth 3)
    ;; setup function called only once, during sketch initialization.
    :setup setup
    ;; update-state is called on each iteration before draw-state.
    :update update-state
    :draw draw-state
    :renderer :opengl
    :features [:keep-on-top]
    :middleware [m/fun-mode m/navigation-3d]))
