
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


#安装maven
wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
yum -y install apache-maven

#安装git
yum install git
#克隆github项目
 git clone https://github.com/knightliao/disconf.git