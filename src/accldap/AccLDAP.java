/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accldap;

import java.io.IOException;
import java.util.Properties;
import java.util.Arrays;
import java.io.Console;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
/*************************************************/
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;


/**
 *
 * @author dc1314daw2@gmail.com
 * 
 */

public class AccLDAP {
    public AccLDAP() {
    }
    //@SuppressWarnings("CallToPrintStackTrace")
    public void CercaDinsLDAP() {
        Console terminal=System.console();
        System.out.print("\033[2J\033[;H");
        System.out.print("AUTENTICACIÓ DE L'ADMINISTRADOR:\n");
        String NomAdmin=terminal.readLine("Nom de l'usuari administrador LDAP: ");
        char[] CtsnyaAdmin=terminal.readPassword("Contrasenya de l'usuari administrador LDAP: ");
        System.out.print("\033[2J\033[;H");
        //       
        Properties connexio = new Properties();
        connexio.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        connexio.put(Context.PROVIDER_URL,"ldap://192.168.1.49:389");
        connexio.put(Context.SECURITY_AUTHENTICATION, "simple");
        connexio.put(Context.SECURITY_PRINCIPAL, "cn="+NomAdmin+",dc=fjeclot,dc=net");
        connexio.put(Context.SECURITY_CREDENTIALS, CtsnyaAdmin);
        //
        try {
            DirContext context = new InitialDirContext(connexio);
            System.out.print("DADES DE L'USUARI A CONSULTAR:\n");
            String uid=terminal.readLine("Nom de l'usuari LDAP: ");
            String uo=terminal.readLine("Unitat organitzativa de l'usuari LDAP: ");
            Attributes attrs = context.getAttributes("uid="+uid+",ou="+uo+",dc=fjeclot,dc=net");
            System.out.print("DADES OBTINGUDES DE L'USUARI DEMANAT:\n");
            System.out.println("Usuari: " + attrs.get("cn").get());
            System.out.println("Title: " + attrs.get("title").get());
            System.out.println("Descripció: " + attrs.get("description").get());
            System.out.println("Telèfon fixe: " + attrs.get("telephoneNumber").get());
            System.out.println("Telèfon mòbil: " + attrs.get("mobile").get());
            System.out.println("Adreça postal: " + attrs.get("postalAddress").get());
            context.close();
        } 
        catch (NamingException e) {
           System.out.print("\033[2J\033[;H");
           System.out.print("ERROR DE D'ACCÉS A LDAP\n");
        }
        finally {
            Arrays.fill(CtsnyaAdmin,' ');
        }
    }
    
    public void AfegirUsuari() {
        Console terminal=System.console();
        System.out.print("\033[2J\033[;H");
        System.out.print("AUTENTICACIÓ DE L'ADMINISTRADOR:\n");
        String NomAdmin=terminal.readLine("Nom de l'usuari administrador LDAP: ");
        char[] CtsnyaAdmin=terminal.readPassword("Contrasenya de l'usuari administrador LDAP: ");
        System.out.print("\033[2J\033[;H");
        //
        Properties connexio = new Properties();
        connexio.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        connexio.put(Context.PROVIDER_URL,"ldap://192.168.1.49:389");
        connexio.put(Context.SECURITY_AUTHENTICATION, "simple");
        connexio.put(Context.SECURITY_PRINCIPAL, "cn="+NomAdmin+",dc=fjeclot,dc=net");
        connexio.put(Context.SECURITY_CREDENTIALS, CtsnyaAdmin);
        //
        try {
            DirContext context = new InitialDirContext(connexio);
            //objectsClass a afegir a la nova entrada
            Attribute objClasses = new BasicAttribute("objectClass");
            objClasses.add("top");
            objClasses.add("person");
            objClasses.add("organizationalPerson");
            objClasses.add("inetOrgPerson");
            objClasses.add("posixAccount");
            objClasses.add("shadowAccount");
            //Obtenció de les dades dels atributs a afegir
            System.out.print("DADES DE L'USUARI A CREAR:\n");
            String uidValue=terminal.readLine("Identificador del nou usuari LDAP: ");
            String uoValue=terminal.readLine("Unitat organitzativa del nou usuari LDAP: ");
            String cnValue=terminal.readLine("Atribut cn del nou usuari LDAP: ");
            String snValue=terminal.readLine("Atribut sn del nou usuari LDAP: ");
            //String givenNameValue=terminal.readLine("Atribut givenName del nou usuari LDAP: ");
            //String telephoneNumberValue=terminal.readLine("Atribut telephoneNumber del nou usuari LDAP: ");
            //String mobileValue=terminal.readLine("Atribut mobile del nou usuari LDAP: ");
            //String postalAddressValue=terminal.readLine("Atribut postalAddress del nou usuari LDAP: ");
            //String descriptionValue=terminal.readLine("Atribut description del nou usuari LDAP: ");
            String gidNumberValue=terminal.readLine("Atribut gidNumber del nou usuari LDAP: ");
            //String homeDirectoryValue=terminal.readLine("Atribut homeDirectory del nou usuari LDAP: ");
            //String loginShellValue=terminal.readLine("Atribut loginShell del nou usuari LDAP: ");
            //String titleValue=terminal.readLine("Atribut title del nou usuari LDAP: ");
            String uidNumberValue=terminal.readLine("Atribut uidNumber del nou usuari LDAP: ");
            char[] userPasswordValue=terminal.readPassword("Atribut userPassword del nou usuari LDAP: ");
            //Atributs de l'entrada    
            Attribute uid = new BasicAttribute("uid", uidValue);
            Attribute uo = new BasicAttribute("uo", uoValue);
            Attribute cn = new BasicAttribute("cn", cnValue);
            Attribute sn = new BasicAttribute("sn", snValue);
            //Attribute givenName = new BasicAttribute("givenName",givenNameValue );
            //Attribute telephoneNumber = new BasicAttribute("telephoneNumber", telephoneNumberValue);
            //Attribute mobile = new BasicAttribute("mobileValue", mobileValue);
            //Attribute postalAddress = new BasicAttribute("postalAddress", postalAddressValue);
            //Attribute description = new BasicAttribute("description", descriptionValue);
            Attribute gidNumber = new BasicAttribute("gidNumber", gidNumberValue);
            //Attribute homeDirectory = new BasicAttribute("homeDirectory", homeDirectoryValue);
            //Attribute loginShell = new BasicAttribute("loginShell", loginShellValue);
            //Attribute title = new BasicAttribute("title", titleValue);
            Attribute uidNumber = new BasicAttribute("uidNumber", uidNumberValue);
            Attribute userPassword = new BasicAttribute("userPassword", userPasswordValue);
            // Create a container set of attributes
            Attributes container = new BasicAttributes();  
            // Add these to the container
            container.put(objClasses);
            container.put(uid);
            container.put(uo);
            container.put(cn);
            container.put(sn);
            //container.put(givenName);
            //container.put(telephoneNumber);
            //container.put(mobile);
            //container.put(postalAddress);
            //container.put(description);
            container.put(gidNumber);
            //container.put(homeDirectory);
            //container.put(loginShell);
            //container.put(title);
            container.put(uidNumber);
            container.put(userPassword);
            // Create the entry
            String entryDN = "uid="+uidValue+",ou="+uo+",dc=fjeclot,dc=net";
            context.createSubcontext(entryDN, container);
        }
        catch (NamingException e) {
           System.out.print("\033[2J\033[;H");
           System.out.print("ERROR D'ACCÉS A LDAP\n");
        }
        finally {
            Arrays.fill(CtsnyaAdmin,' ');
        }
    }
       
    public char SeleccionaOperacio(){
        char selec=' ';
        Console terminal=System.console();
        System.out.print("\033[2J\033[;H");
        System.out.print("SELECCIONA OPICÓ:\n");
        System.out.print("a) Cerca dades d'un usuari dins del directori LDAP\n");
        System.out.print("b) Afegeix un usuari al directori LDAP\n");
        System.out.print("c) Esborra un usuari al directori LDAP\n");
        System.out.print("d) Finalitzar\n");
        try{
            selec = (char)System.in.read();
        }catch (IOException ex) {  }
        return selec;
    }
    
    public static void main(String[] args) {
        char selecciona;
        //
        AccLDAP AccessLDAP = new AccLDAP();
        selecciona=AccessLDAP.SeleccionaOperacio();
        switch(selecciona)
        {
            case 'a' :
                AccessLDAP.CercaDinsLDAP();
                break;
            case 'b' :
                AccessLDAP.AfegirUsuari();
                break;
            case 'c' :
                System.out.println("Opció no desenvolupada");
                break;                    
            case 'd' :
                System.out.println("Sortint sense seleccionar cap opció");
                break;
            default :
                System.out.println("Opció invalida");
        }
    }
}
