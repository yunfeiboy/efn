package com.nowbio.database.efn.modules.fa.service.impl;

import com.nowbio.database.efn.modules.fa.model.FaVariants;
import com.nowbio.database.efn.modules.fa.mapper.FaVariantsMapper;
import com.nowbio.database.efn.modules.fa.service.FaVariantsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class FaVariantsServiceImpl extends ServiceImpl<FaVariantsMapper, FaVariants> implements FaVariantsService {



    @Override
    public Integer mycount() {
        return baseMapper.mycount();
    }
}
