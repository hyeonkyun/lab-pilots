# datahub-pilot

## datahub-pilot project
 - kafka 연동
 - socket 통신
 - postgre sql + jpa
 

## kafka 설치 및 실행
#### Zookeeper 시작:
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```
#### Kafka Broker 시작:
새로운 터미널 창을 열고, 아래 명령어로 Kafka Broker를 시작합니다.
```bash
bin/kafka-server-start.sh config/server.properties
```

### 카프카 테스트
카프카 설치가 제대로 되었는지 확인하기 위해 토픽을 생성하고, 메시지를 주고받을 수 있습니다.
#### 토픽 생성:
```bash
bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```
#### 메시지 보내기:
```bash
bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```
위 명령어를 실행하고 메시지를 입력 후 Enter를 누르면 해당 메시지가 카프카에 전송됩니다.
#### 메시지 읽기:
다른 터미널에서 아래 명령어를 실행하여 위에서 전송한 메시지를 읽을 수 있습니다.
```bash
bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092
```
