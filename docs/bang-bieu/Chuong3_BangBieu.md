# Bảng biểu chương 3

### Bảng 3.1 Danh sách các lớp đối tượng

| STT | Tên lớp | Ý nghĩa |
|---|---|---|
| 1 | TaiKhoan | Tài khoản đăng nhập của người dùng |
| 2 | NguoiDung | Người sử dụng hệ thống: nông hộ, cán bộ kỹ thuật, quản lý tổ thủy nông, ban quản trị, quản trị hệ thống |
| 3 | VungTuoi | Vùng tưới do một tổ thủy nông quản lý, gồm nhiều thửa dùng chung nguồn nước |
| 4 | ThuaRuong | Thửa ruộng canh tác (BM1) |
| 5 | CayTrong | Loại cây trồng được khai báo (BM2) |
| 6 | GiaiDoanSinhTruong | Một giai đoạn sinh trưởng của cây cùng ngưỡng ẩm và độ sâu rễ (BM2) |
| 7 | LichMuaVu | Lịch canh tác của một thửa theo ngày gieo (BM3) |
| 8 | ChiTietLichMuaVu | Một dòng của lịch mùa vụ, ứng với một giai đoạn |
| 9 | SoLieuDongRuong | Lớp trừu tượng cho số liệu đo tại đồng ruộng (BM4) |
| 10 | SoDoDoAm | Số đo độ ẩm đất của một thửa, kế thừa SoLieuDongRuong |
| 11 | DuBaoMua | Lượng mưa dự báo của một vùng, kế thừa SoLieuDongRuong |
| 12 | HanMucNuoc | Lượng nước tối đa cấp cho một vùng trong một kỳ (BM5) |
| 13 | DeXuatTuoi | Đề xuất tưới hằng ngày cho một hoặc nhiều thửa (BM6) |
| 14 | PhamViApDung | Phạm vi áp dụng của đề xuất: thửa, thời gian hiệu lực và căn cứ tính |
| 15 | PhieuGhiDe | Yêu cầu ghi đè đề xuất của nông hộ (BM7) |
| 16 | LyDoGhiDe | Danh mục loại lý do ghi đè, dùng để thống kê ở BM8 |
| 17 | PheDuyetGhiDe | Kết quả duyệt phiếu ghi đè vượt hạn mức (BM7) |
| 18 | QuyDinh | Tham số quy định có thể thay đổi (QĐ11) |

### Bảng 3.2 Danh sách các quan hệ giữa các lớp

| STT | Lớp 1 | Lớp 2 | Loại quan hệ | Bản số | Ý nghĩa |
|---|---|---|---|---|---|
| 1 | NguoiDung | TaiKhoan | Kết hợp | 1 – 1 | Mỗi người dùng có một tài khoản |
| 2 | NguoiDung | ThuaRuong | Kết hợp | 1 – 0..* | Nông hộ sở hữu nhiều thửa |
| 3 | VungTuoi | ThuaRuong | Kết tập | 1 – 1..* | Vùng tưới gồm nhiều thửa |
| 4 | VungTuoi | HanMucNuoc | Kết hợp | 1 – 0..* | Vùng được cấp hạn mức theo từng kỳ |
| 5 | VungTuoi | DuBaoMua | Kết hợp | 1 – 0..* | Mưa dự báo ghi theo vùng |
| 6 | ThuaRuong | SoDoDoAm | Kết hợp | 1 – 0..* | Độ ẩm đo theo thửa |
| 7 | SoDoDoAm, DuBaoMua | SoLieuDongRuong | Tổng quát hóa | — | Hai loại số liệu kế thừa lớp số liệu đồng ruộng |
| 8 | CayTrong | GiaiDoanSinhTruong | Hợp thành | 1 – 1..* | Cây trồng gồm các giai đoạn sinh trưởng |
| 9 | ThuaRuong | LichMuaVu | Kết hợp | 1 – 0..* | Thửa có các lịch mùa vụ qua từng vụ |
| 10 | CayTrong | LichMuaVu | Kết hợp | 1 – 0..* | Lịch mùa vụ trồng một loại cây |
| 11 | LichMuaVu | ChiTietLichMuaVu | Hợp thành | 1 – 1..* | Lịch gồm các dòng chi tiết theo giai đoạn |
| 12 | GiaiDoanSinhTruong | ChiTietLichMuaVu | Kết hợp | 1 – 0..* | Mỗi dòng chi tiết ứng với một giai đoạn |
| 13 | DeXuatTuoi | ThuaRuong | Kết hợp | 0..* – 1..* | Đề xuất áp dụng cho một hoặc nhiều thửa |
| 14 | DeXuatTuoi | HanMucNuoc | Kết hợp | 0..* – 1 | Lượng đề xuất trừ vào hạn mức của vùng |
| 15 | DeXuatTuoi | PhamViApDung | Hợp thành | 1 – 1 | Mỗi đề xuất có một phạm vi áp dụng |
| 16 | PhamViApDung | SoDoDoAm | Kết hợp | 0..* – 1 | Phạm vi ghi số đo độ ẩm làm căn cứ |
| 17 | PhamViApDung | GiaiDoanSinhTruong | Kết hợp | 0..* – 1 | Phạm vi ghi giai đoạn áp dụng |
| 18 | DeXuatTuoi | PhieuGhiDe | Kết hợp | 1 – 0..* | Đề xuất có thể bị ghi đè nhiều lần |
| 19 | NguoiDung | PhieuGhiDe | Kết hợp | 1 – 0..* | Nông hộ lập phiếu ghi đè |
| 20 | PhieuGhiDe | LyDoGhiDe | Kết hợp | 0..* – 1 | Mỗi phiếu có một loại lý do |
| 21 | PhieuGhiDe | PheDuyetGhiDe | Kết hợp | 1 – 0..1 | Phiếu vượt hạn mức có một kết quả duyệt |
| 22 | NguoiDung | PheDuyetGhiDe | Kết hợp | 1 – 0..* | Quản lý tổ duyệt phiếu ghi đè |
| 23 | DeXuatTuoi | QuyDinh | Phụ thuộc «use» | — | Đề xuất dùng tham số quy định khi lập |

### Bảng 3.3 Danh sách phương thức lớp TaiKhoan

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | dangNhap() | bool | Kiểm tra tên đăng nhập và mật khẩu, mở phiên làm việc |
| 2 | dangXuat() | void | Kết thúc phiên làm việc |
| 3 | doiMatKhau(cu, moi) | bool | Kiểm tra mật khẩu cũ và lưu mật khẩu mới |

### Bảng 3.4 Danh sách phương thức lớp NguoiDung

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | layThuaSoHuu() | List | Lấy danh sách thửa mà nông hộ sở hữu |

### Bảng 3.5 Danh sách phương thức lớp VungTuoi

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | layDanhSachThua() | List | Lấy các thửa thuộc vùng |
| 2 | hanMucHienHanh(ngay) | HanMucNuoc | Lấy kỳ hạn mức đang áp dụng tại ngày cho trước |

### Bảng 3.6 Danh sách phương thức lớp ThuaRuong

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | tinhHieuSuat() | float | Trả về hiệu suất theo hình thức tưới (QĐ1) |
| 2 | kiemTraHopLe() | bool | Kiểm tra mã thửa, diện tích và các thông tin bắt buộc (QĐ1) |

### Bảng 3.7 Danh sách phương thức lớp CayTrong

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | tongThoiGian() | int | Tổng số ngày của các giai đoạn sinh trưởng |

### Bảng 3.8 Danh sách phương thức lớp GiaiDoanSinhTruong

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | kiemTraHopLe() | bool | Kiểm tra các ràng buộc theo QĐ2 |

### Bảng 3.9 Danh sách phương thức lớp LichMuaVu

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | tinhLich() | void | Tính từ ngày – đến ngày của từng giai đoạn từ ngày gieo (QĐ3) |
| 2 | giaiDoanHienTai(ngay) | GiaiDoanSinhTruong | Xác định giai đoạn của thửa tại ngày cho trước |

### Bảng 3.10 Danh sách phương thức lớp SoLieuDongRuong

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | laQuaHan(moc) | bool | Số liệu cũ hơn 24 giờ so với mốc lập đề xuất là quá hạn (QĐ4) |
| 2 | kiemTraHopLe() | bool | Kiểm tra giá trị (lớp con định nghĩa lại) |

### Bảng 3.11 Danh sách phương thức lớp SoDoDoAm

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | kiemTraHopLe() | bool | Độ ẩm phải trong khoảng 0–100% |

### Bảng 3.12 Danh sách phương thức lớp DuBaoMua

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | kiemTraHopLe() | bool | Lượng mưa phải trong khoảng 0–500 mm |

### Bảng 3.13 Danh sách phương thức lớp HanMucNuoc

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | conLai() | float | hanMuc − daDung |
| 2 | trangThai() | String | Bình thường / Sắp hết (còn dưới 20%) / Đã hết (còn 0) / Đã kết thúc |
| 3 | giuCho(luong) | void | Giữ chỗ lượng nước cho đề xuất đã lập |
| 4 | ghiNhanThucTe(luong) | void | Thay lượng giữ chỗ bằng lượng thực tế |

### Bảng 3.14 Danh sách phương thức lớp DeXuatTuoi

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | tinhLuongNuoc() | float | V = (ngưỡng trên − độ ẩm)/100 × độ sâu rễ × diện tích / hiệu suất (QĐ6) |
| 2 | xacNhan() | void | Chuyển trạng thái sang Đã xác nhận |
| 3 | ghiThucTe(luong) | void | Ghi lượng thực tế (QĐ7) |
| 4 | kiemTraHieuLuc() | bool | Kiểm tra đề xuất còn hiệu lực |
| 5 | thayThe() | void | Chuyển sang Bị thay thế khi có số đo mới lệch quá 5 điểm % |

### Bảng 3.15 Danh sách phương thức lớp PhamViApDung

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | moTa() | String | Trả về nội dung phạm vi để hiển thị trên BM6 |

### Bảng 3.16 Danh sách phương thức lớp PhieuGhiDe

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | kiemTra() | bool | Kiểm tra phạm vi, lý do, mô tả (QĐ8) |
| 2 | vuotHanMuc(conLai) | bool | Phần tăng thêm có vượt hạn mức còn lại không |
| 3 | gui() | void | Gửi phiếu; nếu vượt hạn mức thì chuyển Chờ duyệt |

### Bảng 3.17 Danh sách phương thức lớp PheDuyetGhiDe

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | duyet() | bool | Kiểm tra theo QĐ9 và cập nhật trạng thái phiếu, đề xuất |

### Bảng 3.18 Danh sách phương thức lớp QuyDinh

| STT | Tên phương thức | Kết quả trả về | Ý nghĩa |
|---|---|---|---|
| 1 | capNhat(giaTri, ngay) | void | Cập nhật giá trị và ngày áp dụng |

### Bảng 3.19 Danh sách trạng thái – Đề xuất tưới

| STT | Trạng thái | Ý nghĩa |
|---|---|---|
| 1 | Còn hiệu lực | Trạng thái cha, đề xuất đang trong thời hạn 24 giờ, gồm Chờ xác nhận và Đã xác nhận |
| 2 | Chờ xác nhận | Đề xuất vừa lập, cần tưới, đang chờ nông hộ xác nhận |
| 3 | Đã xác nhận | Nông hộ đã đồng ý thực hiện đề xuất |
| 4 | Không cần tưới | Độ ẩm đã đạt ngưỡng, lượng đề xuất 0 m³ |
| 5 | Đã ghi đè | Đề xuất đã được thay đổi bằng phiếu ghi đè hợp lệ |
| 6 | Chờ duyệt | Phiếu ghi đè vượt hạn mức, đang chờ quản lý tổ thủy nông duyệt |
| 7 | Bị thay thế | Có số đo mới lệch nhiều nên đề xuất bị thay bằng đề xuất mới |
| 8 | Hết hiệu lực | Quá 24 giờ mà đề xuất chưa được thực hiện |

### Bảng 3.20 Danh sách chuyển trạng thái – Đề xuất tưới

| STT | Trạng thái nguồn | Sự kiện [điều kiện] / hành động | Trạng thái đích |
|---|---|---|---|
| 1 | (Bắt đầu) | lập lúc 05:00 [độ ẩm < ngưỡng dưới] | Chờ xác nhận |
| 2 | (Bắt đầu) | lập lúc 05:00 [độ ẩm ≥ ngưỡng dưới] | Không cần tưới |
| 3 | Chờ xác nhận | xacNhan() | Đã xác nhận |
| 4 | Đã xác nhận | ghiThucTe(luong) | (Kết thúc) |
| 5 | Còn hiệu lực | có số đo mới lệch > 5 điểm % | Bị thay thế |
| 6 | Còn hiệu lực | gui() [không vượt hạn mức] | Đã ghi đè |
| 7 | Còn hiệu lực | gui() [vượt hạn mức] | Chờ duyệt |
| 8 | Còn hiệu lực | quá 24 giờ | Hết hiệu lực |
| 9 | Không cần tưới | quá 24 giờ | Hết hiệu lực |
| 10 | Chờ duyệt | duyet() [chấp nhận / điều chỉnh] | Đã ghi đè |
| 11 | Chờ duyệt | duyet() [từ chối] | Còn hiệu lực |
| 12 | Đã ghi đè | ghiThucTe(luong) | (Kết thúc) |
| 13 | Bị thay thế | — | (Kết thúc) |
| 14 | Hết hiệu lực | — | (Kết thúc) |

### Bảng 3.21 Danh sách trạng thái – Phiếu ghi đè

| STT | Trạng thái | Ý nghĩa |
|---|---|---|
| 1 | Đang lập | Nông hộ đang nhập hành động, giá trị mới, phạm vi thửa, loại lý do và mô tả |
| 2 | Đã ghi đè | Phiếu hợp lệ, không vượt hạn mức, được áp dụng ngay cho đề xuất |
| 3 | Chờ duyệt | Phiếu hợp lệ nhưng phần tăng thêm vượt hạn mức còn lại của vùng |
| 4 | Đã duyệt | Quản lý tổ chấp nhận hoặc điều chỉnh lượng duyệt |
| 5 | Từ chối | Quản lý tổ từ chối, đề xuất gốc được giữ nguyên |

### Bảng 3.22 Danh sách chuyển trạng thái – Phiếu ghi đè

| STT | Trạng thái nguồn | Sự kiện [điều kiện] / hành động | Trạng thái đích |
|---|---|---|---|
| 1 | (Bắt đầu) | nhấn "Ghi đè..." trên BM6 | Đang lập |
| 2 | Đang lập | gui() [kiemTra() = false] / báo lỗi | Đang lập |
| 3 | Đang lập | gui() [hợp lệ, không vượt hạn mức] | Đã ghi đè |
| 4 | Đang lập | gui() [hợp lệ, vượt hạn mức] | Chờ duyệt |
| 5 | Chờ duyệt | duyet() [chấp nhận / điều chỉnh] | Đã duyệt |
| 6 | Chờ duyệt | duyet() [từ chối, có ý kiến] | Từ chối |
| 7 | Đã ghi đè | áp dụng cho đề xuất | (Kết thúc) |
| 8 | Đã duyệt | — | (Kết thúc) |
| 9 | Từ chối | — | (Kết thúc) |

### Bảng 3.23 Danh sách trạng thái – Hạn mức nước

| STT | Trạng thái | Ý nghĩa |
|---|---|---|
| 1 | Bình thường | Phần còn lại từ 20% hạn mức trở lên |
| 2 | Sắp hết | Phần còn lại dưới 20% hạn mức (QĐ5), hiển thị màu đỏ để cảnh báo |
| 3 | Đã hết | Lượng đã dùng bằng hạn mức, còn lại 0 m³, hiển thị màu đỏ; muốn tưới tiếp phải bổ sung nước |
| 4 | Đã kết thúc | Đã qua ngày kết thúc kỳ, chỉ dùng để xem báo cáo |

### Bảng 3.24 Danh sách chuyển trạng thái – Hạn mức nước

| STT | Trạng thái nguồn | Sự kiện [điều kiện] / hành động | Trạng thái đích |
|---|---|---|---|
| 1 | (Bắt đầu) | Thêm kỳ hạn mức [hợp lệ QĐ5] | Bình thường |
| 2 | Bình thường | giuCho() / ghiNhanThucTe() [conLai ≥ 20% hanMuc] | Bình thường |
| 3 | Bình thường | giuCho() / ghiNhanThucTe() [conLai < 20% hanMuc] | Sắp hết |
| 4 | Sắp hết | Sửa: tăng hạn mức [conLai ≥ 20% hanMuc] | Bình thường |
| 5 | Bình thường | qua ngày denNgay | Đã kết thúc |
| 6 | Sắp hết | qua ngày denNgay | Đã kết thúc |
| 7 | Sắp hết | giuCho() / ghiNhanThucTe() [conLai = 0] | Đã hết |
| 8 | Đã hết | Bổ sung nước [conLai < 20% hanMuc] | Sắp hết |
| 9 | Đã hết | Bổ sung nước [conLai ≥ 20% hanMuc] | Bình thường |
| 10 | Đã hết | qua ngày denNgay | Đã kết thúc |
| 11 | Bình thường | Xóa [daDung = 0] | (Kết thúc) |
| 12 | Đã kết thúc | — | (Kết thúc) |

### Bảng 3.25 Danh sách trạng thái – Lịch mùa vụ

| STT | Trạng thái | Ý nghĩa |
|---|---|---|
| 1 | Chờ gieo | Lịch đã lập nhưng chưa tới ngày gieo |
| 2 | Đang canh tác | Trạng thái cha, cây đang sinh trưởng trên thửa |
| 3 | Cây con | Giai đoạn 1, 10 ngày |
| 4 | Phát triển thân lá | Giai đoạn 2, 15 ngày |
| 5 | Ra hoa | Giai đoạn 3, 12 ngày |
| 6 | Nuôi trái | Giai đoạn 4, 20 ngày |
| 7 | Đã thu hoạch | Đã qua ngày thu hoạch dự kiến, kết thúc vụ |

### Bảng 3.26 Danh sách chuyển trạng thái – Lịch mùa vụ

| STT | Trạng thái nguồn | Sự kiện [điều kiện] / hành động | Trạng thái đích |
|---|---|---|---|
| 1 | (Bắt đầu) | tinhLich() và Lưu [ngày hiện tại < ngayGieo] | Chờ gieo |
| 2 | Chờ gieo | đến ngayGieo | Đang canh tác (Cây con) |
| 3 | Cây con | sau 10 ngày | Phát triển thân lá |
| 4 | Phát triển thân lá | sau 15 ngày | Ra hoa |
| 5 | Ra hoa | sau 12 ngày | Nuôi trái |
| 6 | Đang canh tác | qua ngayThuHoach (sau 20 ngày nuôi trái) | Đã thu hoạch |
| 7 | Đã thu hoạch | — | (Kết thúc) |

### Bảng 3.27 Danh sách trạng thái – Số liệu đồng ruộng

| STT | Trạng thái | Ý nghĩa |
|---|---|---|
| 1 | Hợp lệ | Số liệu đạt kiểm tra QĐ4 và còn mới, được dùng làm căn cứ lập đề xuất |
| 2 | Quá hạn | Số liệu cũ hơn 24 giờ so với mốc 05:00, không còn dùng để lập đề xuất |

### Bảng 3.28 Danh sách chuyển trạng thái – Số liệu đồng ruộng

| STT | Trạng thái nguồn | Sự kiện [điều kiện] / hành động | Trạng thái đích |
|---|---|---|---|
| 1 | (Bắt đầu) | Thêm / Nhập từ CSV | (Rẽ nhánh kiểm tra) |
| 2 | (Rẽ nhánh kiểm tra) | [kiemTraHopLe() = true] / lưu | Hợp lệ |
| 3 | (Rẽ nhánh kiểm tra) | [kiemTraHopLe() = false] / báo lỗi | (Kết thúc) |
| 4 | Hợp lệ | cũ hơn 24 giờ so với mốc 05:00 | Quá hạn |
| 5 | Hợp lệ | Xóa [nhập nhầm, chưa dùng lập đề xuất] | (Kết thúc) |
| 6 | Quá hạn | Xóa [nhập nhầm] | (Kết thúc) |
