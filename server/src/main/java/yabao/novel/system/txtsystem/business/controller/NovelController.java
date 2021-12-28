package yabao.novel.system.txtsystem.business.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yabao.novel.system.txtsystem.business.entity.Novel;
import yabao.novel.system.txtsystem.business.service.NovelService;
import yabao.novel.system.txtsystem.common.ApplicationContextUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/novel")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @GetMapping("getBean")
    public Object list1() {
        NovelService bean = ApplicationContextUtil.getBean(NovelService.class);
        return bean.listData();
    }


    @GetMapping("list")
    public Object list() throws ExecutionException, InterruptedException {
        Map<String,Object> kafkaConfig = new HashMap<>();
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(kafkaConfig);

        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("Topic1","key","value");
        Future res = kafkaProducer.send(producerRecord, (metadata, exception) -> {
            System.out.println("res");
        });
        res.get();


        ExecutorService tp = Executors.newFixedThreadPool(5);
        Runnable runnable = () -> {
           System.out.println(1);
        };
        Future<?> submit = tp.submit(runnable);
        submit.isDone();
        ReentrantLock lock = new ReentrantLock();
        List<Novel> list = novelService.listData();
        return "12345";
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------------无所状态是  0 0 1");
        Object a = new Object();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        new Thread(()-> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());

                System.out.println("---------------第一个线程，所以是偏向锁  1 0 1，并且指向某个线程");
            }
        }).start();
        Thread.sleep(200);
        synchronized (a) {
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
            System.out.println("---------------第二个线程，睡了一小会，再去竞争，所以是自旋锁 00 ");
        }

        Thread.sleep(200);

        new Thread(()-> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                System.out.println("---------------第三个线程，睡了一小会，再去竞争，所以是自旋锁 00 ");
            }
        }).start();
        Thread.sleep(200);

        Thread t1 = new Thread(()-> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                System.out.println("---------------第四-1个线程，并发争抢，重量级锁 10");
            }
        });
        Thread t2 = new Thread(()-> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
                System.out.println("---------------第四-2个线程，并发争抢，重量级锁 10");
            }
        });
        t1.start();
        t2.start();;
        t1.join();
        t2.join();
        System.out.println("两个线程并发争抢，重量级锁");

    }
}
