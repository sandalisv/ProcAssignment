-- Create "USER" table
CREATE TABLE "USERS" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255)
);

-- Create "ITEM" table
CREATE TABLE "ITEM" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    userid BIGINT,
    FOREIGN KEY (userid) REFERENCES "USERS"(id)
);

-- Insert sample users
INSERT INTO "USERS" (username) VALUES ('a');
INSERT INTO "USERS" (username) VALUES ('b');

-- Insert sample items associated with users
INSERT INTO "ITEM" (name, price, userid) VALUES ('Product A', 20.0, 1);
INSERT INTO "ITEM" (name, price, userid) VALUES ('Product B', 30.0, 1);
INSERT INTO "ITEM" (name, price, userid) VALUES ('Product C', 25.0, 2);

-- Create "TASKS" table
CREATE TABLE TASKS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(1000),
    status VARCHAR(50)
);

-- Create "COMMENT" table
CREATE TABLE COMMENTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tasks_id BIGINT,
    author VARCHAR(255),
    createDate DATE,
    content VARCHAR(1000),
    FOREIGN KEY (tasks_id) REFERENCES TASKS(id)
);

-- Insert sample data into TASKS table
INSERT INTO TASKS (id, title, description, status) VALUES
(1, 'Task 1', 'Description for Task 1', 'COMPLETED'),
(2, 'Task 2', 'Description for Task 2', 'IN_PROGRESS');

-- Insert sample data into COMMENT table
INSERT INTO COMMENTS (id, tasks_id, author, createDate, content) VALUES
(1, 1, 'Author 1', '2024-01-29', 'Comment for Task 1'),
(2, 1, 'Author 2', '2024-01-30', 'Another comment for Task 1'),
(3, 2, 'Author 1', '2024-02-01', 'Comment for Task 2');


