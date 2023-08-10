 CREATE DATABASE thithu character set utf8mb4 collate UTF8MB4_GENERAL_CI;

CREATE TABLE hocsinh(
hocsinhid INT AUTO_INCREMENT PRIMARY KEY,
lopid  INT NOT NULL,
hoten VARCHAR(300) NOT NULL,
ngaysinh DATETIME NOT NULL,
gioitinh INT ,
diachi VARCHAR(300) NOT NULL,
loptruong VARCHAR(300) NOT NULL,
bithuchidoan int,
ghichu VARCHAR(300) NULL
)
CREATE TABLE lop (
lopid INT AUTO_INCREMENT PRIMARY KEY,
tenlop VARCHAR(300) NOT NULL,
khoiid INT
)
CREATE TABLE khoi(
khoiid INT AUTO_INCREMENT PRIMARY KEY,
tenkhoi VARCHAR(300) NOT NULL
)
CREATE TABLE monhoc (
monhocid INT AUTO_INCREMENT PRIMARY KEY,
tenmon VARCHAR(300) NOT NULL,
hesomon int
)
CREATE TABLE diem(
hocsinhid INT NOT null,
namhocid INT NOT NULL,
monhocid INT NOT NULL,
loaidiem  VARCHAR(300) NOT NULL,
hockyid INT NOT NULL,
diem INT not null
)

-- khóa ngoại bảng hoc sinh 
ALTER TABLE hocsinh
ADD CONSTRAINT fk_lop_id
FOREIGN KEY (lopid) REFERENCES lop(lopid);
-- khóa ngoại bảng lớp 
ALTER TABLE lop 
ADD CONSTRAINT fk_khoi_id
FOREIGN KEY (khoiid) REFERENCES khoi(khoiid);
-- khóa ngoại bảng diem 
ALTER TABLE diem 
ADD CONSTRAINT fk_hocsinh_id
FOREIGN KEY (hocsinhid) REFERENCES hocsinh(hocsinhid);

ALTER TABLE diem 
ADD CONSTRAINT fk_monhoc_id
FOREIGN KEY (monhocid) REFERENCES monhoc(monhocid);


-- dữ liệu 
-- Thêm dữ liệu vào bảng khoi
INSERT INTO khoi (tenkhoi) VALUES
    ('Khối 10'),
    ('Khối 11'),
    ('Khối 12');

-- Thêm dữ liệu vào bảng lop
INSERT INTO lop (tenlop, khoiid) VALUES
    ('10A', 1),
    ('11A', 2),
    ('12A', 3),
    ('10B', 1),
    ('11B', 2);

-- Thêm dữ liệu vào bảng hocsinh
INSERT INTO hocsinh (lopid, hoten, ngaysinh, gioitinh, diachi, loptruong, bithuchidoan, ghichu) VALUES
    (1, 'Nguyen Van An', '2004-01-01', 1, 'Hanoi', 'Class Leader', 1, NULL),
    (1, 'Tran Thi An', '2003-12-15', 0, 'Hanoi', NULL, 0, 'Good student'),
    (2, 'Le Van Anh', '2003-09-10', 1, 'Hanoi', NULL, 0, NULL),
    (3, 'Pham Thi Anh', '2002-11-20', 0, 'Hanoi', 'Class Leader', 1, NULL),
    (4, 'Vo Van An', '2004-03-05', 1, 'Hanoi', NULL, 0, 'Hardworking student');

-- câu 1 
SELECT h.*
FROM hocsinh h
JOIN lop l ON h.lopid = l.lopid
WHERE l.tenlop = '12A';

-- câu 2 
SELECT h.*
FROM hocsinh h
INNER JOIN lop l ON h.lopid = l.lopid
WHERE h.hoten LIKE '%An%';

-- câu 3 
SELECT * FROM hocsinh 
WHERE diachi LIKE 'Hà Nội'



