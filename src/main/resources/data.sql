CREATE TABLE patient (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         age INT,
                         last_visit_date DATE
);

INSERT INTO patient (id, name, age, last_visit_date) VALUES
                                                         (1, 'Alice', 1, '2022-04-01'),
                                                         (2, 'Bob', 2, '2022-03-01'),
                                                         (3, 'Charlie', 2, '2022-05-15'),
                                                         (4, 'David', 1, '2022-01-23'),
                                                         (5, 'Ella', 2, '2022-02-20'),
                                                         (6, 'Fiona', 1, '2022-06-10'),
                                                         (7, 'George', 0, '2022-07-22'),
                                                         (8, 'Hannah', 2, '2022-08-05'),
                                                         (9, 'Ivy', 1, '2022-09-14'),
                                                         (10, 'Jack', 0, '2022-10-30'),
                                                         (11, 'Kylie', 1, '2022-12-01'),
                                                         (12, 'Leo', 2, '2021-12-15'),
                                                         (13, 'Mia', 1, '2021-11-23'),
                                                         (14, 'Noah', 0, '2021-10-05'),
                                                         (15, 'Olivia', 1, '2021-09-20'),
                                                         (16, 'Peyton', 2, '2021-08-25'),
                                                         (17, 'Quinn', 0, '2021-07-30'),
                                                         (18, 'Ruby', 2, '2021-06-19'),
                                                         (19, 'Sophia', 0, '2021-05-09'),
                                                         (20, 'Tyler', 1, '2021-04-03');
