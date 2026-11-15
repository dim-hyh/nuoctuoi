# ================================================================
#  KIỂM THỬ TỰ ĐỘNG WEB QUẢN LÝ NƯỚC TƯỚI
#  Chạy:  powershell -ExecutionPolicy Bypass -File .\test-web.ps1
#  Web phải đang chạy ở http://localhost:8080
#  LƯU Ý: script tự "Khôi phục dữ liệu mẫu" lúc đầu và lúc cuối.
# ================================================================
$B = "http://localhost:8080"
$global:dat = 0; $global:truot = 0
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

function KQ($ten, $dung) {
    if ($dung) { $global:dat++;   Write-Host ("[ĐẠT]  " + $ten) -ForegroundColor Green }
    else       { $global:truot++; Write-Host ("[LỖI]  " + $ten) -ForegroundColor Red }
}
function Lay($s, $url) {
    try {
        $r = Invoke-WebRequest -Uri ($B + $url) -WebSession $s -UseBasicParsing -MaximumRedirection 5
        return @{ Ma = [int]$r.StatusCode; Noi = [string]$r.Content }
    } catch {
        $ma = 0; if ($_.Exception.Response) { $ma = [int]$_.Exception.Response.StatusCode }
        return @{ Ma = $ma; Noi = "" }
    }
}
function Token($noi) {
    if ($noi -match 'name="_csrf"\s+value="([^"]+)"') { return $matches[1] }
    if ($noi -match 'value="([^"]+)"\s+name="_csrf"') { return $matches[1] }
    return ""
}
function Gui($s, $url, $duLieu, $trangToken) {
    $t = Token (Lay $s $trangToken).Noi
    $duLieu["_csrf"] = $t
    $body = ($duLieu.GetEnumerator() | ForEach-Object { [uri]::EscapeDataString($_.Key) + "=" + [uri]::EscapeDataString([string]$_.Value) }) -join "&"
    try {
        $r = Invoke-WebRequest -Uri ($B + $url) -Method Post -Body $body -ContentType "application/x-www-form-urlencoded" `
             -WebSession $s -UseBasicParsing -MaximumRedirection 5
        return @{ Ma = [int]$r.StatusCode; Noi = [string]$r.Content }
    } catch {
        $ma = 0; if ($_.Exception.Response) { $ma = [int]$_.Exception.Response.StatusCode }
        return @{ Ma = $ma; Noi = "" }
    }
}
function DangNhap($ten, $mk) {
    $s = New-Object Microsoft.PowerShell.Commands.WebRequestSession
    $r = Gui $s "/dang-nhap" @{ tenDangNhap = $ten; matKhau = $mk } "/dang-nhap"
    return @{ Phien = $s; Noi = $r.Noi }
}
function DaVao($noi) { return $noi.Contains("/dang-xuat") }

Write-Host "`n===== 0. Chuẩn bị =====" -ForegroundColor Cyan
$kt = Lay (New-Object Microsoft.PowerShell.Commands.WebRequestSession) "/dang-nhap"
if ($kt.Ma -ne 200) { Write-Host "Không kết nối được http://localhost:8080 - hãy chạy web trước." -ForegroundColor Red; exit }
function KhoiPhuc() {
    $q = DangNhap "tam" "123"
    if (-not (DaVao $q.Noi)) { $q = DangNhap "admin" "123" }
    Gui $q.Phien "/quy-dinh/khoi-phuc" @{} "/quy-dinh" | Out-Null
}
KhoiPhuc
Write-Host "Đã khôi phục dữ liệu mẫu."

Write-Host "`n===== 1. Đăng nhập, bảo mật =====" -ForegroundColor Cyan
KQ "01 Trang đăng nhập mở được" ($kt.Noi.Contains('name="matKhau"'))
$r = Lay (New-Object Microsoft.PowerShell.Commands.WebRequestSession) "/de-xuat"
KQ "02 Chưa đăng nhập vào /de-xuat bị chuyển về trang đăng nhập" ($r.Noi.Contains('name="matKhau"') -and -not (DaVao $r.Noi))
$sai = DangNhap "hoa" "sai"
KQ "03 Sai mật khẩu thì không vào được" (-not (DaVao $sai.Noi))
$hoa = DangNhap "hoa" "123";   KQ "04 Đăng nhập hoa (Cán bộ kỹ thuật)" (DaVao $hoa.Noi)
$an  = DangNhap "an" "123";    KQ "05 Đăng nhập an (Nông hộ)" (DaVao $an.Noi)
$binh = DangNhap "binh" "123"; KQ "06 Đăng nhập binh (Quản lý tổ)" (DaVao $binh.Noi)
$ad  = DangNhap "admin" "123"; KQ "07 Đăng nhập admin (Quản trị hệ thống)" (DaVao $ad.Noi)
$tam = DangNhap "tam" "123";   KQ "07b Đăng nhập tam (Ban quản trị)" (DaVao $tam.Noi)
$s0 = New-Object Microsoft.PowerShell.Commands.WebRequestSession
KQ "08 CSS tải được khi chưa đăng nhập" ((Lay $s0 "/css/site.css").Ma -eq 200)
KQ "09 Ảnh nền đăng nhập tải được" ((Lay $s0 "/img/nen-dang-nhap.png").Ma -eq 200)
# Gửi form đổi mật khẩu KHÔNG kèm mã CSRF; nếu bị chặn thì mật khẩu vẫn là 123
$sx = (DangNhap "an" "123").Phien
try { Invoke-WebRequest -Uri ($B + "/doi-mat-khau") -Method Post -Body "matKhauCu=123&matKhauMoi=999&nhapLai=999" `
        -ContentType "application/x-www-form-urlencoded" -WebSession $sx -UseBasicParsing | Out-Null } catch { }
$lai = DangNhap "an" "123"
KQ "10 Gửi form không có mã CSRF bị chặn (mật khẩu không bị đổi)" (DaVao $lai.Noi)

Write-Host "`n===== 2. Phân quyền =====" -ForegroundColor Cyan
$bang = @(
  @("hoa",  $hoa,  @("/thua-ruong","/cay-trong","/lich-mua-vu","/so-lieu","/de-xuat","/doi-mat-khau"), @("/han-muc","/phe-duyet","/quy-dinh","/tai-khoan","/bao-cao")),
  @("an",   $an,   @("/de-xuat","/doi-mat-khau"),                                  @("/thua-ruong","/so-lieu","/han-muc","/phe-duyet","/tai-khoan","/bao-cao","/quy-dinh")),
  @("binh", $binh, @("/han-muc","/phe-duyet","/de-xuat","/bao-cao"),               @("/thua-ruong","/so-lieu","/tai-khoan","/quy-dinh")),
  @("tam",  $tam,  @("/bao-cao","/quy-dinh","/de-xuat"),                           @("/han-muc","/phe-duyet","/thua-ruong","/tai-khoan")),
  @("admin",$ad,   @("/tai-khoan","/quy-dinh","/doi-mat-khau"),                    @("/thua-ruong","/han-muc","/phe-duyet","/bao-cao"))
)
foreach ($d in $bang) {
    $ok = $true; $sai = @()
    foreach ($u in $d[2]) { $r = Lay $d[1].Phien $u; if ($r.Ma -ne 200) { $ok = $false; $sai += "$u($($r.Ma))" } }
    KQ ("11 " + $d[0] + " vào được trang của mình " + ($sai -join " ")) $ok
    $ok = $true; $sai = @()
    foreach ($u in $d[3]) { $r = Lay $d[1].Phien $u; if ($r.Ma -eq 200 -and -not $r.Noi.Contains("khong-co-quyen") -and -not $r.Noi.Contains("Không có quyền")) { $ok = $false; $sai += $u } }
    KQ ("12 " + $d[0] + " bị chặn trang không thuộc quyền " + ($sai -join " ")) $ok
}

Write-Host "`n===== 3. Nghiệp vụ đề xuất - ghi đè - phê duyệt =====" -ForegroundColor Cyan
$r = Gui $hoa.Phien "/de-xuat/lap" @{ ngay = "2026-10-06" } "/de-xuat"
$r = Lay $hoa.Phien "/de-xuat"
KQ "13 Cán bộ lập đề xuất ngày 06/10: có ĐX-2026-1006-014 = 33,3 m³ chờ xác nhận" ($r.Noi.Contains("ĐX-2026-1006-014") -and $r.Noi.Contains("33,3"))
$r = Lay $binh.Phien "/han-muc"
KQ "14 Hạn mức V-02 còn lại 8, báo Sắp hết (QĐ5)" ($r.Noi.Contains("Sắp hết"))
$ma = "ĐX-2026-1006-014"
$trangGD = "/de-xuat/ghi-de?ma=" + [uri]::EscapeDataString($ma)
$r = Gui $an.Phien "/de-xuat/ghi-de" @{ ma = $ma; lyDo = "Nắng nóng bất thường"; moTa = "ngắn"; luongMoi = "45" } $trangGD
KQ "15 Ghi đè mô tả dưới 15 ký tự bị từ chối (QĐ8)" ($r.Noi.Contains("QĐ8"))
$r = Gui $an.Phien "/de-xuat/ghi-de" @{ ma = $ma; lyDo = "Nắng nóng bất thường"; moTa = "Trời nắng gắt, đất khô nhanh hơn dự kiến"; luongMoi = "45" } $trangGD
$r = Lay $binh.Phien "/phe-duyet"
KQ "16 Ghi đè 45 m³ tạo phiếu PGĐ-001 chờ duyệt" ($r.Noi.Contains("PGĐ-001") -and $r.Noi.Contains("Chờ duyệt"))
$trangD = "/phe-duyet/duyet?ma=" + [uri]::EscapeDataString("PGĐ-001")
$r = Gui $binh.Phien "/phe-duyet/duyet" @{ ma = "PGĐ-001"; quyetDinh = "Chấp nhận"; luongDuyet = "45"; yKien = "" } $trangD
KQ "17 Chấp nhận 45 m³ bị chặn vì vượt 41,3 (QĐ9)" ($r.Noi.Contains("41.3") -or $r.Noi.Contains("41,3"))
$r = Gui $binh.Phien "/phe-duyet/duyet" @{ ma = "PGĐ-001"; quyetDinh = "Điều chỉnh"; luongDuyet = "41.3"; yKien = "" } $trangD
KQ "18 Điều chỉnh không ghi ý kiến bị từ chối (QĐ9)" ($r.Noi.Contains("ý kiến"))
$r = Gui $binh.Phien "/phe-duyet/duyet" @{ ma = "PGĐ-001"; quyetDinh = "Điều chỉnh"; luongDuyet = "41.3"; yKien = "Duyệt tối đa theo hạn mức" } $trangD
$r = Lay $an.Phien "/de-xuat"
KQ "19 Duyệt điều chỉnh 41,3: đề xuất áp dụng 41,3 (gốc 33,3)" ($r.Noi.Contains("41,3") -and $r.Noi.Contains("gốc 33,3"))
$r = Gui $an.Phien "/de-xuat/thuc-te" @{ ma = $ma; luong = "41.3" } "/de-xuat"
KQ "20 Ghi lượng thực tế, trạng thái Đã thực hiện" ($r.Noi.Contains("Đã thực hiện"))

Write-Host "`n===== 4. Quy định khi nhập liệu và lập đề xuất =====" -ForegroundColor Cyan
$r = Gui $hoa.Phien "/so-lieu/them-so-do" @{ maThua = "T-014"; thoiDiem = "2026-10-07T04:40"; doAm = "150" } "/so-lieu"
KQ "21 Độ ẩm 150% bị từ chối (QĐ4)" ($r.Noi.Contains("QĐ4"))
$r = Gui $hoa.Phien "/so-lieu/them-so-do" @{ maThua = "T-014"; thoiDiem = "2026-10-07T05:20"; doAm = "22" } "/so-lieu"
$r = Gui $hoa.Phien "/de-xuat/lap" @{ ngay = "2026-10-07" } "/de-xuat"
KQ "22 Số đo lúc 05:20 không được dùng (QĐ4)" ($r.Noi.Contains("QĐ4"))
$r = Gui $hoa.Phien "/de-xuat/lap" @{ ngay = "2026-12-20" } "/de-xuat"
KQ "23 Ngày ngoài mùa vụ thì không lập đề xuất" ($r.Noi.Contains("lịch mùa vụ"))
$r = Gui $hoa.Phien "/de-xuat/lap" @{ ngay = "2026-10-06" } "/de-xuat"
KQ "24 Lập lại ngày đã có đề xuất bị báo trùng" ($r.Noi.Contains("trước đó"))
$r = Gui $hoa.Phien "/lich-mua-vu/them" @{ maCay = "C01"; ngayGieo = "2026-10-01"; thuas = "T-014" } "/lich-mua-vu"
KQ "25 Lập lịch trùng thời gian cho T-014 bị chặn (QĐ3)" ($r.Noi.Contains("QĐ3"))
$r = Gui $hoa.Phien "/thua-ruong/them" @{ maThua = "T-099"; maVung = "V-02"; chuHo = "Thử"; dienTich = "0"; loaiDat = "Thịt"; hinhThucTuoi = "Nhỏ giọt" } "/thua-ruong"
KQ "26 Diện tích 0 m² bị từ chối (QĐ1)" ($r.Noi.Contains("QĐ1"))
$r = Gui $hoa.Phien "/doi-mat-khau" @{ matKhauCu = "sai"; matKhauMoi = "456"; nhapLai = "456" } "/doi-mat-khau"
KQ "27 Đổi mật khẩu sai mật khẩu cũ bị từ chối" ($r.Noi.Contains("không đúng"))
$r = Gui $ad.Phien "/tai-khoan/them" @{ tenDangNhap = "hoa"; hoTen = "Trùng"; vaiTro = "NongHo"; matKhau = "123" } "/tai-khoan"
KQ "28 Thêm tài khoản trùng tên bị từ chối" ($r.Noi.Contains("đã tồn tại"))

Write-Host "`n===== 5. Bị thay thế, hết hiệu lực, số đo quá hạn =====" -ForegroundColor Cyan
KhoiPhuc
$hoa = DangNhap "hoa" "123"; $an = DangNhap "an" "123"
Gui $hoa.Phien "/de-xuat/lap" @{ ngay = "2026-10-06" } "/de-xuat" | Out-Null
$r = Gui $hoa.Phien "/so-lieu/them-so-do" @{ maThua = "T-014"; thoiDiem = "2026-10-06T07:00"; doAm = "15" } "/so-lieu"
KQ "29 Số đo mới lệch 7 điểm % (> 5): đề xuất cũ chuyển Bị thay thế (QĐ6)" ($r.Noi.Contains("Bị thay thế"))
$r = Lay $hoa.Phien "/de-xuat"
KQ "30 Hệ thống lập đề xuất mới ĐX-2026-1006-014-2" ($r.Noi.Contains("ĐX-2026-1006-014-2"))
$r = Gui $hoa.Phien "/so-lieu/them-so-do" @{ maThua = "T-015"; thoiDiem = "2026-10-06T07:30"; doAm = "24" } "/so-lieu"
KQ "31 Số đo lệch ít (2 điểm %) thì không thay đề xuất" (-not $r.Noi.Contains("chuyển Bị thay thế"))
$r = Gui $hoa.Phien "/de-xuat/lap" @{ ngay = "2026-10-08" } "/de-xuat"
KQ "32 Lập đề xuất ngày 08/10: đề xuất cũ quá 24 giờ chuyển Hết hiệu lực" ($r.Noi.Contains("Hết hiệu lực"))
$r = Gui $an.Phien "/de-xuat/xac-nhan" @{ ma = "ĐX-2026-1006-014-2" } "/de-xuat"
KQ "33 Không xác nhận được đề xuất đã hết hiệu lực" ($r.Noi.Contains("hết hiệu lực"))
$r = Lay $hoa.Phien "/so-lieu"
KQ "34 Số đo ngày 06/10 hiển thị Quá hạn (QĐ4)" ($r.Noi.Contains("Quá hạn"))
$r = Gui $tam.Phien "/quy-dinh/luu" @{ gioLap = "5"; hanSoDoGio = "6"; hieuLucGio = "24"; mucLechThayThe = "5"; canhBaoPhanTram = "20"; heSoMua = "0.8"; moTaToiThieu = "15"; hsNhoGiot = "0.9"; hsPhunMua = "0.75"; hsRanh = "0.6" } "/quy-dinh"
KQ "35 Ban quản trị lưu được quy định (QĐ11)" ($r.Noi.Contains("Đã lưu quy định"))

Write-Host "`n===== Dọn dẹp =====" -ForegroundColor Cyan
KhoiPhuc
Write-Host "Đã khôi phục dữ liệu mẫu."
Write-Host ("`nKẾT QUẢ: " + $global:dat + " đạt, " + $global:truot + " lỗi") -ForegroundColor Yellow
