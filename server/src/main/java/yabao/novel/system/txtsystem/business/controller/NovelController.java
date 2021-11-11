package yabao.novel.system.txtsystem.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yabao.novel.system.txtsystem.business.entity.Novel;
import yabao.novel.system.txtsystem.business.service.NovelService;

import java.util.List;

@RestController
@RequestMapping("/novel")
public class NovelController {

    @Autowired
    private NovelService novelService;

    @GetMapping("list")
    public Object list() {
        List<Novel> list = novelService.listData();
        return "12345";
    }
}
