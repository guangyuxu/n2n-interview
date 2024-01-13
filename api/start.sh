#!/usr/bin/env /bin/bash
set -x

SPRING_PROFILE="${SPRING_PROFILE:-local}"
JVM_MEMORY="${JVM_MEMORY:--Xmx1024M -XX:MaxMetaspaceSize=1024M}"

JAVA_OPTS="$JAVA_OPTS \
-server \
-Dspring.profiles.active="${SPRING_PROFILE}" \
-Dfile.root.path=/n2n \
"${JVM_MEMORY}" \
-Dcom.sun.management.jmxremote=true \
-Dcom.sun.management.jmxremote.port=11619 \
-Dcom.sun.management.jmxremote.rmi.port=11619 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:G1HeapRegionSize=8m -XX:+ParallelRefProcEnabled -XX:-ResizePLAB \
-verbose:gc -Xlog:gc:/n2n/gc-log/gc.`date '+%Y%m%d%H%M'`.log -XX:+PrintClassHistogram \
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/n2n/gc-log/heapdump_`date '+%Y%m%d%H%M'`.hprof"

exec java ${JAVA_OPTS} -jar /n2n/app/app.jar > /n2n/app-log/app.log