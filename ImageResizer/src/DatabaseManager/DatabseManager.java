
package DatabaseManager;

import BeanClass.StudentBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class DatabseManager {
  //usindh connection starts
	private static Connection con;
   // private static String fac_id;
	static{
		try{
			init();
		}catch(Exception e){
		}
	}
	private static void init()throws Exception{
//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		con=DriverManager.getConnection("jdbc:odbc:university");
//                System.out.println("Connection succesfull");
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                //give your directory path of access here
                String path="F:\\7th Semester\\DIP\\Image Processing Project\\ImageResizerDB.accdb";
                String url = "jdbc:ucanaccess://"+path;      
                con=DriverManager.getConnection(url);
                System.out.println("Connection Successful");            
	}
     // usindh connection ends 
	public static int addStudent(String stdName,String fName,String caste,String rollNo,String phoneNo,String gender)throws Exception{
		String query="INSERT into student (name,fname,caste,roll_no,phone_no,gender) values ('"+stdName+"','"+fName+"','"+caste+"','"+rollNo+"','"+phoneNo+"','"+gender+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}

	public static int deleteStudent(int stdId)throws Exception{
		String query="DELETE from student where std_id="+stdId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int updateStudent(int stdId,String stdName,String fName,String caste,String rollNo,String phoneNo,String gender)throws Exception{
		String query="UPDATE student set name='"+stdName+"' ,fname='"+fName+"' ,caste='"+caste+"' ,roll_no='"+rollNo+"' , phone_no='"+phoneNo+"',gender='"+gender+"' where std_id="+stdId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	} 
public static Vector getStudent()throws Exception{
		String query="select * from student ";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
                        int sno=0;
			while(result.next()){
                            sno++;
				StudentBean bean=new StudentBean();	
				
                                
                                bean.setStdId( result.getInt("std_id") );
				bean.setName( result.getString("name") );
				bean.setFname( result.getString("fname") );
				bean.setCaste( result.getString("caste") );
				bean.setRollNo( result.getString("roll_no") );
                                bean.setPhoneNo( result.getString("phone_no") );	
                                bean.setGender(result.getString("gender"));
				
                                //bean.setSerialNum(result.getInt("serial_num"));
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}

public static StudentBean getStudent(String name)throws Exception{
		String query="select * from student ";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
                StudentBean bean=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
                        int sno=0;
			while(result.next()){
                            sno++;
				bean=new StudentBean();	
				
                                
                                bean.setStdId( result.getInt("std_id") );
				bean.setName( result.getString("name") );
				bean.setFname( result.getString("fname") );
				bean.setCaste( result.getString("caste") );
				bean.setRollNo( result.getString("roll_no") );
                                bean.setPhoneNo( result.getString("phone_no") );	
                                bean.setGender(result.getString("gender"));
				
                                //bean.setSerialNum(result.getInt("serial_num"));
				v.addElement(bean);
			}
			return bean;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}
 /* public static StudentBean getStudent1()throws Exception{
		String query="select * from student ";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			//Vector v=new Vector();
                        StudentBean bean=null;
                        int sno=0;
			while(result.next()){
                            sno++;
				bean=new StudentBean();	
				bean.setSerialNum(sno);
                                
                                bean.setStdId( result.getInt("std_id") );
				bean.setBatchId( result.getInt("batch_id") );
				bean.setName( result.getString("std_name") );
				bean.setFname( result.getString("fname") );
				bean.setSurname( result.getString("surname") );
				bean.setRollNo( result.getString("roll_no") );
                                bean.setGender(result.getString("gender"));
				bean.setRemarks( result.getString("remarks") );	
                                //bean.setSerialNum(result.getInt("serial_num"));
				//addElement(bean);
                             // return bean;  
			}
			return bean;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}   
   */     
}
