package com.du.userservice.controller;

import com.du.userservice.entity.TbUser;
import com.du.userservice.service.TbUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(TbUser)表控制层
 *
 * @author makejava
 * @since 2020-07-07 19:19:13
 */
@RestController
@RequestMapping("/tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TbUser selectOne(Long id) {
        return this.tbUserService.queryById(id);
    }


    /**
     * 通过用户名查询密码
     *
     * @param username 主键
     * @return 单条数据
     */
    @GetMapping("/selectByUsername")
    public TbUser selectByUsername(String username) {
        return this.tbUserService.getPassword(username);
    }
}