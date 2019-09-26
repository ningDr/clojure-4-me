(ns $clojureDoc.core.slurp)
;; 使用方式：(slurp f & opts)
;; Opens a reader on f and reads all its contents, returning a string.
;; See clojure.java.io/reader for a complete list of supported arguments.
;; 打开一个基于函数f的读取器，读取所有的内容，返回一个字符串
;; 参照clojure.java.io/reader查看支持的所有参数列表
;; 以下是clojure.java.io/reader的介绍：
;; Attempts to coerce its argument into an open java.io.Reader.
;; Default implementations always return a java.io.BufferedReader.
;;
;; Default implementations are provided for Reader, BufferedReader,
;; InputStream, File, URI, URL, Socket, byte arrays, character arrays,
;; and String.
;;
;; If argument is a String, it tries to resolve it first as a URI, then
;; as a local file name.  URIs with a 'file' protocol are converted to
;; local file names.
;;
;; Should be used inside with-open to ensure the Reader is properly
;; closed.
;; 可以看出，默认返回一个BufferedReader
;; 参数类型可以是 Reader, BufferedReader, InputStream, File, URI, URL, Socket, byte arrays, character arrays, and String

;; 编辑文件，后面是内容，如果文件不存在，则创建，类似于Linux shell中的"echo > fileName"
(spit "test.txt" "this is a test file for slurp.\n Bye.")
(println "============")
;; => ============
;; 提供的参数是String，以URI解析不通，然后以本地文件方式解析，并输出内容
(println (slurp "test.txt"))
;; => this is a test file for slurp.
;;     Bye.

;; java.lang.IllegalArgumentException: Cannot open xxx as an InputStream.
;; (def x (atom 123))
;; (slurp x)

