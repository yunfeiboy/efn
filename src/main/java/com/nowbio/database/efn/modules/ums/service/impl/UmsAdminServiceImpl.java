package com.nowbio.database.efn.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.security.utils.JwtTokenUtil;
import com.nowbio.database.efn.common.utils.RequestUtil;
import com.nowbio.database.efn.modules.ums.dao.UmsAdminRoleRelationDao;
import com.nowbio.database.efn.modules.ums.dto.AdminRegisterParam;
import com.nowbio.database.efn.modules.ums.dto.UpdateAdminPasswordParam;
import com.nowbio.database.efn.modules.ums.mapper.UmsAdminLoginLogMapper;
import com.nowbio.database.efn.modules.ums.mapper.UmsAdminRoleRelationMapper;
import com.nowbio.database.efn.modules.ums.model.*;
import com.nowbio.database.efn.modules.ums.mapper.UmsAdminMapper;
import com.nowbio.database.efn.modules.ums.service.UmsAdminCacheService;
import com.nowbio.database.efn.modules.ums.service.UmsAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author nowbio
 * @since 2021-05-06
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if(admin!=null) return  admin;
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdmin::getUsername,username);
        UmsAdmin user = baseMapper.selectOne(queryWrapper);
        if(user != null){
            adminCacheService.setAdmin(user);
            return user;
        }
        return null;
    }


    @Override
    public String login(String username, String password) {
        String token = null;
        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UmsAdmin register(AdminRegisterParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);

        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(UmsAdmin::getUsername,umsAdminParam.getUsername());
        List<UmsAdmin> umsAdminList = baseMapper.selectList(queryWrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //查询是否有相同用户名的用户
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
//        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
//        if (umsAdminList.size() > 0) {
//            return null;
//        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        baseMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if(StrUtil.isEmpty(param.getUsername())
                ||StrUtil.isEmpty(param.getOldPassword())
                ||StrUtil.isEmpty(param.getNewPassword())){
            return -1;
        }
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",param.getUsername());
        List<UmsAdmin> adminList = baseMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
        if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        baseMapper.updateById(umsAdmin);
        adminCacheService.delAdmin(umsAdmin.getId());
        return 1;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public IPage<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {

        Page<UmsAdmin> page = new Page<>(pageNum, pageSize);

        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("username",keyword).or().like("nick_name",keyword);
        }
        return baseMapper.selectPage(page,queryWrapper);


//        PageHelper.startPage(pageNum, pageSize);
//        UmsAdminExample example = new UmsAdminExample();
//        UmsAdminExample.Criteria criteria = example.createCriteria();
//        if (!StringUtils.isEmpty(keyword)) {
//            criteria.andUsernameLike("%" + keyword + "%");
//            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
//        }
//        return adminMapper.selectByExample(example);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin record = new UmsAdmin();
        record.setLoginTime(new Date());
        UpdateWrapper<UmsAdmin> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("username",username);
        adminMapper.update(record,updateWrapper);

//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectById(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int count = adminMapper.updateById(admin);
        adminCacheService.delAdmin(id);
        return count;
    }

    @Override
    public int delete(Long id) {
        adminCacheService.delAdmin(id);
        int count = adminMapper.deleteById(id);
        adminCacheService.delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();


        //先删除原来的关系
//        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
//        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
//        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
//
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_id",adminId);
        adminRoleRelationMapper.delete(queryWrapper);

        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }


}
