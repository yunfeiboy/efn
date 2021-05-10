package com.nowbio.database.efn.modules.fa.service;

import com.nowbio.database.efn.modules.fa.model.FaVariants;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yun
 * @since 2021/5/10
 */
public interface FaVariantsService extends IService<FaVariants> {

    public Integer mycount();
}
