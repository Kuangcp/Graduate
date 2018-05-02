env=$1
configFile=src/main/resources/application.yml
sed -i "s/active:.*/active: "$1"/g" $configFile
