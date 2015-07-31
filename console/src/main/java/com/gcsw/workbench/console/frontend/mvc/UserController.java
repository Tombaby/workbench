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
    public String getUsers(String id, Model model) {
        try {
            model.addAttribute("result", userService.fetchAllDepartmentsAsJsonStr());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "welcome";
    }

}
