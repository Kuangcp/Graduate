# 重新构建一个容器

docker rm -f web_graduate\
    & gradle clean && gradle bootImage \
    && gradle --stop \
    && docker run --name web_graduate --link postgre:db -p 8889:8889 -it graduate:release