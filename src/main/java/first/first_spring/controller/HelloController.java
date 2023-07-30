package first.first_spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public  String hello(Model model){
        model.addAttribute("data","hello!!");
        // resources밑의 templates의 hello.html로 이동 model(data:hello!!)
        return "hello";

    }
}
