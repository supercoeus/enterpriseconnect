Enterprise Connect使用Maven作为项目构建工具，其工程结构如下：
```
connect
  |
  +---connect-parent
  |
  +---connect-dao
  |
  +---connect-service
  |
  +---connect-task
  |
  +---connect-web
```

如果想要将Enterprise Connect导入成 Eclipse工程，你需要安装M2Eclipse插件。安装地址如下：

核心插件：http://m2eclipse.sonatype.org/sites/m2e

额外工具：http://m2eclipse.sonatype.org/sites/m2e-extras

关于 Maven,M2Eclipse 关注此博客 http://www.juvenxu.com/category/m2eclipse

关于使用M2eclipse进行Maven项目的导入以及开发，请参考相关文档。。。


---


开始将 Enterprise Connect 导入 Eclipse 之前，请确认你已经安装了 Eclipse(JEE)  M2Eclipse 插件。

  * 开启 Eclipse Import 向导，选择 Maven/Existing Maven Projects 选项

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import1.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import1.png)

  * 选择你从 Google SVN 中 Check Out 出来的源代码的根目录路径，当你选定之后，在下方就可以看大 Enterprise Connect 的所有子工程，然后确定导入即可

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import2.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import2.png)

  * 当导入完成之后，会在 Eclipse 的 Project Explorer 视图中看到导入的目标工程，包括所有子工程，其中的 connect-web 就是一个类似直接用 Eclipse 创建的 Web 工程

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import3.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import3.png)

  * 当你完成正确的工程导入之后，你还需要做的一件事就是配置数据库环境，Enterprise Connect 的开发环境目前支持 MySQL Postgresql 数据库，接下来你要做的就是选定你要使用的数据库及用户名密码。在 Preferences 中选择 Maven-->UserSettings 如下图，并如图所示点击 (open file) 来编辑 settings.xml 这个文件

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import7.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import7.png)

  * 在 settings.xml 中的 profiles 元素下添加一个 profile 配置，如下：

```
<profile>
    <id>development</id>
    <properties>
        <database>mysql</database>
        <jdbc.username>root</jdbc.username>
        <jdbc.password>123456</jdbc.password>
    </properties>
</profile>
```

  * 接下来你需要告诉 Maven，你要启用上面配置的这个 Profile，因此，你还需要在 settings.xml 的最后加上下面这段配置，来告诉 Maven 你要启用上面配置的这个 development profile

```
<activeProfiles>
    <activeProfile>development</activeProfile>
</activeProfiles>
```

  * 当你完成上面的配置之后，开发环境就已经完成了，接下来你可以在 Eclipse Run or Debug 你的 Enterprise Connect 了， 如下图：

![http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import4.png](http://enterpriseconnect.googlecode.com/svn/trunk/src/images/Connect-Import4.png)

#### 是不是很简单呢？ 至少我觉得比将 Enterprise Connect 转成 MyEclipse 简单！ :D ####