import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {
    public static void main(String[] args) throws Exception {
        //1创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.200.128:61616");
        //2.获取连接
        Connection connection = connectionFactory.createConnection();
        //3启动连接
        connection.start();

        //4获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建发布/订阅对象,实际就是给队列起名字
        Topic topic = session.createTopic("test_Topic");
        //6,创建消息生产者
        MessageProducer producer = session.createProducer(topic);
        //7创建消息
        TextMessage textMessage = session.createTextMessage("欢迎来到我的家乡大内蒙");
        //8,通过信息的生产者将信息发送到Mq上
        producer.send(textMessage);
         //9关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
