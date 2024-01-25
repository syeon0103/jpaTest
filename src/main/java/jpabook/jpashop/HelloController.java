package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { //model에 데이터 실어서 넘김
        model.addAttribute("data","hello!");
        return "hello"; //화면 이름
    }
}
