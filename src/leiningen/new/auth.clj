(ns leiningen.new.auth
  (:require [leiningen.new.common :refer :all]))

(defn auth-features [[assets options :as state]]
  (if (some #{"+auth"} (:features options))
    [assets
     (-> options
         (append-options :dependencies [['buddy "0.5.4"]])
         (append-formatted :auth-middleware-required
                           [['buddy.auth.middleware :refer ['wrap-authentication]]
                            ['buddy.auth.backends.session :refer ['session-backend]]
                            ['buddy.auth.accessrules :refer ['wrap-access-rules]]
                            ['buddy.auth :refer ['authenticated?]]
                            [(symbol (str (:project-ns options) ".layout")) :refer ['*identity*]]]
                           plugin-indent))]
    state))
