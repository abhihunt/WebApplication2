package navigation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;


/**
 *
 * @author Abhishek
 */
@Named(value = "navigationController")
@RequestScoped
public class NavigationController{

   private static final long serialVersionUID = 1L; 

   @ManagedProperty(value="#{param.pageId}")
   private String pageId;

   public String moveToPage1(){
      return "page1";
   }

   public String moveToPage2(){
      return "page2";
   }

   public String moveToHomePage(){
      return "home";
   }

   public String processPage1(){
      return "page";
   }

   public String processPage2(){
      return "page";
   }

   public String showPage(){
      System.out.print("--------------> "+ pageId);
       if(pageId == null){
         return "home";
      }
      if(pageId.equals("1")){
         return "signin";
      }else{
         return "home";
      }
   }

   public String getPageId() {
      return pageId;
   }

   public void setPageId(String pageId) {
      this.pageId = pageId;
   }
}
