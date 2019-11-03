/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineiinterface;

/**
 *
 * @author Mahmoud Ahmed
 */
public class User 
{
    private String userName;
    private String password;
    
    public User(String _userName, String _password)
    {
        this.userName = _userName;
        this.password = _password;
    }
    
    public String getUserName()
    {
        return userName;
    }
}
