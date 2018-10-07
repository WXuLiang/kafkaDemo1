# kafkaDemo1
配置kafka非常重要的一点是选择版本，没有版本的冲突。
1.kafka服务器的版本 如2.10-0.10.2.1
2.依赖jar包版本 		
    <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-stream-kafka</artifactId>
			<version>1.2.1.RELEASE</version>
		</dependency>
此包下依赖包
    <dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
			<version>0.10.1.1</version>
		</dependency>
主要就是这个版本要与kafka服务器的版本要基本对应，不然就很难成功。
