/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import common.Database;
import common.Encription_MD5;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Abhishek
 */
@ManagedBean
@SessionScoped
public class LoginManagedBean {

    public static String AUTH_KEY = null;
    /**
     * Creates a new instance of UserManagedBean
     */
    private String userName = null;
    private String password = null;

    private int user_id = 0;
    private String user_name = null;
    private String email = null;
    private String user_username = null;
    private String user_status = null;
    private String user_added = null;
    private String user_image = null;

    public LoginManagedBean() {
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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

    public String userAuthetication() {

        System.out.print("<------------------ USER INFO  -------------------->");
        FacesContext context = FacesContext.getCurrentInstance();
        ArrayList data = null;
        String query;
        String flag = null;

        System.out.print("userName :" + userName);
        System.out.print("password :" + password);

        try {

            if (userName.trim().length() > 0 && password.trim().length() > 0) {
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                        .getExternalContext().getContext();
                String realPath = ctx.getRealPath("/");
                Database db = new Database(realPath);

                query = "SELECT "
                        + "user_name,"
                        + "email,"
                        + "user_username,"
                        + "user_password,"
                        + "user_status,"
                        + "user_added,"
                        + "user_image,"
                        + "user_id"
                        + " FROM user"
                        + " WHERE  user_username = '" + userName + "' AND "
                        + "user_password ='" + Encription_MD5.encription(password) + "'";

                System.out.print("QUERY -----> " + query);
                data = db.db_setect(query);

                if (data != null && data.size() > 0) {

                    for (Object list : data) {

                        ArrayList arrList = (ArrayList) list;

                        for (Object col : arrList) {
                            System.out.println("=================>> " + String.valueOf(col));
                        }

                        setUser_name((String) arrList.get(0));
                        setEmail((String) arrList.get(1));
                        setUser_username((String) arrList.get(2));
                        //user_password   = (String) arrList.get(3);
                        setUser_status((String) arrList.get(4));
                        setUser_added((String) arrList.get(5));
                        setUser_image((String) arrList.get(6));
                        setUser_id( java.lang.Integer.valueOf((String)arrList.get(7)));
                        
                        AUTH_KEY = String.valueOf(getUser_id());
                        
                        FacesContext.getCurrentInstance().getExternalContext().
                                getSessionMap().put(AUTH_KEY, AUTH_KEY);
                    }
                    flag = "success";
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome , " + getUser_name(), null));
                } else {
                    flag = "fail";
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Invalid User name or Password ", null));
                }

            } else {
                flag = "fail";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : No Record Found ", null));
            }
        } catch (Exception e) {
            System.out.print("<---------------------------in Catch-------------------------------->");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : " + e.getMessage(), null));
            flag = "fail";
            e.printStackTrace();
        }

        return flag;
    }
    
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " You are logout Successfully  ", null));
        return "success";
    }


    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
     * @return the user_username
     */
    public String getUser_username() {
        return user_username;
    }

    /**
     * @param user_username the user_username to set
     */
    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    /**
     * @return the user_status
     */
    public String getUser_status() {
        return user_status;
    }

    /**
     * @param user_status the user_status to set
     */
    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    /**
     * @return the user_added
     */
    public String getUser_added() {
        return user_added;
    }

    /**
     * @param user_added the user_added to set
     */
    public void setUser_added(String user_added) {
        this.user_added = user_added;
    }

    /**
     * @return the user_image
     */
    public String getUser_image() {
        return user_image;
    }

    /**
     * @param user_image the user_image to set
     */
    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
