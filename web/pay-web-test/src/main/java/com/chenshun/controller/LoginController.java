package com.chenshun.controller;

import com.chenshun.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wusc.edu.pay.common.utils.rest.RestResultDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * User: mew <p />
 * Time: 17/7/12 08:35  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Api(tags = "银联支付")
@Controller
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login")
    @ResponseBody
    public RestResultDTO checkLogin(HttpServletRequest request) {
        logger.info("进入LoginController");

        RestResultDTO dto = new RestResultDTO();
        dto.setStatusCode("200");

        return dto;
    }

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/regist")
    @ResponseBody
    @ApiImplicitParam(name = "user", value = "用户注册信息信息", required = true, dataType = "User")
    public RestResultDTO addUser(User user) {
        logger.info("进入addUser");

        RestResultDTO dto = new RestResultDTO();
        dto.setStatusCode("200");
        dto.setBody(user);

        return dto;
    }

}
