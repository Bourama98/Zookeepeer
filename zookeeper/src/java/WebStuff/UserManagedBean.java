/*
 * CS-499
 * 5-2 Milestone Four: Enhancement Three: Databases
 * Bourama Mangara
 * 02 August 2020
 */
package WebStuff;


import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.*;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Bourama Mangara
 */
@SessionScoped
@Named
public class UserManagedBean implements Serializable{
    private static final long serialVersionUID = 1094801825228386363L;
    DBManager dbm;
    ConvertToMD5 convertPassWord = new ConvertToMD5(); // Call to the convertToMD5 class, which will return and encrypted password.
   
    private static final Logger logger = Logger.getLogger("WebStuff");
    
    private Integer id;
    private String name;
    private String password;
    Zookeeper keeper;
    String pass;
    String passwordMD5;
    int numberOfAttempt =0;
    HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
   
    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        this.dbm = new DBManager();
          
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberOfAttempt() {
        return numberOfAttempt;
    }

    public void setNumberOfAttempt(int numberOfAttempt) {
        this.numberOfAttempt = numberOfAttempt;
    }

    // Check if the user exists in the database.
    public boolean checkUser(){
        try {
        keeper = dbm.getOneUser(name);
         pass = keeper.getPassword();
         passwordMD5 = convertPassWord.ConvertToMD5(password.trim());
            return pass.equals(passwordMD5) && name.equals(keeper.getName());
        } catch (Exception e) {
            return false;
        }
      
    }
    
    public String login() throws NoSuchAlgorithmException{
        
        if(checkUser()){

  			session.setAttribute("username", name);
               
            return keeper.getRole();
        }else{
               
            numberOfAttempt++;
            if(2 < numberOfAttempt){
                return "loginFail";
            }else{
                // Send the error message to login page for invalid username and password.
                FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
           
            System.out.println(numberOfAttempt);
            return "login";
            }
        }
      
    }
public String logout() throws IOException  {// Implement the logout mechanism
         session.invalidate();// Terminate the session
         return "login";
    }
    
}
