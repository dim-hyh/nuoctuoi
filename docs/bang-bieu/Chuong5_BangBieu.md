# Bảng biểu chương 5

### Bảng 5.1 Các công nghệ sử dụng

| STT | Thành phần | Công nghệ / phiên bản | Vai trò trong hệ thống |
|---|---|---|---|
| 1 | Ngôn ngữ | Java 25 | Ngôn ngữ lập trình hướng đối tượng cài đặt các lớp đã phân tích ở chương 3. |
| 2 | Framework | Spring Boot 3.5.7 | Khung ứng dụng web, tự cấu hình máy chủ Tomcat nhúng, quản lý các đối tượng (bean). |
| 3 | Web MVC | Spring Web MVC | Tiếp nhận yêu cầu từ trình duyệt và điều phối tới các controller. |
| 4 | Giao diện | Thymeleaf, HTML, CSS | Sinh trang HTML từ dữ liệu do controller truyền sang. |
| 5 | Truy cập dữ liệu | Spring Data JPA, Hibernate | Ánh xạ lớp thực thể ↔ bảng, tự tạo bảng và câu truy vấn. |
| 6 | Bảo mật | Spring Security, BCrypt | Đăng nhập, đăng xuất, phân quyền theo 5 vai trò, chống giả mạo form (CSRF), mã hóa mật khẩu. |
| 7 | Cơ sở dữ liệu | Microsoft SQL Server 2022 Express | Lưu trữ 14 bảng dữ liệu (chương 4); kết nối bằng tài khoản Windows. |
| 8 | Quản lý thư viện | Maven (mvnw) | Tải thư viện, biên dịch và chạy ứng dụng. |

### Bảng 5.2 Các thành phần mã nguồn

| STT | Gói / thư mục | Thành phần chính | Nhiệm vụ |
|---|---|---|---|
| 1 | config | SecurityConfig | Cấu hình Spring Security: trang đăng nhập, đăng xuất, phân quyền đường dẫn theo vai trò, mã hóa BCrypt. |
| 2 | config | BienChung | Đưa thông tin người dùng đang đăng nhập và ngày hiện tại vào mọi trang. |
| 3 | config | DuLieuMau | Tạo dữ liệu mẫu khi cơ sở dữ liệu trống và khôi phục dữ liệu mẫu khi quản lý yêu cầu. |
| 4 | controller | HomeController | Trang chủ, trang đăng nhập, đổi mật khẩu, trang báo không có quyền (UC01–UC03). |
| 5 | controller | ThuaRuongController, CayTrongController, LichMuaVuController, SoLieuController | Quản lý thửa ruộng, cây trồng và giai đoạn, lịch mùa vụ, số liệu đồng ruộng (UC04–UC07). |
| 6 | controller | HanMucController | Cấp hạn mức nước và bổ sung nước cho kỳ (UC08). |
| 7 | controller | DeXuatController | Lập đề xuất, xác nhận, ghi đè, ghi lượng thực tế (UC09–UC11, UC13). |
| 8 | controller | PheDuyetController | Danh sách và duyệt phiếu ghi đè (UC12). |
| 9 | controller | BaoCaoController, QuyDinhController, TaiKhoanController | Báo cáo sử dụng nước (UC14), thay đổi quy định (UC15), quản lý tài khoản. |
| 10 | service | XuLyDeXuat | Tính lượng nước theo QĐ6, xác định giai đoạn sinh trưởng, kiểm tra số đo theo QĐ4, giữ chỗ hạn mức; chuyển đề xuất quá 24 giờ sang Hết hiệu lực, thay thế đề xuất khi số đo mới lệch quá 5 điểm %; xác nhận và ghi thực tế. |
| 11 | service | XuLyGhiDe | Ghi đè đề xuất theo QĐ8, tính lượng được duyệt tối đa và xử lý quyết định duyệt theo QĐ9. |
| 12 | service | MatKhau, DinhDang, KetQua | Mã hóa/kiểm tra mật khẩu, định dạng số và ngày, gói kết quả xử lý trả về controller. |
| 13 | repository | 14 interface (ThuaRuongRepository, DeXuatTuoiRepository ...) | Đọc/ghi dữ liệu từng bảng bằng Spring Data JPA. |
| 14 | model | 14 lớp thực thể và HangSo | Ánh xạ tới 14 bảng ở chương 4; HangSo chứa tên vai trò và các trạng thái. |
| 15 | dto | NguoiDungPhien, LichHienThi, KyBaoCao | Đối tượng trung gian để hiển thị (người dùng trong phiên, lịch theo giai đoạn, dòng báo cáo). |
| 16 | resources/templates | 17 trang .html | Giao diện các màn hình ở chương 6. |
| 17 | resources/static | css, img | Định dạng giao diện và hình ảnh. |
| 18 | resources | application.properties | Cấu hình cổng 8080 và chuỗi kết nối SQL Server (không chứa mật khẩu). |
