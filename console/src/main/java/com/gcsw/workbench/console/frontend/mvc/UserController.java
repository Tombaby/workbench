package com.gcsw.workbench.console.frontend.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/7/17.
 */
@Controller
public class UserController {

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public String getUsers(@RequestParam("id") String id, Model model) {
        if (id == null)
            model.addAttribute("user", "All Users");
        else
            model.addAttribute("user", "One User");
        return "welcome";
    }

}
