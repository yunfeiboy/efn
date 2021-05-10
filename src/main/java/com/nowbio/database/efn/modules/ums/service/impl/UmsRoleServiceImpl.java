package com.nowbio.database.efn.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.modules.ums.dao.UmsRoleDao;
import com.nowbio.database.efn.modules.ums.mapper.UmsRoleMenuRelationMapper;
import com.nowbio.database.efn.modules.ums.mapper.UmsRoleResourceRelationMapper;
import com.nowbio.database.efn.modules.ums.model.*;
import com.nowbio.database.efn.modules.ums.mapper.UmsRoleMapper;
import com.nowbio.database.efn.modules.ums.service.UmsAdminCacheService;
import com.nowbio.database.efn.modules.ums.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author nowbio
 * @since 2021-05-06
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private UmsRoleDao roleDao;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateById(role);
    }

    @Override
    public int delete(List<Long> ids) {

        int count = roleMapper.deleteBatchIds(ids);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;

//        UmsRoleExample example = new UmsRoleExample();
//        example.createCriteria().andIdIn(ids);
//        int count = roleMapper.deleteByExample(example);
//        adminCacheService.delResourceListByRoleIds(ids);
//        return count;
    }

    @Override
    public List<UmsRole> list() {
        QueryWrapper<UmsRole> queryWrapper = new QueryWrapper();
        return  roleMapper.selectList(queryWrapper);
//        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public IPage<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        QueryWrapper<UmsRole> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name",keyword);
        }
        Page<UmsRole> page = new Page<>(pageNum, pageSize);
        return roleMapper.selectPage(page,queryWrapper);

//        PageHelper.startPage(pageNum, pageSize);
//        UmsRoleExample example = new UmsRoleExample();
//        if (!StringUtils.isEmpty(keyword)) {
//            example.createCriteria().andNameLike("%" + keyword + "%");
//        }
//        return roleMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {


        //先删除原有关系
//        UmsRoleMenuRelationExample example=new UmsRoleMenuRelationExample();
//        example.createCriteria().andRoleIdEqualTo(roleId);
//        roleMenuRelationMapper.deleteByExample(example);

        QueryWrapper<UmsRoleMenuRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        roleMenuRelationMapper.delete(queryWrapper);

        //批量插入新关系
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
//        UmsRoleResourceRelationExample example=new UmsRoleResourceRelationExample();
//        example.createCriteria().andRoleIdEqualTo(roleId);
//        roleResourceRelationMapper.deleteByExample(example);

        //先删除原有关系
        QueryWrapper<UmsRoleResourceRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        roleResourceRelationMapper.delete(queryWrapper);

        //批量插入新关系
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
