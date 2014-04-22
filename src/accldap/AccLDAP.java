/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accldap;

import java.util.Properties;
import java.util.Arrays;
import java.io.Console;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author dc1314daw2@gmail.com
 * 
 */

public class AccLDAP {
    public AccLDAP() {
    }
    //@SuppressWarnings("CallToPrintStackTrace")
    public void CercaDinsLDAP(String uid,String ou) {
        Properties connexio = new Properties();
        connexio.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        connexio.put(Context.PROVIDER_URL,"ldap://adreça_ip_o_nom");
        try {
            DirContext context = new InitialDirContext(connexio);
            Attributes attrs = context.getAttributes("uid=,ou=,dc=,dc=");
            System.out.println("Usuari: " + attrs.get("cn").get());
            System.out.println("Title: " + attrs.get("").get());
            System.out.println("Descripció : " + attrs.get("").get());
            System.out.println("telephone number : " + attrs.get("").get());
        } 
        catch (NamingException e) {
           System.out.print("\033[2J\033[;H");
           System.out.print("ERROR D'ACCÉS A LES DADES DE L'USUARI:\n");
           System.out.println("Nom d'usuari i/o unitat organitzativa incorrectes");
        }
    }
            
    public boolean AutenticaDinsLDAP() {
        Console terminal=System.console();
        boolean autenticacio=false;
        //
        System.out.print("\033[2J\033[;H");
        System.out.print("AUTENTICACIÓ DE L'ADMINISTRADOR:\n");
        String NomAdmin=terminal.readLine("Nom de l'usuari administrador LDAP: ");
        char[] CtsnyaAdmin=terminal.readPassword("Contrasenya de l'usuari administrador LDAP: ");
        //
        Properties connexio = new Properties();
        connexio.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        connexio.put(Context.PROVIDER_URL,"ldap://adreça_ip_o_nom:port");
        connexio.put(Context.SECURITY_AUTHENTICATION, "simple");
        connexio.put(Context.SECURITY_PRINCIPAL, "cn=,dc=,dc=");
        connexio.put(Context.SECURITY_CREDENTIALS, CtsnyaAdmin);
        try {
            DirContext context = new InitialDirContext(connexio);
            autenticacio=true;
            context.close();
        } 
        catch (NamingException e) {
            autenticacio=false;
        }
        finally {
            Arrays.fill(CtsnyaAdmin,' ');
        }
        return autenticacio;
    }
    
    public static void main(String[] args) {
        Console terminal=System.console();
        boolean autenticat;
        //
        AccLDAP AccessLDAP = new AccLDAP();
        autenticat=AccessLDAP.AutenticaDinsLDAP();
        if (autenticat) {
            System.out.print("\033[2J\033[;H");
            System.out.print("DADES DE L'USUARI A CONSULTAR:\n");
            String Usuari=terminal.readLine("Nom de l'usuari LDAP: ");
            String UnitatOrganitzativa=terminal.readLine("OU de l'usuari LDAP: ");
            AccessLDAP.CercaDinsLDAP(Usuari,UnitatOrganitzativa);
        } else {
           System.out.print("\033[2J\033[;H");
           System.out.print("ERROR D'AUTENTICACIÓ:\n");
           System.out.println("Nom de l'usuari administrador i/o la seva contrasenya incorrectes");   
        }
    }
}
