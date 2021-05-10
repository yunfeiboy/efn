package com.nowbio.database.efn.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.nowbio.database.efn.modules.ums.model.UmsResource;
import com.nowbio.database.efn.modules.ums.mapper.UmsResourceMapper;
import com.nowbio.database.efn.modules.ums.service.UmsAdminCacheService;
import com.nowbio.database.efn.modules.ums.service.UmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author nowbio
 * @since 2021-05-06
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return resourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
//        int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
        int count = resourceMapper.updateById(umsResource);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteById(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public IPage<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<UmsResource> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper();
        if(categoryId!=null){
            queryWrapper.eq("category_id",categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            queryWrapper.like("name",nameKeyword);
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            queryWrapper.like("url",urlKeyword);
        }
        return  resourceMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectList(new QueryWrapper<UmsResource>() );
    }
}
