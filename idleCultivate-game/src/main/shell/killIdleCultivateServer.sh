#!/bin/sh

IdleCultivateServerPID=$(ps -ef |grep java |grep IdleCultivateServer | awk '{print $2}')
if [ -z "$IdleCultivateServerPID" ]; then
    echo "Idle Cultivate Server is not running"
else
    echo "kill $IdleCultivateServerPID"
    kill -9 $IdleCultivateServerPID
fi

