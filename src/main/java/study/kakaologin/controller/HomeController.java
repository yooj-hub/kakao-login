package study.kakaologin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.kakaologin.domain.auth.dto.Login;
import study.kakaologin.domain.auth.dto.SessionUser;

@Controller
public class HomeController {
    @GetMapping("")
    public String home(@Login SessionUser sessionUser, Model model){
        if(sessionUser!=null){
            model.addAttribute("user",sessionUser);
        }

        return "/index";
    }
}
