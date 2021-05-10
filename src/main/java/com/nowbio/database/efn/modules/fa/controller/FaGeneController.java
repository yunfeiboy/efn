package com.nowbio.database.efn.modules.fa.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.common.api.CommonPage;
import com.nowbio.database.efn.common.api.CommonResult;
import com.nowbio.database.efn.modules.fa.model.dto.GeneQueryParam;
import com.nowbio.database.efn.modules.fa.model.tda.GeneGroup;
import com.nowbio.database.efn.modules.fa.service.FaGeneService;
import com.nowbio.database.efn.modules.fa.model.FaGene;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nowbio
 * @since 2021-04-21
 */
@RestController
@Api(tags = "FaGeneController", description = "基因")
@RequestMapping("/fa/faGene")
public class FaGeneController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FaGeneController.class);

    @Autowired
    private FaGeneService geneService;

    @ApiOperation("分页查询基因列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<FaGene>> listBrand(
            GeneQueryParam geneQueryParam,
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize) {
        QueryWrapper<FaGene> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("chr");
        if( geneQueryParam.getChr() != null){
            queryWrapper.eq("chr",geneQueryParam.getChr());
        }
        if( geneQueryParam.getStart() != null){
            queryWrapper.ge("start",geneQueryParam.getStart());
        }
        if( geneQueryParam.getEnd() != null){
            queryWrapper.le("end",geneQueryParam.getEnd());
        }
        Page<FaGene> page = new Page<>(pageNum, pageSize);
        Page<FaGene> pageResult = geneService.page(page,queryWrapper);
        return CommonResult.success(CommonPage.restPage(pageResult));
    }

    @ApiOperation("分页 基因 chr 分组 列表")
    @RequestMapping(value = "/groupByList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IPage<GeneGroup>> groupBy(
            @RequestParam(value = "keyWork",defaultValue = "chr") @ApiParam("排序方式") String keyWork,
            @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam("页码") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "3") @ApiParam("每页数量") Integer pageSize){
//        QueryWrapper<FaGene> queryWrapper = new QueryWrapper();
//        queryWrapper.select(keyword +" , id");
//        queryWrapper.getSqlSet();
//        queryWrapper.groupBy(keyword);
          Page<GeneGroup> page = new Page<>(pageNum, pageSize);
//        Page<FaGene> pageResult = geneServicse.page(page,queryWrapper);
//        geneService.GroupByKeyWord()
        return CommonResult.success(geneService.GroupByKeyWord(page,"`"+keyWork+"`"));
    }
}

