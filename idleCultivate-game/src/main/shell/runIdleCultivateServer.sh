#!/bin/sh
# -Djava.library.path=/opt/phoenix/lib64
JAVA_HOME=/usr/java/jdk1.8.0_131
ICJAVAHOME=/usr/local/idlecultivate

echo RUNNING Idle Cultivate Server 
# exec ${JAVA_HOME}/bin/java -Dprocess.name=IdleCultivateServer -classpath "${ICJAVAHOME}/lib/*" -Xmx512M -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=7999 com.idleCultivate.game.server.GameServer "$@" &> /dev/null &
java -Dprocess.name=IdleCultivateServer -Xmx512M -Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=7999 -jar idleCultivate-game-1.0-SNAPSHOT.jar "$@" &> /dev/null &
