package com.qts.storm.spout;

import java.util.Properties;

import com.qts.storm.Keys;

import backtype.storm.spout.SchemeAsMultiScheme;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

/**
 * @author vishnu viswanath
 *
 */
public class SpoutBuilder {
	
	public Properties configs = null;
	
	public SpoutBuilder(Properties configs) {
		this.configs = configs;
	}
	public KafkaSpout buildKafkaSpout() {
		BrokerHosts hosts = new ZkHosts(configs.getProperty(Keys.KAFKA_ZOOKEEPER));
		String topic = configs.getProperty(Keys.KAFKA_TOPIC);
		String zkRoot = configs.getProperty(Keys.KAFKA_ZKROOT);
		String groupId = configs.getProperty(Keys.KAFKA_CONSUMERGROUP);
		SpoutConfig spoutConfig = new SpoutConfig(hosts, topic, zkRoot, groupId);
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		return kafkaSpout;
	}
}
