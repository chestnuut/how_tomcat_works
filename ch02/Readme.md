# Chapter 2
## A simple Servlet Container

**Goal:**

| what we need to implement?

implement一个static resource 和一个servlet container处理请求

### Learned

Facade 外观类的作用

Request implements ServletRequest

在Request class中，有一些method是我们不想暴露给servletProcessor的，此时可以用外观类RequestFacade implements ServletRequest

在RequestFacade 的所有override method, 都通过Request对象给出

这样servlet层面就没有办法access Request对象的方法