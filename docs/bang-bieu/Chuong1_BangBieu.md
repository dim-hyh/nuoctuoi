# Bảng biểu chương 1

### Bảng 1.1 Vấn đề tồn tại và hướng giải quyết

| Vấn đề | Hệ quả | Hướng giải quyết |
|---|---|---|
| Tưới theo thói quen, không dựa vào độ ẩm đất | Thừa nước ở giai đoạn ít cần, thiếu nước ở giai đoạn nhạy cảm | Đề xuất tưới dựa trên ngưỡng độ ẩm của từng giai đoạn sinh trưởng |
| Hạn mức chia đều theo diện tích | Thửa đang ra hoa vẫn thiếu nước | Khi thiếu nước, ưu tiên giai đoạn nhạy cảm |
| Khuyến cáo kỹ thuật chung chung | Nông hộ khó áp dụng cho thửa của mình | Mỗi đề xuất ghi rõ phạm vi áp dụng: thửa, khung giờ, giai đoạn, số đo căn cứ |
| Làm khác khuyến cáo nhưng không ghi lý do | Không rút được kinh nghiệm, không điều chỉnh được ngưỡng | Ghi đè đề xuất bắt buộc chọn lý do, lưu vết người và thời điểm |
| Giải pháp hiện có chỉ xét từng ruộng riêng lẻ | Không điều phối được nước giữa các hộ khi khan hiếm | Kết hợp lịch mùa vụ, độ ẩm và hạn mức chung của vùng |
| Không có số liệu tưới thực tế | Không đối chiếu được với hạn mức | Ghi lượng tưới thực tế và báo cáo đề xuất – thực tế – hạn mức |

### Bảng 1.2 Chức năng nhiệm vụ của từng bộ phận

| Bộ phận | Nhiệm vụ chính | Công việc trên hệ thống |
|---|---|---|
| Ban quản trị | Điều hành chung, duyệt kế hoạch mùa vụ | Xem báo cáo sử dụng nước |
| Tổ thủy nông | Nhận nước theo đợt, chia cho các vùng | Cấp hạn mức, duyệt ghi đè vượt hạn mức |
| Kỹ thuật – khuyến nông | Hướng dẫn canh tác, lập lịch thời vụ | Quản lý thửa, cây trồng, lịch mùa vụ; nhập độ ẩm, dự báo mưa |
| Nông hộ | Trực tiếp canh tác, tưới | Xem đề xuất, xác nhận hoặc ghi đè có lý do, ghi lượng tưới thực tế |
| Quản trị hệ thống | Vận hành phần mềm | Quản lý tài khoản, thay đổi quy định |
| Kế toán – hành chính | Thu chi, hồ sơ xã viên | Không sử dụng (ngoài phạm vi) |

### Bảng 1.3 Danh sách các yêu cầu

| STT | Tên yêu cầu | Biểu mẫu | Quy định | Người thực hiện |
|---|---|---|---|---|
| 1 | Lập hồ sơ thửa ruộng | BM1 | QĐ1 | Cán bộ kỹ thuật |
| 2 | Khai báo cây trồng, giai đoạn sinh trưởng | BM2 | QĐ2 | Cán bộ kỹ thuật |
| 3 | Lập lịch mùa vụ | BM3 | QĐ3 | Cán bộ kỹ thuật |
| 4 | Ghi nhận độ ẩm đất và dự báo mưa | BM4 | QĐ4 | Cán bộ kỹ thuật, nông hộ |
| 5 | Cấp hạn mức nước | BM5 | QĐ5 | Quản lý tổ thủy nông |
| 6 | Lập đề xuất tưới | BM6 | QĐ6 | Hệ thống (tự động) |
| 7 | Ghi nhận lượng tưới thực tế | BM6 | QĐ7 | Nông hộ |
| 8 | Tra cứu đề xuất | BM6 |  | Tất cả |
| 9 | Ghi đè đề xuất | BM7 | QĐ8 | Nông hộ |
| 10 | Phê duyệt ghi đè | BM7 | QĐ9 | Quản lý tổ thủy nông |
| 11 | Lập báo cáo sử dụng nước | BM8 | QĐ10 | Quản lý tổ thủy nông |
| 12 | Thay đổi quy định |  | QĐ11 | Ban quản trị, Quản trị hệ thống |

### Bảng 1.4 Thuộc tính và thiết kế – Màn hình chính

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Điều hướng đến các chức năng chính của hệ thống |
| Đối tượng sử dụng | Tất cả người dùng (nông hộ, cán bộ kỹ thuật, quản lý tổ, quản trị) |
| Đọc/Ghi dữ liệu | Không đọc/ghi dữ liệu nghiệp vụ; chỉ đọc thông tin tài khoản đang đăng nhập |
| Input/Output | Các nút mở form tương ứng: Thửa ruộng, Cây trồng, Lịch mùa vụ, Số liệu đồng ruộng, Hạn mức, Đề xuất tưới, Ghi đè & phê duyệt, Báo cáo |
| Kịch bản/Validation | Không thao tác nghiệp vụ trực tiếp trên màn hình chính; nút nào không thuộc quyền của vai trò đăng nhập thì bị ẩn hoặc làm mờ |

### Bảng 1.5 Biểu mẫu BM1 Hồ sơ thửa ruộng

| BM1: | HỒ SƠ THỬA RUỘNG |
|---|---|
| Mã thửa: ……………………        Vùng tưới: …………………… | Mã thửa: ……………………        Vùng tưới: …………………… |
| Chủ thửa: ……………………       Diện tích (m²): …………………… | Chủ thửa: ……………………       Diện tích (m²): …………………… |
| Loại đất: ……………………        Hình thức tưới: …………………… | Loại đất: ……………………        Hình thức tưới: …………………… |
| Hiệu suất tưới: …………………… | Hiệu suất tưới: …………………… |

### Bảng 1.6 Thuộc tính và thiết kế – Form Quản lý thửa ruộng

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Thêm, sửa, xóa và xem danh sách hồ sơ thửa ruộng |
| Đối tượng sử dụng | Cán bộ kỹ thuật |
| Đọc/Ghi dữ liệu | Đọc/ghi bảng Thửa; đọc bảng Vùng tưới và Nông hộ để chọn |
| Input/Output | Input: mã thửa, vùng tưới, chủ thửa, diện tích, loại đất, hình thức tưới. Output: danh sách thửa gồm mã thửa, vùng, chủ thửa, diện tích, hình thức tưới, hiệu suất |
| Kịch bản/Validation | Mã thửa không trùng; diện tích phải lớn hơn 0; chọn hình thức tưới thì hiệu suất tự điền (0,9 / 0,75 / 0,6); không cho xóa thửa đang có mùa vụ; thiếu trường bắt buộc thì báo lỗi và không lưu |

### Bảng 1.7 Biểu mẫu BM2 Danh mục giai đoạn sinh trưởng

| BM2: | BM2: | DANH MỤC GIAI ĐOẠN SINH TRƯỞNG | DANH MỤC GIAI ĐOẠN SINH TRƯỞNG | DANH MỤC GIAI ĐOẠN SINH TRƯỞNG | DANH MỤC GIAI ĐOẠN SINH TRƯỞNG | DANH MỤC GIAI ĐOẠN SINH TRƯỞNG | DANH MỤC GIAI ĐOẠN SINH TRƯỞNG |
|---|---|---|---|---|---|---|---|
| Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… | Tên cây trồng: ……………………        Tổng thời gian sinh trưởng (ngày): …………………… |
| STT | Giai đoạn | Giai đoạn | Số ngày | Ngưỡng ẩm dưới (%) | Ngưỡng ẩm trên (%) | Độ sâu rễ (m) | Mức ưu tiên |
| 1 |  |  |  |  |  |  |  |
| 2 |  |  |  |  |  |  |  |

### Bảng 1.8 Thuộc tính và thiết kế – Form Cây trồng và giai đoạn sinh trưởng

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Khai báo cây trồng và các giai đoạn sinh trưởng làm căn cứ tính lượng nước tưới |
| Đối tượng sử dụng | Cán bộ kỹ thuật |
| Đọc/Ghi dữ liệu | Đọc/ghi bảng Cây trồng và Giai đoạn sinh trưởng |
| Input/Output | Input: tên cây; mỗi giai đoạn gồm tên, số ngày, ngưỡng ẩm dưới/trên, độ sâu rễ, mức ưu tiên. Output: danh sách giai đoạn của cây, tổng thời gian sinh trưởng |
| Kịch bản/Validation | Ngưỡng dưới < ngưỡng trên, trong khoảng 0–100%; số ngày > 0; độ sâu rễ từ 0,05 đến 2,0 m; ưu tiên chỉ nhận 1, 2, 3; tổng thời gian tự tính; không cho xóa cây đang có mùa vụ |

### Bảng 1.9 Biểu mẫu BM3 Lịch mùa vụ

| BM3: | BM3: | LỊCH MÙA VỤ | LỊCH MÙA VỤ | LỊCH MÙA VỤ | LỊCH MÙA VỤ |
|---|---|---|---|---|---|
| Mã thửa: ……………………        Cây trồng: …………………… | Mã thửa: ……………………        Cây trồng: …………………… | Mã thửa: ……………………        Cây trồng: …………………… | Mã thửa: ……………………        Cây trồng: …………………… | Mã thửa: ……………………        Cây trồng: …………………… | Mã thửa: ……………………        Cây trồng: …………………… |
| Ngày gieo: …/…/……        Ngày thu hoạch dự kiến: …/…/…… | Ngày gieo: …/…/……        Ngày thu hoạch dự kiến: …/…/…… | Ngày gieo: …/…/……        Ngày thu hoạch dự kiến: …/…/…… | Ngày gieo: …/…/……        Ngày thu hoạch dự kiến: …/…/…… | Ngày gieo: …/…/……        Ngày thu hoạch dự kiến: …/…/…… | Ngày gieo: …/…/……        Ngày thu hoạch dự kiến: …/…/…… |
| STT | Giai đoạn | Giai đoạn | Từ ngày | Đến ngày | Công việc canh tác |
| 1 |  |  |  |  |  |
| 2 |  |  |  |  |  |

### Bảng 1.10 Thuộc tính và thiết kế – Form Lập lịch mùa vụ

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Lập lịch mùa vụ cho một thửa, xác định cây đang ở giai đoạn nào theo thời gian |
| Đối tượng sử dụng | Cán bộ kỹ thuật |
| Đọc/Ghi dữ liệu | Ghi bảng Mùa vụ; đọc bảng Thửa, Cây trồng và Giai đoạn sinh trưởng |
| Input/Output | Input: mã thửa, cây trồng, ngày gieo, công việc canh tác. Output: ngày bắt đầu – kết thúc từng giai đoạn, ngày thu hoạch dự kiến |
| Kịch bản/Validation | Phải chọn đủ thửa, cây và ngày gieo mới bấm Tính lịch được; ngày giai đoạn tự tính từ số ngày ở BM2; không cho lưu nếu trùng thời gian với mùa vụ khác của cùng thửa (QĐ3) |

### Bảng 1.11 Biểu mẫu BM4 Phiếu số liệu đồng ruộng

| BM4: | BM4: | PHIẾU SỐ LIỆU ĐỒNG RUỘNG | PHIẾU SỐ LIỆU ĐỒNG RUỘNG | PHIẾU SỐ LIỆU ĐỒNG RUỘNG | PHIẾU SỐ LIỆU ĐỒNG RUỘNG | PHIẾU SỐ LIỆU ĐỒNG RUỘNG |
|---|---|---|---|---|---|---|
| Người ghi: ……………………        Ngày: …/…/…… | Người ghi: ……………………        Ngày: …/…/…… | Người ghi: ……………………        Ngày: …/…/…… | Người ghi: ……………………        Ngày: …/…/…… | Người ghi: ……………………        Ngày: …/…/…… | Người ghi: ……………………        Ngày: …/…/…… | Người ghi: ……………………        Ngày: …/…/…… |
| STT | Mã thửa / Vùng | Mã thửa / Vùng | Loại số liệu (độ ẩm / mưa) | Thời điểm | Giá trị | Trạng thái |
| 1 |  |  |  |  |  |  |
| 2 |  |  |  |  |  |  |

### Bảng 1.12 Thuộc tính và thiết kế – Form Số liệu đồng ruộng

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Ghi nhận độ ẩm đất của từng thửa và dự báo mưa của từng vùng, làm căn cứ lập đề xuất tưới |
| Đối tượng sử dụng | Cán bộ kỹ thuật, nông hộ |
| Đọc/Ghi dữ liệu | Ghi bảng Số đo độ ẩm và Dự báo mưa; đọc bảng Thửa và Vùng tưới |
| Input/Output | Input: loại số liệu, mã thửa hoặc vùng, thời điểm, giá trị; hoặc nhập từ file CSV. Output: danh sách số liệu kèm trạng thái hợp lệ / quá hạn |
| Kịch bản/Validation | Độ ẩm trong khoảng 0–100%; lượng mưa không âm; thời điểm không được ở tương lai; số đo cũ hơn 6 giờ bị đánh dấu "Quá hạn" và không dùng để lập đề xuất (QĐ4); file CSV có dòng lỗi thì báo số dòng lỗi |

### Bảng 1.13 Biểu mẫu BM5 Phiếu cấp hạn mức nước

| BM5: | PHIẾU CẤP HẠN MỨC NƯỚC |
|---|---|
| Vùng tưới: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng tưới: ……………………        Kỳ: từ …/…/…… đến …/…/…… |
| Hạn mức (m³): ……………………        Người lập: …………………… | Hạn mức (m³): ……………………        Người lập: …………………… |
| Đã dùng (m³): ……………………        Còn lại (m³): …………………… | Đã dùng (m³): ……………………        Còn lại (m³): …………………… |

### Bảng 1.14 Thuộc tính và thiết kế – Form Hạn mức nước

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Cấp hạn mức nước cho từng vùng theo kỳ và theo dõi lượng nước đã dùng |
| Đối tượng sử dụng | Quản lý tổ thủy nông |
| Đọc/Ghi dữ liệu | Đọc/ghi bảng Hạn mức; đọc bảng Vùng tưới và lượng nước đã dùng từ Đề xuất, Ghi đè |
| Input/Output | Input: vùng tưới, kỳ (từ ngày – đến ngày), hạn mức (m³), người lập. Output: danh sách hạn mức kèm lượng đã dùng, còn lại, trạng thái |
| Kịch bản/Validation | Hạn mức phải lớn hơn 0; đến ngày phải sau từ ngày; không cho lưu nếu kỳ trùng với kỳ khác của cùng vùng; còn lại dưới 20% thì hiện cảnh báo (QĐ5); không cho xóa kỳ đã có đề xuất sử dụng |

### Bảng 1.15 Biểu mẫu BM6 Danh sách đề xuất tưới

| BM6: | BM6: | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI | DANH SÁCH ĐỀ XUẤT TƯỚI |
|---|---|---|---|---|---|---|---|---|---|---|
| Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… | Vùng: ……………………     Từ ngày: …/…/……     Đến ngày: …/…/…… |
| Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… | Hạn mức còn lại (m³): …………………… |
| STT | Mã đề xuất | Mã đề xuất | Thửa | Giai đoạn | Ẩm / ngưỡng (%) | Đề xuất (m³) | Khung giờ | Hiệu lực đến | Trạng thái | Thực tế (m³) |
| 1 |  |  |  |  |  |  |  |  |  |  |
| 2 |  |  |  |  |  |  |  |  |  |  |
| Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất | Phạm vi áp dụng của đề xuất |
| Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… | Thửa áp dụng: ……………………        Giai đoạn: …………………… |
| Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… | Hiệu lực: từ …… đến ……        Số đo căn cứ: …………………… |
| Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… | Căn cứ tính: ………………………………………… |

### Bảng 1.16 Thuộc tính và thiết kế – Form Đề xuất tưới

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Lập, xem, xác nhận đề xuất tưới kèm phạm vi áp dụng; ghi lượng tưới thực tế; tra cứu đề xuất theo vùng, ngày, trạng thái |
| Đối tượng sử dụng | Nông hộ, cán bộ kỹ thuật (hệ thống tự lập đề xuất lúc 5h00) |
| Đọc/Ghi dữ liệu | Đọc/ghi bảng Đề xuất và Phạm vi áp dụng; đọc bảng Mùa vụ, Giai đoạn, Số đo độ ẩm, Dự báo mưa, Hạn mức |
| Input/Output | Input: điều kiện lọc (vùng, ngày, trạng thái); lượng tưới thực tế. Output: danh sách đề xuất; chi tiết phạm vi áp dụng và căn cứ tính của đề xuất đang chọn; hạn mức còn lại của vùng |
| Kịch bản/Validation | Chỉ đề xuất tưới khi độ ẩm thấp hơn ngưỡng dưới; tính lượng nước, hoãn khi mưa đủ bù, ưu tiên giai đoạn nhạy cảm khi thiếu hạn mức (QĐ6); không cho xác nhận đề xuất đã hết hiệu lực hoặc bị thay thế; lượng thực tế phải lớn hơn 0 và chỉ ghi cho đề xuất đã xác nhận hoặc đã ghi đè (QĐ7) |

### Bảng 1.17 Biểu mẫu BM7 Phiếu ghi đè đề xuất tưới

| BM7: | PHIẾU GHI ĐÈ ĐỀ XUẤT TƯỚI |
|---|---|
| Mã đề xuất gốc: ……………………     Lượng gốc (m³): ……     Khung giờ: …… | Mã đề xuất gốc: ……………………     Lượng gốc (m³): ……     Khung giờ: …… |
| Người ghi đè: ……………………        Ngày ghi đè: …/…/…… | Người ghi đè: ……………………        Ngày ghi đè: …/…/…… |
| Hành động:   □ Hoãn tưới      □ Đổi lượng      □ Đổi khung giờ | Hành động:   □ Hoãn tưới      □ Đổi lượng      □ Đổi khung giờ |
| Giá trị mới: ……………………        Phạm vi ghi đè (thửa): …………………… | Giá trị mới: ……………………        Phạm vi ghi đè (thửa): …………………… |
| Loại lý do: ………………………………………… | Loại lý do: ………………………………………… |
| Mô tả lý do (tối thiểu 15 ký tự): ………………………………………… | Mô tả lý do (tối thiểu 15 ký tự): ………………………………………… |
| PHÊ DUYỆT (Quản lý tổ thủy nông – khi vượt hạn mức) | PHÊ DUYỆT (Quản lý tổ thủy nông – khi vượt hạn mức) |
| Người duyệt: ……………………        Ngày duyệt: …/…/…… | Người duyệt: ……………………        Ngày duyệt: …/…/…… |
| Quyết định:   □ Chấp nhận      □ Điều chỉnh      □ Từ chối        Lượng duyệt (m³): …… | Quyết định:   □ Chấp nhận      □ Điều chỉnh      □ Từ chối        Lượng duyệt (m³): …… |
| Ý kiến: ……………………………………………………………… | Ý kiến: ……………………………………………………………… |

### Bảng 1.18 Thuộc tính và thiết kế – Form Ghi đè đề xuất

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Cho nông hộ quyết định khác với đề xuất và ghi lại lý do; cho quản lý tổ duyệt các ghi đè làm vùng vượt hạn mức |
| Đối tượng sử dụng | Nông hộ (phần Nội dung ghi đè), quản lý tổ thủy nông (phần Phê duyệt) |
| Đọc/Ghi dữ liệu | Đọc bảng Đề xuất, Phạm vi áp dụng, Hạn mức; ghi bảng Ghi đè và Phê duyệt; cập nhật trạng thái Đề xuất và lượng đã dùng của Hạn mức |
| Input/Output | Input: hành động, giá trị mới, thửa trong phạm vi, loại lý do, mô tả; quyết định, lượng duyệt, ý kiến. Output: chênh lệch so với lượng gốc, cảnh báo hạn mức, trạng thái (Có hiệu lực / Chờ duyệt / Đã ghi đè / Bị từ chối) |
| Kịch bản/Validation | Thiếu loại lý do hoặc mô tả dưới 15 ký tự thì không cho gửi; chỉ chọn được thửa trong phạm vi gốc (QĐ8). Hệ thống tự tính phần tăng thêm và so với hạn mức còn lại: trong hạn mức thì có hiệu lực ngay, vượt thì chuyển Chờ duyệt. Lượng duyệt không quá 41,3 m³ (33,3 + 8); điều chỉnh hoặc từ chối phải có ý kiến (QĐ9) |

### Bảng 1.19 Biểu mẫu BM8 Báo cáo sử dụng nước

| BM8: | BM8: | BÁO CÁO SỬ DỤNG NƯỚC | BÁO CÁO SỬ DỤNG NƯỚC | BÁO CÁO SỬ DỤNG NƯỚC | BÁO CÁO SỬ DỤNG NƯỚC | BÁO CÁO SỬ DỤNG NƯỚC | BÁO CÁO SỬ DỤNG NƯỚC | BÁO CÁO SỬ DỤNG NƯỚC |
|---|---|---|---|---|---|---|---|---|
| Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… | Vùng: ……………………        Kỳ: từ …/…/…… đến …/…/…… |
| Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… | Hạn mức (m³): ……        Đã dùng (m³): ……        Còn lại (m³): …… |
| Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa | Sử dụng nước theo thửa |
| STT | Mã thửa | Mã thửa | Đề xuất (m³) | Thực tế (m³) | Chênh lệch (m³) | Chênh lệch (m³) | Số lần ghi đè | % hạn mức |
| 1 |  |  |  |  |  |  |  |  |
| 2 |  |  |  |  |  |  |  |  |
| Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do | Thống kê ghi đè theo lý do |
| STT | Loại lý do | Loại lý do | Loại lý do | Loại lý do | Loại lý do | Số lần | Số lần | Tỷ lệ (%) |
| 1 |  |  |  |  |  |  |  |  |
| 2 |  |  |  |  |  |  |  |  |

### Bảng 1.20 Thuộc tính và thiết kế – Form Báo cáo sử dụng nước

| Thuộc tính | Thiết kế |
|---|---|
| Mục đích | Tổng hợp lượng nước đề xuất, thực tế và hạn mức theo kỳ; thống kê ghi đè theo lý do để điều chỉnh ngưỡng tưới và kiểm tra thiết bị |
| Đối tượng sử dụng | Quản lý tổ thủy nông, Ban quản trị HTX |
| Đọc/Ghi dữ liệu | Chỉ đọc bảng Đề xuất, Nhật ký thực tế, Ghi đè và Hạn mức; không ghi dữ liệu |
| Input/Output | Input: vùng, kỳ báo cáo. Output: tổng hạn mức – đã dùng – còn lại, tỷ lệ sử dụng; bảng theo thửa (đề xuất, thực tế, chênh lệch, số lần ghi đè); bảng thống kê lý do ghi đè; tệp CSV |
| Kịch bản/Validation | Phải chọn vùng và kỳ trước khi xem; kỳ phải trùng kỳ hạn mức (QĐ10); chênh lệch tự tính, dương thì tô đỏ; dòng Tổng tự cộng; khi xuất CSV, tệp được lưu dạng UTF-8 để giữ tiếng Việt |

### Bảng 1.21 Yêu cầu phi chức năng

| Yêu cầu | Mô tả | Cách đáp ứng trong hệ thống |
|---|---|---|
| Bảo mật | Chỉ người có tài khoản mới dùng được hệ thống; mỗi vai trò chỉ thấy và thao tác đúng chức năng của mình. | Mật khẩu mã hóa BCrypt; phân quyền theo 5 vai trò; chống giả mạo form (CSRF); vào trang không đúng quyền thì hiện màn hình "Không có quyền truy cập". |
| Tính đúng đắn | Lượng nước đề xuất và hạn mức phải được tính đúng theo quy định; không cho lưu dữ liệu sai. | Kiểm tra các quy định QĐ1–QĐ10 khi lưu; tính lượng nước theo công thức QĐ6; giữ chỗ hạn mức ngay khi lập đề xuất. |
| Tiện dụng | Người dùng ở hợp tác xã ít dùng máy tính vẫn thao tác được. | Giao diện tiếng Việt; menu chỉ hiện chức năng đúng vai trò; ngày giờ được điền sẵn; trạng thái được tô màu; hỏi xác nhận trước khi đăng xuất, bổ sung nước, khôi phục dữ liệu. |
| Tính tiến hóa | Khi hợp tác xã đổi quy định thì không phải sửa chương trình. | Các tham số quy định được lưu trong cơ sở dữ liệu và sửa trên màn hình Thay đổi quy định (QĐ11). |
| Tương thích | Chạy được trên máy tính sẵn có của hợp tác xã. | Ứng dụng web, dùng trình duyệt Chrome hoặc Edge trong mạng nội bộ; máy chủ chạy Windows với SQL Server Express. |
| Lưu vết | Biết ai đã làm gì để đối chiếu khi có thắc mắc về lượng nước. | Lưu người ghi số đo, căn cứ của đề xuất, người ghi đè, lý do, thời điểm ghi đè, quyết định và ý kiến duyệt. |
