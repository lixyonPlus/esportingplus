## centos7/ubuntu安装docker
>最好使用最新的操作系统，dockers要求linux内核在3.0以上，那什么centos6.7之类老掉渣的东西直接pass

## 新建安装脚本``vim docker.sh``   ``chmod +x install.sh``

```shell
# bin/bash
rm -rf /usr/local/bin/docker-compose
sudo yum remove -y docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-selinux \
                  docker-engine-selinux \
                  docker-engine

sudo yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2

sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum install -y docker-ce.x86_64 0:18.03.1.ce-1.el7.centos

curl -L https://get.daocloud.io/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```
## docker 加速

* 由于国内网络原因，DOCKER访问docker hub经常超时，为了能够给DOCKER加速，我们可使用国内DOCKER镜像：
>vim /etc/docker/daemon.json
```json
{
"registry-mirrors": [ "https://registry.docker-cn.com"]
}
```

## 重启docker 

```
systemctl daemon-reload 
systemctl restart docker
```