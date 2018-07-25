package com.fufu.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by zhoujunfu on 2018/7/25.
 */
public class ExchangerTest {

    static class Producer implements Runnable {

        private List<String> buffer;

        private Exchanger<List<String>> exchanger;

        public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {

            for (int i = 1; i < 3; i++) {
                System.out.println("生产者第" + i + "次生产.");
                for (int j = 0; j < 5; j++) {
                    System.out.println("生产, " + i + "---" + j);
                    buffer.add("i + \"---\" + j");
                }

                try {
                    System.out.println("生产者, 等待交付");
                    exchanger.exchange(buffer);
                    System.out.println("生产者, 交付完毕");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private List<String> buffer;

        private Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 1; i < 3; i++) {
                //调用exchange()与消费者进行数据交换
                try {
                    System.out.println("消费者等待取数据");
                    buffer = exchanger.exchange(buffer);
                    System.out.println("消费者取数据完毕");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("消费者第" + i + "次提取");
                for (int j = 1; j <= 5 ; j++) {
                    System.out.println("消费者 : " + buffer.get(0));
                    buffer.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> buffer = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();


        Exchanger<List<String>> exchanger = new Exchanger<>();
        Producer producer = new Producer(buffer, exchanger);
        Thread threadp =  new Thread(producer);
        threadp.start();

        Consumer consumer = new Consumer(buffer2, exchanger);
        Thread threadc = new Thread(consumer);
        threadc.start();


        threadp.join();
        threadc.join();
    }
}
