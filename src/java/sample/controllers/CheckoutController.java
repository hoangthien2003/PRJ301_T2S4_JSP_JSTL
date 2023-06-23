/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.OrderDTO;
import sample.shopping.OrderDetailDTO;
import sample.shopping.Tea;
import sample.shopping.TeaDAO;
import sample.user.UserDTO;

/**
 *
 * @author hoang
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {
    private static final String ERROR = "view_cart.jsp";
    private static final String SUCCESS = "checkout.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("CART");
            TeaDAO teaDAO = new TeaDAO();
            if (cart == null) {
                request.setAttribute("ERROR", "Cart is empty!");
            } else {
                boolean isSoldOut = false;
                boolean isError = false;
                for (Tea tea : cart.getCart().values()) {
                    isSoldOut = teaDAO.checkSoldOut(tea.getId().trim(), tea.getQuantity());
                    if (isSoldOut) {
                        request.setAttribute("ERROR", tea.getName() + " is sold out! Check again");
                        isError = true;
                    }
                }
                if (isError) {
                    UUID uuid = UUID.randomUUID();
                    String orderID = uuid.toString();
                    UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
                    String userID = "";
                    if (loginUser == null) userID = "admin";
//                    String userID = loginUser.getUserID();
                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    double total = Double.parseDouble(request.getParameter("total"));
                    boolean isInsertOrder = teaDAO.insertOrder(new OrderDTO(orderID, userID, date, total));
                    if (isInsertOrder) {
                        boolean isInsertDetail = false;
                        boolean isUpdate = false;
                        for (Tea tea : cart.getCart().values()) {
                            uuid = UUID.randomUUID();
                            String orderDetailID = uuid.toString();
                            String productID = tea.getId();
                            double price = tea.getPrice();
                            int quantity = tea.getQuantity();
                            isInsertDetail = teaDAO.insertOrderDetail(
                                    new OrderDetailDTO(orderDetailID, orderID, productID, price, quantity)
                            );
                            isUpdate = teaDAO.updateQuantity(tea.getId(), tea.getQuantity());
                        }
                        if (isInsertDetail && isUpdate) {
                            request.setAttribute("MESSAGE", "Checkout successfully!");
                            cart.setCart(null);
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
