![https://enterpriseconnect.googlecode.com/svn/trunk/src/images/mvc.png](https://enterpriseconnect.googlecode.com/svn/trunk/src/images/mvc.png)

上图是一次页面请求的处理流程


---


下面以首页 http://localhost:8080/connect-web 的请求为例，来了解一下 Enterprise Connect 的一次完整页面请求。

  1. 当浏览器发起一次请求时，首先会被 URLRewriter 模块拦截，该模块会根据 /WEB-INF/ 下的 urlrewrite.xml 里定义的规则来冲重定向请求。首页的请求将会被下面这条规则所匹配，因此，重写之后的请求将是 http://localhost:8080/connect-web/page?viewName=/dashboardTname=home
```
<rule>
    <from>^/$</from>
    <to last="true">/page?viewName=/dashboardTname=home</to>
</rule>
```
```
注意：viewName所指定的是页面定义文件，页面定义文件在 /WEB-INF/sites/${site_name} 下。
     T后面跟的是一些自定义参数，例如：上面的 name=home 表示在 dashboard 这个页面定义文件里选择 page name 为 home 的页面配置。
```
  1. 所有 /page 的请求都会被 FragmentController 的 renderPage() 方法来处理，该方法会根据 /page 后的参数来获取当前请求的页面的配置，例如： viewName=/dashboardTname=home，renderPage() 会调用 ConfigFactory对象的 getPageConfig() 从 /WEB-INF/sites/${site\_name}/dashboard.xml 这个页面定义文件读取页面 home 的定义。
  1. 当 renderPage() 的处理结束，就会返回 /WEB-INF/themes/${theme\_name}/layout.jsp 这个页面布局文件，在渲染这个布局文件的时候，会遇到几个自定义 JSP 标签，例如下面这段，该标签首页会判定是否在页面定义文件里定义了列 leftColumn,如果该列存在就会 render 该列，在 render 的过程中，会发送多个 include 请求，这些 include 的请求的形式是 /${context\_path}/fragment/${fragment\_id},该类请求会被 FragmentController 的 renderFragment() 处理，并返回 include 的页面。
```
<osf:position exist="leftColumn">
    <div class="span-5 left-column">
        <osf:position render="leftColumn"/>
    </div>
</osf:position>
```
  1. 当渲染 layout.jsp 过程中，经过多次的 include 请求之后就渲染成了最终的请求页面，这个过程其实跟 Portlet 是一样，Enterprise Connect没有使用复杂的 Portlet，而是自己实现了一种比较简单的 Fragment，该 Fragment 由 Spring 容器来管理。