/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Radek
 */
@WebServlet(name = "ChangeAvatar", urlPatterns = {"/ChangeAvatar5220"})
public class ChangeAvatar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement ps = null;
        
        int cnt = 0;
        try {
            BufferedReader bufferedReader = request.getReader();
            JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);

            InitialContext ic = new InitialContext();
            Context initialContext = (Context) ic.lookup("java:comp/env");
            DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
            connection = datasource.getConnection();

            String getSettingsQuery = "select * from settings where ID='" + (Long) jsonObject.get("settingsId") + "'";
            ps = connection.prepareStatement(getSettingsQuery);
            ResultSet rs = ps.executeQuery();

            cnt ++;
            if (rs.next()) {
                int familyChange = rs.getInt(2);
                familyChange++;
                String updateFamilyChangeQuery = "update settings set FAMILY_CHANGE=" + familyChange + " where ID=" + (Long) jsonObject.get("settingsId");
                ps = connection.prepareStatement(updateFamilyChangeQuery);
                ps.executeUpdate();
            }
            cnt++;
            if((Long) jsonObject.get("avatar") > 0){
                cnt++;
                String updatePeopleAvatarQuery = "update people set AVATAR=" + (Long) jsonObject.get("avatar") + " where ID=" + (Long) jsonObject.get("peopleId");
                ps = connection.prepareStatement(updatePeopleAvatarQuery);
                ps.executeUpdate();
            }else{
                String updatePeopleImageQuery = "update people set AVATAR=" + (Long) jsonObject.get("avatar") + ", IMAGE='" + (String) jsonObject.get("photo") + "' where ID=" + (Long) jsonObject.get("peopleId") ;
                ps = connection.prepareStatement(updatePeopleImageQuery);
                ps.executeUpdate();
            }
            cnt++;
            JSONObject json = new JSONObject();
            json.put("error", 0);
            response.getWriter().write(json.toString());

        } catch (IOException ex) {
            JSONObject json = new JSONObject();
            json.put("error", 2);
            json.put("desc", ex.getMessage());
            response.getWriter().write(json.toString());
        } catch (NamingException ex) {
            JSONObject json = new JSONObject();
            json.put("error", 2);
            json.put("desc", ex.getMessage());
            response.getWriter().write(json.toString());
        } catch (SQLException ex) {
            JSONObject json = new JSONObject();
            json.put("error", 2);
            json.put("desc", ex.getMessage());
            response.getWriter().write(json.toString());
        } catch (Exception ex) {
            JSONObject json = new JSONObject();
            json.put("error", 2);
            json.put("desc", ex.getMessage()+cnt);
            response.getWriter().write(json.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (out != null) {
                    out.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                JSONObject json = new JSONObject();
                json.put("error", 2);
                json.put("desc", ex.getMessage());
                response.getWriter().write(json.toString());
            }
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
