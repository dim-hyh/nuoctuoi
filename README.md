# Hệ thống quản lý nước tưới và lịch canh tác tiết kiệm

Đồ án cuối kỳ môn **Phương pháp phát triển phần mềm hướng đối tượng**
Trường Đại học Tài nguyên và Môi trường TP. Hồ Chí Minh – Khoa Công nghệ Thông tin

- Sinh viên: Nguyễn Thị Diễm Huỳnh – MSSV 1250080077 – Lớp 12_ĐH_CNPM2
- Giảng viên hướng dẫn: ThS. Phạm Trọng Huynh

## Giới thiệu

Hệ thống kết hợp **lịch mùa vụ**, **độ ẩm đất** và **hạn mức nước** của vùng để đề xuất cho từng thửa: có cần tưới không, tưới bao nhiêu và trong khung giờ nào. Nông hộ được ghi đè đề xuất nếu nêu lý do; ghi đè vượt hạn mức phải được quản lý tổ thủy nông duyệt.

Chức năng chính:
- Đăng nhập, đổi mật khẩu, phân quyền 5 vai trò
- Quản lý thửa ruộng, cây trồng và giai đoạn sinh trưởng, lập lịch mùa vụ
- Ghi nhận độ ẩm đất và dự báo mưa
- Cấp và bổ sung hạn mức nước, cảnh báo Sắp hết / Đã hết
- Lập đề xuất tưới theo công thức QĐ6, hết hiệu lực sau 24 giờ, tự thay thế khi có số đo mới lệch nhiều
- Xác nhận, ghi đè có lý do, phê duyệt ghi đè, ghi lượng tưới thực tế
- Báo cáo sử dụng nước theo vùng và kỳ; thay đổi quy định

## Công nghệ

| Thành phần | Phiên bản |
|---|---|
| Ngôn ngữ | Java 25 |
| Framework | Spring Boot 3.5.7 (Spring MVC, Spring Data JPA, Spring Security) |
| Giao diện | Thymeleaf, HTML, CSS |
| Cơ sở dữ liệu | SQL Server 2022 Express |
| Công cụ build | Maven Wrapper (`mvnw`) |

## Cấu trúc thư mục

```
├── src/main/java/com/nuoctuoi/   mã nguồn (config, controller, service, repository, model, dto)
├── src/main/resources/           application.properties, templates (Thymeleaf), static (css, img)
├── lib/                          thư viện đăng nhập Windows của SQL Server (mssql-jdbc_auth)
├── test-web.ps1                  tập lệnh kiểm thử tự động (44 trường hợp)
└── docs/
    ├── bao-cao/                  báo cáo Word (.docx)
    ├── hinh-anh/                 toàn bộ hình trong báo cáo, chia theo chương
    ├── so-do/                    tệp draw.io gốc của sơ đồ
    └── bang-bieu/                bảng biểu trong báo cáo (Markdown, chia theo chương)
```

## Cài đặt và chạy

1. Cài **JDK 25** và **SQL Server Express** (kèm SQL Server Management Studio).
2. Bật TCP/IP cho SQL Server Express (SQL Server Configuration Manager → Protocols for SQLEXPRESS → TCP/IP → Enabled, cổng **14330**), rồi khởi động lại dịch vụ.
3. Trong SSMS tạo cơ sở dữ liệu tên **NuocTuoi**. Các bảng được tạo tự động khi chạy chương trình lần đầu.
4. Kiểm tra `src/main/resources/application.properties` cho đúng máy của bạn (đăng nhập bằng Windows Authentication, không cần mật khẩu).
5. Mở PowerShell tại thư mục project và chạy:
   ```
   .\mvnw.cmd clean spring-boot:run
   ```
6. Mở trình duyệt: http://localhost:8080

Khi demo với dữ liệu mẫu tháng 10/2026, giữ dòng sau trong `application.properties` (xóa dòng này để dùng ngày thật của máy):
```
nuoctuoi.ngay-he-thong=2026-10-06
```

## Tài khoản dùng thử (mật khẩu đều là `123`)

| Tên đăng nhập | Vai trò | Chức năng chính |
|---|---|---|
| hoa | Cán bộ kỹ thuật | Thửa ruộng, cây trồng, lịch mùa vụ, số liệu, lập đề xuất |
| an | Nông hộ | Xác nhận, ghi đè đề xuất, ghi lượng thực tế |
| binh | Quản lý tổ thủy nông | Hạn mức nước, phê duyệt ghi đè, báo cáo |
| tam | Ban quản trị | Báo cáo, thay đổi quy định, khôi phục dữ liệu mẫu |
| admin | Quản trị hệ thống | Quản lý tài khoản, quy định |

Kịch bản demo nhanh: **tam** khôi phục dữ liệu mẫu → **hoa** lập đề xuất ngày 06/10/2026 (33,3 m³) → **an** ghi đè 45 m³ → **binh** duyệt điều chỉnh 41,3 m³ → **an** xác nhận và ghi thực tế → **binh** xem báo cáo.

## Kiểm thử

Khi web đang chạy, mở PowerShell khác tại thư mục project:
```
powershell -ExecutionPolicy Bypass -File .\test-web.ps1
```
Kết quả mong đợi: `44 đạt, 0 lỗi`.
