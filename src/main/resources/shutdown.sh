#!/bin/bash

#cd `dirname $0`/../target
#target_dir=`pwd`

pid=`ps ax | grep -i 'pixivapi.jar' | grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
        echo "No pixivapi running."
        exit 0;
fi

echo "The pixivapi(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to pixivapi(${pid}) OK"