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
import java.util.ArrayList;
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
import org.postgresql.util.Base64;

/**
 *
 * @author Radek
 */
@WebServlet(name = "RegisterFamilyMember", urlPatterns = {"/RegisterFamilyMember5220"})
public class RegisterFamilyMember extends HttpServlet {

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
        
        int cnt =0;
        try {
            BufferedReader bufferedReader = request.getReader();
            JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);

            InitialContext ic = new InitialContext();
            Context initialContext = (Context) ic.lookup("java:comp/env");
            DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
            connection = datasource.getConnection();
            cnt++;
            String checkAddPeopleQuery = "select * from add_people where code='" + jsonObject.get("code").toString() + "'";
            ps = connection.prepareStatement(checkAddPeopleQuery);
            ResultSet rs = ps.executeQuery();
            cnt++;
            int context = 0;
            long userId = 0L;
            long peopleId = 0L;
            long messagesId = 0L;
            int familyChange = 0;
            cnt++;
            if (rs.next()) {
                context = rs.getInt(3);
                messagesId = rs.getInt(5);
                     cnt++;   
                JSONObject json = new JSONObject();
                JSONObject jsonSettings = new JSONObject();
                JSONObject jsonMessages = new JSONObject();
                JSONArray jSONArrayPeoples = new JSONArray();
                JSONArray jSONArrayPlaces = new JSONArray();
                JSONArray jSONArrayPlace2People = new JSONArray();
                cnt++;
                String getSettingsQuery = "select * from settings where ID='" + rs.getInt(4) + "'";
                ps = connection.prepareStatement(getSettingsQuery);
                rs = ps.executeQuery();
                cnt++;
                if (rs.next()) {
                    cnt++;
                    userId = rs.getLong(6);
                    cnt++;
                    familyChange = rs.getInt(2);
                    familyChange++;
                    cnt++;
                    jsonSettings.put("id", rs.getLong(1));
                    jsonSettings.put("familyChange", familyChange);
                    jsonSettings.put("placesChange", rs.getInt(3));
                    jsonSettings.put("gpsRefresh", rs.getLong(4));
                    jsonSettings.put("notifications", rs.getString(5));
                    jsonSettings.put("userId", userId);
                }
                cnt++;
                String updateFamilyChangeQuery = "update settings set FAMILY_CHANGE='"+familyChange+"' where ID='"+rs.getLong(1)+"'";
                ps = connection.prepareStatement(updateFamilyChangeQuery);
                ps.executeUpdate();
                cnt++;
                String getMessagesQuery = "select * from messages where ID='" + messagesId + "'";
                ps = connection.prepareStatement(getMessagesQuery);
                rs = ps.executeQuery();
                cnt++;
                if (rs.next()) {
                    jsonMessages.put("id", rs.getLong(1));
                    cnt++;
                    jsonMessages.put("msg", rs.getString(2));
                }
                cnt++;
                String getPlacesQuery = "select * from places where USER_ID='" + userId + "'";
                ps = connection.prepareStatement(getPlacesQuery);
                rs = ps.executeQuery();
                cnt++;
                while (rs.next()) {
                    JSONObject places = new JSONObject();
                    places.put("id", rs.getLong(1));
                    places.put("name", rs.getString(2));
                    places.put("radius", rs.getInt(3));
                    places.put("longitude", rs.getDouble(4));
                    places.put("latitude", rs.getDouble(5));

                    jSONArrayPlaces.add(places);
                }
                cnt++;
                String getPlaces2PeopleQuery = "select * from place2people where USER_ID='" + userId + "'";
                ps = connection.prepareStatement(getPlaces2PeopleQuery);
                rs = ps.executeQuery();
                cnt++;
                while (rs.next()) {
                    JSONObject place2People = new JSONObject();
                    place2People.put("id", rs.getLong(1));
                    place2People.put("placeId", rs.getLong(2));
                    place2People.put("peopleId", rs.getLong(3));

                    jSONArrayPlace2People.add(place2People);
                }
                cnt++;
                String insertLocalizationQuery = "insert into localizations values ()";
                ps = connection.prepareStatement(insertLocalizationQuery, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                cnt++;
                ResultSet generatedKeys = ps.getGeneratedKeys();
                long localizationId = 0;
                if (generatedKeys.next()) {
                    localizationId = generatedKeys.getInt(1);
                }
                cnt++;
                String insertPeopleQuery = "insert into people (name, user_id, localization_id, context, messages_id) values (?,?,?,?,?)";
                ps = connection.prepareStatement(insertPeopleQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, jsonObject.get("name").toString());
                ps.setLong(2, userId);
                ps.setLong(3, localizationId);
                ps.setInt(4, context);
                ps.setLong(5, messagesId);
                ps.executeUpdate();
                cnt++;
                generatedKeys = ps.getGeneratedKeys();
                peopleId = 0;
                if (generatedKeys.next()) {
                    peopleId = generatedKeys.getInt(1);
                }
                cnt++;
                String updateLocalizationQuery = "update localizations set "
                        + "PEOPLE_ID='" + peopleId + "' where ID=" + localizationId + "";
                ps = connection.prepareStatement(updateLocalizationQuery);
                ps.executeUpdate();
                cnt++;
                String getPeopleQuery = "select * from people where USER_ID=" + userId + "";
                ps = connection.prepareStatement(getPeopleQuery);
                rs = ps.executeQuery();
                cnt++;
                while (rs.next()) {
                    JSONObject people = new JSONObject();
                    people.put("id", rs.getLong(1));
                    people.put("name", rs.getString(2));
                    people.put("localizationId", rs.getInt(4));
                    people.put("active", rs.getInt(5));
                    
                    byte[] bdata = rs.getBlob(6).getBytes(1, (int)rs.getBlob(6).length());
                    String photo = new String(bdata);
                  
                    
                    people.put("image", photo);
                    people.put("context", rs.getInt(8));
                    people.put("authorizedSpeed", rs.getInt(9));
                    people.put("messagesId", rs.getInt(10));
                    people.put("avatar", rs.getInt(11));
                    jSONArrayPeoples.add(people);
                }
                cnt++;
                json.put("peoples", jSONArrayPeoples);
                
                String deleteAddPeopleQuery = "delete from add_people where code=" + jsonObject.get("code").toString();
                ps = connection.prepareStatement(deleteAddPeopleQuery);
                ps.executeUpdate();
                cnt++;
                json.put("settings", jsonSettings);
                json.put("messages", jsonMessages);
                json.put("places", jSONArrayPlaces);
                json.put("memberContext", context);
                json.put("place2Peoples", jSONArrayPlace2People);
                json.put("peopleId", peopleId);
                json.put("error", 0);
                response.getWriter().write(json.toString());

            } else {
                JSONObject json = new JSONObject();
                json.put("error", 1);
                response.getWriter().write(json.toString());
            }

        } catch (IOException ex) {
            JSONObject json = new JSONObject();
            json.put("error", 2);
            json.put("desc", ex.getMessage()+ cnt);
            response.getWriter().write(json.toString());
        } catch (NamingException ex) {
            JSONObject json = new JSONObject();
            json.put("error", 2);
            json.put("desc", ex.getMessage()+ cnt);
            response.getWriter().write(json.toString());
        } catch (SQLException ex) {
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
