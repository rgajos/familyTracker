/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
@WebServlet(name = "ChallengeUpdateDistanceTime", urlPatterns = {"/ChallengeUpdateDistanceTime5220"})
public class ChallengeUpdateDistanceTime extends HttpServlet {

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

        try {

            BufferedReader bufferedReader = request.getReader();
            JSONObject jsonObject = (JSONObject) JSONValue.parse(bufferedReader);

            InitialContext ic = new InitialContext();
            Context initialContext = (Context) ic.lookup("java:comp/env");
            DataSource datasource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
            connection = datasource.getConnection();
            ResultSet rs;

            JSONObject json = new JSONObject();

            if ((Long)jsonObject.get("id") == 0) {
                
                String insertRecordQuery = "";
                
                switch((Integer)jsonObject.get("route")){
                    case 1:
                        insertRecordQuery = "insert into challenge_walking_2000 (name, time, country) values (?,?,?)";
                        break;
                    case 2:
                        insertRecordQuery = "insert into challenge_walking_4000 (name, time, country) values (?,?,?)";
                        break;
                    case 3:
                        insertRecordQuery = "insert into challenge_walking_6000 (name, time, country) values (?,?,?)";
                        break;
                    case 4:
                        insertRecordQuery = "insert into challenge_walking_8000 (name, time, country) values (?,?,?)";
                        break;
                    case 5:
                        insertRecordQuery = "insert into challenge_walking_10000 (name, time, country) values (?,?,?)";
                        break;
                    case 6:
                        insertRecordQuery = "insert into challenge_walking_12000 (name, time, country) values (?,?,?)";
                        break;
                    case 7:
                        insertRecordQuery = "insert into challenge_walking_14000 (name, time, country) values (?,?,?)";
                        break;
                    case 8:
                        insertRecordQuery = "insert into challenge_walking_16000 (name, time, country) values (?,?,?)";
                        break;
                    case 9:
                        insertRecordQuery = "insert into challenge_walking_18000 (name, time, country) values (?,?,?)";
                        break;
                    case 10:
                        insertRecordQuery = "insert into challenge_walking_20000 (name, time, country) values (?,?,?)";
                        break;
                    case 11:
                        insertRecordQuery = "insert into challenge_walking_22000 (name, time, country) values (?,?,?)";
                        break;
                    case 12:
                        insertRecordQuery = "insert into challenge_walking_24000 (name, time, country) values (?,?,?)";
                        break;
                    case 13:
                        insertRecordQuery = "insert into challenge_walking_26000 (name, time, country) values (?,?,?)";
                        break;
                    case 14:
                        insertRecordQuery = "insert into challenge_walking_28000 (name, time, country) values (?,?,?)";
                        break;
                    case 15:
                        insertRecordQuery = "insert into challenge_walking_30000 (name, time, country) values (?,?,?)";
                        break;
                    case 16:
                        insertRecordQuery = "insert into challenge_walking_42195 (name, time, country) values (?,?,?)";
                        break;
                    case 17:
                        insertRecordQuery = "insert into challenge_running_2000 (name, time, country) values (?,?,?)";
                        break;
                    case 18:
                        insertRecordQuery = "insert into challenge_running_4000 (name, time, country) values (?,?,?)";
                        break;
                    case 19:
                        insertRecordQuery = "insert into challenge_running_6000 (name, time, country) values (?,?,?)";
                        break;
                    case 20:
                        insertRecordQuery = "insert into challenge_running_8000 (name, time, country) values (?,?,?)";
                        break;
                    case 21:
                        insertRecordQuery = "insert into challenge_running_10000 (name, time, country) values (?,?,?)";
                        break;
                    case 22:
                        insertRecordQuery = "insert into challenge_running_12000 (name, time, country) values (?,?,?)";
                        break;
                    case 23:
                        insertRecordQuery = "insert into challenge_running_14000 (name, time, country) values (?,?,?)";
                        break;
                    case 24:
                        insertRecordQuery = "insert into challenge_running_16000 (name, time, country) values (?,?,?)";
                        break;
                    case 25:
                        insertRecordQuery = "insert into challenge_running_18000 (name, time, country) values (?,?,?)";
                        break;
                    case 26:
                        insertRecordQuery = "insert into challenge_running_20000 (name, time, country) values (?,?,?)";
                        break;
                    case 27:
                        insertRecordQuery = "insert into challenge_running_22000 (name, time, country) values (?,?,?)";
                        break;
                    case 28:
                        insertRecordQuery = "insert into challenge_running_24000 (name, time, country) values (?,?,?)";
                        break;
                    case 29:
                        insertRecordQuery = "insert into challenge_running_26000 (name, time, country) values (?,?,?)";
                        break;
                    case 30:
                        insertRecordQuery = "insert into challenge_running_28000 (name, time, country) values (?,?,?)";
                        break;
                    case 31:
                        insertRecordQuery = "insert into challenge_running_30000 (name, time, country) values (?,?,?)";
                        break;
                    case 32:
                        insertRecordQuery = "insert into challenge_running_42195 (name, time, country) values (?,?,?)";
                        break;
                    case 33:
                        insertRecordQuery = "insert into challenge_cycling_5000 (name, time, country) values (?,?,?)";
                        break;
                    case 34:
                        insertRecordQuery = "insert into challenge_cycling_10000 (name, time, country) values (?,?,?)";
                        break;
                    case 35:
                        insertRecordQuery = "insert into challenge_cycling_15000 (name, time, country) values (?,?,?)";
                        break;
                    case 36:
                        insertRecordQuery = "insert into challenge_cycling_20000 (name, time, country) values (?,?,?)";
                        break;
                    case 37:
                        insertRecordQuery = "insert into challenge_cycling_25000 (name, time, country) values (?,?,?)";
                        break;
                    case 38:
                        insertRecordQuery = "insert into challenge_cycling_30000 (name, time, country) values (?,?,?)";
                        break;
                    case 39:
                        insertRecordQuery = "insert into challenge_cycling_35000 (name, time, country) values (?,?,?)";
                        break;
                    case 40:
                        insertRecordQuery = "insert into challenge_cycling_40000 (name, time, country) values (?,?,?)";
                        break;
                    case 41:
                        insertRecordQuery = "insert into challenge_cycling_45000 (name, time, country) values (?,?,?)";
                        break;
                    case 42:
                        insertRecordQuery = "insert into challenge_cycling_50000 (name, time, country) values (?,?,?)";
                        break;
                    case 43:
                        insertRecordQuery = "insert into challenge_cycling_55000 (name, time, country) values (?,?,?)";
                        break;
                    case 44:
                        insertRecordQuery = "insert into challenge_cycling_60000 (name, time, country) values (?,?,?)";
                        break;
                    case 45:
                        insertRecordQuery = "insert into challenge_cycling_65000 (name, time, country) values (?,?,?)";
                        break;
                    case 46:
                        insertRecordQuery = "insert into challenge_cycling_70000 (name, time, country) values (?,?,?)";
                        break;
                    case 47:
                        insertRecordQuery = "insert into challenge_cycling_75000 (name, time, country) values (?,?,?)";
                        break;
                    case 48:
                        insertRecordQuery = "insert into challenge_cycling_100000 (name, time, country) values (?,?,?)";
                        break;
                }
                
                
                ps = connection.prepareStatement(insertRecordQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, jsonObject.get("name").toString());
                ps.setString(2, jsonObject.get("time").toString());
                ps.setString(3, jsonObject.get("country").toString());
                ps.executeUpdate();
                
                ResultSet generatedKeys = ps.getGeneratedKeys();
                long recordId = 0;
                if (generatedKeys.next()) {
                    recordId = generatedKeys.getInt(1);
                }
                
                JSONObject jSONObject = new JSONObject();
                json.put("id", recordId);
                json.put("error", 0);
                response.getWriter().write(jSONObject.toString());
                
            }else{
                String updateRecordQuery = "";
                
                switch ((Integer) jsonObject.get("route")) {
                    case 1:
                        updateRecordQuery = "update challenge_walking_2000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 2:
                        updateRecordQuery = "update challenge_walking_4000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 3:
                        updateRecordQuery = "update challenge_walking_6000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 4:
                        updateRecordQuery = "update challenge_walking_8000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 5:
                        updateRecordQuery = "update challenge_walking_10000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 6:
                        updateRecordQuery = "update challenge_walking_12000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 7:
                        updateRecordQuery = "update challenge_walking_14000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 8:
                        updateRecordQuery = "update challenge_walking_16000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 9:
                        updateRecordQuery = "update challenge_walking_18000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 10:
                        updateRecordQuery = "update challenge_walking_20000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 11:
                        updateRecordQuery = "update challenge_walking_22000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 12:
                        updateRecordQuery = "update challenge_walking_24000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 13:
                        updateRecordQuery = "update challenge_walking_26000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 14:
                        updateRecordQuery = "update challenge_walking_28000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 15:
                        updateRecordQuery = "update challenge_walking_30000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 16:
                        updateRecordQuery = "update challenge_walking_42195 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 17:
                        updateRecordQuery = "update challenge_running_2000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 18:
                        updateRecordQuery = "update challenge_running_4000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 19:
                        updateRecordQuery = "update challenge_running_6000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 20:
                        updateRecordQuery = "update challenge_running_8000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 21:
                        updateRecordQuery = "update challenge_running_10000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 22:
                        updateRecordQuery = "update challenge_running_12000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 23:
                        updateRecordQuery = "update challenge_running_14000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 24:
                        updateRecordQuery = "update challenge_running_16000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 25:
                        updateRecordQuery = "update challenge_running_18000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 26:
                        updateRecordQuery = "update challenge_running_20000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 27:
                        updateRecordQuery = "update challenge_running_22000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 28:
                        updateRecordQuery = "update challenge_running_24000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 29:
                        updateRecordQuery = "update challenge_running_26000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 30:
                        updateRecordQuery = "update challenge_running_28000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 31:
                        updateRecordQuery = "update challenge_running_30000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 32:
                        updateRecordQuery = "update challenge_running_42195 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 33:
                        updateRecordQuery = "update challenge_cycling_5000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 34:
                        updateRecordQuery = "update challenge_cycling_10000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 35:
                        updateRecordQuery = "update challenge_cycling_15000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 36:
                        updateRecordQuery = "update challenge_cycling_20000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 37:
                        updateRecordQuery = "update challenge_cycling_25000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 38:
                        updateRecordQuery = "update challenge_cycling_30000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 39:
                        updateRecordQuery = "update challenge_cycling_35000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 40:
                        updateRecordQuery = "update challenge_cycling_40000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 41:
                        updateRecordQuery = "update challenge_cycling_45000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 42:
                        updateRecordQuery = "update challenge_cycling_50000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 43:
                        updateRecordQuery = "update challenge_cycling_55000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 44:
                        updateRecordQuery = "update challenge_cycling_60000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 45:
                        updateRecordQuery = "update challenge_cycling_65000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 46:
                        updateRecordQuery = "update challenge_cycling_70000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 47:
                        updateRecordQuery = "update challenge_cycling_75000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                    case 48:
                        updateRecordQuery = "update challenge_cycling_100000 set "
                                + "TIME='" + (Long) jsonObject.get("time") + "'"
                                + " where ID='" + (Long) jsonObject.get("id") + "'";
                        break;
                }

                ps = connection.prepareStatement(updateRecordQuery);
                ps.executeUpdate();
                
                JSONObject jSONObject = new JSONObject();
                json.put("error", 0);
                response.getWriter().write(jSONObject.toString());
            }
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
