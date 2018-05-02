# 重新构建一个容器

port=8890 # 暴露本机端口
docker rm -f web_graduate\
    & ./reconfig.sh docker && gradle clean \
	&& gradle bootImage \
    && gradle --stop \
	&& ./reconfig.sh dev \
    && docker run --name web_graduate --link postgre:db -p $port:8889 -it graduate:release
