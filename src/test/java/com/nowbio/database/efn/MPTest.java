package com.nowbio.database.efn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nowbio.database.efn.modules.fa.model.FaGene;
import com.nowbio.database.efn.modules.fa.model.tda.GeneGroup;
import com.nowbio.database.efn.modules.fa.service.FaGeneService;
import com.nowbio.database.efn.modules.user.mapper.UserMapper;
import com.nowbio.database.efn.modules.user.model.User;
import org.junit.Assert;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MPTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FaGeneService geneService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);

        System.out.println(("----- select via ids test ------"));
        userList = userMapper.selectBatchIds(Arrays.asList(1, 5));
        userList.forEach(System.out::println);
    }
    @Test
    public void testOrderBy(){
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("age");
//        queryWrapper.gt("salary",3500).like("name","小");
        List<User> userList = userMapper.selectList(queryWrapper);
//        System.out.println(userList);
        userList.forEach(System.out::println);

    }
    @Test
    public void gene(){
        IPage<FaGene> page = new Page<>(0, 5,false);
        QueryWrapper<FaGene> queryWrapper=new QueryWrapper();
        queryWrapper.select("chr");
        queryWrapper.groupBy("chr");
//        geneService.page()
        IPage<FaGene> gene = geneService.page(page,queryWrapper);
//        genelist.forEach(System.out::println);
        System.out.println(gene.getTotal());
//        System.out.println(gene.condition());
//         gene.getRecords().forEach(System.out::println);
//         List list = new List();
//        ArrayList list = new ArrayList();
//        Map<Object, Object> map = new HashMap<>();
//        list.forEach((key,value)->{
//
//        });
        List<FaGene> list = gene.getRecords();
        list.forEach(item-> {
            System.out.println(item.getChr());
        });
//        list.stream().filter();
//        steam
//        selectChrPage
//        geneService.sel

    }

    @Test
    public void geneChr(){
        IPage<GeneGroup> page = new Page<>(0, 5);
        IPage<GeneGroup> gene =geneService.selectChrPage(page);
        gene.getRecords().forEach(System.out::println);
        System.out.println(gene.hashCode());
        gene.getClass();

        String a = "45";
        String b = "45";
        String c = "4";
        String d = "5";
        String e = c + d;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(e.hashCode());
        System.out.println(a.equals(b));

        String f = new String("45");
        System.out.println(f.hashCode());

        Integer x = 45;
        Integer y = 5*9;
        Integer z = new Integer(45);
        Integer w = new Integer(45);
        System.out.println(x.hashCode());
        System.out.println(y.hashCode());
        System.out.println(z.hashCode());
        System.out.println(x==y);
        System.out.println(x==z);
        System.out.println(x.equals(z));

        System.out.println(w.hashCode());
        System.out.println(z==w);
        System.out.println(z.equals(w));

        Integer a1 = new Integer(12);
        Integer a2 = 12;
        System.out.println(a1==a2);

        int a3 =12;
        System.out.println(a3==a1);
        System.out.println(a3==a2);
        System.out.println("———————————————");

        // StringBuilder 线程不安全 但速度快
        final StringBuilder sb = new StringBuilder("验证");
        System.out.println("sb=" + sb);
        System.out.println("sb的哈希编码hashcode=" + sb.hashCode());

        // 为sb追加内容   验证变量所指向的对象的内容是否可变
        sb.append("成功");
        System.out.println("追加后的sb=" + sb);
        System.out.println("追加后的sb的哈希编码hashcode=" + sb.hashCode());

        Set set =new HashSet();
        set.add("123");
        set.add("123");
        set.add("456");
        System.out.println(set);

//
    }

}
