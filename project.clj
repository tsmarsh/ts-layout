(defproject ts-layout "0.1.0-SNAPSHOT"
  :description "Window manager for the web"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]
                 [ring "1.4.0"]
                 [http-kit "2.1.18"]]
  :plugins [[lein-cloverage "1.0.6"]
            [lein-cljfmt "0.1.12"]])
