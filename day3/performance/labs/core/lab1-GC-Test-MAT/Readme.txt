
GC Algorithms Benchmarking:


-Xms2g -Xmx2g -Xloggc:gc_parallel2g.log  -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps


-Xms4g -Xmx4g -Xloggc:gc_parallel4g.log  -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps



-Xms2g -Xmx2g -Xloggc:gc_cms2g.log  -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps

-Xms4g -Xmx4g -Xloggc:gc_cms4g.log  -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps


-Xms2g -Xmx2g -Xloggc:gc_g1gc2g.log  -XX:+UseG1GC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps

-Xms4g -Xmx4g -Xloggc:gc_g1gc4g.log  -XX:+UseG1GC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps



G1 GC With Pause time goal:

-XX:MaxGCPauseMillis=200 


