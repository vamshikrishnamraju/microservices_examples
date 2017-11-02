Docker setup:

1. set the memory for Docker instance
 Select Oracle VM Virtual Box -> System -> 4GB
Note : Stop the running instance and set it. To stop the instance select-> Close-> PowerOFF

2. docker run -d -it --name es -p 9200:9200 -p 9300:9300 elasticsearch

3. docker run -d -it --name kibana --link es:elasticsearch -p 5601:5601 kibana

4. docker run -d -it --name logstash -p 5000:5000 logstash -e 'input { tcp { port => 5000 codec => "json" } } 
    output { elasticsearch { hosts => ["192.168.99.100"] index => "micro-%{serviceName}"} }'

5. Select Oracle VM -> settings > Network > NAT adapter > Port forwarding


Name: Demo
Protocol: TCP
Host IP : 0.0.0.0
Gost Port : 5601
Guest IP: 
Guest Port:

6. Access the application
http://localhost:8765/api/customer/customers

7. Kibana is available under address http://localhost:5601
Select Management -> Index patterns-> micro-*
Select Discover

8. To access Zipkin, point to the below url
http://localhost:9411/





