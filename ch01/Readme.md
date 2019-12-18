# Chapter 1 
## A simple web server
**Goal:**

| what we need to implement?

打开浏览器，输入
http://localhost:8080/index.html
显示index.html

verify 控制台同样显示了信息

### Learned

IOUtils is very handy to use, but it doesn't close the stream.

HTTP Response format: "HTTP/1.1 200 OK\r\n\r\n"

### ISSUES

can't use direct InputStream on SocketInputStream, thr request will hang.

Probably because postman/browser did not send EOS marker?

Fixed by reading InputStream into a buffered byte array and use new String(byte[], Charset) to convert to String. 

