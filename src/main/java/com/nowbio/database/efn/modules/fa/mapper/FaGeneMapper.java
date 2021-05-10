package com.nowbio.database.efn.modules.fa.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.modules.fa.model.FaGene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nowbio.database.efn.modules.fa.model.tda.GeneGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nowbio
 * @since 2021-04-21
 */
public interface FaGeneMapper extends BaseMapper<FaGene> {

    IPage<GeneGroup> GroupByKeyWord(Page<GeneGroup> page, String keyWork);

    // 分页查询
    @Select("<script>" +
            " select `chr` as name,  `chr` as value" +
            " from fa_gene " +
            " group by`chr`" +
            " </script>")
    IPage<GeneGroup> selectChrPage(IPage<GeneGroup> page);
}
