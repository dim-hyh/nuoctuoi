# Bảng biểu chương 4

### Bảng 4.1 Danh sách các bảng dữ liệu

| STT | Tên bảng | Lớp đối tượng | Ý nghĩa |
|---|---|---|---|
| 1 | nguoi_dung | NguoiDung | Người dùng và tài khoản đăng nhập. |
| 2 | vung_tuoi | VungTuoi | Vùng tưới do một tổ thủy nông quản lý, gồm nhiều thửa dùng chung nguồn nước. |
| 3 | thua_ruong | ThuaRuong | Hồ sơ thửa ruộng (BM1, QĐ1). |
| 4 | cay_trong | CayTrong | Danh mục cây trồng (BM2). |
| 5 | giai_doan_sinh_truong | GiaiDoanSinhTruong | Các giai đoạn sinh trưởng của từng cây trồng, mỗi giai đoạn có ngưỡng ẩm và độ sâu rễ riêng (BM2, QĐ2). |
| 6 | lich_mua_vu | LichMuaVu | Lịch mùa vụ: một loại cây gieo trên một hoặc nhiều thửa từ một ngày gieo (BM3, QĐ3). |
| 7 | lich_mua_vu_thua | LichMuaVuThua | Bảng trung gian: một lịch áp dụng cho nhiều thửa, một thửa có nhiều lịch qua các vụ. |
| 8 | so_do_do_am | SoDoDoAm | Số đo độ ẩm đất của từng thửa (BM4, QĐ4). |
| 9 | du_bao_mua | DuBaoMua | Lượng mưa dự báo theo vùng và ngày (BM4). |
| 10 | han_muc_nuoc | HanMucNuoc | Hạn mức nước cấp cho vùng theo kỳ (BM5, QĐ5). |
| 11 | de_xuat_tuoi | DeXuatTuoi | Đề xuất tưới lập hằng ngày lúc 05:00 cho mỗi lịch mùa vụ (BM6, QĐ6, QĐ7). |
| 12 | de_xuat_thua | DeXuatThua | Bảng trung gian: một đề xuất áp dụng cho nhiều thửa, một thửa có nhiều đề xuất. |
| 13 | phieu_ghi_de | PhieuGhiDe | Phiếu ghi đè đề xuất có lý do và kết quả duyệt (BM7, QĐ8, QĐ9). |
| 14 | quy_dinh | QuyDinh | Tham số quy định có thể thay đổi (QĐ11). |

### Bảng 4.2 Mô tả NguoiDung (nguoi_dung)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK, tự tăng | Mã người dùng |
| 2 | ten_dang_nhap | nvarchar(255) | NOT NULL, không trùng | Tên đăng nhập, ví dụ hoa, an, binh |
| 3 | mat_khau_hash | nvarchar(255) | NOT NULL | Mật khẩu đã mã hóa BCrypt (không lưu mật khẩu gốc) |
| 4 | ho_ten | nvarchar(255) | NOT NULL | Họ tên người dùng |
| 5 | vai_tro | nvarchar(255) | NOT NULL, ∈ {NongHo, CanBoKyThuat, QuanLyTo, BanQuanTri, QuanTriHeThong} | Vai trò dùng để phân quyền chức năng |

### Bảng 4.3 Mô tả VungTuoi (vung_tuoi)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_vung | nvarchar(255) | PK | Mã vùng, ví dụ V-02 |
| 2 | ten_vung | nvarchar(255) | NOT NULL | Tên vùng |

### Bảng 4.4 Mô tả ThuaRuong (thua_ruong)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_thua | nvarchar(255) | PK, không trùng | Mã thửa, ví dụ T-014 |
| 2 | ma_vung | nvarchar(255) | FK → vung_tuoi | Vùng tưới chứa thửa |
| 3 | chu_ho | nvarchar(255) | NOT NULL | Họ tên nông hộ chủ thửa |
| 4 | dien_tich | float | > 0 (QĐ1) | Diện tích (m²) |
| 5 | loai_dat | nvarchar(255) |  | Loại đất |
| 6 | hinh_thuc_tuoi | nvarchar(255) | ∈ {Nhỏ giọt, Phun mưa, Rãnh} | Hình thức tưới, quyết định hiệu suất tưới (QĐ6) |

### Bảng 4.5 Mô tả CayTrong (cay_trong)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_cay | nvarchar(255) | PK, không trùng | Mã cây, ví dụ DL (dưa leo) |
| 2 | ten_cay | nvarchar(255) | NOT NULL | Tên cây trồng |

### Bảng 4.6 Mô tả GiaiDoanSinhTruong (giai_doan_sinh_truong)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK, tự tăng | Mã giai đoạn |
| 2 | ma_cay | nvarchar(255) | FK → cay_trong | Cây trồng của giai đoạn |
| 3 | thu_tu | int | > 0 | Thứ tự giai đoạn (1, 2, 3...) |
| 4 | ten_giai_doan | nvarchar(255) | NOT NULL | Tên giai đoạn, ví dụ Ra hoa |
| 5 | so_ngay | int | > 0 | Số ngày kéo dài của giai đoạn |
| 6 | nguong_duoi | float | 0–100, < nguong_tren (QĐ2) | Ngưỡng ẩm dưới (%): thấp hơn thì cần tưới |
| 7 | nguong_tren | float | 0–100 (QĐ2) | Ngưỡng ẩm trên (%): tưới lên tới mức này |
| 8 | do_sau_re | float | 0,05–2,0 (QĐ2) | Độ sâu rễ (m) |

### Bảng 4.7 Mô tả LichMuaVu (lich_mua_vu)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_lich | nvarchar(255) | PK | Mã lịch, ví dụ L-01 |
| 2 | ma_cay | nvarchar(255) | FK → cay_trong | Cây trồng của vụ |
| 3 | ngay_gieo | date | NOT NULL | Ngày gieo |
| 4 | ds_thua | nvarchar(255) |  | Danh sách thửa để hiển thị nhanh, ví dụ "T-014, T-015" |

### Bảng 4.8 Mô tả LichMuaVuThua (lich_mua_vu_thua)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK, tự tăng | Mã dòng |
| 2 | ma_lich | nvarchar(255) | FK → lich_mua_vu | Lịch mùa vụ |
| 3 | ma_thua | nvarchar(255) | FK → thua_ruong | Thửa thuộc lịch |

### Bảng 4.9 Mô tả SoDoDoAm (so_do_do_am)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK, tự tăng | Mã số đo |
| 2 | ma_thua | nvarchar(255) | FK → thua_ruong | Thửa được đo |
| 3 | thoi_diem | datetime2 | NOT NULL | Thời điểm đo |
| 4 | do_am | float | 0–100 (QĐ4) | Độ ẩm đất (%) |
| 5 | nguoi_ghi | nvarchar(255) |  | Họ tên cán bộ ghi số liệu |

### Bảng 4.10 Mô tả DuBaoMua (du_bao_mua)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK, tự tăng | Mã dự báo |
| 2 | ma_vung | nvarchar(255) | FK → vung_tuoi | Vùng tưới |
| 3 | ngay | date | NOT NULL, mỗi vùng một dòng/ngày | Ngày dự báo |
| 4 | luong_mua | float | ≥ 0 | Lượng mưa dự báo (mm) |

### Bảng 4.11 Mô tả HanMucNuoc (han_muc_nuoc)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_han_muc | nvarchar(255) | PK | Mã kỳ hạn mức, ví dụ HM-01 |
| 2 | ma_vung | nvarchar(255) | FK → vung_tuoi | Vùng được cấp |
| 3 | tu_ngay | date | NOT NULL | Ngày bắt đầu kỳ |
| 4 | den_ngay | date | ≥ tu_ngay, không trùng kỳ khác cùng vùng (QĐ5) | Ngày kết thúc kỳ |
| 5 | han_muc | float | > 0 (QĐ5) | Lượng nước được cấp (m³) |
| 6 | da_dung | float | ≥ 0 | Lượng đã dùng, gồm cả phần giữ chỗ của đề xuất (m³) |

### Bảng 4.12 Mô tả DeXuatTuoi (de_xuat_tuoi)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_de_xuat | nvarchar(255) | PK | Mã đề xuất, ví dụ ĐX-2026-1006-014 |
| 2 | ma_lich | nvarchar(255) | FK → lich_mua_vu | Lịch mùa vụ được đề xuất |
| 3 | ma_vung | nvarchar(255) | FK → vung_tuoi | Vùng để trừ hạn mức |
| 4 | ngay_lap | date | NOT NULL | Ngày lập đề xuất |
| 5 | giai_doan | nvarchar(255) |  | Giai đoạn sinh trưởng tại ngày lập |
| 6 | luong_goc | float | ≥ 0 | Lượng hệ thống tính theo QĐ6 (m³) |
| 7 | luong_ap_dung | float | ≥ 0 | Lượng áp dụng sau ghi đè hoặc duyệt (m³) |
| 8 | luong_giu_cho | float | ≥ 0 | Phần đã giữ chỗ trong hạn mức (m³) |
| 9 | khung_gio | nvarchar(255) |  | Khung giờ tưới, ví dụ 05:00–08:00 |
| 10 | hieu_luc_den | datetime2 |  | Thời hạn hiệu lực của đề xuất (QĐ6) |
| 11 | can_cu | nvarchar(255) |  | Số đo và mưa dùng để tính, ví dụ "T-014 22% lúc 04:40" |
| 12 | can_cu_so | nvarchar(255) |  | Độ ẩm căn cứ từng thửa, ví dụ "T-014=22.0;T-015=22.0"; dùng để so mức lệch thay thế (QĐ6) |
| 13 | ds_thua | nvarchar(255) |  | Các thửa trong phạm vi áp dụng |
| 14 | trang_thai | nvarchar(255) | ∈ {Không cần tưới, Chờ xác nhận, Đã xác nhận, Chờ duyệt, Đã ghi đè, Đã thực hiện, Hết hiệu lực, Bị thay thế} | Trạng thái đề xuất |
| 15 | luong_thuc_te | float | NULL được, ≥ 0 | Lượng nông hộ tưới thực tế (m³) |

### Bảng 4.13 Mô tả DeXuatThua (de_xuat_thua)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK, tự tăng | Mã dòng |
| 2 | ma_de_xuat | nvarchar(255) | FK → de_xuat_tuoi | Đề xuất |
| 3 | ma_thua | nvarchar(255) | FK → thua_ruong | Thửa được tưới |

### Bảng 4.14 Mô tả PhieuGhiDe (phieu_ghi_de)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | ma_phieu | nvarchar(255) | PK | Mã phiếu, ví dụ PGĐ-001 |
| 2 | ma_de_xuat | nvarchar(255) | FK → de_xuat_tuoi | Đề xuất bị ghi đè |
| 3 | nguoi_ghi_de | nvarchar(255) | NOT NULL | Họ tên nông hộ ghi đè |
| 4 | thoi_diem | datetime2 | NOT NULL | Thời điểm ghi đè |
| 5 | ly_do | nvarchar(255) | ∈ danh sách lý do | Loại lý do, ví dụ Nắng nóng bất thường |
| 6 | mo_ta | nvarchar(255) | ≥ 15 ký tự (QĐ8) | Mô tả chi tiết lý do |
| 7 | luong_goc | float | ≥ 0 | Lượng đề xuất gốc (m³) |
| 8 | luong_moi | float | ≥ 0 | Lượng nông hộ xin (m³) |
| 9 | trang_thai | nvarchar(255) | ∈ {Đã áp dụng, Chờ duyệt, Đã duyệt, Điều chỉnh, Từ chối} | Trạng thái phiếu |
| 10 | luong_duyet | float | NULL được, ≤ gốc + còn lại (QĐ9) | Lượng được duyệt (m³) |
| 11 | y_kien | nvarchar(255) | Bắt buộc khi Điều chỉnh/Từ chối (QĐ9) | Ý kiến của quản lý tổ |
| 12 | nguoi_duyet | nvarchar(255) |  | Họ tên người duyệt |

### Bảng 4.15 Mô tả QuyDinh (quy_dinh)

| STT | Tên thuộc tính | Kiểu dữ liệu | Ràng buộc | Diễn giải |
|---|---|---|---|---|
| 1 | id | bigint | PK | Luôn bằng 1 |
| 2 | gio_lap | int | 0–23 | Giờ lập đề xuất, mặc định 5 (QĐ6) |
| 3 | han_so_do_gio | int | > 0 | Số đo cũ hơn số giờ này thì không dùng, mặc định 6 (QĐ4) |
| 4 | hieu_luc_gio | int | > 0 | Thời hạn hiệu lực đề xuất, mặc định 24 giờ (QĐ6) |
| 5 | muc_lech_thay_the | float | 0–100 | Số đo mới lệch quá mức này (điểm %) thì thay đề xuất, mặc định 5 (QĐ6) |
| 6 | canh_bao_phan_tram | float | 0–100 | Ngưỡng cảnh báo hạn mức, mặc định 20% (QĐ5) |
| 7 | he_so_mua | float | 0–1 | Hệ số mưa hữu hiệu, mặc định 0,8 (QĐ4) |
| 8 | mo_ta_toi_thieu | int | > 0 | Số ký tự tối thiểu của mô tả ghi đè, mặc định 15 (QĐ8) |
| 9 | hs_nho_giot | float | 0–1 | Hiệu suất tưới nhỏ giọt, mặc định 0,9 |
| 10 | hs_phun_mua | float | 0–1 | Hiệu suất tưới phun mưa, mặc định 0,75 |
| 11 | hs_ranh | float | 0–1 | Hiệu suất tưới rãnh, mặc định 0,6 |
| 12 | lan_lap_gan_nhat | datetime2 | NULL được | Mốc của lần lập đề xuất gần nhất; số đo cũ hơn mốc này 6 giờ hiển thị Quá hạn (QĐ4) |
