/*
Navicat MySQL Data Transfer

Source Server         : LOCAL
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : ncodeblog

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-08-02 17:51:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbblog
-- ----------------------------
DROP TABLE IF EXISTS `tbblog`;
CREATE TABLE `tbblog` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `typeid` bigint(32) NOT NULL DEFAULT '0' COMMENT '文章类型 11:原创, 12:转载, 13:翻译',
  `uid` bigint(32) NOT NULL COMMENT '用户ID',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0:草稿, 1:公开, 2:私密, 3:回收站',
  `top` tinyint(4) NOT NULL COMMENT '置顶 0:非置顶, 1:置顶',
  `summary` text NOT NULL COMMENT '摘要',
  `mdContent` text NOT NULL COMMENT 'md文章内容',
  `htmlContent` text NOT NULL COMMENT 'html文章内容',
  `delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除 0:未删除,1:删除',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `modifytime` datetime NOT NULL COMMENT '修改时间',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  KEY `idx_state` (`state`),
  KEY `idx_type` (`typeid`),
  KEY `idx_title` (`title`),
  KEY `idx_uid` (`uid`),
  KEY `idx_top` (`top`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of tbblog
-- ----------------------------
INSERT INTO `tbblog` VALUES ('1', 'Linux中安装zookeeper', '11', '1', '0', '0', '最近打算出一个系列，介绍Dubbo的使用。\n\n分布式应用现在已经越来越广泛，Spring Could', '最近打算出一个系列，介绍Dubbo的使用。\n\n---\n分布式应用现在已经越来越广泛，Spring Could也是一个不错的一站式解决方案，不过据我了解国内目前貌似使用阿里Dubbo的公司比较多，一方面这个框架也确实很OK，另一方面可能也是因为Dubbo的中文文档比较全的缘故，据Dubbo官网上的消息，阿里已经重新开始了对Dubbo的维护，这也算是使用Dubbo的互联网公司的福音了吧。OK，废话不多说，今天我们就先来看看如何在Linux上安装zookeeper。\n\n---\n\n了解过Dubbo的小伙伴都知道，Dubbo官方建议我们使用的注册中心就是zookeeper，zookeeper本来是Hadoop的一个子项目，现在发展成了Apache的顶级项目，看名字就知道Zookeeper就是动物园管理员，管理Hadoop(大象)、Hive(蜂房/蜜蜂)等动物。Apache上的Zookeeper分Linux版和Windows版，但是考虑到实际生产环境都是Linux，所以我们这里主要介绍Linux上Zookeeper的安装，Windows上Zookeeper的安装则比较简单，下载解压即可，和Tomcat差不多。\n\nOK，废话不多说，接下来我们就来看看zookeeper的安装步骤。\n\n---\n环境：\n>1.VMware® Workstation 12 Pro  \n>2.CentOS7  \n>3.zookeeper-3.4.10(本文写作时的最新稳定版)  \n\n---\n# 安装步骤\n1.下载zookeeper\n\nzookeeper下载地址如下，小伙伴们可以在第一个地址中选择适合自己的zookeeper版本，也可以直接点击第二个地址下载我们本文使用的zookeeper。\n\n>1.[http://mirrors.hust.edu.cn/apache/zookeeper/](http://mirrors.hust.edu.cn/apache/zookeeper/)  \n\n>2.[http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz](http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz)\n\n\n\n2.将下载好的zookeeper上传到Linux服务器上\n\n上传方式多种多样，我这里采用了xftp，小伙伴们也可以直接使用putty上传，上传结果如下：\n![这里写图片描述](http://img.blog.csdn.net/20170825114622362?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n3.将文件解压到/opt目录下  \n\n![这里写图片描述](http://img.blog.csdn.net/20170825115122378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n4.进入到刚刚解压好的目录中，创建两个文件夹，分别是data和logs，如下：\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115324970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n5.将解压后zookeeper-3.4.10文件夹下的zoo_sample.cfg文件拷贝一份命名为zoo.cfg，如下：\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115426251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n6.修改zoo.cfg文件，添加data和log目录，如下：\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115527367?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n>1.2888 端口号是zookeeper服务之间通信的端口   \n>2.3888 是zookeeper 与其他应用程序通信的端口  \n>3.initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5*2000=10 秒。  \n>4.syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2*2000=4 秒  \n>5.server.A=B:C:D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的IP地址或/etc/hosts文件中映射了IP的主机名；C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。\n\n7.在 dataDir=/opt/zookeeper-3.4.10/data下创建 myid文件 编辑myid文件，并在对应的IP的机器上输入对应的编号。如在zookeeper上，myid文件内容就是1。如果只在单点上进行安装配置，那么只有一个server.1。如下：\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115647920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n8.在.bash_profile文件中增加zookeeper配置：\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115729473?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n9.使配置生效\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115807787?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n10.关闭防火墙\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115848488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n11.启动并测试\n\n![这里写图片描述](http://img.blog.csdn.net/20170825115938795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n>启动之后如果能看到Mode:standalone就表示启动成功了。\n\n12.关闭zookeeper\n\n![这里写图片描述](http://img.blog.csdn.net/20170825121021364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n13.配置开机启动zookeeper\n\n![这里写图片描述](http://img.blog.csdn.net/20170825121059827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)\n\n>**注意注意** 在centos7中，/etc/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限\n```chmod +x /etc/rc.d/rc.local```\n\n\nOK,以上就是我们在CentOS7中安装zookeeper的全过程，做好这一切之后我们就可以在Dubbo中使用这个注册中心了，这个我们放在下一篇博客中介绍。\n\n更多JavaEE资料请关注公众号：\n\n![](http://img.blog.csdn.net/20170823174820001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)', '<p>最近打算出一个系列，介绍Dubbo的使用。</p>\n<hr />\n<p>分布式应用现在已经越来越广泛，Spring Could也是一个不错的一站式解决方案，不过据我了解国内目前貌似使用阿里Dubbo的公司比较多，一方面这个框架也确实很OK，另一方面可能也是因为Dubbo的中文文档比较全的缘故，据Dubbo官网上的消息，阿里已经重新开始了对Dubbo的维护，这也算是使用Dubbo的互联网公司的福音了吧。OK，废话不多说，今天我们就先来看看如何在Linux上安装zookeeper。</p>\n<hr />\n<p>了解过Dubbo的小伙伴都知道，Dubbo官方建议我们使用的注册中心就是zookeeper，zookeeper本来是Hadoop的一个子项目，现在发展成了Apache的顶级项目，看名字就知道Zookeeper就是动物园管理员，管理Hadoop(大象)、Hive(蜂房/蜜蜂)等动物。Apache上的Zookeeper分Linux版和Windows版，但是考虑到实际生产环境都是Linux，所以我们这里主要介绍Linux上Zookeeper的安装，Windows上Zookeeper的安装则比较简单，下载解压即可，和Tomcat差不多。</p>\n<p>OK，废话不多说，接下来我们就来看看zookeeper的安装步骤。</p>\n<hr />\n<p>环境：</p>\n<blockquote>\n<p>1.VMware® Workstation 12 Pro<br />\n2.CentOS7<br />\n3.zookeeper-3.4.10(本文写作时的最新稳定版)</p>\n</blockquote>\n<hr />\n<h1>安装步骤</h1>\n<p>1.下载zookeeper</p>\n<p>zookeeper下载地址如下，小伙伴们可以在第一个地址中选择适合自己的zookeeper版本，也可以直接点击第二个地址下载我们本文使用的zookeeper。</p>\n<blockquote>\n<p>1.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/</a></p>\n</blockquote>\n<blockquote>\n<p>2.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz</a></p>\n</blockquote>\n<p>2.将下载好的zookeeper上传到Linux服务器上</p>\n<p>上传方式多种多样，我这里采用了xftp，小伙伴们也可以直接使用putty上传，上传结果如下：<br />\n<img src=\"http://img.blog.csdn.net/20170825114622362?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>3.将文件解压到/opt目录下</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115122378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>4.进入到刚刚解压好的目录中，创建两个文件夹，分别是data和logs，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115324970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>5.将解压后zookeeper-3.4.10文件夹下的zoo_sample.cfg文件拷贝一份命名为zoo.cfg，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115426251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>6.修改zoo.cfg文件，添加data和log目录，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115527367?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>1.2888 端口号是zookeeper服务之间通信的端口<br />\n2.3888 是zookeeper 与其他应用程序通信的端口<br />\n3.initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5<em>2000=10 秒。<br />\n4.syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2</em>2000=4 秒<br />\n5.server.A=B:C:D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的IP地址或/etc/hosts文件中映射了IP的主机名；C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。</p>\n</blockquote>\n<p>7.在 dataDir=/opt/zookeeper-3.4.10/data下创建 myid文件 编辑myid文件，并在对应的IP的机器上输入对应的编号。如在zookeeper上，myid文件内容就是1。如果只在单点上进行安装配置，那么只有一个server.1。如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115647920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>8.在.bash_profile文件中增加zookeeper配置：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115729473?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>9.使配置生效</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115807787?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>10.关闭防火墙</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115848488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>11.启动并测试</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115938795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>启动之后如果能看到Mode:standalone就表示启动成功了。</p>\n</blockquote>\n<p>12.关闭zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121021364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>13.配置开机启动zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121059827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p><strong>注意注意</strong> 在centos7中，/etc/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限<br />\n<code>chmod +x /etc/rc.d/rc.local</code></p>\n</blockquote>\n<p>OK,以上就是我们在CentOS7中安装zookeeper的全过程，做好这一切之后我们就可以在Dubbo中使用这个注册中心了，这个我们放在下一篇博客中介绍。</p>\n<p>更多JavaEE资料请关注公众号：</p>\n<p><img src=\"http://img.blog.csdn.net/20170823174820001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n', '0', '2019-08-01 17:12:50', '2019-08-01 17:12:53', '2019-08-01 17:13:01');

-- ----------------------------
-- Table structure for tbblogcommentrelation
-- ----------------------------
DROP TABLE IF EXISTS `tbblogcommentrelation`;
CREATE TABLE `tbblogcommentrelation` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bid` bigint(32) NOT NULL COMMENT '博客ID',
  `cid` bigint(32) NOT NULL COMMENT '评论ID',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bid` (`bid`),
  KEY `idx_cid` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章与评论关联表';

-- ----------------------------
-- Records of tbblogcommentrelation
-- ----------------------------

-- ----------------------------
-- Table structure for tbblogtagrelation
-- ----------------------------
DROP TABLE IF EXISTS `tbblogtagrelation`;
CREATE TABLE `tbblogtagrelation` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bid` bigint(32) NOT NULL COMMENT '博客ID',
  `tid` bigint(32) NOT NULL COMMENT '标签ID',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bid` (`bid`),
  KEY `idx_tid` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='博客标签关联表';

-- ----------------------------
-- Records of tbblogtagrelation
-- ----------------------------
INSERT INTO `tbblogtagrelation` VALUES ('1', '1', '1', '2019-08-01 17:14:28', '2019-08-01 17:14:33');
INSERT INTO `tbblogtagrelation` VALUES ('2', '1', '2', '2019-08-01 17:14:40', '2019-08-01 17:14:42');

-- ----------------------------
-- Table structure for tbcomment
-- ----------------------------
DROP TABLE IF EXISTS `tbcomment`;
CREATE TABLE `tbcomment` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bid` bigint(32) NOT NULL COMMENT '博客ID',
  `pid` bigint(32) NOT NULL DEFAULT '-1' COMMENT '上级评论ID,-1:正常评论，其他:评论的评论',
  `uid` bigint(32) DEFAULT NULL COMMENT '评论用户ID',
  `content` text NOT NULL COMMENT '评论内容',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bid` (`bid`),
  KEY `idx_pid` (`pid`),
  KEY `idx_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of tbcomment
-- ----------------------------

-- ----------------------------
-- Table structure for tbreads
-- ----------------------------
DROP TABLE IF EXISTS `tbreads`;
CREATE TABLE `tbreads` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bid` bigint(32) NOT NULL COMMENT '博客ID',
  `uid` bigint(32) NOT NULL COMMENT '用户ID',
  `pv` bigint(32) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bid` (`bid`),
  KEY `idx_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览量';

-- ----------------------------
-- Records of tbreads
-- ----------------------------

-- ----------------------------
-- Table structure for tbroles
-- ----------------------------
DROP TABLE IF EXISTS `tbroles`;
CREATE TABLE `tbroles` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tbroles
-- ----------------------------
INSERT INTO `tbroles` VALUES ('1', 'admin');
INSERT INTO `tbroles` VALUES ('2', 'dev');
INSERT INTO `tbroles` VALUES ('3', 'test');

-- ----------------------------
-- Table structure for tbtags
-- ----------------------------
DROP TABLE IF EXISTS `tbtags`;
CREATE TABLE `tbtags` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '标签名称',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Records of tbtags
-- ----------------------------
INSERT INTO `tbtags` VALUES ('1', 'Java', '2019-08-01 17:31:32', '2019-08-01 17:31:30');
INSERT INTO `tbtags` VALUES ('2', '后端', '2019-08-01 17:31:35', '2019-08-01 17:31:37');
INSERT INTO `tbtags` VALUES ('11', 'html', '2019-08-01 17:31:09', '2019-08-01 17:31:09');

-- ----------------------------
-- Table structure for tbtype
-- ----------------------------
DROP TABLE IF EXISTS `tbtype`;
CREATE TABLE `tbtype` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `tsrefreshtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客类型表';

-- ----------------------------
-- Records of tbtype
-- ----------------------------

-- ----------------------------
-- Table structure for tbuser
-- ----------------------------
DROP TABLE IF EXISTS `tbuser`;
CREATE TABLE `tbuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) NOT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `enabled` tinyint(2) DEFAULT '2' COMMENT '用户状态 0未激活 1注销 2激活',
  `face` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `refreshTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_email` (`email`),
  UNIQUE KEY `uni_phone` (`phone`),
  KEY `index_email` (`email`),
  KEY `index_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tbuser
-- ----------------------------
INSERT INTO `tbuser` VALUES ('1', '梅子酒', '123456', 'syang_010@163.com', '18801420263', '1', '111', '2019-07-16 09:58:22', '2019-07-17 10:57:19');

-- ----------------------------
-- Table structure for tbuserrolerelation
-- ----------------------------
DROP TABLE IF EXISTS `tbuserrolerelation`;
CREATE TABLE `tbuserrolerelation` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(32) NOT NULL COMMENT '用户ID',
  `rid` bigint(32) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户全权限表';

-- ----------------------------
-- Records of tbuserrolerelation
-- ----------------------------
INSERT INTO `tbuserrolerelation` VALUES ('1', '1', '1');
