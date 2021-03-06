package bustracking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


import bustracking.model.AddUser;

import bustracking.model.OrgBusinessRule;
import bustracking.model.OrgRegistration;

public class OrgBusinessRuleDAO {
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int insert_organisation(OrgBusinessRule orgBusinessRule){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		int flag=0;
		try{
			con = dataSource.getConnection();
			stmt = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try{
			
			PreparedStatement preparedStatement=con.prepareStatement("insert into tbl_business_rule(org_id,google_map_traffic,pickup_start_time,pickup_end_time,drop_start_time,drop_end_time,kg_start_time,kg_end_time,speed_limit,sms_options,alert_time_interval,saturday,sms_sending) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1,orgBusinessRule.getOrg_id());
			preparedStatement.setString(2,orgBusinessRule.getGoogle_map_traffic());
			preparedStatement.setString(3,orgBusinessRule.getPickup_start_time());
			preparedStatement.setString(4,orgBusinessRule.getPickup_end_time());
			preparedStatement.setString(5,orgBusinessRule.getDrop_start_time());
			preparedStatement.setString(6,orgBusinessRule.getDrop_end_time());
			preparedStatement.setString(7,orgBusinessRule.getKg_start_time());
			preparedStatement.setString(8,orgBusinessRule.getKg_end_time());
			preparedStatement.setString(9,orgBusinessRule.getSpeed_limit());
			preparedStatement.setString(10,orgBusinessRule.getSms_options());
			preparedStatement.setString(11,orgBusinessRule.getAlert_time_interval());
			preparedStatement.setString(12,orgBusinessRule.getSaturday());
			preparedStatement.setString(13,orgBusinessRule.getSms_sending());
			preparedStatement.execute();
			
				
	
			flag=1;
		}
		catch(Exception e){
	    	System.out.println(e.toString());
	    	releaseStatement(stmt);
	    	releaseConnection(con);
	    	flag=0;
	    }
		finally{
	    	
	    	releaseStatement(stmt);
	    	releaseConnection(con);	    	
	    }
		if(flag==1)
    		return 1;
    	else
    		return 0;
	    
		
	}
	

	
	public List<OrgBusinessRule> getOrgBusinessRules(){
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet=null;
		
		try {
			con = dataSource.getConnection();
			statement = con.createStatement();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		List<OrgBusinessRule> orgBusinessRules=new ArrayList<OrgBusinessRule>();
		{	
				try{
					System.out.println("this is another hai");
					String cmd="Select t1.org_name,t1.branch,t2.google_map_traffic,t2.pickup_start_time,t2.pickup_end_time,t2.drop_start_time,t2.drop_end_time,t2.kg_start_time,t2.kg_end_time,t2.speed_limit,t2.sms_options,t2.alert_time_interval,t2.saturday,t2.sms_sending from tbl_organization as t1 join tbl_business_rule as t2 ON t1.org_id=t2.org_id;";
					resultSet=statement.executeQuery(cmd);
					while(resultSet.next())
					{
						orgBusinessRules.add(new OrgBusinessRule(resultSet.getString("org_name"),resultSet.getString("branch"),resultSet.getString("google_map_traffic"),
								resultSet.getString("pickup_start_time"),resultSet.getString("pickup_end_time"),resultSet.getString("drop_start_time"),resultSet.getString("drop_end_time"),
								resultSet.getString("kg_start_time"),resultSet.getString("kg_end_time"),resultSet.getString("speed_limit"),
								resultSet.getString("sms_options"),resultSet.getString("alert_time_interval"), resultSet.getString("saturday"),
								resultSet.getString("sms_sending")));
					
					}
				}

					catch(Exception e){
				        System.out.println(e.toString());
				        	releaseResultSet(resultSet);
				        	releaseStatement(statement);
				        	releaseConnection(con);
				        }finally{
				        	releaseResultSet(resultSet);
				        	releaseStatement(statement);
				        	releaseConnection(con);	    	
				        }
		}
		return orgBusinessRules;
	
	}
	
				
	public void releaseConnection(Connection con){
		try{if(con != null)
			con.close();
		}catch(Exception e){}
	}
	public void releaseResultSet(ResultSet rs){
		try{if(rs != null)
			rs.close();
	}catch(Exception e){}
	}
	public void releaseStatement(Statement stmt){
		try{if(stmt != null)
			stmt.close();
	}catch(Exception e){}
	}

}
