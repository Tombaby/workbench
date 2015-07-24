package com.gcsw.workbench.console.frontend.mvc;

import com.gcsw.workbench.console.backend.service.ServiceException;
import com.gcsw.workbench.console.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public String getUsers(@RequestParam("id") String id, Model model) {
        try {
            userService.fetchAllDepartments();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (id == null)
            model.addAttribute("user", "All Users");
        else
            model.addAttribute("user", "One User");
        return "welcome";
    }

}
