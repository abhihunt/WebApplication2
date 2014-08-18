/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import common.Database;
import common.Encription_MD5;
import common.FileUpload;
import common.RandomNumberGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import javax.el.ELException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;


/**
 *
 * @author Abhishek
 */
@Named(value = "userManagedBean")
@RequestScoped
public class UserManagedBean {

    /**
     * Creates a new instance of UserManagedBean
     */
    
    private Part file_upload = null;
    public UserManagedBean() {
    }
    
   
    /**
     * @return the file_upload
     */
    public Part getFile_upload() {
        return file_upload;
    }

    /**
     * @param file_upload the file_upload to set
     */
    public void setFile_upload(Part file_upload) {
        this.file_upload = file_upload;
    }
    
    

    public void upload() throws IOException {
    
    // Create path components to save the file    
    String query = null;
    ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                        .getExternalContext().getContext();
    String realPath = ctx.getRealPath("/");    
    
    try{
        
    final String path = realPath +"\\resources\\img\\user\\";
    final Part filePart = file_upload;
    final String fileName = getFilename(file_upload);
    
    
    FileUpload fileUpolad = new FileUpload();
    String uploadFileName = fileUpolad.uploadFile(path, filePart, fileName);
    
    System.out.print("uploadFileName ->"+ uploadFileName);
    
    if(uploadFileName!=null && uploadFileName.trim().length() > 0){
       
        FacesContext context = FacesContext.getCurrentInstance();
        
        LoginManagedBean bean = (LoginManagedBean) context.getApplication()
                                                   .evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
        
        int LoggedUserId = bean.getUser_id();
        
        Database db = new Database(realPath);

                query = "UPDATE user "
                        + "SET user_image= '"+uploadFileName+"' "
                        + "WHERE user_id = "+LoggedUserId;
                        
                System.out.print("QUERY -----> " + query);
                int qryResult = db.db_update(query);        
                if(qryResult == 1) {
                    String oldFileName = bean.getUser_image();
                    fileUpolad.removeFile(path,oldFileName);
                    
                    bean.setUser_image(uploadFileName);
                }        
    }
    }catch(IOException | ELException e){
        e.getMessage();
    }
    
    ctx.getRequestDispatcher("/profile/index.xhtml");     
    //return "/profile/index.xhtml?faces-redirect=true";
    
}

private String getFilename(final Part part) {
    final String partHeader = part.getHeader("content-disposition");    
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
}
