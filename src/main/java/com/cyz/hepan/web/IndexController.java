package com.cyz.hepan.web;

import com.cyz.hepan.pojo.Product;
import com.cyz.hepan.service.ProductService;
import com.cyz.hepan.util.TestMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static com.cyz.hepan.util.Util.work;

/*
create table secondhand(
        id int primary key auto_increment,
        pid int
        )engine=innodb default charset=utf8mb4
*/

@Controller
public class IndexController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String Index(){
        TestMail t;
        while (true){
            try {
                List<Map> list = work();
                if (list!= null){
                    for (Map map1:list){
                        int pid = (int) map1.get("topic_id");
                        String title = (String) map1.get("title");
                        String content = (String) map1.get("subject")+
                                "链接地址：http://bbs.uestc.edu.cn/forum.php?mod=viewthread&tid="+pid
                                ;
                        if (productService.get(pid)){
                            continue;
                        }else {
                            productService.add(new Product(pid));
                            t= new TestMail("uestc@cyz.ink","123",
                                    "421248827@qq.com",title,content);
                            t.send();
//                            t= new TestMail("test@cyz.ink","123",
//                                    "511637310@qq.com",title,content);
//                            t.send();
//                            t= new TestMail("test@cyz.ink","123",
//                                    "2300199031@qq.com",title,content);
//                            t.send();
                        }
                    }
                }
                Thread.currentThread().sleep(1000*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

}
