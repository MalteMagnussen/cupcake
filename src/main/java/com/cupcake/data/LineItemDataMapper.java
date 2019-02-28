/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 
 */
public class LineItemDataMapper {
    private final DBConnector conn = null;
    /**
     * Line items Data Mapper.
     * Contains:
     * - Get new invoice ID: Should return the highest invoice ID +1.
     */
    
    public int getInvID() throws DataException{
        try{
            DBConnector conn = new DBConnector();
            
            String query = "SELECT COUNT(ID) FROM `Cupcake`.`invoices`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            int highestID = 0;
            
            while (rs.next()){
                highestID = rs.getInt("COUNT(ID)") + 1;
            }
            return highestID;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return -1;
    }
}
