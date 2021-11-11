package yabao.novel.system.txtsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("yabao.novel.system.txtsystem")
public class TxtsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxtsystemApplication.class, args);
    }

}
