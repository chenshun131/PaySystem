CREATE TABLE book(
       isbn VARCHAR(50) PRIMARY KEY,
       book_name VARCHAR(100) COMMENT '书名',
       price INT COMMENT '价格'
);

CREATE TABLE book_stock(
       isbn VARCHAR(50) PRIMARY KEY,
       stock INT COMMENT '书的存量',
       CHECK(stock > 0)
);

CREATE TABLE account(
       username VARCHAR(50) PRIMARY KEY,
       balance INT COMMENT '余额',
       CHECK(balance > 0)
);
