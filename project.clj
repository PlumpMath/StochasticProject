(defproject maximileiningen "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GNU GPL v3+"
            :url "http://www.gnu.org/licenses/gpl-3.0.en.html"}
  :main ^:skip-aot maximileiningen.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.3.0"]
                 [incanter "1.5.7"] ;; for probabilities... way too big
                 [net.mikera/core.matrix "0.49.0"]
                 [org.clojure/core.logic "0.8.10"]])
