package com.chan.paljachance.crawler.libTestExamples.rabbit;


import com.rabbitmq.client.*;

import java.io.IOException;

public class Worker {
    private final static String QUEUE_NAME = "hello";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // amqp://guest@127.0.0.1:5672/ 요거로 하나 만들어짐, 새로 선언하면 동일한 걸로 다시 reset 됨 (새로 또 하나 더 생성하는 것이 아님)
        Connection connection = factory.newConnection();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + " tag: " + delivery + "'");
//            try {
//                doWork(message);
//            } catch (Exception e) {
//
//            } finally {
//                System.out.println(" [x] Done");
//            }
//        };
        boolean autoAck = true;

//        Channel channel = connection.createChannel();
//        channel.queueDeclare("hello", false, false, false, null);
//        channel.basicQos(1);

        for (int i = 0; i<5; i++){
            Channel channel = connection.createChannel();
            channel.queueDeclare("hello"+Integer.toString(i), false, false, false, null);
            channel.basicQos(1);
            String consumerTagIs = channel.basicConsume("hello", autoAck, new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery delivery) throws IOException {
                        String message = new String(delivery.getBody(), "UTF-8");
                        System.out.println(" [x] Received '" + message + " tag: " + consumerTag + "'");
                        try {
                            doWork(message);
                        } catch (Exception e) {

                        } finally {
                            System.out.println(" [x] Done");
                        }
                    }
                }, consumerTag -> { }
            );
            System.out.println(consumerTagIs);
            System.out.println(channel.consumerCount("hello"));
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(3000);
        }
    }
}
