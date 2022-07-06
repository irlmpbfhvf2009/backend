package com.lwdevelop.backend.rabbitmq;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendUtil {
    private static SendUtil sendUtil;
    private RabbitmqConfig rabbitmqConfig;

    public SendUtil (RabbitmqConfig rabbitmqConfig){
        this.rabbitmqConfig=rabbitmqConfig;
    }

    @PostConstruct
    public void init(){
        sendUtil=this;
    }
    
    public static boolean pushMsg(String exchange,BuiltinExchangeType type,String routerKey, Object object ) throws InterruptedException{
        Connection connection = sendUtil.rabbitmqConfig.getConn();
        Channel channel=null;
        try{
            assert connection !=null;
            channel = connection.createChannel(true);
            channel.exchangeDeclare(exchange,type,true);
            channel.basicPublish(exchange,routerKey,null,object.toString().getBytes());
            return true;
        }catch(Exception e){
            log.info("SendUtil ==> pushMsg.. {}",e);
            e.printStackTrace();
        }finally{
            if(channel!=null){
                try{
                    channel.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
