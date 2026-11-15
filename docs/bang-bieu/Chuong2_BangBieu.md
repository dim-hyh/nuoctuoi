# Bảng biểu chương 2

### Bảng 2.1 Danh sách các actor

| STT | Tên Actor | Ý nghĩa |
|---|---|---|
| 1 | Người dùng | Tác nhân tổng quát, đại diện cho mọi người có tài khoản trong hệ thống. Thực hiện đăng nhập, đăng xuất, thay đổi mật khẩu. |
| 2 | Nông hộ | Chủ thửa ruộng. Tra cứu và xác nhận đề xuất tưới, ghi đè đề xuất có lý do, ghi lượng tưới thực tế. |
| 3 | Cán bộ kỹ thuật | Cán bộ HTX phụ trách kỹ thuật. Quản lý thửa ruộng, cây trồng và giai đoạn sinh trưởng, lịch mùa vụ, số liệu độ ẩm và mưa; có thể lập lại đề xuất tưới khi cần. |
| 4 | Quản lý tổ thủy nông | Người điều phối nước của vùng tưới. Cấp hạn mức nước theo kỳ, phê duyệt hoặc điều chỉnh yêu cầu ghi đè, xem báo cáo sử dụng nước. |
| 5 | Ban quản trị | Lãnh đạo HTX. Xem báo cáo sử dụng nước, quyết định thay đổi quy định (ngưỡng, hạn mức, thời hạn hiệu lực…). |
| 6 | Quản trị hệ thống | Người vận hành phần mềm. Quản lý tài khoản và cập nhật các tham số quy định vào hệ thống. |
| 7 | Bộ hẹn giờ | Tác nhân hệ thống (không phải người). Tự kích hoạt lập đề xuất tưới lúc 5h00 hằng ngày. |

### Bảng 2.2 Danh sách các usecase

| STT | Tên Usecase | Ý nghĩa |
|---|---|---|
| 1 | Đăng nhập | Xác thực người dùng để vào hệ thống theo đúng quyền |
| 2 | Đăng xuất | Kết thúc phiên làm việc |
| 3 | Thay đổi mật khẩu | Người dùng đổi mật khẩu tài khoản của mình |
| 4 | Quản lý thửa ruộng | Thêm, sửa, xóa hồ sơ thửa: chủ thửa, vùng tưới, diện tích, hình thức tưới (BM1) |
| 5 | Quản lý cây trồng và giai đoạn sinh trưởng | Khai báo các giai đoạn của cây với số ngày, ngưỡng ẩm, độ sâu rễ, mức ưu tiên (BM2) |
| 6 | Lập lịch mùa vụ | Lập lịch theo ngày gieo, hệ thống tự tính các giai đoạn và ngày thu hoạch (BM3) |
| 7 | Ghi nhận số liệu đồng ruộng | Ghi độ ẩm đất theo thửa và mưa dự báo theo vùng, đánh dấu số liệu quá hạn (BM4) |
| 8 | Cấp hạn mức nước | Cấp lượng nước tối đa cho từng vùng theo kỳ, theo dõi đã dùng và còn lại (BM5) |
| 9 | Lập đề xuất tưới | Tính lượng nước và khung giờ tưới cho từng thửa, kèm phạm vi áp dụng; tự chạy lúc 5h00 (BM6) |
| 10 | Tra cứu và xác nhận đề xuất | Xem đề xuất theo vùng, ngày, trạng thái; nông hộ xác nhận đề xuất (BM6) |
| 11 | Ghi đè đề xuất có lý do | Hoãn tưới, đổi lượng hoặc đổi giờ so với đề xuất, bắt buộc ghi lý do (BM7) |
| 12 | Phê duyệt ghi đè | Quản lý tổ chấp nhận, điều chỉnh hoặc từ chối yêu cầu ghi đè vượt hạn mức (BM7) |
| 13 | Ghi lượng tưới thực tế | Ghi lượng nước đã tưới thật sau khi tưới xong (BM6) |
| 14 | Báo cáo sử dụng nước | Tổng hợp lượng đề xuất so với thực tế theo thửa và thống kê lý do ghi đè (BM8) |
| 15 | Thay đổi quy định | Cập nhật các tham số quy định như ngưỡng, hạn mức, thời hạn hiệu lực (QĐ11) |

### Bảng 2.3 Đặc tả usecase “Đăng nhập”

| UCID | UC01 | UC01 |
|---|---|---|
| Name | Đăng nhập | Đăng nhập |
| Summary | Người dùng nhập tên đăng nhập và mật khẩu để vào hệ thống và sử dụng các chức năng theo vai trò. | Người dùng nhập tên đăng nhập và mật khẩu để vào hệ thống và sử dụng các chức năng theo vai trò. |
| Preconditions | Người dùng đã được cấp tài khoản. | Người dùng đã được cấp tài khoản. |
| Postconditions | Người dùng vào được màn hình chính; hệ thống ghi nhận phiên làm việc. | Người dùng vào được màn hình chính; hệ thống ghi nhận phiên làm việc. |
| Primary Actor(s) | Người dùng (Nông hộ, Cán bộ kỹ thuật, Quản lý tổ thủy nông, Ban quản trị, Quản trị hệ thống) | Người dùng (Nông hộ, Cán bộ kỹ thuật, Quản lý tổ thủy nông, Ban quản trị, Quản trị hệ thống) |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Người dùng mở phần mềm. | Người dùng mở phần mềm. |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống hiển thị màn hình đăng nhập. |
|  | 2 | Người dùng nhập tên đăng nhập, mật khẩu và nhấn "Đăng nhập". |
|  | 3 | Hệ thống kiểm tra thông tin tài khoản. |
|  | 4 | Hệ thống mở màn hình chính, hiển thị các chức năng 1–8 và nút "Đăng xuất". |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 2a | Bỏ trống tên đăng nhập hoặc mật khẩu: hệ thống yêu cầu nhập đủ thông tin. |
|  | 3a | Sai tên đăng nhập hoặc mật khẩu: hệ thống báo lỗi, quay lại bước 2. |
| Open Issues | 1 | Có khóa tài khoản tạm thời khi đăng nhập sai nhiều lần hay không. |
|  | 2 | Khi người dùng quên mật khẩu, Quản trị hệ thống cấp lại theo quy trình nào. |

### Bảng 2.4 Đặc tả usecase “Đăng xuất”

| UCID | UC02 | UC02 |
|---|---|---|
| Name | Đăng xuất | Đăng xuất |
| Summary | Người dùng kết thúc phiên làm việc và quay về màn hình đăng nhập. | Người dùng kết thúc phiên làm việc và quay về màn hình đăng nhập. |
| Preconditions | Người dùng đang đăng nhập. | Người dùng đang đăng nhập. |
| Postconditions | Phiên làm việc kết thúc, các form đang mở được đóng. | Phiên làm việc kết thúc, các form đang mở được đóng. |
| Primary Actor(s) | Người dùng | Người dùng |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Người dùng nhấn nút "Đăng xuất" trên màn hình chính. | Người dùng nhấn nút "Đăng xuất" trên màn hình chính. |
| Main Scenario | Step | Action |
|  | 1 | Người dùng nhấn "Đăng xuất". |
|  | 2 | Hệ thống hỏi xác nhận đăng xuất. |
|  | 3 | Người dùng chọn "Yes". |
|  | 4 | Hệ thống đóng màn hình chính và hiển thị lại màn hình đăng nhập. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 3a | Người dùng chọn "No": hệ thống giữ nguyên phiên làm việc. |
| Open Issues | 1 | Có tự động đăng xuất khi người dùng không thao tác trong thời gian dài hay không. |

### Bảng 2.5 Đặc tả usecase “Thay đổi mật khẩu”

| UCID | UC03 | UC03 |
|---|---|---|
| Name | Thay đổi mật khẩu | Thay đổi mật khẩu |
| Summary | Người dùng đổi mật khẩu tài khoản của mình. | Người dùng đổi mật khẩu tài khoản của mình. |
| Preconditions | Người dùng đang đăng nhập. | Người dùng đang đăng nhập. |
| Postconditions | Mật khẩu mới được lưu và dùng cho lần đăng nhập sau. | Mật khẩu mới được lưu và dùng cho lần đăng nhập sau. |
| Primary Actor(s) | Người dùng | Người dùng |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Người dùng chọn chức năng "Thay đổi mật khẩu". | Người dùng chọn chức năng "Thay đổi mật khẩu". |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống hiển thị các ô Mật khẩu cũ, Mật khẩu mới, Nhập lại mật khẩu mới. |
|  | 2 | Người dùng nhập đủ thông tin và nhấn "Lưu". |
|  | 3 | Hệ thống kiểm tra mật khẩu cũ và mật khẩu mới. |
|  | 4 | Hệ thống lưu mật khẩu mới và thông báo thành công. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 3a | Mật khẩu cũ không đúng: hệ thống báo lỗi, quay lại bước 2. |
|  | 3b | Mật khẩu mới dưới 6 ký tự hoặc nhập lại không khớp: hệ thống báo lỗi. |
|  | 3c | Mật khẩu mới trùng mật khẩu cũ: hệ thống yêu cầu chọn mật khẩu khác. |
| Open Issues | 1 | Đây là chức năng hệ thống nên không có biểu mẫu nghiệp vụ trong BM1–BM8. |

### Bảng 2.6 Đặc tả usecase “Quản lý thửa ruộng”

| UCID | UC04 | UC04 |
|---|---|---|
| Name | Quản lý thửa ruộng | Quản lý thửa ruộng |
| Summary | Cán bộ kỹ thuật thêm, sửa, xóa hồ sơ thửa ruộng: chủ thửa, loại đất, vùng tưới, diện tích, hình thức tưới (BM1, QĐ1). | Cán bộ kỹ thuật thêm, sửa, xóa hồ sơ thửa ruộng: chủ thửa, loại đất, vùng tưới, diện tích, hình thức tưới (BM1, QĐ1). |
| Preconditions | Cán bộ kỹ thuật đã đăng nhập. | Cán bộ kỹ thuật đã đăng nhập. |
| Postconditions | Hồ sơ thửa được lưu, dùng cho lập lịch mùa vụ và tính lượng nước đề xuất. | Hồ sơ thửa được lưu, dùng cho lập lịch mùa vụ và tính lượng nước đề xuất. |
| Primary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Cán bộ kỹ thuật chọn chức năng "1" (Hồ sơ thửa ruộng) trên màn hình chính. | Cán bộ kỹ thuật chọn chức năng "1" (Hồ sơ thửa ruộng) trên màn hình chính. |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM1, hiển thị danh sách thửa: mã thửa, chủ thửa, loại đất, vùng tưới, diện tích, hình thức tưới, hiệu suất. |
|  | 2 | Cán bộ kỹ thuật nhập Mã thửa, chọn Chủ thửa, Loại đất, Vùng tưới, nhập Diện tích và chọn Hình thức tưới. |
|  | 3 | Hệ thống tự hiển thị Hiệu suất tưới theo hình thức tưới (nhỏ giọt 90%, phun mưa 75%, rãnh 60%). |
|  | 4 | Cán bộ kỹ thuật nhấn "Thêm". |
|  | 5 | Hệ thống kiểm tra theo QĐ1 và thêm thửa vào danh sách. |
|  | 6 | Cán bộ kỹ thuật nhấn "Lưu"; hệ thống lưu và thông báo số thửa đã lưu. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 4a | Sửa: chọn một dòng, thông tin hiện lên các ô nhập; sửa rồi nhấn "Sửa", hệ thống kiểm tra lại theo QĐ1. |
|  | 4b | Xóa: chọn một dòng, nhấn "Xóa"; hệ thống hỏi xác nhận rồi xóa và đánh lại STT. |
|  | 5a | Mã thửa không đúng dạng T-xxx hoặc đã tồn tại: hệ thống báo lỗi. |
|  | 5b | Chưa chọn chủ thửa, loại đất, vùng tưới hoặc hình thức tưới: hệ thống báo lỗi. |
|  | 5c | Diện tích không phải số hoặc nhỏ hơn hay bằng 0: hệ thống báo lỗi. |
| Open Issues | 1 | Xử lý thế nào khi xóa thửa đã có lịch mùa vụ hoặc đề xuất tưới. |
|  | 2 | Hiệu suất tưới có được chỉnh theo tình trạng thiết bị thực tế hay không. |

### Bảng 2.7 Đặc tả usecase “Quản lý cây trồng và giai đoạn sinh trưởng”

| UCID | UC05 | UC05 |
|---|---|---|
| Name | Quản lý cây trồng và giai đoạn sinh trưởng | Quản lý cây trồng và giai đoạn sinh trưởng |
| Summary | Cán bộ kỹ thuật khai báo các giai đoạn sinh trưởng của cây: số ngày, ngưỡng ẩm, độ sâu rễ, mức ưu tiên (BM2, QĐ2). | Cán bộ kỹ thuật khai báo các giai đoạn sinh trưởng của cây: số ngày, ngưỡng ẩm, độ sâu rễ, mức ưu tiên (BM2, QĐ2). |
| Preconditions | Cán bộ kỹ thuật đã đăng nhập. | Cán bộ kỹ thuật đã đăng nhập. |
| Postconditions | Danh mục giai đoạn được cập nhật, dùng để lập lịch mùa vụ và tính lượng nước. | Danh mục giai đoạn được cập nhật, dùng để lập lịch mùa vụ và tính lượng nước. |
| Primary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Cán bộ kỹ thuật chọn chức năng "2" (Giai đoạn sinh trưởng) trên màn hình chính. | Cán bộ kỹ thuật chọn chức năng "2" (Giai đoạn sinh trưởng) trên màn hình chính. |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM2, chọn sẵn cây trồng, hiển thị các giai đoạn và Tổng thời gian (Dưa leo: 4 giai đoạn, 57 ngày). |
|  | 2 | Cán bộ kỹ thuật nhập Giai đoạn, Số ngày, Ngưỡng ẩm dưới, Ngưỡng ẩm trên, Độ sâu rễ và chọn Mức ưu tiên (1 là cao nhất). |
|  | 3 | Cán bộ kỹ thuật nhấn "Thêm". |
|  | 4 | Hệ thống kiểm tra theo QĐ2, thêm giai đoạn và cập nhật Tổng thời gian. |
|  | 5 | Cán bộ kỹ thuật nhấn "Lưu"; hệ thống lưu và thông báo. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 3a | Sửa: chọn một dòng, sửa thông tin rồi nhấn "Sửa". |
|  | 3b | Xóa: chọn một dòng, nhấn "Xóa" và xác nhận; hệ thống cập nhật lại Tổng thời gian. |
|  | 4a | Tên giai đoạn bỏ trống hoặc đã có: hệ thống báo lỗi. |
|  | 4b | Số ngày không phải số nguyên dương: hệ thống báo lỗi. |
|  | 4c | Ngưỡng dưới ngoài 0–100% hoặc ngưỡng trên không lớn hơn ngưỡng dưới: hệ thống báo lỗi. |
|  | 4d | Độ sâu rễ ngoài khoảng 0,05 – 2,0 m, hoặc chưa chọn mức ưu tiên: hệ thống báo lỗi. |
| Open Issues | 1 | Khi sửa ngưỡng ẩm trong lúc vụ đang chạy, các đề xuất đã lập có tính lại hay không. |
|  | 2 | Hiện mới có dữ liệu mẫu cho Dưa leo; cần bổ sung danh mục cho các cây trồng khác. |

### Bảng 2.8 Đặc tả usecase “Lập lịch mùa vụ”

| UCID | UC06 | UC06 |
|---|---|---|
| Name | Lập lịch mùa vụ | Lập lịch mùa vụ |
| Summary | Cán bộ kỹ thuật lập lịch canh tác cho thửa theo ngày gieo; hệ thống tự tính các giai đoạn và ngày thu hoạch (BM3, QĐ3). | Cán bộ kỹ thuật lập lịch canh tác cho thửa theo ngày gieo; hệ thống tự tính các giai đoạn và ngày thu hoạch (BM3, QĐ3). |
| Preconditions | Thửa ruộng (UC04) và giai đoạn sinh trưởng của cây (UC05) đã được khai báo. | Thửa ruộng (UC04) và giai đoạn sinh trưởng của cây (UC05) đã được khai báo. |
| Postconditions | Lịch mùa vụ được lưu; hệ thống xác định được giai đoạn hiện tại của thửa để lập đề xuất. | Lịch mùa vụ được lưu; hệ thống xác định được giai đoạn hiện tại của thửa để lập đề xuất. |
| Primary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Cán bộ kỹ thuật chọn chức năng "3" (Lịch mùa vụ) trên màn hình chính. | Cán bộ kỹ thuật chọn chức năng "3" (Lịch mùa vụ) trên màn hình chính. |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM3. |
|  | 2 | Cán bộ kỹ thuật chọn Mã thửa, Cây trồng và Ngày gieo. |
|  | 3 | Cán bộ kỹ thuật nhấn "Tính lịch". |
|  | 4 | Hệ thống tính Từ ngày, Đến ngày, Số ngày, Công việc canh tác của từng giai đoạn theo BM2, hiển thị Ngày thu hoạch dự kiến và tô vàng giai đoạn hiện tại. |
|  | 5 | Cán bộ kỹ thuật nhấn "Lưu". |
|  | 6 | Hệ thống kiểm tra theo QĐ3, lưu lịch và thông báo. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 3a | Đổi Ngày gieo: hệ thống tự tính lại lịch. |
|  | 5a | Nhấn "Hủy": hệ thống trả về giá trị mặc định và tính lại lịch. |
|  | 6a | Chưa chọn thửa: hệ thống báo lỗi. |
|  | 6b | Chưa tính lịch: hệ thống báo lỗi. |
| Open Issues | 1 | Khi thực tế gieo trễ hoặc cây phát triển chậm, có cho dời lịch từng giai đoạn hay không. |
|  | 2 | Một thửa có được lập nhiều vụ nối tiếp nhau trong năm hay không. |

### Bảng 2.9 Đặc tả usecase “Ghi nhận số liệu đồng ruộng”

| UCID | UC07 | UC07 |
|---|---|---|
| Name | Ghi nhận số liệu đồng ruộng | Ghi nhận số liệu đồng ruộng |
| Summary | Ghi độ ẩm đất theo thửa và mưa dự báo theo vùng; hệ thống đánh dấu số liệu hợp lệ hoặc quá hạn (BM4, QĐ4). | Ghi độ ẩm đất theo thửa và mưa dự báo theo vùng; hệ thống đánh dấu số liệu hợp lệ hoặc quá hạn (BM4, QĐ4). |
| Preconditions | Thửa ruộng và vùng tưới đã tồn tại. | Thửa ruộng và vùng tưới đã tồn tại. |
| Postconditions | Số liệu được lưu; số liệu hợp lệ mới nhất được dùng để lập đề xuất tưới. | Số liệu được lưu; số liệu hợp lệ mới nhất được dùng để lập đề xuất tưới. |
| Primary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Secondary Actor(s) | Nông hộ (có thể là người ghi số liệu) | Nông hộ (có thể là người ghi số liệu) |
| Trigger | Có số đo độ ẩm buổi sáng (trước 05:00) hoặc có dự báo mưa mới. | Có số đo độ ẩm buổi sáng (trước 05:00) hoặc có dự báo mưa mới. |
| Main Scenario | Step | Action |
|  | 1 | Người ghi chọn chức năng "4" (Số liệu đồng ruộng); hệ thống mở BM4 với Người ghi, Ngày và danh sách số liệu. |
|  | 2 | Người ghi chọn Loại số liệu (Độ ẩm đất hoặc Mưa dự báo); hệ thống đổi danh sách Mã thửa/Vùng và đơn vị (% hoặc mm). |
|  | 3 | Người ghi chọn Mã thửa/Vùng, Thời điểm và nhập Giá trị. |
|  | 4 | Người ghi nhấn "Thêm". |
|  | 5 | Hệ thống kiểm tra theo QĐ4, thêm dòng và đánh Trạng thái: "Hợp lệ" hoặc "Quá hạn" (cũ hơn 6 giờ so với mốc lập đề xuất 05:00). |
|  | 6 | Người ghi nhấn "Lưu"; hệ thống lưu và thông báo số số liệu quá hạn. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 3a | Nhấn "Nhập từ CSV" và chọn file (Mã;DoAm\|Mua;dd/MM/yyyy HH:mm;Giá trị): hệ thống nhập các dòng hợp lệ và báo số dòng bị bỏ qua. |
|  | 5a | Giá trị không phải số: hệ thống báo lỗi. |
|  | 5b | Độ ẩm ngoài 0–100% hoặc mưa ngoài 0–500 mm: hệ thống báo lỗi. |
|  | 5c | Thời điểm đo sau ngày ghi phiếu: hệ thống báo lỗi. |
|  | 5d | Trùng mã, loại số liệu và thời điểm đã ghi: hệ thống báo lỗi. |
|  | 5e | Chọn một dòng và nhấn "Xóa": hệ thống xóa số liệu đó. |
| Open Issues | 1 | Có kết nối trực tiếp cảm biến độ ẩm thay cho nhập tay và nhập CSV hay không. |
|  | 2 | Một thửa có nhiều cảm biến thì lấy giá trị nhỏ nhất hay giá trị trung bình. |

### Bảng 2.10 Đặc tả usecase “Cấp hạn mức nước”

| UCID | UC08 | UC08 |
|---|---|---|
| Name | Cấp hạn mức nước | Cấp hạn mức nước |
| Summary | Quản lý tổ thủy nông cấp lượng nước tối đa cho từng vùng theo kỳ và theo dõi lượng đã dùng, còn lại (BM5, QĐ5). | Quản lý tổ thủy nông cấp lượng nước tối đa cho từng vùng theo kỳ và theo dõi lượng đã dùng, còn lại (BM5, QĐ5). |
| Preconditions | Vùng tưới đã tồn tại; Quản lý tổ thủy nông đã đăng nhập. | Vùng tưới đã tồn tại; Quản lý tổ thủy nông đã đăng nhập. |
| Postconditions | Hạn mức của kỳ được lưu, dùng để giới hạn đề xuất tưới và kiểm tra ghi đè. | Hạn mức của kỳ được lưu, dùng để giới hạn đề xuất tưới và kiểm tra ghi đè. |
| Primary Actor(s) | Quản lý tổ thủy nông | Quản lý tổ thủy nông |
| Secondary Actor(s) | Không có | Không có |
| Trigger | Đầu mỗi kỳ cấp nước, Quản lý tổ chọn chức năng "5" (Hạn mức nước). | Đầu mỗi kỳ cấp nước, Quản lý tổ chọn chức năng "5" (Hạn mức nước). |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM5, hiển thị danh sách các kỳ hạn mức. |
|  | 2 | Quản lý tổ chọn Vùng tưới, Kỳ từ – đến, nhập Hạn mức (m³) và chọn Người lập. |
|  | 3 | Quản lý tổ nhấn "Thêm". |
|  | 4 | Hệ thống kiểm tra theo QĐ5 và thêm kỳ mới với Đã dùng = 0. |
|  | 5 | Khi chọn một kỳ, hệ thống hiển thị Đã dùng, Còn lại, thanh % sử dụng và Trạng thái (Bình thường, Sắp hết khi hạn mức còn lại dưới 20%, Đã hết khi còn lại 0 m³, Đã kết thúc). |
|  | 6 | Quản lý tổ nhấn "Lưu"; hệ thống lưu và thông báo. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 3a | Sửa: chọn kỳ đang áp dụng, sửa và nhấn "Sửa". |
|  | 3b | Xóa: chọn kỳ và nhấn "Xóa". |
|  | 4a | Ngày kết thúc không sau ngày bắt đầu, hoặc hạn mức nhỏ hơn hay bằng 0: hệ thống báo lỗi. |
|  | 4b | Kỳ bị trùng với kỳ đã cấp của cùng vùng: hệ thống báo lỗi. |
|  | 4c | Sửa kỳ đã kết thúc, hoặc hạn mức mới nhỏ hơn lượng đã dùng: hệ thống không cho sửa. |
|  | 4d | Xóa kỳ đã phát sinh lượng sử dụng: hệ thống không cho xóa. |
| Open Issues | 1 | Phần hạn mức chưa dùng có được chuyển sang kỳ sau hay không. |
|  | 2 | Có cần chia hạn mức xuống từng thửa hay chỉ quản lý theo vùng. |

### Bảng 2.11 Đặc tả usecase “Lập đề xuất tưới”

| UCID | UC09 | UC09 |
|---|---|---|
| Name | Lập đề xuất tưới | Lập đề xuất tưới |
| Summary | Hệ thống tính lượng nước và khung giờ tưới cho từng thửa dựa trên lịch mùa vụ, độ ẩm đất và hạn mức còn lại; mỗi đề xuất có phạm vi áp dụng rõ ràng (BM6, QĐ6). | Hệ thống tính lượng nước và khung giờ tưới cho từng thửa dựa trên lịch mùa vụ, độ ẩm đất và hạn mức còn lại; mỗi đề xuất có phạm vi áp dụng rõ ràng (BM6, QĐ6). |
| Preconditions | Thửa có lịch mùa vụ đang hiệu lực, có số đo độ ẩm hợp lệ và vùng có hạn mức của kỳ hiện tại. | Thửa có lịch mùa vụ đang hiệu lực, có số đo độ ẩm hợp lệ và vùng có hạn mức của kỳ hiện tại. |
| Postconditions | Danh sách đề xuất trong ngày được tạo ở trạng thái "Chờ xác nhận"; lượng đề xuất được giữ chỗ trong hạn mức. | Danh sách đề xuất trong ngày được tạo ở trạng thái "Chờ xác nhận"; lượng đề xuất được giữ chỗ trong hạn mức. |
| Primary Actor(s) | Bộ hẹn giờ | Bộ hẹn giờ |
| Secondary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Trigger | Đến 05:00 hằng ngày, hoặc Cán bộ kỹ thuật nhấn "Tạo đề xuất" trên BM6. | Đến 05:00 hằng ngày, hoặc Cán bộ kỹ thuật nhấn "Tạo đề xuất" trên BM6. |
| Main Scenario | Step | Action |
|  | 1 | Bộ hẹn giờ kích hoạt việc lập đề xuất lúc 05:00. |
|  | 2 | Hệ thống xác định giai đoạn hiện tại của từng thửa theo lịch mùa vụ. |
|  | 3 | Hệ thống lấy số đo độ ẩm hợp lệ mới nhất của thửa và mưa dự báo của vùng. |
|  | 4 | Nếu độ ẩm thấp hơn ngưỡng dưới, hệ thống tính V = (ngưỡng trên − độ ẩm)/100 × độ sâu rễ × diện tích / hiệu suất. Ví dụ T-014, T-015: (32 − 22)/100 × 0,3 × 1.000 / 0,9 = 33,3 m³. |
|  | 5 | Hệ thống đối chiếu với hạn mức còn lại của vùng, ưu tiên giai đoạn có mức ưu tiên cao. |
|  | 6 | Hệ thống tạo đề xuất (mã ĐX-yyyy-mmdd-xxx) gồm lượng nước, khung giờ 05:00–08:00 và phạm vi áp dụng: thửa, giai đoạn, hiệu lực 24 giờ, số đo căn cứ, căn cứ tính. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 1a | Cán bộ kỹ thuật nhấn "Tạo đề xuất": hệ thống lập lại đề xuất từ bước 2. |
|  | 3a | Thửa không có số đo hợp lệ (chỉ có số đo quá hạn): hệ thống không lập đề xuất cho thửa đó và báo cho Cán bộ kỹ thuật. |
|  | 4a | Độ ẩm không thấp hơn ngưỡng dưới hoặc mưa dự báo đủ bù: đề xuất "Không cần tưới", lượng 0 m³. |
|  | 5a | Hạn mức còn lại không đủ: hệ thống giảm lượng của các thửa theo thứ tự ưu tiên giai đoạn. |
|  | 6a | Có số đo mới lệch quá 5 điểm % so với số đo căn cứ: đề xuất cũ chuyển "Bị thay thế" và hệ thống lập đề xuất mới. |
|  | 6b | Quá thời hạn hiệu lực mà chưa xác nhận: đề xuất chuyển "Hết hiệu lực". |
| Open Issues | 1 | Cách quy đổi lượng mưa dự báo để trừ vào lượng tưới cho sát thực tế. |
|  | 2 | Nguồn lấy dữ liệu dự báo mưa (nhập tay hay kết nối dịch vụ dự báo). |

### Bảng 2.12 Đặc tả usecase “Tra cứu và xác nhận đề xuất”

| UCID | UC10 | UC10 |
|---|---|---|
| Name | Tra cứu và xác nhận đề xuất | Tra cứu và xác nhận đề xuất |
| Summary | Nông hộ tra cứu đề xuất tưới của thửa mình, xem phạm vi áp dụng và xác nhận thực hiện (BM6). | Nông hộ tra cứu đề xuất tưới của thửa mình, xem phạm vi áp dụng và xác nhận thực hiện (BM6). |
| Preconditions | Đã có đề xuất tưới trong ngày (UC09). | Đã có đề xuất tưới trong ngày (UC09). |
| Postconditions | Đề xuất được chuyển sang trạng thái "Đã xác nhận". | Đề xuất được chuyển sang trạng thái "Đã xác nhận". |
| Primary Actor(s) | Nông hộ | Nông hộ |
| Secondary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Trigger | Nông hộ chọn chức năng "6" (Đề xuất tưới) sau 05:00. | Nông hộ chọn chức năng "6" (Đề xuất tưới) sau 05:00. |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM6, hiển thị Hạn mức còn lại của vùng. |
|  | 2 | Nông hộ chọn Vùng, Từ ngày, Đến ngày, Trạng thái và nhấn "Lọc". |
|  | 3 | Hệ thống hiển thị danh sách đề xuất phù hợp. |
|  | 4 | Nông hộ chọn một đề xuất; hệ thống hiển thị khung Phạm vi áp dụng (thửa, giai đoạn, hiệu lực, số đo căn cứ, căn cứ tính). |
|  | 5 | Nông hộ nhấn "Xác nhận". |
|  | 6 | Hệ thống chuyển trạng thái đề xuất sang "Đã xác nhận". |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 5a | Đề xuất đã "Hết hiệu lực" hoặc "Bị thay thế": hệ thống báo không thể xác nhận. |
|  | 5b | Đề xuất không ở trạng thái "Chờ xác nhận": hệ thống thông báo chỉ xác nhận được đề xuất đang chờ. |
|  | 5c | Nông hộ không đồng ý với đề xuất: nhấn "Ghi đè..." và chuyển sang UC11 (quan hệ extend). |
| Open Issues | 1 | Có gửi thông báo đề xuất mới cho nông hộ qua SMS hoặc Zalo hay không. |
|  | 2 | Nông hộ không xác nhận trước khung giờ tưới thì xử lý thế nào. |

### Bảng 2.13 Đặc tả usecase “Ghi đè đề xuất có lý do”

| UCID | UC11 | UC11 |
|---|---|---|
| Name | Ghi đè đề xuất có lý do | Ghi đè đề xuất có lý do |
| Summary | Nông hộ hoãn tưới, đổi lượng hoặc đổi khung giờ so với đề xuất, bắt buộc chọn phạm vi và ghi lý do (BM7, QĐ8). | Nông hộ hoãn tưới, đổi lượng hoặc đổi khung giờ so với đề xuất, bắt buộc chọn phạm vi và ghi lý do (BM7, QĐ8). |
| Preconditions | Đề xuất đang "Chờ xác nhận" hoặc "Đã xác nhận" và còn hiệu lực. | Đề xuất đang "Chờ xác nhận" hoặc "Đã xác nhận" và còn hiệu lực. |
| Postconditions | Ghi đè được lưu cùng lý do; đề xuất chuyển "Đã ghi đè" hoặc "Chờ duyệt". | Ghi đè được lưu cùng lý do; đề xuất chuyển "Đã ghi đè" hoặc "Chờ duyệt". |
| Primary Actor(s) | Nông hộ | Nông hộ |
| Secondary Actor(s) | Quản lý tổ thủy nông (khi ghi đè vượt hạn mức) | Quản lý tổ thủy nông (khi ghi đè vượt hạn mức) |
| Trigger | Nông hộ chọn đề xuất trên BM6 và nhấn "Ghi đè...". | Nông hộ chọn đề xuất trên BM6 và nhấn "Ghi đè...". |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM7, hiển thị Đề xuất gốc (mã, lượng, khung giờ, thửa, hiệu lực), Người ghi đè và Ngày ghi đè. |
|  | 2 | Nông hộ chọn Hành động: Hoãn tưới, Đổi lượng hoặc Đổi khung giờ. |
|  | 3 | Nông hộ nhập Giá trị mới; hệ thống hiển thị mức chênh lệch so với đề xuất gốc. |
|  | 4 | Nông hộ chọn Phạm vi ghi đè (các thửa T-014, T-015 trong phạm vi đề xuất gốc). |
|  | 5 | Nông hộ chọn Loại lý do và nhập Mô tả lý do. |
|  | 6 | Nông hộ nhấn "Gửi ghi đè". |
|  | 7 | Hệ thống kiểm tra theo QĐ8; nếu không vượt hạn mức, đề xuất chuyển "Đã ghi đè". |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 2a | Chọn Hoãn tưới: không cần nhập giá trị mới. |
|  | 7a | Chưa chọn thửa trong phạm vi ghi đè hoặc chưa chọn loại lý do: hệ thống báo lỗi. |
|  | 7b | Mô tả lý do dưới 15 ký tự: hệ thống báo lỗi. |
|  | 7c | Lượng tăng vượt hạn mức còn lại (ví dụ xin 45,0 m³, tăng 11,7 m³ trong khi vùng V-02 chỉ còn 8 m³): hệ thống hiện cảnh báo, chuyển trạng thái "Chờ duyệt" và chuyển sang UC12. |
| Open Issues | 1 | Có giới hạn số lần ghi đè của một thửa trong một kỳ hay không. |
|  | 2 | Có cần đính kèm ảnh hiện trường làm bằng chứng cho lý do ghi đè hay không. |

### Bảng 2.14 Đặc tả usecase “Phê duyệt ghi đè”

| UCID | UC12 | UC12 |
|---|---|---|
| Name | Phê duyệt ghi đè | Phê duyệt ghi đè |
| Summary | Quản lý tổ thủy nông chấp nhận, điều chỉnh hoặc từ chối yêu cầu ghi đè vượt hạn mức (BM7, QĐ9). | Quản lý tổ thủy nông chấp nhận, điều chỉnh hoặc từ chối yêu cầu ghi đè vượt hạn mức (BM7, QĐ9). |
| Preconditions | Có yêu cầu ghi đè ở trạng thái "Chờ duyệt". | Có yêu cầu ghi đè ở trạng thái "Chờ duyệt". |
| Postconditions | Ghi đè được áp dụng theo lượng duyệt, hoặc bị từ chối và giữ nguyên đề xuất gốc; hệ thống lưu người duyệt và ý kiến. | Ghi đè được áp dụng theo lượng duyệt, hoặc bị từ chối và giữ nguyên đề xuất gốc; hệ thống lưu người duyệt và ý kiến. |
| Primary Actor(s) | Quản lý tổ thủy nông | Quản lý tổ thủy nông |
| Secondary Actor(s) | Nông hộ (nhận kết quả duyệt) | Nông hộ (nhận kết quả duyệt) |
| Trigger | Quản lý tổ mở yêu cầu ghi đè đang "Chờ duyệt" (chức năng "7"). | Quản lý tổ mở yêu cầu ghi đè đang "Chờ duyệt" (chức năng "7"). |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM7, hiển thị đề xuất gốc, nội dung ghi đè, lý do và cảnh báo vượt hạn mức. |
|  | 2 | Quản lý tổ chọn Quyết định: Chấp nhận, Điều chỉnh hoặc Từ chối. |
|  | 3 | Quản lý tổ nhập Ý kiến. |
|  | 4 | Quản lý tổ nhấn "Duyệt". |
|  | 5 | Hệ thống kiểm tra theo QĐ9, cập nhật trạng thái và ghi nhận người duyệt. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 2a | Chọn Điều chỉnh: nhập Lượng duyệt, không vượt đề xuất gốc cộng hạn mức còn lại (33,3 + 8 = 41,3 m³). |
|  | 5a | Lượng duyệt vượt mức cho phép: hệ thống báo lỗi. |
|  | 5b | Chọn Điều chỉnh hoặc Từ chối mà không nhập ý kiến: hệ thống báo lỗi. |
|  | 5c | Từ chối hợp lệ: đề xuất gốc được giữ nguyên. |
| Open Issues | 1 | Quản lý tổ chưa duyệt khi đã đến khung giờ tưới thì xử lý thế nào. |
|  | 2 | Có cho phép xin cấp bổ sung hạn mức cho vùng thay vì chỉ điều chỉnh lượng hay không. |

### Bảng 2.15 Đặc tả usecase “Ghi lượng tưới thực tế”

| UCID | UC13 | UC13 |
|---|---|---|
| Name | Ghi lượng tưới thực tế | Ghi lượng tưới thực tế |
| Summary | Sau khi tưới, ghi lượng nước đã dùng thật cho đề xuất đã xác nhận hoặc đã ghi đè (BM6, QĐ7). | Sau khi tưới, ghi lượng nước đã dùng thật cho đề xuất đã xác nhận hoặc đã ghi đè (BM6, QĐ7). |
| Preconditions | Đề xuất ở trạng thái "Đã xác nhận" hoặc "Đã ghi đè". | Đề xuất ở trạng thái "Đã xác nhận" hoặc "Đã ghi đè". |
| Postconditions | Lượng thực tế được ghi vào đề xuất và thay cho lượng giữ chỗ trong hạn mức của vùng. | Lượng thực tế được ghi vào đề xuất và thay cho lượng giữ chỗ trong hạn mức của vùng. |
| Primary Actor(s) | Nông hộ | Nông hộ |
| Secondary Actor(s) | Cán bộ kỹ thuật | Cán bộ kỹ thuật |
| Trigger | Nông hộ tưới xong theo đề xuất. | Nông hộ tưới xong theo đề xuất. |
| Main Scenario | Step | Action |
|  | 1 | Nông hộ mở BM6 và lọc đề xuất của mình. |
|  | 2 | Nông hộ chọn đề xuất đã tưới. |
|  | 3 | Nông hộ nhập Lượng thực tế (m³). |
|  | 4 | Nông hộ nhấn "Ghi thực tế". |
|  | 5 | Hệ thống kiểm tra theo QĐ7, ghi vào cột Thực tế và cập nhật lượng đã dùng của vùng. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 2a | Chưa chọn đề xuất: hệ thống yêu cầu chọn một đề xuất. |
|  | 5a | Đề xuất không ở trạng thái "Đã xác nhận" hoặc "Đã ghi đè": hệ thống báo lỗi. |
|  | 5b | Lượng thực tế không phải số hoặc nhỏ hơn hay bằng 0: hệ thống báo lỗi. |
| Open Issues | 1 | Có lấy số liệu tự động từ đồng hồ đo nước hay không. |
|  | 2 | Khi lượng thực tế chênh lệch lớn so với đề xuất, có cần cảnh báo cho Quản lý tổ hay không. |

### Bảng 2.16 Đặc tả usecase “Báo cáo sử dụng nước”

| UCID | UC14 | UC14 |
|---|---|---|
| Name | Báo cáo sử dụng nước | Báo cáo sử dụng nước |
| Summary | Tổng hợp tình hình sử dụng nước của vùng trong kỳ: hạn mức, đã dùng, còn lại, so sánh đề xuất với thực tế theo thửa và thống kê ghi đè theo lý do (BM8, QĐ10). | Tổng hợp tình hình sử dụng nước của vùng trong kỳ: hạn mức, đã dùng, còn lại, so sánh đề xuất với thực tế theo thửa và thống kê ghi đè theo lý do (BM8, QĐ10). |
| Preconditions | Đã có dữ liệu đề xuất và lượng thực tế trong kỳ. | Đã có dữ liệu đề xuất và lượng thực tế trong kỳ. |
| Postconditions | Báo cáo được hiển thị và có thể xuất ra file CSV. | Báo cáo được hiển thị và có thể xuất ra file CSV. |
| Primary Actor(s) | Quản lý tổ thủy nông | Quản lý tổ thủy nông |
| Secondary Actor(s) | Ban quản trị | Ban quản trị |
| Trigger | Cuối kỳ hoặc khi cần đánh giá, người dùng chọn chức năng "8" (Báo cáo sử dụng nước). | Cuối kỳ hoặc khi cần đánh giá, người dùng chọn chức năng "8" (Báo cáo sử dụng nước). |
| Main Scenario | Step | Action |
|  | 1 | Hệ thống mở BM8. |
|  | 2 | Người dùng chọn Vùng, Kỳ và nhấn "Xem báo cáo". |
|  | 3 | Hệ thống hiển thị Hạn mức, Đã dùng (thực tế + giữ chỗ), Còn lại và % sử dụng (ví dụ V-02: 1.500 / 1.492 / 8 m³, 99,5%). |
|  | 4 | Hệ thống hiển thị bảng theo thửa: Đề xuất, Thực tế, Chênh lệch, Số lần ghi đè, % hạn mức và dòng Tổng. |
|  | 5 | Hệ thống hiển thị bảng thống kê ghi đè theo lý do (số lần, tỷ lệ). |
|  | 6 | Người dùng nhấn "Xuất CSV", chọn nơi lưu; hệ thống xuất file. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 2a | Vùng hoặc kỳ chưa có dữ liệu: hệ thống thông báo "Chưa có dữ liệu" và để trống báo cáo. |
|  | 6a | Người dùng hủy hộp thoại lưu: hệ thống không xuất file. |
| Open Issues | 1 | Có cần xuất báo cáo dạng Excel hoặc PDF có định dạng sẵn hay không. |
|  | 2 | Có cần so sánh nhiều kỳ hoặc nhiều vùng trên cùng một báo cáo hay không. |

### Bảng 2.17 Đặc tả usecase “Thay đổi quy định”

| UCID | UC15 | UC15 |
|---|---|---|
| Name | Thay đổi quy định | Thay đổi quy định |
| Summary | Ban quản trị quyết định và cập nhật các tham số quy định của hệ thống (QĐ11). | Ban quản trị quyết định và cập nhật các tham số quy định của hệ thống (QĐ11). |
| Preconditions | Đăng nhập với quyền Ban quản trị hoặc Quản trị hệ thống. | Đăng nhập với quyền Ban quản trị hoặc Quản trị hệ thống. |
| Postconditions | Quy định mới được lưu kèm ngày áp dụng; áp dụng cho các đề xuất lập từ ngày đó. | Quy định mới được lưu kèm ngày áp dụng; áp dụng cho các đề xuất lập từ ngày đó. |
| Primary Actor(s) | Ban quản trị | Ban quản trị |
| Secondary Actor(s) | Quản trị hệ thống | Quản trị hệ thống |
| Trigger | Ban quản trị quyết định điều chỉnh quy định. | Ban quản trị quyết định điều chỉnh quy định. |
| Main Scenario | Step | Action |
|  | 1 | Người dùng chọn chức năng "Thay đổi quy định". |
|  | 2 | Hệ thống hiển thị các tham số hiện hành: giờ lập đề xuất (05:00), thời hạn hiệu lực (24 giờ), mức lệch thay thế (5 điểm %), độ dài mô tả lý do tối thiểu (15 ký tự), thời hạn số đo (6 giờ), ngưỡng cảnh báo hạn mức (còn lại 20%), hệ số mưa hữu hiệu (0,8), hiệu suất các hình thức tưới. |
|  | 3 | Người dùng sửa giá trị và nhập ngày áp dụng. |
|  | 4 | Người dùng nhấn "Lưu". |
|  | 5 | Hệ thống kiểm tra, lưu quy định mới và ghi lịch sử thay đổi. |
| Extensions | Step | Hoạt động rẽ nhánh |
|  | 5a | Giá trị âm hoặc ngoài phạm vi cho phép: hệ thống báo lỗi. |
|  | 5b | Ngày áp dụng trước ngày hiện tại: hệ thống báo lỗi. |
| Open Issues | 1 | Chức năng chưa có form riêng trong bộ BM1–BM8. |
|  | 2 | Các đề xuất đã lập trước ngày áp dụng có được tính lại theo quy định mới hay không. |
