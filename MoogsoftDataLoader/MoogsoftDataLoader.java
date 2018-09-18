package MoogsoftDataLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MoogsoftDataLoader {

	private Connection conn = null;
    private PreparedStatement updateCI;
    private PreparedStatement updateCIR;
    private PreparedStatement updateCompany;
    private PreparedStatement updateUser;
	//private ArrayList<String> listofStrings;
	
	public void setUpConnections(){
			try
			{

					String url = "jdbc:mysql://0010.122.192.31/optus_cmdb";	// may have broken IP address as a security defense to running wrong program!!
					String username = "rob";
					String password  = "rob";
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, username, password);
		        System.out.println("Connected to database");
		        conn.setAutoCommit(false);
		    } 
		    catch (Exception e){
		    	System.out.println("Did not connect to database");
		    	System.exit(0);
		    }    
		}
	
  public void buildPreparedStatements(){
    updateCI = null;
    updateCIR = null;
    updateCompany = null;
    updateUser = null;

    String updateCIsSQL = "INSERT INTO CIs"
    		+ "(ci_id, ci, install_status, address, class_name, company_id, company, contact_id, contact, default_gateway, end_of_life, line_class_code, mac_address, maintenance_start_date, maintenance_end_date, maintenance_type, managed_by_id, managed_by,  managed_by_group_id, managed_by_group, ip_address, host_ci_information, location_comments, network_address, managed_ip_subnet_mask, management_start_date, management_end_date, manufacturer_id, manufacturer, model_number, part_number, priority, rack_location, security_ci, security_classification, serial_number, vendor_id, vendor , version) VALUES"
    		+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    String updateCIRsSQL = "INSERT INTO ci_topology"
    		+ "(ci_relationship_id, child_ci_id, child_ci, parent_ci_id, parent_ci, relation_type_id, `relation_ type`) VALUES"
    		+ "(?,?,?,?,?,?,?)";
    
    String updateCompanySQL = "INSERT INTO Companies"
    		+ "(company_id, company, trading_name, sm7_name, `sensitive`) VALUES"
    		+ "(?,?,?,?,?)";
    /*
    String updateCompanySQL = "INSERT INTO Companies"
    		+ " VALUES "
    		+ "(?,?,?,?,?)";
    */
    String updateUserSQL = "INSERT INTO Users"
    		+ "(user_id, user, company_id, company, email, title, `sensitive`, employee_number) VALUES"
    		+ "(?,?,?,?,?,?,?,?)";
    
   try{ 
    updateCI = conn.prepareStatement(updateCIsSQL);
    updateCIR = conn.prepareStatement(updateCIRsSQL);
    updateCompany = conn.prepareStatement(updateCompanySQL);
    updateUser = conn.prepareStatement(updateUserSQL);
  } catch (Exception e){
	System.out.println("Failed to create the prepared statement ");
	System.exit(0);
  }
  }
    
    public void writelinetoDBChooser(ArrayList<String> listofStrings, String methodName){
		
        switch (methodName) {
        case "CIs":  
        	writelinetoDB(listofStrings, updateCI);
                 break;
        case "CIRs":  
        	writelinetoDB(listofStrings, updateCIR);
                 break;
        case "Users":  
        	writelinetoDB(listofStrings, updateUser);
                 break;
        case "Companies":  
        	writelinetoDB(listofStrings, updateCompany);
                 break;
        default: 
        	System.exit(0);
                 break;
        }
    }
    
    
    // careful calling this statement
    
    public void deleteElementsSigChanged (){

           //String changeStatement = "Delete from Companies where id < 43";         
        // Companies I am changing
    	// 6a4f240bd33211009712ae2c6ba16a84
    	// 024e20c7d33211009712ae2c6ba16ac6
    	
    	String changeStatement = "";
    	
    	//String changeStatement = "update Companies set `sensitive` = 'true' where company_id = '024e20c7d33211009712ae2c6ba16ac6'";
    	
    	try{ 
        	  PreparedStatement chgPrepStatement = conn.prepareStatement(changeStatement);

                chgPrepStatement.executeUpdate();
                conn.commit();
            }
         catch (Exception e ) {
        	System.out.println("SQL exception on writing to the database for the delete " + e.getMessage());
        	System.exit(0);
            }
         finally {
        }
      }
    
    private void writelinetoDB (ArrayList<String> listofStrings,PreparedStatement myPrepStatement){
    try {
    	int SQLAttributeCounter = 1;
    	for (String value: listofStrings){
    		
    		myPrepStatement.setString(SQLAttributeCounter, value);	
    		//myPrepStatement.setString(SQLAttributeCounter, listofStrings.get(SQLAttributeCounter -1));	
    		SQLAttributeCounter++; 
    	}
            myPrepStatement.executeUpdate();
            conn.commit();
        }
     catch (Exception e ) {
    	System.out.println("SQL exception on writing to the database " + e.getMessage());
    	System.exit(0);
        }
     finally {
    }
  }
}    
