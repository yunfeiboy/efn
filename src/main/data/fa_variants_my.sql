-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- 主机： mysql
-- 生成日期： 2021-04-22 03:57:53
-- 服务器版本： 5.7.33
-- PHP 版本： 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- 数据库： `mall`
--

-- --------------------------------------------------------

--
-- 表的结构 `fa_variants`
--

CREATE TABLE `fa_variants` (
                             `id` int(10) UNSIGNED NOT NULL,
                             `type` varchar(64) NOT NULL,
                             `chr` int(11) NOT NULL,
                             `position` int(11) NOT NULL,
                             `ref` text NOT NULL,
                             `alt` text NOT NULL,
                             `info` text NOT NULL,
                             `gene_symbol` varchar(1000) DEFAULT NULL,
                             `interpro_number` varchar(1000) DEFAULT NULL,
                             `ko_number` varchar(1000) DEFAULT NULL,
                             `go_number` varchar(1000) DEFAULT NULL,
                             `vulturine_kenya` varchar(64) NOT NULL,
                             `wild_sudan` varchar(64) NOT NULL,
                             `wild_nigeria` varchar(64) NOT NULL,
                             `wild_kenya` varchar(64) NOT NULL,
                             `domestic_sudan` varchar(64) NOT NULL,
                             `domestic_nigeria` varchar(64) NOT NULL,
                             `domestic_kenya` varchar(64) NOT NULL,
                             `domestic_italy` varchar(64) NOT NULL,
                             `domestic_iran` varchar(64) NOT NULL,
                             `domestic_hungary` varchar(64) NOT NULL,
                             `domestic_china` varchar(64) NOT NULL

) ENGINE=Myisam DEFAULT CHARSET=utf8;

--
-- 转储表的索引
--

--
-- 表的索引 `fa_variants`
--
ALTER TABLE `fa_variants`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fa_variants_index_chr` (`chr`),
  ADD KEY `fa_variants_index_position` (`position`),
  ADD KEY `fa_variants_index_type` (`type`),
  ADD KEY `fa_variants_index_gene_symbol` (`gene_symbol`),
  ADD KEY `fa_variants_index_interpro_number` (`interpro_number`),
  ADD KEY `fa_variants_index_ko_number` (`ko_number`),
  ADD KEY `fa_variants_index_go_number` (`go_number`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `fa_variants`
--
ALTER TABLE `fa_variants`
    MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;
