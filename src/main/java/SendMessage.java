/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Radek
 */
@WebServlet(name = "SendMessage", urlPatterns = {"/SendMessage5220"})
public class SendMessage extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            BufferedReader bufferedReader = request.getReader();
            JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);

            InitialContext ic = new InitialContext();
            Context initialContext = (Context) ic.lookup("java:comp/env");
            DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
            connection = datasource.getConnection();
            
            JSONObject json = new JSONObject();
            
            String getMessagesquery = "select MSG from messages where ID=" + (Long)jsonObject.get("messagesId");
            ps = connection.prepareStatement(getMessagesquery);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Gson gson = new Gson();

                ArrayList serverMessages = new ArrayList<Message>(Arrays.asList(gson.fromJson(rs.getString(1), Message[].class)));
                
                if(serverMessages.size() > 19){
                    serverMessages.remove(0);
                }
                
                Message message = new Message(jsonObject.get("message").toString(), (Long)jsonObject.get("peopleId"), jsonObject.get("time").toString());
                serverMessages.add(message);
                
                String actualMessages = gson.toJson(serverMessages);
                
                String updateMessagesQuery = "update messages set MSG='" + actualMessages + "' where ID=" + (Long)jsonObject.get("messagesId");
                ps = connection.prepareStatement(updateMessagesQuery);
                ps.executeUpdate();
                
                json.put("error", jsonObject.get("message").toString());
                
                String getSettingsQuery = "select * from settings where ID=" + (Long)jsonObject.get("settingsId");
                ps = connection.prepareStatement(getSettingsQuery);
                rs = ps.executeQuery();

                int messageCounter = 0;
                
                if (rs.next()) {
                    messageCounter = rs.getInt(7);
                    messageCounter++;
                }
                String updateFamilyChangeQuery = "update settings set MSG_COUNTER='" + messageCounter + "' where ID="+ (Long) jsonObject.get("settingsId");
                ps = connection.prepareStatement(updateFamilyChangeQuery);
                ps.executeUpdate();
                
            }else{
                json.put("error", 1);
                json.put("desc", "Try Again");
            }
            
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
