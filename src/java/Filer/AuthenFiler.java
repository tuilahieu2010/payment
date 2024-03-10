/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filer;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Users;

/**
 *
 * @author DAO
 */
@WebFilter(filterName = "AuthenFiler", urlPatterns = {"/*"})
public class AuthenFiler implements Filter {
        private static final int US = 2;
        private static final int AD = 1;   
        private static final String LOGIN_PAGE = "login.jsp";
        private static final List<String>ADMIN_FUNC = new ArrayList<>();
        private static final List<String>USER_FUNC = new ArrayList<>();
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthenFiler() {
        ADMIN_FUNC.add("LOGIN_PAGE");
        ADMIN_FUNC.add("LoginServlet");
        ADMIN_FUNC.add("admin.jsp");
        ADMIN_FUNC.add("ViewMovieServlet");
        ADMIN_FUNC.add("addmovie.jsp");
        ADMIN_FUNC.add("ScheduleMovieServlet");
        ADMIN_FUNC.add("MovieShowServlet");
        ADMIN_FUNC.add("addroom.jsp");
        ADMIN_FUNC.add("ViewRoomServlet");
        ADMIN_FUNC.add("EditMovieServlet1");
        ADMIN_FUNC.add("DeleteMovieServlet");
        ADMIN_FUNC.add("EditMovieShowServlet1");
        ADMIN_FUNC.add("DeleteMovieShowServlet");
        ADMIN_FUNC.add("EditRoomServlet");
        ADMIN_FUNC.add("DeleteRoomServlet");
        
        
    }    
    
    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            String resource = uri.substring(uri.lastIndexOf("/") + 1); // Lấy tên resource từ URI

            // Tối ưu hóa điều kiện kiểm tra URI
            if (uri.endsWith(".jpg") || uri.endsWith(".png")) {
                chain.doFilter(request, response);
                return;
            }

            // Kiểm tra trang đăng nhập và các servlet liên quan
            if (uri.contains(LOGIN_PAGE) || resource.equals("LoginServlet")) {
                chain.doFilter(request, response);
                return;
            }

            // Kiểm tra session
            HttpSession session = req.getSession(false); // Không tạo mới session nếu không tồn tại
            if (session == null || session.getAttribute("user") == null) {
                res.sendRedirect(LOGIN_PAGE);
                return;
            }

            // Kiểm tra quyền truy cập của user
            Users user = (Users) session.getAttribute("user");
            int roleID = user.getRoleId();
            if ((roleID == US && USER_FUNC.contains(resource)) || (roleID == AD && ADMIN_FUNC.contains(resource))) {
                chain.doFilter(request, response);
            } else {
                // Không chuyển hướng người dùng về trang đăng nhập nếu đã đăng nhập thành công
                chain.doFilter(request, response);
            }
        }
    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
        @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
        @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthenFiler:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFiler()");
        }
        StringBuilder sb = new StringBuilder("AuthenFiler(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
