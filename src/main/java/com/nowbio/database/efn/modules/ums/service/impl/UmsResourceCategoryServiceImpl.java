package com.nowbio.database.efn.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nowbio.database.efn.modules.ums.model.UmsResourceCategory;
import com.nowbio.database.efn.modules.ums.mapper.UmsResourceCategoryMapper;
import com.nowbio.database.efn.modules.ums.service.UmsResourceCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资源分类表 服务实现类
 * </p>
 *
 * @author nowbio
 * @since 2021-05-06
 */
@Service
public class UmsResourceCategoryServiceImpl extends ServiceImpl<UmsResourceCategoryMapper, UmsResourceCategory> implements UmsResourceCategoryService {
    @Override
    public List<UmsResourceCategory> listAll() {
        QueryWrapper<UmsResourceCategory> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("sort");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return baseMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return baseMapper.updateById(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return baseMapper.deleteById(id);
    }
}
