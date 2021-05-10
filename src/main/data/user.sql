DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

INSERT INTO user (id, name, age, email) VALUES
(1, 'Guo ', 18, 'trek@erbadagang.com'),
(2, 'xiu', 20, 'specialized@erbadagang.com'),
(3, 'zhi', 28, 'giant@erbadagang.com'),
(4, 'Oliver', 21, 'xds@erbadagang.com'),
(5, 'Messi', 24, 'look@erbadagang.com');