cd "$(dirname "$0")"

cd ..

echo `pwd`

mkdir /opt/sh/logs

java -Xmx256M -Xms256M -XX:MaxDirectMemorySize=128M -Dlog4j.configurationFile=/opt/sh/conf/log4j2.xml -jar /opt/sh/vm-agent.jar >/opt/sh/logs/console.log 2>/opt/sh/logs/error.log
tail -f /dev/null