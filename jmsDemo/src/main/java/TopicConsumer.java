import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer {
    public static void main(String[] args) throws Exception {
        //1创建工厂
        ActiveMQConnectionFactory con = new ActiveMQConnectionFactory("tcp://192.168.200.128:61616");
        //2获取连接Mq
        Connection connection = con.createConnection();
        //3开启连接
        connection.start();

        //4创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//第一个是否开启分布式事务，第二个这么匹配信息
        //5创建信息的队列名
        Topic topic = session.createTopic("test_Topic");
        //6创建信息消费
        MessageConsumer consumer = session.createConsumer(topic);

        //7,监听消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("topic接收到信息"+textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
