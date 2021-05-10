package com.nowbio.database.efn.modules.fa.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.modules.fa.model.FaGene;
import com.nowbio.database.efn.modules.fa.mapper.FaGeneMapper;
import com.nowbio.database.efn.modules.fa.model.tda.GeneGroup;
import com.nowbio.database.efn.modules.fa.service.FaGeneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yun
 * @since 2021/5/10
 */
@Service
public class FaGeneServiceImpl extends ServiceImpl<FaGeneMapper, FaGene> implements FaGeneService {

//    @Override
//    public List<GeneGroup> GroupByKeyWord(String keyWork) {
//        return baseMapper.GroupByKeyWord("`"+keyWork+"`");
//    }

//    @Override
//    public List<GeneGroup> GroupByKeyWord(Page<GeneGroup> page, String keyWork) {
////        PageHelper.startPage(pageNum, pageSize);
//        return baseMapper.GroupByKeyWord(page,"`"+keyWork+"`");
////        return null;
//    }

    @Override
    public IPage<GeneGroup> GroupByKeyWord(Page<GeneGroup> page, String keyWork) {
        return (IPage<GeneGroup>) baseMapper.GroupByKeyWord(page,keyWork);
//        return null;
    }

    @Override
    public IPage<GeneGroup> selectChrPage(IPage<GeneGroup> page) {
        return (IPage<GeneGroup>) baseMapper.selectChrPage(page);
    }
}
