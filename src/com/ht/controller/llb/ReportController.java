package com.ht.controller.llb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/report")
public class ReportController {

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "llb/report/home";
    }
}
