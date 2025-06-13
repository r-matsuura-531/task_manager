USE taskmanager;

SET SESSION sql_mode = '';

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET collation_connection = 'utf8mb4_0900_ai_ci';

ALTER DATABASE taskmanager CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS task_category;

CREATE TABLE IF NOT EXISTS `task` (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description TEXT,
    due_date DATE,
    status INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `category` (
    id VARCHAR(36) PRIMARY KEY COMMENT 'ID',
    name VARCHAR(30) NOT NULL COMMENT 'カテゴリ名'
);

CREATE TABLE IF NOT EXISTS `task_category` (
    id VARCHAR(36) PRIMARY KEY COMMENT 'ID',
    task_id VARCHAR(36) NOT NULL COMMENT 'タスクID',
    category_id VARCHAR(36) NOT NULL COMMENT 'カテゴリID',
    FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE,
    UNIQUE (task_id, category_id)
);

CREATE TABLE IF NOT EXISTS `menu` (
    id VARCHAR(36) PRIMARY KEY COMMENT 'ID',
    name VARCHAR(255) NOT NULL COMMENT '名前',
    url VARCHAR(255) NOT NULL COMMENT 'URL',
    order_by INT NOT NULL
);
