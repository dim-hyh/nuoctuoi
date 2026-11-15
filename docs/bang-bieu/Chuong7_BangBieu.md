# Bảng biểu chương 7

### Bảng 7.1 Môi trường phát triển và triển khai

| Hạng mục | Môi trường phát triển | Môi trường triển khai |
|---|---|---|
| Hệ điều hành | Windows 11 | Máy tính đặt tại văn phòng hợp tác xã chạy Windows 10/11 |
| Công cụ lập trình | Visual Studio Code (Extension Pack for Java) | Không cần |
| Nền tảng chạy | JDK 25, Maven Wrapper | JDK 25 (chạy tệp .jar hoặc lệnh mvnw spring-boot:run) |
| Cơ sở dữ liệu | SQL Server 2022 Express, SQL Server Management Studio | SQL Server Express, cơ sở dữ liệu NuocTuoi, cổng 14330 |
| Máy chủ web | Tomcat nhúng trong Spring Boot, cổng 8080 | Tomcat nhúng, cổng 8080 |
| Người dùng truy cập | Trình duyệt Chrome tại http://localhost:8080 | Trình duyệt trên máy tính, điện thoại trong mạng nội bộ của HTX |
| Quản lý mã nguồn | Git, GitHub | Mã nguồn công khai tại: [dán đường dẫn repository GitHub] |

### Bảng 7.2 Kết quả thực hiện các chức năng

| STT | Chức năng | Kết quả |
|---|---|---|
| 1 | Đăng nhập, đăng xuất, đổi mật khẩu, phân quyền 5 vai trò, mã hóa mật khẩu BCrypt | Hoàn thành |
| 2 | Quản lý thửa ruộng, cây trồng và giai đoạn sinh trưởng, lịch mùa vụ | Hoàn thành |
| 3 | Ghi nhận độ ẩm đất và dự báo mưa | Hoàn thành |
| 4 | Cấp và bổ sung hạn mức nước, cảnh báo khi còn dưới 20% | Hoàn thành |
| 5 | Lập đề xuất tưới theo công thức QĐ6, giữ chỗ hạn mức | Hoàn thành (lập bằng nút bấm, thay cho bộ hẹn giờ 05:00) |
| 6 | Xác nhận, ghi đè có lý do, phê duyệt ghi đè vượt hạn mức, ghi lượng thực tế | Hoàn thành |
| 7 | Đề xuất hết hiệu lực sau 24 giờ, thay thế đề xuất khi số đo mới lệch quá 5 điểm %, đánh dấu số đo Hợp lệ / Quá hạn | Hoàn thành |
| 8 | Báo cáo sử dụng nước theo vùng và kỳ | Hoàn thành |
| 9 | Thay đổi quy định, khôi phục dữ liệu mẫu, quản lý tài khoản | Hoàn thành |

### Bảng 7.3 Kết quả kiểm thử

| STT | Nhóm kiểm thử | Số trường hợp | Kết quả |
|---|---|---|---|
| 1 | Đăng nhập, mật khẩu BCrypt, chống giả mạo form (CSRF), tải ảnh và giao diện | 11 | Đạt |
| 2 | Phân quyền 5 vai trò: vào được trang của mình, bị chặn trang khác | 10 | Đạt |
| 3 | Lập đề xuất 33,3 m³ → ghi đè 45 m³ → duyệt điều chỉnh 41,3 m³ → ghi thực tế (QĐ6, QĐ8, QĐ9) | 8 | Đạt |
| 4 | Ràng buộc nhập liệu: QĐ1, QĐ3, QĐ4, trùng đề xuất, mật khẩu, tài khoản trùng | 8 | Đạt |
| 5 | Đề xuất bị thay thế, hết hiệu lực, số đo quá hạn, Ban quản trị thay đổi quy định (QĐ4, QĐ6, QĐ11) | 7 | Đạt |

### Bảng 7.4 Phân chia công việc

| STT | Công việc | Người thực hiện | Mức độ hoàn thành |
|---|---|---|---|
| 1 | Khảo sát hiện trạng, xác định yêu cầu, biểu mẫu và quy định (chương 1) | Nguyễn Thị Diễm Huỳnh | 100% |
| 2 | Mô hình usecase và đặc tả (chương 2) | Nguyễn Thị Diễm Huỳnh | 100% |
| 3 | Phân tích: sơ đồ lớp, trạng thái, hoạt động, tuần tự (chương 3) | Nguyễn Thị Diễm Huỳnh | 100% |
| 4 | Thiết kế dữ liệu, kiến trúc, giao diện (chương 4–6) | Nguyễn Thị Diễm Huỳnh | 100% |
| 5 | Lập trình web, kiểm thử, đưa mã nguồn lên GitHub | Nguyễn Thị Diễm Huỳnh | 100% |
| 6 | Viết báo cáo | Nguyễn Thị Diễm Huỳnh | 100% |
