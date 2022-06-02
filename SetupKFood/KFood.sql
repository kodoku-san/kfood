CREATE DATABASE DA1_QLQA_Setup
GO

USE DA1_QLQA_Setup
GO

CREATE TABLE Mailer(
	UsernameMailer varchar(50) NOT NULL,
	PasswordMailer varchar(255) NOT NULL,
	PRIMARY KEY(UsernameMailer)
)
GO

CREATE TABLE USERS(
	IdUser int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	HoTen nvarchar(100) NOT NULL,
	TaiKhoan varchar(50) NOT NULL,
	MatKhau varchar(50) NOT NULL,
	Email nvarchar(100) NOT NULL,
	DiaChi nvarchar(500),
	Hinh nvarchar(500) DEFAULT 'default-user.png',
	GioiTinh bit NOT NULL,	
	Quyen int NOT NULL,	
	QuenMK varchar(15)
)
GO

CREATE TABLE ROOM(
	IdRoom int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	TenRoom nvarchar(100) NOT NULL,
	TotalTable int NOT NULL	
)
GO

CREATE TABLE TABLE_ROOM(
	IdTable int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	TenTable nvarchar(100) NOT NULL,
	IdRoom int NOT NULL,
	SoChoNgoi int NOT NULL,
	GhiChu nvarchar(255),
	FOREIGN KEY (IdRoom) REFERENCES ROOM(IdRoom)
)
GO

CREATE TABLE CATEGORY(
	IdCategory int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	TenDanhMuc nvarchar(150) NOT NULL,
	MoTa nvarchar(255)
)
GO

CREATE TABLE PRODUCT(
	IdProduct int IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	TenSanPham nvarchar(255) NOT NULL,
	GiaBan float NOT NULL,
	DonVi nvarchar(100) NOT NULL,
	Hinh nvarchar(500) NOT NULL,
	MoTa nvarchar(155),
	IdCategory int NOT NULL,
	FOREIGN KEY (IdCategory) REFERENCES CATEGORY(IdCategory)
)
GO

CREATE TABLE BILL(
	IdBill int IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
	IdUser int NOT NULL,
	IdTable int NOT NULL,
	TrangThai nvarchar(50) DEFAULT 'new',
	DateBill datetime NOT NULL DEFAULT getDate(),
	FOREIGN KEY (IdUser) REFERENCES USERS(IdUser),
	FOREIGN KEY (IdTable) REFERENCES TABLE_ROOM(IdTable)
)
GO

CREATE TABLE DETAIL_BILL(
	IdDetailBill int IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
	IdBill int NOT NULL,
	IdProduct int NOT NULL,
	GiaSP float NOT NULL,
	SoLuong int NOT NULL,
	GhiChu nvarchar(500),
	TrangThai bit Default 0,
	FOREIGN KEY (IdBill) REFERENCES BILL(IdBill) ON DELETE CASCADE,
	FOREIGN KEY (IdProduct) REFERENCES PRODUCT(IdProduct)
)
GO

CREATE TABLE KHO(
	IdKho nvarchar(150) PRIMARY KEY NOT NULL,
	TenNguyenLieu nvarchar(255) NOT NULL,
	TenDanhMuc nvarchar(255) NOT NULL,
	NgayNhap datetime default getDate() NOT NULL,
	SoLuong float NOT NULL,
	DonVi nvarchar(100) NOT NULL,
	CHECK(SoLuong >= 0)
)
GO

CREATE TABLE XUAT_KHO(
	IdXuatKho int IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	IdKho nvarchar(150) NOT NULL,
	NgayXuat datetime default getDate() NOT NULL,
	SoLuong float NOT NULL,
	IdUser int NOT NULL,
	FOREIGN KEY (IdUser) REFERENCES USERS(IdUser),
	FOREIGN KEY (IdKho) REFERENCES KHO(IdKho),
	CHECK(SoLuong > 0)
)
GO

--insert dữ liệu
INSERT INTO USERS VALUES 
(N'Hồ Hoàng Phú', N'phuho', N'123456', N'phuvncom007@gmail.com', N'Đồng Tháp', N'1Untitled.png', 1, 0, NULL),
(N'Hồ Ngọc Kỳ', N'ngocky', N'123456', N'ngocky@gmail.com', N'', N'156923586_3425758007529208_2245103228996113662_o.jpg', 0, 0, N''),
(N'wibumecode', N'wibu', N'123456', N'wibu@gmail.com', N'Nhật Bản', N'default-user.png', 1, 0, N''),
(N'Bếp "Chưởng"', N'nvbep', N'123456', N'nvbep@gmail.com', N'', N'default-user.png', 1, 1, N''),
(N'Fujii Kodoku', N'nvorder', N'123456', N'nvorder@gmail.com', N'', N'Untitled.png', 1, 2, N''),
(N'Trần Văn A', N'tranvana', N'123456', N'tranvana@gmail.com', N'Việt Nam', N'default-user.png', 1, 2, N''),
(N'Trần Thị B', N'tranthib', N'123456', N'tranthib@gmail.com', N'Việt Nam', N'default-user.png', 0, 2, N'')
GO

INSERT INTO CATEGORY VALUES
(N'Món ăn', N''),
(N'Đồ uống', N''),
(N'Khác', N'')
GO

INSERT INTO PRODUCT VALUES 
(N'Mì kim chi bò', 38000, N'Phần', N'mi-kim-chi-bo.jpg', N'Mô tả mì kim chi bò', 1),
(N'Mì kim chi hải sản', 38000, N'Phần', N'mi-kim-chi-hai-san.jpg', N'Mô tả mì kim chi hải sản', 1),
(N'Mì kim chi ba chỉ bò mỹ', 38000, N'Phần', N'mi-kim-chi-bo-my.jpg', N'Mô tả mì kim chi ba chỉ bò mỹ', 1),
(N'Bò kho sốt tiêu xanh', 38000, N'Phần', N'bo-nau-tieu-xanh.jpg', N'Mô tả Bò kho sốt tiêu xanh', 1),
(N'Kimbap', 30000, N'Phần', N'kimbap.jpg', N'Mô tả Kimbap', 1),
(N'Takoyaki', 30000, N'Phần', N'takoyaki.jpg', N'Mô tả Takoyaki', 1),
(N'Lẩu bò Mỹ kim chi', 145000, N'Phần', N'lau-thit-bo-my.jpg', N'Lẩu bò Mỹ kim chi', 1),
(N'Lẩu bạch tuộc', 145000, N'Phần', N'lau-bach-tuoc.jpg', N'Lẩu bạch tuộc', 1),
(N'Lẩu đầu cá hồi', 145000, N'Phần', N'lau-dau-ca-hoi.jpg', N'Lẩu cá hồi', 1),
(N'Lẩu hải sản', 145000, N'Phần', N'lau-hai-san.jpg', N'Lẩu hải sản', 1),
(N'Lẩu thái đặc biệt', 188000, N'Phần', N'lau-thai-db.jpg', N'Mô tả Lẩu thái đặc biệc', 1),
(N'Coca cola', 8000, N'Lon', N'cocacola.jpg', N'Nước coca cola giải khát', 2),
(N'Sting', 12000, N'Lon', N'Sting.jpg', N'Nước Sting giải khát', 2),
(N'Nước suối', 6000, N'Chai', N'nuoc-suoi.jpg', N'Nước suối giải khát', 2),
(N'Trà ô lông', 10000, N'Chai', N'tra-oolong.jpg', N'Olong giải khát', 2),
(N'Trà sữa', 18000, N'Ly', N'Trà-sữa-Trân-châu-đen-1.png', N'Trà sữa giải khát', 2),
(N'Trà đào', 21000, N'Ly', N'tra-dao.jpg', N'Trà đào ngon rẻ!', 2),
(N'Strongbow', 18000, N'Chai', N'Strongbow.jpg', N'Ngon rẻ', 2),
(N'Bia tiger', 16000, N'Lon', N'bia-tiger.jpg', N'', 2),
(N'Cam ép', 22000, N'Ly', N'cam-ep.jpg', N'', 2),
(N'Soda Blue', 12000, N'Ly', N'sodablue.jpg', N'', 2),
(N'Thuốc lá 555', 32000, N'Gói', N'thuocla555.jpg', N'', 3)
GO

INSERT INTO ROOM VALUES
(N'Tầng 1', 18),
(N'Tầng 2', 18),
(N'Phòng Vip', 6)
GO

INSERT INTO TABLE_ROOM VALUES
(N'Bàn 01', 1, 4, ''),
(N'Bàn 02', 1, 2, ''),
(N'Bàn 03', 1, 4, ''),
(N'Bàn 04', 1, 2, ''),
(N'Bàn 05', 1, 4, ''),
(N'Bàn 06', 1, 4, ''),
(N'Bàn 07', 1, 4, ''),
(N'Bàn 08', 1, 4, ''),
(N'Bàn 09', 1, 2, ''),
(N'Bàn 10', 1, 4, ''),
(N'Bàn 11', 1, 4, ''),
(N'Bàn 12', 1, 4, ''),
(N'Bàn 13', 1, 4, ''),
(N'Bàn 14', 1, 2, ''),
(N'Bàn 15', 1, 4, ''),
(N'Bàn 16', 1, 2, ''),
(N'Bàn 17', 1, 4, ''),
(N'Bàn 18', 1, 4, ''),
(N'Bàn 01', 2, 4, ''),
(N'Bàn 02', 2, 2, ''),
(N'Bàn 03', 2, 4, ''),
(N'Bàn 04', 2, 2, ''),
(N'Bàn 05', 2, 4, ''),
(N'Bàn 06', 2, 4, ''),
(N'Bàn 07', 2, 4, ''),
(N'Bàn 08', 2, 4, ''),
(N'Bàn 09', 2, 2, ''),
(N'Bàn 10', 2, 4, ''),
(N'Bàn 11', 2, 4, ''),
(N'Bàn 12', 2, 4, ''),
(N'Bàn 13', 2, 4, ''),
(N'Bàn 14', 2, 2, ''),
(N'Bàn 15', 2, 4, ''),
(N'Bàn 16', 2, 2, ''),
(N'Bàn 17', 2, 4, ''),
(N'Bàn 18', 2, 4, ''),
(N'Bàn Vip 1', 3, 2, ''),
(N'Bàn Vip 2', 3, 2, ''),
(N'Bàn Vip 3', 3, 2, ''),
(N'Bàn Vip 4', 3, 2, ''),
(N'Bàn Vip 5', 3, 2, ''),
(N'Bàn Vip 6', 3, 2, ''),
(N'Bàn Vip 7', 3, 2, ''),
(N'Bàn Vip 8', 3, 2, ''),
(N'Bàn Vip 9', 3, 2, ''),
(N'Bàn Vip 10', 3, 2, ''),
(N'Bàn Vip 11', 3, 2, ''),
(N'Bàn Vip 12', 3, 2, ''),
(N'Bàn Vip 13', 3, 2, ''),
(N'Bàn Vip 14', 3, 2, ''),
(N'Bàn Vip 15', 3, 2, ''),
(N'Bàn Vip 16', 3, 2, ''),
(N'Bàn Vip 17', 3, 2, ''),
(N'Bàn Vip 18', 3, 2, '')
GO

INSERT INTO KHO VALUES 
(N'Bach tuoc', N'Bạch tuộc', N'Kho hải sản', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 34, N'Kg'),
(N'Bia Tiger', N'Bia Tiger', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 50, N'Thùng/24 chai'),
(N'Bot tra dao', N'Bột trà đào', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 20, N'Kg'),
(N'Bot tra sua', N'Bột trà sữa', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 20, N'Kg'),
(N'Ca hoi', N'Cá hồi', N'Kho hải sản', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Kg'),
(N'Cam', N'Cam', N'Kho rau - củ - quả', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Kg'),
(N'Coca cola', N'Coca cola', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 20, N'Thùng/24 lon'),
(N'Dao dong hop', N'Đào đóng hộp', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 120, N'Lon'),
(N'Duong', N'Đường', N'Kho gia vị', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 50000, N'gr'),
(N'Hat nem', N'Hạt nêm', N'Kho gia vị', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 40000, N'gr'),
(N'Muoi', N'Muối', N'Kho gia vị', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 50000, N'gr'),
(N'Nam kim chi', N'Nấm kim chi', N'Kho rau - củ - quả', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 22, N'Kg'),
(N'Nam linh chi', N'Nấm linh chi', N'Kho rau - củ - quả', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 27, N'Kg'),
(N'Nam meo', N'Nấm mèo', N'Kho rau - củ - quả', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 44, N'Kg'),
(N'Nuoc mam', N'Nước mắm', N'Kho gia vị', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 70000, N'ml'),
(N'Nuoc suoi', N'Nước suối', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 20, N'Thùng/30 chai'),
(N'So diep', N'Sò điệp', N'Kho hải sản', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Kg'),
(N'So huyet', N'Sò huyết', N'Kho hải sản', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Kg'),
(N'So long', N'Sò lông', N'Kho hải sản', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Kg'),
(N'Sting', N'Sting', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 20, N'Thùng/16 lon'),
(N'Strongbow', N'Strongbow', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Thùng/16 chai'),
(N'Tom su', N'Tôm sú', N'Kho hải sản', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 40, N'Kg'),
(N'Thit bo', N'Thịt bò', N'Kho thịt', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 40, N'Kg'),
(N'Thit bo kobe', N'Thịt bò kobe', N'Kho thịt', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 30, N'Kg'),
(N'Thit ga', N'Thịt gà', N'Kho thịt', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 55, N'Kg'),
(N'Thit heo', N'Thịt heo', N'Kho thịt', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 36, N'Kg'),
(N'Tra o long', N'Trà ô lông', N'Kho thức uống', CAST(N'2021-11-21T18:17:24.553' AS DateTime), 20, N'Thùng/30 chai')
GO

INSERT INTO XUAT_KHO VALUES
(N'Thit bo', CAST(N'2021-10-21T18:17:24.567' AS DateTime), 1, 4),
(N'Nuoc mam', CAST(N'2021-10-21T18:17:24.567' AS DateTime), 10, 4),
(N'Muoi', CAST(N'2021-10-21T18:17:24.567' AS DateTime), 2, 4),
(N'Duong', CAST(N'2021-10-21T18:17:24.567' AS DateTime), 1, 4),
(N'Hat nem', CAST(N'2021-10-21T18:17:24.567' AS DateTime), 2, 4),
(N'Cam', CAST(N'2021-10-04T18:17:24.567' AS DateTime), 1, 4),
(N'So huyet', CAST(N'2021-11-21T22:36:46.000' AS DateTime), 2, 4),
(N'Cam', CAST(N'2021-11-22T09:51:32.000' AS DateTime), 1, 4),
(N'Nam linh chi', CAST(N'2021-11-22T09:52:58.000' AS DateTime), 0.8, 4),
(N'Thit ga', CAST(N'2021-11-28T15:01:48.000' AS DateTime), 2, 4),
(N'Thit heo', CAST(N'2021-12-02T07:00:00.000' AS DateTime), 4, 4)
GO

INSERT INTO BILL VALUES 
(5, 27, N'close', CAST(N'2020-11-21T12:20:24.593' AS DateTime)),
(7, 16, N'close', CAST(N'2020-11-21T11:20:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2020-11-21T10:20:24.593' AS DateTime)),
(5, 9, N'close', CAST(N'2020-11-21T09:20:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2020-11-21T08:20:24.593' AS DateTime)),
(5, 8, N'close', CAST(N'2020-11-21T13:20:24.593' AS DateTime)),
(5, 10, N'close', CAST(N'2020-04-27T12:20:24.593' AS DateTime)),
(5, 36, N'close', CAST(N'2020-04-27T18:20:24.593' AS DateTime)),
(6, 1, N'close', CAST(N'2020-04-27T19:20:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2020-04-27T16:20:24.593' AS DateTime)),
(5, 14, N'close', CAST(N'2020-04-27T20:20:24.593' AS DateTime)),
(6, 27, N'close', CAST(N'2020-09-16T12:20:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2020-09-16T16:20:24.593' AS DateTime)),
(7, 27, N'close', CAST(N'2020-09-16T18:20:24.593' AS DateTime)),
(5, 9, N'close', CAST(N'2020-09-16T19:20:24.593' AS DateTime)),
(5, 16, N'close', CAST(N'2021-01-12T18:17:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2021-01-12T20:17:24.593' AS DateTime)),
(5, 12, N'close', CAST(N'2021-01-14T14:17:24.593' AS DateTime)),
(6, 10, N'close', CAST(N'2021-01-14T18:17:24.593' AS DateTime)),
(5, 3, N'close', CAST(N'2021-01-16T20:17:24.593' AS DateTime)),
(6, 10, N'close', CAST(N'2021-01-16T21:17:24.593' AS DateTime)),
(5, 36, N'close', CAST(N'2021-02-12T20:17:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2021-02-12T19:17:24.593' AS DateTime)),
(5, 14, N'close', CAST(N'2021-03-19T17:17:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-03-19T18:12:24.593' AS DateTime)),
(5, 1, N'close', CAST(N'2021-03-26T21:17:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2021-04-27T16:17:24.593' AS DateTime)),
(6, 14, N'close', CAST(N'2021-04-27T17:00:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-04-27T18:00:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2021-04-27T19:00:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2021-04-27T20:00:24.593' AS DateTime)),
(7, 14, N'close', CAST(N'2021-04-27T21:00:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-04-27T21:00:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2021-04-27T23:00:24.593' AS DateTime)),
(7, 27, N'close', CAST(N'2021-05-20T17:00:24.593' AS DateTime)),
(5, 14, N'close', CAST(N'2021-05-20T15:00:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-05-20T20:00:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2021-05-09T17:00:24.593' AS DateTime)),
(6, 2, N'close', CAST(N'2021-05-09T18:00:24.593' AS DateTime)),
(5, 14, N'close', CAST(N'2021-06-09T20:00:24.593' AS DateTime)),
(7, 27, N'close', CAST(N'2021-06-09T23:00:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2021-06-09T17:00:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-06-09T18:00:24.593' AS DateTime)),
(5, 14, N'close', CAST(N'2021-07-12T19:00:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-07-14T16:00:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2021-07-30T17:00:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2021-08-01T17:00:24.593' AS DateTime)),
(5, 14, N'close', CAST(N'2021-08-01T17:00:24.593' AS DateTime)),
(5, 27, N'close', CAST(N'2021-08-01T17:00:24.593' AS DateTime)),
(5, 4, N'close', CAST(N'2021-09-01T17:00:24.593' AS DateTime)),
(5, 2, N'close', CAST(N'2021-09-21T21:21:03.000' AS DateTime)),
(5, 6, N'close', CAST(N'2021-09-21T22:06:32.000' AS DateTime)),
(5, 3, N'close', CAST(N'2021-10-26T07:39:48.000' AS DateTime)),
(5, 16, N'close', CAST(N'2021-10-26T09:08:00.000' AS DateTime)),
(5, 6, N'close', CAST(N'2021-10-26T10:02:01.000' AS DateTime)),
(5, 6, N'close', CAST(N'2021-11-28T14:12:18.000' AS DateTime)),
(5, 16, N'close', CAST(N'2021-11-28T14:15:46.000' AS DateTime)),
(5, 10, N'close', CAST(N'2021-11-28T15:39:25.000' AS DateTime)),
(5, 42, N'close', CAST(N'2021-11-29T16:05:21.000' AS DateTime))
GO

INSERT INTO DETAIL_BILL VALUES 
(1, 1, 38000, 1, N'', 1),
(1, 2, 38000, 1, N'', 1),
(1, 14, 6000, 4, N'', 1),
(1, 20, 22000, 1, N'', 1),
(1, 12, 8000, 1, N'', 1),
(1, 11, 188000, 4, N'', 1),
(1, 16, 18000, 1, N'', 1),
(2, 1, 38000, 2, N'', 1),
(2, 4, 38000, 1, N'', 1),
(2, 6, 30000, 1, N'', 1),
(2, 4, 38000, 1, N'', 1),
(2, 2, 38000, 1, N'', 1),
(3, 1, 38000, 1, N'', 1),
(3, 2, 38000, 1, N'', 1),
(3, 3, 38000, 1, N'', 1),
(3, 14, 6000, 3, N'', 1),
(3, 21, 12000, 1, N'', 1),
(3, 10, 145000, 3, N'', 1),
(4, 1, 38000, 1, N'', 1),
(4, 20, 22000, 1, N'', 1),
(4, 12, 8000, 1, N'', 1),
(4, 2, 38000, 1, N'', 1),
(4, 4, 38000, 1, N'', 1),
(4, 16, 18000, 1, N'', 1),
(5, 4, 38000, 1, N'', 1),
(5, 6, 30000, 1, N'', 1),
(5, 15, 10000, 1, N'', 1),
(5, 14, 6000, 1, N'', 1),
(5, 16, 18000, 1, N'', 1),
(5, 2, 38000, 1, N'', 1),
(6, 4, 38000, 1, N'', 1),
(6, 6, 30000, 1, N'', 1),
(6, 14, 6000, 1, N'', 1),
(6, 1, 38000, 1, N'', 1),
(6, 2, 38000, 1, N'', 1),
(6, 5, 30000, 1, N'', 1),
(7, 1, 38000, 1, N'', 1),
(7, 2, 38000, 1, N'', 1),
(7, 3, 38000, 1, N'', 1),
(8, 14, 6000, 4, N'', 1),
(8, 20, 22000, 1, N'', 1),
(8, 1, 38000, 2, N'', 1),
(8, 4, 38000, 1, N'', 1),
(9, 14, 6000, 4, N'', 1),
(9, 20, 22000, 1, N'', 1),
(9, 1, 38000, 2, N'', 1),
(9, 4, 38000, 1, N'', 1),
(10, 6, 30000, 1, N'', 1),
(10, 14, 6000, 1, N'', 1),
(10, 1, 38000, 1, N'', 1),
(10, 2, 38000, 1, N'', 1),
(10, 5, 30000, 1, N'', 1),
(11, 1, 38000, 1, N'', 1),
(11, 2, 38000, 1, N'', 1),
(11, 3, 38000, 1, N'', 1),
(12, 6, 30000, 1, N'', 1),
(12, 4, 38000, 1, N'', 1),
(12, 2, 38000, 1, N'', 1),
(13, 1, 38000, 2, N'', 1),
(13, 4, 38000, 1, N'', 1),
(13, 14, 6000, 4, N'', 1),
(14, 5, 30000, 1, N'', 1),
(14, 1, 38000, 1, N'', 1),
(14, 2, 38000, 1, N'', 1),
(15, 4, 38000, 1, N'', 1),
(15, 14, 6000, 4, N'', 1),
(15, 20, 22000, 1, N'', 1),
(15, 1, 38000, 2, N'', 1),
(15, 4, 38000, 1, N'', 1),
(15, 6, 30000, 1, N'', 1),
(16, 1, 38000, 1, N'', 1),
(16, 2, 38000, 1, N'', 1),
(16, 3, 38000, 1, N'', 1),
(17, 1, 38000, 1, N'', 1),
(17, 2, 38000, 1, N'', 1),
(17, 3, 38000, 1, N'', 1),
(18, 6, 30000, 1, N'', 1),
(18, 15, 10000, 1, N'', 1),
(18, 14, 6000, 1, N'', 1),
(19, 14, 6000, 1, N'', 1),
(19, 1, 38000, 1, N'', 1),
(19, 2, 38000, 1, N'', 1),
(19, 5, 30000, 1, N'', 1),
(20, 3, 38000, 1, N'', 1),
(20, 14, 6000, 4, N'', 1),
(20, 20, 22000, 1, N'', 1),
(21, 1, 38000, 2, N'', 1),
(21, 4, 38000, 1, N'', 1),
(21, 14, 6000, 4, N'', 1),
(21, 20, 22000, 1, N'', 1),
(22, 1, 38000, 2, N'', 1),
(22, 4, 38000, 1, N'', 1),
(22, 6, 30000, 1, N'', 1),
(23, 14, 6000, 1, N'', 1),
(23, 1, 38000, 1, N'', 1),
(23, 2, 38000, 1, N'', 1),
(23, 5, 30000, 1, N'', 1),
(21, 20, 22000, 1, N'', 1),
(22, 1, 38000, 2, N'', 1),
(23, 20, 22000, 1, N'', 1),
(23, 1, 38000, 2, N'', 1),
(23, 4, 38000, 1, N'', 1),
(23, 2, 38000, 1, N'', 1),
(24, 5, 30000, 1, N'', 1),
(24, 1, 38000, 1, N'', 1),
(24, 2, 38000, 1, N'', 1),
(25, 1, 38000, 2, N'', 1),
(25, 4, 38000, 1, N'', 1),
(25, 6, 30000, 1, N'', 1),
(25, 4, 38000, 1, N'', 1),
(26, 2, 38000, 1, N'', 1),
(27, 20, 22000, 1, N'', 1),
(27, 1, 38000, 2, N'', 1),
(27, 4, 38000, 1, N'', 1),
(28, 6, 30000, 1, N'', 1),
(28, 1, 38000, 1, N'', 1),
(29, 2, 38000, 1, N'', 1),
(29, 6, 30000, 1, N'', 1),
(29, 15, 10000, 1, N'', 1),
(30, 14, 6000, 1, N'', 1),
(30, 14, 6000, 1, N'', 1),
(30, 1, 38000, 1, N'', 1),
(31, 2, 38000, 1, N'', 1),
(31, 14, 6000, 4, N'', 1),
(31, 20, 22000, 1, N'', 1),
(32, 1, 38000, 2, N'', 1),
(32, 4, 38000, 1, N'', 1),
(32, 2, 38000, 1, N'', 1),
(32, 12, 8000, 4, N'', 1),
(33, 20, 22000, 1, N'', 1),
(33, 1, 38000, 2, N'', 1),
(33, 4, 38000, 1, N'', 1),
(33, 6, 30000, 1, N'', 1),
(34, 14, 6000, 4, N'', 1),
(34, 20, 22000, 1, N'', 1),
(34, 1, 38000, 2, N'', 1),
(35, 2, 38000, 4, N'', 1),
(35, 6, 30000, 2, N'', 1),
(36, 14, 6000, 4, N'', 1),
(36, 20, 22000, 1, N'', 1),
(36, 1, 38000, 2, N'', 1),
(36, 4, 38000, 1, N'', 1),
(37, 6, 30000, 1, N'', 1),
(37, 6, 30000, 1, N'', 1),
(37, 15, 10000, 1, N'', 1),
(38, 14, 6000, 1, N'', 1),
(38, 14, 6000, 1, N'', 1),
(38, 1, 38000, 1, N'', 1),
(39, 2, 38000, 1, N'', 1),
(39, 5, 30000, 1, N'', 1),
(39, 3, 38000, 1, N'', 1),
(40, 14, 6000, 4, N'', 1),
(40, 20, 22000, 1, N'', 1),
(41, 1, 38000, 2, N'', 1),
(41, 4, 38000, 1, N'', 1),
(42, 4, 38000, 1, N'', 1),
(42, 6, 30000, 1, N'', 1),
(42, 1, 38000, 1, N'', 1),
(43, 2, 38000, 1, N'', 1),
(43, 6, 30000, 1, N'', 1),
(43, 15, 10000, 1, N'', 1),
(44, 14, 6000, 1, N'', 1),
(44, 14, 6000, 1, N'', 1),
(44, 1, 38000, 1, N'', 1),
(45, 2, 38000, 1, N'', 1),
(45, 14, 6000, 4, N'', 1),
(45, 20, 22000, 1, N'', 1),
(46, 4, 38000, 1, N'', 1),
(46, 6, 30000, 1, N'', 1),
(46, 1, 38000, 1, N'', 1),
(47, 2, 38000, 1, N'', 1),
(47, 6, 30000, 1, N'', 1),
(47, 15, 10000, 1, N'', 1),
(48, 14, 6000, 1, N'', 1),
(48, 14, 6000, 1, N'', 1),
(48, 1, 38000, 1, N'', 1),
(49, 2, 38000, 1, N'', 1),
(49, 14, 6000, 4, N'', 1),
(49, 20, 22000, 1, N'', 1),
(49, 4, 38000, 1, N'', 1),
(50, 2, 38000, 1, N'', 1),
(50, 12, 8000, 4, N'', 1),
(50, 20, 22000, 1, N'', 1),
(50, 1, 38000, 2, N'', 1),
(50, 4, 38000, 1, N'', 1),
(51, 1, 38000, 1, N'', 1),
(51, 6, 30000, 1, N'', 1),
(51, 19, 16000, 2, N'', 1),
(52, 1, 38000, 1, N'', 1),
(52, 6, 30000, 1, N'', 1),
(52, 19, 16000, 2, N'lạnh, k tẩy', 1),
(52, 7, 145000, 1, N'', 1),
(53, 6, 30000, 1, N'', 1),
(53, 1, 38000, 1, N'', 1),
(53, 19, 16000, 2, N'', 1),
(54, 1, 38000, 1, N'', 1),
(54, 6, 30000, 1, N'', 1),
(54, 19, 16000, 2, N'', 1),
(54, 7, 145000, 1, N'', 1),
(54, 20, 22000, 2, N'', 1),
(54, 15, 10000, 1, N'', 1),
(55, 2, 38000, 1, N'', 1),
(55, 19, 16000, 2, N'', 1),
(55, 4, 38000, 1, N'', 1),
(55, 6, 30000, 1, N'', 1),
(56, 1, 38000, 1, N'', 1),
(56, 6, 30000, 1, N'', 1),
(56, 19, 16000, 2, N'', 1),
(57, 7, 145000, 1, N'lẩu cay', 1),
(57, 6, 30000, 2, N'', 1),
(57, 19, 16000, 6, N'', 1),
(58, 1, 38000, 1, N'', 1),
(59, 1, 38000, 1, N'', 1),
(59, 6, 30000, 1, N'', 1),
(59, 19, 16000, 2, N'', 1),
(59, 3, 38000, 1, N'', 1)
GO

IF OBJECT_ID('sp_NVorder_BanAn') IS NOT NULL
	DROP PROC sp_NVorder_BanAn
GO
CREATE PROC sp_NVorder_BanAn(@IdUser INT) AS 
BEGIN
	select BILL.IdTable, BILL.IdBill, TenTable, SoChoNgoi, TrangThai, IdRoom
	from 
	BILL inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable 
	where
	IdUser = @IdUser and TrangThai not like 'close'
END
GO

IF OBJECT_ID('sp_NVorder_BanAn_Search') IS NOT NULL
	DROP PROC sp_NVorder_BanAn_Search
GO
CREATE PROC sp_NVorder_BanAn_Search(@IdUser INT, @Keyword nvarchar(255)) AS 
BEGIN
	select BILL.IdTable, BILL.IdBill, TenTable, SoChoNgoi, TrangThai, IdRoom
	from 
	BILL inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable 
	where
	(IdUser = @IdUser and TrangThai not like 'close')
	and		
	(
		TenTable like '%' + @Keyword + '%' OR
		BILL.IdTable like '%' + @Keyword + '%' OR
		TrangThai like '%' + @Keyword + '%'
	)
END
GO

IF OBJECT_ID('sp_NVBep_Order') IS NOT NULL
	DROP PROC sp_NVBep_Order
GO
CREATE PROC sp_NVBep_Order(@Keyword nvarchar(255) = '', @flag int = 0) AS 
BEGIN
	if(@Keyword = 'all')
		Select IdUser, BILL.IdTable, IdBill, ROOM.IdRoom, TenTable, SoChoNgoi, TrangThai, TenRoom, DateBill
		from 
		BILL inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable
		inner join ROOM on TABLE_ROOM.IdRoom = ROOM.IdRoom
		where TrangThai not like 'close'
		ORDER BY DateBill ASC
	else if(@flag > 0) 
		Select IdUser, BILL.IdTable, IdBill, ROOM.IdRoom, TenTable, SoChoNgoi, TrangThai, TenRoom, DateBill
		from 
		BILL inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable
		inner join ROOM on TABLE_ROOM.IdRoom = ROOM.IdRoom
		where 
		TrangThai not like 'close' and
		TenTable like '%' + @Keyword + '%' and
		ROOM.IdRoom like @flag
		ORDER BY DateBill ASC
	else if(@flag < 0) 
		Select IdUser, BILL.IdTable, IdBill, ROOM.IdRoom, TenTable, SoChoNgoi, TrangThai, TenRoom, DateBill
		from 
		BILL inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable
		inner join ROOM on TABLE_ROOM.IdRoom = ROOM.IdRoom
		where 
		TrangThai like @Keyword and
		ROOM.IdRoom like -@flag
		ORDER BY DateBill ASC
	else 
		Select IdUser, BILL.IdTable, IdBill, ROOM.IdRoom, TenTable, SoChoNgoi, TrangThai, TenRoom, DateBill
		from 
		BILL inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable
		inner join ROOM on TABLE_ROOM.IdRoom = ROOM.IdRoom
		where 
		TrangThai not like 'close' and
		ROOM.IdRoom like '%' + @Keyword + '%'
		ORDER BY DateBill ASC
END
GO

IF OBJECT_ID('sp_NVbep_ChiTiet') IS NOT NULL
	DROP PROC sp_NVbep_ChiTiet
GO
CREATE PROC sp_NVbep_ChiTiet(@IdRoom INT, @TenBan nvarchar(255)) AS 
BEGIN
	Select TenSanPham, SoLuong, DonVi, GhiChu, DETAIL_BILL.TrangThai, BILL.IdBill, DETAIL_BILL.IdProduct, DETAIL_BILL.IdDetailBill, GiaSP
	from BILL inner join DETAIL_BILL on BILL.IdBill = DETAIL_BILL.IdBill 
	inner join PRODUCT on DETAIL_BILL.IdProduct = PRODUCT.IdProduct
	where 
	IdTable = (Select IdTable from TABLE_ROOM where TenTable like '%' + @TenBan + '%' and IdRoom = @IdRoom)
	and
	BILL.TrangThai not like 'close'
END
GO

-- get doanh thu tháng
IF OBJECT_ID('sp_DoanhThuThang') IS NOT NULL
	DROP PROC sp_DoanhThuThang
GO
CREATE PROC sp_DoanhThuThang(@Month INT) AS 
BEGIN
	select 
		sum(GiaSP*SoLuong) as 'TongDoanhthu'
	from 
		BILL inner join DETAIL_BILL on BILL.IdBill = DETAIL_BILL.IdBill
	where
		Month(DateBill) = @Month and Year(DateBill) = YEAR(getDate()) and Bill.TrangThai = 'close'
END
GO

-- lịch sử bán hàng
IF OBJECT_ID('sp_LichSuBan') IS NOT NULL
	DROP PROC sp_LichSuBan
GO
CREATE PROC sp_LichSuBan(@s_Date date, @e_Date date) AS 
BEGIN
	select 
		Bill.IdBill, TenTable, TenRoom, sum(GiaSP*SoLuong) as 'TongTien', DateBill, HoTen
	from 
	BILL inner join DETAIL_BILL on BILL.IdBill = DETAIL_BILL.IdBill
		 inner join TABLE_ROOM on BILL.IdTable = TABLE_ROOM.IdTable
		 inner join USERS on USERS.IdUser = BILL.IdUser
		 inner join ROOM on ROOM.IdRoom = TABLE_ROOM.IdRoom
	where
		Bill.TrangThai = 'close' and 
		DateBill between @s_Date and DATEADD(DAY, 1, @e_Date)
	Group by DateBill, TenTable, HoTen, TenRoom, Bill.IdBill Order by DateBill DESC
END
GO

-- get doanh thu 
IF OBJECT_ID('sp_ThongKeDoanhThu') IS NOT NULL
	DROP PROC sp_ThongKeDoanhThu
GO
CREATE PROC sp_ThongKeDoanhThu(@s_Date date, @e_Date date) AS 
BEGIN
	select 
		TenSanPham,
		min(DateBill) 'BatDau',
		max(DateBill) as 'KetThuc',
		sum(SoLuong) as 'TongSanPham',
		sum(GiaSp*SoLuong) as 'TongDoanhthu'
	from 
		BILL inner join DETAIL_BILL on BILL.IdBill = DETAIL_BILL.IdBill
		inner join PRODUCT on DETAIL_BILL.IdProduct = PRODUCT.IdProduct
	where
		Bill.TrangThai = 'close' and
		DateBill between @s_Date and DATEADD(DAY, 1, @e_Date)
	Group by TenSanPham ORDER BY TongDoanhthu DESC
END
GO
 
-- get món ăn bán chạy
IF OBJECT_ID('sp_MonBanChay') IS NOT NULL
	DROP PROC sp_MonBanChay
GO
CREATE PROC sp_MonBanChay(@IdCaterogy int) AS 
BEGIN
	select 
		p.IdProduct, 
		p.TenSanPham,
		p.GiaBan,
		p.DonVi,
		Sum(dtb.SoLuong) as 'TongBanDuoc',
		Sum(p.GiaBan * dtb.SoLuong) as 'TongDT'
	from 
		PRODUCT as p inner join DETAIL_BILL as dtb on p.IdProduct = dtb.IdProduct
	WHERE p.IdCategory = @IdCaterogy
	GROUP BY p.IdProduct, p.TenSanPham, p.GiaBan, p.DonVi
	ORDER BY TongBanDuoc DESC
END
GO

-- get top nhan vien
IF OBJECT_ID('sp_TopNhanVien') IS NOT NULL
	DROP PROC sp_TopNhanVien
GO
CREATE PROC sp_TopNhanVien(@Month int) AS 
BEGIN
	select 
		u.IdUser,
		HoTen,
		GioiTinh,
		SUM(GiaSP*SoLuong) as 'TongDoanhThu'
	from 
		USERS as u inner join BILL as b on u.IdUser = b.IdUser
		inner join DETAIL_BILL as dtb on dtb.IdBill = b.IdBill
	where 
		YEAR(b.DateBill) = YEAR(getDate()) and
		Month(b.DateBill) = @Month
	Group by u.IdUser, HoTen, GioiTinh
	ORDER BY TongDoanhThu DESC
END
GO

--trigger xuất kho
IF OBJECT_ID('trg_XuatKho') IS NOT NULL
	DROP TRIGGER trg_XuatKho
GO
CREATE TRIGGER trg_XuatKho ON XUAT_KHO AFTER INSERT AS 
BEGIN
	UPDATE KHO	
	SET SoLuong = KHO.SoLuong - (SELECT SoLuong FROM inserted WHERE IdKho = KHO.IdKho)
	FROM KHO INNER JOIN inserted ON KHO.IdKho = inserted.IdKho
END
GO

-- ============================================= 
-- Author: Microsoft 
-- Create date: 2010-02-06
-- Description: Backup Databases for SQLExpress
-- Parameter1: databaseName 
-- Parameter2: backupType F=full, D=differential, L=log
-- Parameter3: backup file location
-- =============================================

USE master
IF OBJECT_ID('sp_BackupDatabases') IS NOT NULL
	DROP PROCEDURE sp_BackupDatabases
GO
CREATE PROCEDURE [dbo].[sp_BackupDatabases]  
    @databaseName sysname = null,
    @backupType CHAR(1),
    @backupLocation nvarchar(999) 
AS 
    SET NOCOUNT ON; 

        DECLARE @DBs TABLE
        (
            ID int IDENTITY PRIMARY KEY,
            DBNAME nvarchar(500)
        )
           
             -- Pick out only databases which are online in case ALL databases are chosen to be backed up

             -- If specific database is chosen to be backed up only pick that out from @DBs

            INSERT INTO @DBs (DBNAME)
            SELECT Name FROM master.sys.databases
            where state=0
            AND name=@DatabaseName
            OR @DatabaseName IS NULL
            ORDER BY Name

 
           -- Filter out databases which do not need to backed up
 
           IF @backupType='F'
                  BEGIN
                  DELETE @DBs where DBNAME IN ('tempdb','Northwind','pubs','AdventureWorks')
                  END
            ELSE IF @backupType='D'
                  BEGIN
                  DELETE @DBs where DBNAME IN ('tempdb','Northwind','pubs','master','AdventureWorks')
                  END
            ELSE IF @backupType='L'
                  BEGIN
                  DELETE @DBs where DBNAME IN ('tempdb','Northwind','pubs','master','AdventureWorks')
                  END
            ELSE
                  BEGIN
                  RETURN
                  END
           

            -- Declare variables

            DECLARE @BackupName varchar(100)
            DECLARE @BackupFile varchar(100)
            DECLARE @DBNAME varchar(300)
            DECLARE @sqlCommand NVARCHAR(1000) 
			DECLARE @dateTime NVARCHAR(20)
            DECLARE @Loop int                  
                       
            -- Loop through the databases one by one
            SELECT @Loop = min(ID) FROM @DBs
       WHILE @Loop IS NOT NULL
      BEGIN
 
-- Database Names have to be in [dbname] format since some have - or _ in their name

      SET @DBNAME = '['+(SELECT DBNAME FROM @DBs WHERE ID = @Loop)+']'


-- Set the current date and time n yyyyhhmmss format

      SET @dateTime = REPLACE(CONVERT(VARCHAR, GETDATE(),101),'/','') + '_' +  REPLACE(CONVERT(VARCHAR, GETDATE(),108),':','')  
 

-- Create backup filename in path\filename.extension format for full,diff and log backups

      IF @backupType = 'F'
            SET @BackupFile = @backupLocation+REPLACE(REPLACE(@DBNAME, '[',''),']','')+ '_FULL_'+ @dateTime+ '.BAK'
      ELSE IF @backupType = 'D'
            SET @BackupFile = @backupLocation+REPLACE(REPLACE(@DBNAME, '[',''),']','')+ '_DIFF_'+ @dateTime+ '.BAK'
      ELSE IF @backupType = 'L'
            SET @BackupFile = @backupLocation+REPLACE(REPLACE(@DBNAME, '[',''),']','')+ '_LOG_'+ @dateTime+ '.TRN'
 

-- Provide the backup a name for storing in the media

      IF @backupType = 'F'
            SET @BackupName = REPLACE(REPLACE(@DBNAME,'[',''),']','') +' full backup for '+ @dateTime

      IF @backupType = 'D'
            SET @BackupName = REPLACE(REPLACE(@DBNAME,'[',''),']','') +' differential backup for '+ @dateTime

      IF @backupType = 'L'
            SET @BackupName = REPLACE(REPLACE(@DBNAME,'[',''),']','') +' log backup for '+ @dateTime


-- Generate the dynamic SQL command to be executed

       IF @backupType = 'F' 
                  BEGIN
               SET @sqlCommand = 'BACKUP DATABASE ' +@DBNAME+  ' TO DISK = '''+@BackupFile+ ''' WITH INIT, NAME= ''' +@BackupName+''', NOSKIP, NOFORMAT'
                  END

       IF @backupType = 'D'
                  BEGIN
               SET @sqlCommand = 'BACKUP DATABASE ' +@DBNAME+  ' TO DISK = '''+@BackupFile+ ''' WITH DIFFERENTIAL, INIT, NAME= ''' +@BackupName+''', NOSKIP, NOFORMAT'        
                  END

       IF @backupType = 'L' 
                  BEGIN
               SET @sqlCommand = 'BACKUP LOG ' +@DBNAME+  ' TO DISK = '''+@BackupFile+ ''' WITH INIT, NAME= ''' +@BackupName+''', NOSKIP, NOFORMAT'        
                  END
 

-- Execute the generated SQL command

       EXEC(@sqlCommand)

 
-- Goto the next database

SELECT @Loop = min(ID) FROM @DBs where ID>@Loop
 

END​
