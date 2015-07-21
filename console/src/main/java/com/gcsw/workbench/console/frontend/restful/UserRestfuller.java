package com.gcsw.workbench.console.frontend.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/7/17.
 */
@Controller
public class UserRestfuller {

    @RequestMapping("/rest/users")
    @ResponseBody
    public String getUsers() {
        return "";
    }
}
