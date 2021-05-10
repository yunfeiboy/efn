package com.nowbio.database.efn.modules.user.service.impl;

import com.nowbio.database.efn.modules.user.model.User;
import com.nowbio.database.efn.modules.user.mapper.UserMapper;
import com.nowbio.database.efn.modules.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nowbio
 * @since 2021-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
