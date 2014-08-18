/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;

/**
 *
 * @author Abhishek
 */
public class FileUpload {
    
    public String uploadFile(String path, Part filePart, String fileName) throws IOException{
    
         OutputStream out = null;
         InputStream filecontent = null;
         String fileNewName = null;
        try {

            System.out.print("File Name :: "+ fileName);
            String extension = fileName.substring(fileName.indexOf(".")); 
            fileNewName = RandomNumberGenerator.getRendomNumber(9999, 99999)+extension ;            
            out = new FileOutputStream(new File(path + File.separator
                    + fileNewName));
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (FileNotFoundException fne) {
            return null;
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
        return fileNewName;
    }
    
    
    
    
    public boolean removeFile(String path, String oldFileName){
    
        File file =  new File(path + File.separator + oldFileName);
        if(file.exists())
            file.delete();
    return true;
    }
    
}
