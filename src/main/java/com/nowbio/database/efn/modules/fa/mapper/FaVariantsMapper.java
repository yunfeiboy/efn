package com.nowbio.database.efn.modules.fa.mapper;

import com.nowbio.database.efn.modules.fa.model.FaVariants;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nowbio
 * @since 2021-04-21
 */
@Mapper
public interface FaVariantsMapper extends BaseMapper<FaVariants> {

    Integer mycount();
}
