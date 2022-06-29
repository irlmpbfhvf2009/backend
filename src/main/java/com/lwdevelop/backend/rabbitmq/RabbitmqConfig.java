/* package com.lwdevelop.backend.rabbitmq;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class RabbitmqConfig {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws Exception {
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory("moose.rmq.cloudamqp.com");
        connectionFactory.setUsername("vexfecmf");
        connectionFactory.setPassword("5kn7ylP6hqOX0aydtWJXvx-_a6KNox1_");
        connectionFactory.setVirtualHost("vexfecmf");
      
        //Recommended settings
        connectionFactory.setRequestedHeartBeat(60);
        connectionFactory.setConnectionTimeout(30000);
      
        //Set up queue, exchanges and bindings
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue(QUEUE_NAME);
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("myEExchange");
        admin.declareExchange(exchange);
        admin.declareBinding(
          BindingBuilder.bind(queue).to(exchange).with("foo.*"));
      
        //Set up the listener
        SimpleMessageListenerContainer container =
          new SimpleMessageListenerContainer(connectionFactory);
          
          Object listener = new Object();
      
        //Send a message
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames(QUEUE_NAME);
        container.start();
      
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myExchange", "foo.bar", "Hello CloudAMQP!");
        try{
          Thread.sleep(1000);
        } catch(InterruptedException e) {
           Thread.currentThread().interrupt();
        }
        container.stop();
    }
    public void handleMessage(String foo) {
        System.out.println(foo);
      }
}
 */