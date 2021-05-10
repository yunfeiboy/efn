package com.nowbio.database.efn.modules.fa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.modules.fa.model.FaGene;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nowbio.database.efn.modules.fa.model.tda.GeneGroup;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yun
 * @since 2021/5/10
 */
public interface FaGeneService extends IService<FaGene> {

//    List<GeneGroup>
    IPage<GeneGroup> GroupByKeyWord(Page<GeneGroup> page, String keyWork);

    IPage<GeneGroup> selectChrPage(IPage<GeneGroup> page);
}
