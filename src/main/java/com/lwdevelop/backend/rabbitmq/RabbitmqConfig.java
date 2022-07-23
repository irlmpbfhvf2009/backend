package com.lwdevelop.backend.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {
  
  private static final String url="amqps://vexfecmf:5kn7ylP6hqOX0aydtWJXvx-_a6KNox1_@moose.rmq.cloudamqp.com/vexfecmf";

  private static final String username="vexfecmf";
  private static final String password="5kn7ylP6hqOX0aydtWJXvx-_a6KNox1_";
  private static final String virtualHost="vexfecmf";
  private static final String Hosts="moose.rmq.cloudamqp.com";
  private static final String PRESS_EXCHANGE="press_exchange";


  public static Connection getConnection(){
    CachingConnectionFactory connectionFactory=new CachingConnectionFactory(Hosts);
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setVirtualHost(virtualHost);
    connectionFactory.setRequestedHeartBeat(30);
    connectionFactory.setConnectionTimeout(30000);
    Connection connection = connectionFactory.createConnection();
    return connection;
  }
}
