/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import common.Database;
import common.Encription_MD5;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;



/**
 *
 * @author Abhishek
 */
@ManagedBean
@RequestScoped
public class SignupManagedBean implements Serializable {

    /**
     * Creates a new instance of SignupManagedBean
     */
    private static final long serialVersionUID = 1L;
    
     private String firstname;
     private String lastname;
     private String email;
     private String username;
     private String password;
     private String confirmpassword;
     private String WARNING_MESSAGE;
    
    
    public SignupManagedBean() {
    }
    
    public String userSignUp() {
        
        System.out.print("<------------------ USER INFO  -------------------->");       
        FacesContext context = FacesContext.getCurrentInstance();    
       
         String query; 
         int uid;
         String flag = null;
        
        
        System.out.print("firstname :"+firstname);
        System.out.print("lastname :"+lastname );
        System.out.print("email :"+email );
        System.out.print("username :"+username );
        System.out.print("password :"+password );
        System.out.print("confirmpassword :"+confirmpassword );        
        
        try{
         
        if(password.trim().equals(confirmpassword)){
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                                .getExternalContext().getContext();
            String realPath = ctx.getRealPath("/");
            Database db = new Database(realPath) ;
            
            query = "INSERT INTO user( "+                               
                                "user_name,"  +
                                "email," +
                                "user_username," +
                                "user_password," +
                                "user_status)" +                               
                            "VALUES("                               
                                + "'"+firstname+" "+lastname+"',"
                                + "'"+email+"',"
                                + "'"+username+"',"
                                + "'"+Encription_MD5.encription(password)+"',"
                                + "'0'"
                                +")";
                    
            System.out.print("QUERY -----> "+query);
            uid = db.db_insert(query);            
            System.out.print("UID -----> "+uid);
            if(uid > 0){
                flag = "success";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Info Successfully Saved in DB", null));
            }
            else{
                flag = "fail";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR : Fatal Error", null));
            }  
            blankForm();
        }
        else{
                flag = "fail";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR : Password and Confirm Password not matched", null));
            }
        }catch(Exception e){            
            System.out.print("<---------------------------in Catch-------------------------------->");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR : "+e.getMessage(), null));
            flag = "fail";
            e.printStackTrace();
        }
        
        return flag;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmpassword
     */
    public String getConfirmpassword() {
        return confirmpassword;
    }

    /**
     * @param confirmpassword the confirmpassword to set
     */
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    
    public String checkEmailExistance(AjaxBehaviorEvent e){
    System.out.print("<------------------------- emailExist -------------------------->: "+email);
    this.WARNING_MESSAGE = "<------------------------- emailExist -------------------------->: "+email;
    return "abhishek";
    }
    
    
    public void blankForm() {
     this.firstname = "";
     this.lastname = "";
     this.email ="";
     this.username ="";
     this.password ="";
     this.confirmpassword="";
}
    
}
