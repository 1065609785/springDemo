
#设置读写权限
chmod -R 775 ./


#查看防火墙状态
service iptables status
#关闭防火墙，即时生效，重启后复原
service iptables stop
#永久关闭
chkconfig iptables off

#解压tar
tar -zxvf xxx.tar.gz

#压缩  demo：将test文件夹压缩成test.tar.gz
tar -zcvf test.tar.gz test

#卸载linux自带的jdk
rpm -qa|grep jdk #查看自带的信息
rpm -e --nodeps jdk-1.6.0_16-fcs #开始卸载命令

#安装jdk1.7
#下载jdk包
wget http://download.oracle.com/otn/java/jdk/7u80-b15/jdk-7u80-linux-x64.tar.gz 
#解压
tar -zxvf jdk-7u80-linux-x64.tar.gz 
#配置jdk
vim /etc/profile
#在最后追加下面一段话
JAVA_HOME=/usr/java/jdk1.7.0_80
TOMCAT_HOME=/home/syx/server/tomcat
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export JAVA_HOME
export TOMCAT_HOME
export PATH
export CLASSPATH

#设置立即生效
source /etc/profile

#安装mysql
wget  http://dev.mysql.com/get/Downloads/MySQL-5.6/mysql-5.6.33-linux-glibc2.5-x86_64.tar.gz
tar -zxvf mysql-5.6.33-linux-glibc2.5-x86_64.tar.gz
#mysql安装参照这个http://www.jb51.net/article/104107.htm


#安装tomcat
wget http://mirrors.shu.edu.cn/apache/tomcat/tomcat-7/v7.0.84/bin/apache-tomcat-7.0.84.tar.gz
tar -zxvf apache-tomcat-7.0.84.tar.gz

#安装nginx
wget http://nginx.org/download/nginx-1.8.1.tar.gz
yum install -y pcre pcre-devel #安装nginx需要该库
yum install -y zlib zlib-devel  #安装nginx需要该库
tar -zxvf nginx-1.8.1.tar.gz
./nginx-1.8.1/configure
make
make install
#启动、停止nginx
cd /usr/local/nginx/sbin/
./nginx 
./nginx -s stop
./nginx -s quit
./nginx -s reload


#安装redis
wget http://download.redis.io/releases/redis-3.2.11.tar.gz
tar xzf redis-3.2.11.tar.gz
cd redis-3.2.11
make
./redis-server &
#测试redis
src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"

#安装zookeeper
wget http://mirrors.shu.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
tar -zxvf zookeeper-3.4.10.tar.gz
cp zookeeper-3.4.10/conf/zoo_sample.cfg  ./zookeeper-3.4.10/conf/zoo.cfg
vim /etc/profile
#添加一下
export ZOOKEEPER_HOME=/usr/zookeeper/zookeeper-3.4.10/
export PATH=$ZOOKEEPER_HOME/bin:$PATH
export PATH
#立即生效
  source /etc/profile
#启动
 zkServer.sh start

#安装zkui客户端
git clone https://github.com/DeemOpen/zkui.git 
tar -zxvf xxx
mvn clean install
nohup java -jar target/zkui-2.0-SNAPSHOT-jar-with-dependencies.jar &
 

#安装maven
wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
yum -y install apache-maven

#安装git
yum install git
#克隆github项目
 git clone https://github.com/knightliao/disconf.git