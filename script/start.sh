#!/bin/sh
# sh start.sh springbootapp.jar start/stop/restart 
app_name=$1
start(){
	echo "$app_name is starting..."
	java -jar ${app_name} &
}
stop(){
	pid=`ps -ef | grep java | grep "$app_name" | awk '{print $2}'`
	if [ -z $pid ] ;
	then
		echo "$app_name is not running"
	else
		echo "$app_name is stoping..."
		kill -9 $pid
	fi
}
case "$2" in
	start)
		start
		;;
	stop)
		stop
		;;
	restart)
		stop
		start
		;;
	*)
		printf 'Usage: %s {start|stop|restart}\n'
		exit 1
		;;
esac
exit 0