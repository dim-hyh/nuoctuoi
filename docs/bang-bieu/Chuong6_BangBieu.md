# Bảng biểu chương 6

### Bảng 6.1 Danh sách các màn hình

| STT | Tên màn hình | Đường dẫn | Người sử dụng | Ý nghĩa |
|---|---|---|---|---|
| 1 | Đăng nhập | /dang-nhap | Tất cả | Nhập tên đăng nhập và mật khẩu để vào hệ thống (UC01). |
| 2 | Trang chủ | / | Tất cả | Màn hình chính sau khi đăng nhập: lời chào, số việc đang chờ và tình hình hạn mức nước. |
| 3 | Đổi mật khẩu | /doi-mat-khau | Tất cả | Người dùng tự đổi mật khẩu của mình (UC03). |
| 4 | Quản lý tài khoản | /tai-khoan | Quản trị hệ thống | Thêm, đặt lại mật khẩu và xóa tài khoản người dùng. |
| 5 | Quản lý thửa ruộng | /thua-ruong | Cán bộ kỹ thuật | Thêm và xóa hồ sơ thửa ruộng (UC04, BM1). |
| 6 | Cây trồng và giai đoạn sinh trưởng | /cay-trong | Cán bộ kỹ thuật | Khai báo cây trồng và các giai đoạn sinh trưởng (UC05, BM2). |
| 7 | Lập lịch mùa vụ | /lich-mua-vu | Cán bộ kỹ thuật | Lập lịch canh tác cho một hoặc nhiều thửa (UC06, BM3). |
| 8 | Số liệu đồng ruộng | /so-lieu | Cán bộ kỹ thuật | Ghi nhận độ ẩm đất và dự báo mưa (UC07, BM4). |
| 9 | Hạn mức nước | /han-muc | Quản lý tổ thủy nông | Cấp hạn mức nước theo kỳ và bổ sung nước (UC08, BM5). |
| 10 | Đề xuất tưới | /de-xuat | Cán bộ kỹ thuật, Nông hộ, Quản lý tổ | Xem danh sách đề xuất; cán bộ lập đề xuất, nông hộ xác nhận và ghi thực tế (UC09, UC10, UC13, BM6). |
| 11 | Ghi đè đề xuất | /de-xuat/ghi-de | Nông hộ | Nông hộ xin tưới khác lượng đề xuất, có lý do (UC11, BM7). |
| 12 | Phê duyệt ghi đè | /phe-duyet | Quản lý tổ thủy nông | Danh sách phiếu ghi đè (UC12). |
| 13 | Duyệt phiếu ghi đè | /phe-duyet/duyet | Quản lý tổ thủy nông | Ra quyết định cho một phiếu ghi đè vượt hạn mức (UC12, BM7). |
| 14 | Báo cáo sử dụng nước | /bao-cao | Quản lý tổ thủy nông, Ban quản trị | Báo cáo lượng nước theo vùng và kỳ (UC14, BM8). |
| 15 | Thay đổi quy định | /quy-dinh | Ban quản trị, Quản trị hệ thống | Sửa các tham số quy định (UC15, QĐ11). |
| 16 | Không có quyền truy cập | /khong-co-quyen | Tất cả | Hiện ra khi người dùng mở chức năng không thuộc vai trò của mình. |

### Bảng 6.2 Thành phần màn hình đăng nhập

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Tên đăng nhập | Ô nhập | Bắt buộc nhập. |
| 2 | Mật khẩu | Ô nhập mật khẩu | Hiển thị dạng ẩn (•••). |
| 3 | Nút con mắt | Nút | Bấm để hiện/ẩn mật khẩu. |
| 4 | Đăng nhập | Nút | Kiểm tra tài khoản; sai thì báo "Sai tên đăng nhập hoặc mật khẩu", đúng thì chuyển tới trang chủ. |

### Bảng 6.3 Thành phần màn hình trang chủ

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Đầu trang | Vùng hiển thị | Tên hệ thống, ngày hiện tại, họ tên và vai trò người dùng. |
| 2 | Đăng xuất | Nút | Hỏi xác nhận trước khi đăng xuất (UC02). |
| 3 | Menu | Thanh liên kết | Chỉ hiện các chức năng đúng vai trò người dùng; mục đang mở được tô đậm. |
| 4 | Số việc chờ | Nhãn | Số đề xuất chờ xác nhận và số phiếu ghi đè chờ duyệt. |
| 5 | Bảng hạn mức | Bảng | Vùng, kỳ, hạn mức, đã dùng, còn lại, trạng thái (đỏ khi "Sắp hết" hoặc "Đã hết"). |

### Bảng 6.4 Thành phần màn hình đổi mật khẩu

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Mật khẩu cũ | Ô nhập mật khẩu | Phải đúng mật khẩu hiện tại. |
| 2 | Mật khẩu mới | Ô nhập mật khẩu | Ít nhất 3 ký tự. |
| 3 | Nhập lại | Ô nhập mật khẩu | Phải trùng mật khẩu mới. |
| 4 | Lưu | Nút | Lưu mật khẩu mới đã mã hóa BCrypt. |

### Bảng 6.5 Thành phần màn hình quản lý tài khoản

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Tên đăng nhập, Họ tên | Ô nhập | Tên đăng nhập không được trùng. |
| 2 | Vai trò | Danh sách chọn | Nông hộ, Cán bộ kỹ thuật, Quản lý tổ thủy nông, Ban quản trị, Quản trị hệ thống. |
| 3 | Mật khẩu | Ô nhập | Mật khẩu ban đầu, ít nhất 3 ký tự. |
| 4 | Thêm tài khoản | Nút | Tạo tài khoản mới. |
| 5 | Bảng tài khoản | Bảng | Danh sách tài khoản theo vai trò. |
| 6 | Đặt lại | Nút | Đặt mật khẩu mới cho tài khoản, có hộp xác nhận. |
| 7 | Xóa | Nút | Hỏi xác nhận trước khi xóa; không xóa được tài khoản đang đăng nhập. |

### Bảng 6.6 Thành phần màn hình quản lý thửa ruộng

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Mã thửa, Chủ hộ | Ô nhập | Bắt buộc; mã thửa không trùng. |
| 2 | Vùng tưới | Danh sách chọn | Vùng chứa thửa. |
| 3 | Diện tích (m²) | Ô nhập số | Phải lớn hơn 0 (QĐ1). |
| 4 | Loại đất | Ô nhập | Ví dụ: đất thịt, đất cát. |
| 5 | Hình thức tưới | Danh sách chọn | Nhỏ giọt, Phun mưa, Rãnh. |
| 6 | Thêm thửa | Nút | Lưu thửa mới. |
| 7 | Bảng thửa ruộng | Bảng | Danh sách thửa; nút Xóa có hỏi xác nhận, không xóa được thửa đang có lịch mùa vụ. |

### Bảng 6.7 Thành phần màn hình cây trồng và giai đoạn sinh trưởng

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Chọn cây | Danh sách chọn | Chọn cây để xem các giai đoạn. |
| 2 | Bảng giai đoạn | Bảng | Tên giai đoạn, số ngày, ngưỡng ẩm dưới/trên, độ sâu rễ và tổng thời gian. |
| 3 | Thêm giai đoạn | Nhóm ô nhập + nút | Ngưỡng 0–100%, ngưỡng dưới < ngưỡng trên, độ sâu rễ 0,05–2,0 m (QĐ2). |
| 4 | Thêm cây | Nhóm ô nhập + nút | Mã cây và tên cây mới. |

### Bảng 6.8 Thành phần màn hình lập lịch mùa vụ

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Cây trồng | Danh sách chọn | Cây gieo trong vụ. |
| 2 | Ngày gieo | Ô chọn ngày | Ngày bắt đầu vụ. |
| 3 | Các thửa | Ô đánh dấu | Chọn ít nhất một thửa; thửa đang có vụ chưa thu hoạch thì không chọn được (QĐ3). |
| 4 | Lập lịch | Nút | Tạo lịch và tự tính ngày bắt đầu, kết thúc từng giai đoạn. |
| 5 | Bảng lịch | Bảng | Mỗi lịch hiển thị các giai đoạn với từ ngày, đến ngày, số ngày. |

### Bảng 6.9 Thành phần màn hình số liệu đồng ruộng

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Thửa, Thời điểm, Độ ẩm | Nhóm ô nhập | Độ ẩm 0–100% (QĐ4); thời điểm điền sẵn 04:40 sáng của ngày cần lập đề xuất. |
| 2 | Vùng, Ngày, Lượng mưa | Nhóm ô nhập | Lượng mưa dự báo (mm) của vùng theo ngày. |
| 3 | Lưu | Nút | Lưu từng loại số liệu. |
| 4 | Bảng số đo | Bảng | Các số đo đã ghi, mới nhất ở trên, kèm trạng thái Hợp lệ / Quá hạn (cũ hơn 6 giờ so với lần lập đề xuất gần nhất, QĐ4). |
| 5 | Thông báo thay thế | Nhãn | Khi số đo mới lệch quá 5 điểm % so với căn cứ, báo đề xuất cũ chuyển Bị thay thế và đề xuất mới được lập (QĐ6). |
| 6 | Bảng dự báo | Bảng | Các dự báo mưa đã ghi. |

### Bảng 6.10 Thành phần màn hình hạn mức nước

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Vùng, Từ ngày, Đến ngày, Hạn mức | Nhóm ô nhập | Hạn mức > 0, kỳ không trùng thời gian với kỳ khác cùng vùng (QĐ5). |
| 2 | Cấp hạn mức | Nút | Tạo kỳ hạn mức mới. |
| 3 | Bảng hạn mức | Bảng | Hạn mức, đã dùng (gồm giữ chỗ), còn lại, trạng thái; "Sắp hết" khi còn dưới 20%, "Đã hết" khi còn 0 m³. |
| 4 | Bổ sung nước | Ô nhập + nút | Cộng thêm lượng nước vào kỳ đang có, có hỏi xác nhận. |

### Bảng 6.11 Thành phần màn hình đề xuất tưới

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Ngày lập + Lập đề xuất | Ô chọn ngày + nút | Chỉ cán bộ kỹ thuật thấy; thay cho bộ hẹn giờ 05:00, thông báo kết quả từng lịch. |
| 2 | Bảng đề xuất | Bảng | Mã, ngày, thửa, giai đoạn, lượng (kèm lượng gốc nếu đã ghi đè), khung giờ, hiệu lực, căn cứ, trạng thái có màu (gồm Hết hiệu lực, Bị thay thế). |
| 3 | Xác nhận | Nút | Nông hộ đồng ý tưới theo đề xuất; đề xuất đã hết hiệu lực hoặc bị thay thế thì không xác nhận được. |
| 4 | Ghi đè | Liên kết | Mở màn hình ghi đè đề xuất. |
| 5 | Ghi thực tế | Ô nhập + nút | Nông hộ ghi lượng nước đã tưới, chuyển trạng thái "Đã thực hiện". |

### Bảng 6.12 Thành phần màn hình ghi đè đề xuất

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Thông tin đề xuất | Vùng hiển thị | Lượng gốc và phần hạn mức còn lại của vùng. |
| 2 | Lý do | Danh sách chọn | Nắng nóng bất thường, Cây có dấu hiệu héo, Máy bơm hỏng, Đất giữ nước kém, Lý do khác. |
| 3 | Mô tả cụ thể | Ô nhập nhiều dòng | Ít nhất 15 ký tự (QĐ8). |
| 4 | Lượng mới (m³) | Ô nhập số | Trong phần còn lại thì áp dụng ngay; vượt thì chờ quản lý duyệt. |
| 5 | Gửi / Hủy | Nút / liên kết | Gửi phiếu ghi đè hoặc quay lại. |

### Bảng 6.13 Thành phần màn hình phê duyệt ghi đè

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Bảng phiếu | Bảng | Mã phiếu, đề xuất, người ghi đè, lý do, mô tả, lượng gốc, lượng xin, trạng thái. |
| 2 | Duyệt | Liên kết | Chỉ hiện với phiếu "Chờ duyệt", mở màn hình duyệt phiếu. |

### Bảng 6.14 Thành phần màn hình duyệt phiếu ghi đè

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Thông tin phiếu | Vùng hiển thị | Đề xuất, người ghi đè, lý do, lượng gốc/xin và lượng được duyệt tối đa = gốc + còn lại (QĐ9). |
| 2 | Quyết định | Nút chọn | Chấp nhận, Điều chỉnh, Từ chối; tự chọn "Điều chỉnh" khi lượng xin vượt mức tối đa. |
| 3 | Lượng duyệt | Ô nhập số | Không vượt mức tối đa. |
| 4 | Ý kiến | Ô nhập nhiều dòng | Bắt buộc khi Điều chỉnh hoặc Từ chối (QĐ9). |
| 5 | Lưu quyết định / Hủy | Nút / liên kết | Lưu kết quả và cập nhật đề xuất. |

### Bảng 6.15 Thành phần màn hình báo cáo sử dụng nước

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Vùng | Danh sách chọn | Đổi vùng thì báo cáo tự tải lại. |
| 2 | Dòng tổng kỳ | Vùng hiển thị | Kỳ, hạn mức, đã dùng, còn lại và trạng thái. |
| 3 | Bảng chi tiết | Bảng | Đề xuất, ngày, thửa, lượng gốc, lượng áp dụng, thực tế, chênh lệch, có ghi đè hay không. |

### Bảng 6.16 Thành phần màn hình thay đổi quy định

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Bảng tham số | Nhóm ô nhập | Giờ lập, hạn số đo (giờ), hiệu lực (giờ), mức lệch thay thế (điểm %), ngưỡng cảnh báo (%), hệ số mưa, số ký tự mô tả tối thiểu, hiệu suất 3 hình thức tưới. |
| 2 | Lưu quy định | Nút | Lưu các giá trị mới, áp dụng cho lần lập đề xuất sau. |
| 3 | Khôi phục dữ liệu mẫu | Nút | Đưa dữ liệu về trạng thái mẫu ban đầu, có hỏi xác nhận. |

### Bảng 6.17 Thành phần màn hình không có quyền truy cập

| STT | Thành phần | Loại | Mô tả / xử lý |
|---|---|---|---|
| 1 | Thông báo | Nhãn | Báo người dùng không có quyền truy cập. |
| 2 | Về trang chủ | Liên kết | Quay về trang chủ. |
