package com.nuoctuoi.config;

import com.nuoctuoi.dto.NguoiDungPhien;
import com.nuoctuoi.model.HangSo;
import com.nuoctuoi.model.NguoiDung;
import com.nuoctuoi.repository.NguoiDungRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Cấu hình Spring Security: đăng nhập, đăng xuất, phân quyền theo vai trò
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Mã hóa mật khẩu bằng BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Lấy tài khoản từ CSDL để Spring Security kiểm tra đăng nhập
    @Bean
    public UserDetailsService userDetailsService(NguoiDungRepository nguoiDungRepo) {
        return tenDangNhap -> {
            NguoiDung nd = nguoiDungRepo.findByTenDangNhap(tenDangNhap)
                    .orElseThrow(() -> new UsernameNotFoundException("Không có tài khoản " + tenDangNhap));
            return User.withUsername(nd.getTenDangNhap())
                    .password(nd.getMatKhauHash())
                    .roles(nd.getVaiTro())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, NguoiDungRepository nguoiDungRepo)
            throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/dang-nhap", "/css/**", "/img/**", "/error").permitAll()
                // Cán bộ kỹ thuật
                .requestMatchers("/thua-ruong/**", "/cay-trong/**", "/lich-mua-vu/**",
                                 "/so-lieu/**", "/de-xuat/lap").hasRole(HangSo.CAN_BO)
                // Nông hộ
                .requestMatchers("/de-xuat/xac-nhan", "/de-xuat/ghi-de", "/de-xuat/thuc-te")
                                 .hasRole(HangSo.NONG_HO)
                // Quản lý tổ thủy nông
                .requestMatchers("/han-muc/**", "/phe-duyet/**").hasRole(HangSo.QUAN_LY)
                // Báo cáo sử dụng nước (BM8): Quản lý tổ thủy nông, Ban quản trị
                .requestMatchers("/bao-cao/**").hasAnyRole(HangSo.QUAN_LY, HangSo.BAN_QUAN_TRI)
                // Thay đổi quy định (QĐ11): Ban quản trị, Quản trị hệ thống
                .requestMatchers("/quy-dinh/**").hasAnyRole(HangSo.BAN_QUAN_TRI, HangSo.QUAN_TRI)
                // Quản lý tài khoản
                .requestMatchers("/tai-khoan/**").hasRole(HangSo.QUAN_TRI)
                // Các trang còn lại: chỉ cần đăng nhập
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/dang-nhap")
                .loginProcessingUrl("/dang-nhap")
                .usernameParameter("tenDangNhap")
                .passwordParameter("matKhau")
                .failureUrl("/dang-nhap?loi")
                // Đăng nhập thành công: lưu thông tin người dùng vào phiên để giao diện hiển thị
                .successHandler((request, response, authentication) -> {
                    NguoiDung nd = nguoiDungRepo.findByTenDangNhap(authentication.getName()).orElseThrow();
                    request.getSession().setAttribute("nd",
                            new NguoiDungPhien(nd.getTenDangNhap(), nd.getHoTen(), nd.getVaiTro()));
                    response.sendRedirect("/");
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/dang-xuat")
                .logoutSuccessUrl("/dang-nhap")
                .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/khong-co-quyen"));

        return http.build();
    }
}