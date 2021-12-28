package yabao.novel.system.txtsystem.business.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import yabao.novel.system.txtsystem.business.entity.Novel;
import yabao.novel.system.txtsystem.business.mapper.NovelMapper;

import java.util.List;

@Service
public class NovelService extends ServiceImpl<NovelMapper, Novel> {



    public List<Novel> listData() {
//        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>();
//        kafkaConsumer.poll(1000);
        List<Novel> list = list();
        System.out.println(list);
        return list;
    }

}
