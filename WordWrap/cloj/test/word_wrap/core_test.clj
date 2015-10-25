(ns word-wrap.core-test
  (:use midje.sweet)
  (:use [word-wrap.core]))

(fact "it returns the same string for a short sentence"
      (wrap "Hola esto es un texto corto" 10000) => "Hola esto es un texto corto"
      (wrap "Hola esto es un texto" 13) => "Hola esto es\nun texto"
      (wrap "Hola esto text long" 5) => "Hola\nesto\ntext\nlong"
      (wrap "Hola esto es un texto largo" 5) => "Hola\nesto\nes un\ntexto\nlargo"
      )

