/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bluemixtwitter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Noe
 */
@WebServlet(name = "TranslateBluemixServlet", urlPatterns = {"/TwitterBluemixServlet"})
public class TwitterBluemixServlet extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            
            String username = "2b57f511-bb2c-4ed1-bbec-ea18bcd2d69a";
            String password = "WOmvWorXIx";
            String query = request.getParameter("query");
            
            String authorization = username + ":" + password;
            
            // String urlString = "https://" + username + ":" + password + "@cdeservice.mybluemix.net:443/api/v1/messages/search?q=" + query + "&size=5";
            String urlString = "https://cdeservice.mybluemix.net/api/v1/messages/search?q=" + query + "&size=5";

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            // conn.setRequestMethod("POST");
            // conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-length", "0");
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);            
            conn.connect();
            
            int status = conn.getResponseCode();
            
            StringBuilder sb = new StringBuilder();  
            int HttpResult = conn.getResponseCode(); 
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line = null;  
                while ((line = br.readLine()) != null) {  
                    sb.append(line + "\n");  
                }
                br.close();
                System.out.println("" + sb.toString());  
            } else {
                System.out.println(conn.getResponseMessage());  
            }  
            
            System.out.println("Estatus: " + status);
            
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
