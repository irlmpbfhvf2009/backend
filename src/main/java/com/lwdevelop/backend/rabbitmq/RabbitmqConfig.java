package com.lwdevelop.backend.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

  private String url="moose.rmq.cloudamqp.com";
  private String username="vexfecmf";
  private String password="5kn7ylP6hqOX0aydtWJXvx-_a6KNox1_";
  public static final String PRESS_EXCHANGE="press_exchange";
  @Bean
  Queue directQueue(){
    return new Queue(PRESS_EXCHANGE, true);
  }

  public Connection getConn() throws InterruptedException {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(url);
    Connection connection=null;
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setVirtualHost(username);
    connection=connectionFactory.createConnection();
    return connection;
  }
}
