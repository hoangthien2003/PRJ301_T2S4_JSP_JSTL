/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author hoang
 */
public class TeaDAO {
    private static final String GET_ALL_TEA = "SELECT * FROM tblProducts";
    private static final String QUANTITY = "SELECT quantity FROM tblProducts WHERE productID = ?";
    private static final String INSERT_ORDER = "INSERT INTO tblOrders(orderID, userID, date, total) VALUES(?,?,?,?)";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO tblOrderDetails(orderDetailID, orderID, productID, price, quantity) VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUANTITY = "UPDATE tblProducts SET quantity = ? WHERE productID = ?";
    
    public List<Tea> getAllTea() throws SQLException {
        List<Tea> listTea = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_TEA);
                rs = ptm.executeQuery();
                while(rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    listTea.add(new Tea(id, name, price, quantity));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return listTea;
    }
    
    public int getQuantity(String teaID) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(QUANTITY);
                ptm.setString(1, teaID);
                rs = ptm.executeQuery();
                while(rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return quantity;
    }

    public boolean checkSoldOut(String teaID, int quantityCart) throws SQLException {
        boolean check = false;
        int quantityDB = getQuantity(teaID);
        if (quantityDB - quantityCart < 0) check = true;
        return check;
    }

    public boolean insertOrder(OrderDTO orderDTO) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER);
                ptm.setString(1, orderDTO.getOrderID());
                ptm.setString(2, orderDTO.getUserID());
                ptm.setDate(3, orderDTO.getDate());
                ptm.setDouble(4, orderDTO.getTotal());
                check = ptm.executeUpdate()>0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean insertOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER_DETAIL);
                ptm.setString(1, orderDetailDTO.getOrderDetailID());
                ptm.setString(2, orderDetailDTO.getOrderID());
                ptm.setString(3, orderDetailDTO.getProductID());
                ptm.setDouble(4, orderDetailDTO.getPrice());
                ptm.setInt(5, orderDetailDTO.getQuantity());
                check = ptm.executeUpdate()>0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean updateQuantity(String teaID, int quantityCart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                int quantityDB = getQuantity(teaID);
                int quantityUpdate = quantityDB - quantityCart;
                ptm.setInt(1, quantityUpdate);
                ptm.setString(2, teaID);
                check = ptm.executeUpdate()>0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
}
