/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

 
public class Biblioteka {
 
    public static void main(String[] argv) {
    
       
    
        HibernateUtil hb = new HibernateUtil();
        System.out.println(hb.getClass());
        System.out.println("Wybierz co Cie interesi");
        System.out.println("-----------------------");
        System.out.println("0. Tabela Ksiazka");
        System.out.println("1. Tabela Osoba");
        System.out.println("2. Tabela Wydano");
        System.out.println("3. Dodaj ksiazke");
        System.out.println("4. Dodaj osobe");
        System.out.println("5. Wypozycz");
        System.out.println("6. Zwroc");
       /* System.out.println("7. Wypozyczone ksiazki z osobami");
        System.out.println("8. Wszystkie dostÄ™pne ksiÄ…zki");
        System.out.println("9. Ksiazki w bibliotece");*/
		
        System.out.println("-----------------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
 
        switch (choice) {
            case 0:
                try {
                    List<Ksiazka> list = hb.getEntities(Ksiazka.class);
                    for(Ksiazka i : list)
                    {
                        System.out.println(i.getId() + "\n");
                        System.out.println(i.getTytul()+ "\n");
                        System.out.println(i.getAutor()+ "\n");
                        System.out.println(i.getRok()+ "\n");
                        System.out.println(i.getLiczbastron()+ "\n");
                    }
                  }
                
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 1:
                try {
                    List<Osoba> list = hb.getEntities(Osoba.class);
                        for(Osoba i : list)
                       {
                           System.out.println(i.getId() + "\n");
                           System.out.println(i.getNazwisko()+ "\n");
                           System.out.println(i.getImie()+ "\n");
                       }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    List<Wydane> list = hb.getEntities(Wydane.class);
                        for(Wydane i : list)
                       {
                           System.out.println(i.getId() + "\n");
                           System.out.println(i.getId_ksiazki()+ "\n");
                           System.out.println(i.getId_osoby()+ "\n");
                           System.out.println(i.getWydano()+ "\n");
                           System.out.println(i.getZwrot()+ "\n");
                       }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                Ksiazka ksiazka = new Ksiazka();
                System.out.println("Podaj tytul");
                String tytul = scanner.next();
                ksiazka.setTytul(tytul);
                System.out.println("Podaj autora");
                String autor = scanner.next();
                ksiazka.setAutor(autor);
                System.out.println("Podaj rok");
                String rok = scanner.next();
                ksiazka.setRok(Integer.parseInt(rok));
                System.out.println("Podaj liczbe stron");
                int lstron = scanner.nextInt();
                ksiazka.setLiczbastron(lstron);
                                
                try {
                    hb.saveEntity(ksiazka);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                Osoba osoba = new Osoba();
                System.out.println("Podaj nazwisko");
                String nazwisko = scanner.next();
                osoba.setNazwisko(nazwisko);
                System.out.println("Podaj imie");
                String imie = scanner.next();
                osoba.setImie(imie);
                try {
                    hb.saveEntity(osoba);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            case 5:
                Wydane wydane = new Wydane();
                System.out.println("Podaj id ksiazki");
                Long book = scanner.nextLong();
                wydane.setId_ksiazki(book);
                System.out.println("Podaj id osoby");
                Long person = scanner.nextLong();
                wydane.setId_osoby(person);
                try {
                   hb.saveEntity(wydane);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                System.out.println("Podaj id ksiazki");
                 Long idksiazki = scanner.nextLong();
                 System.out.println("Podaj id osoby");
                 Long idosoby = scanner.nextLong();
                 hb.update(idksiazki, idosoby);
                break;
            case 7:
                try {
                    List<Biblioteka> list = hb.borrowedBooks();
                    /*for(Biblioteka b:list)
                    {
                       
                      
                    }*/
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 8:
                try{
                    hb.showBooks();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            case 9:
                try{
                    hb.availableBooks();
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            case 10:
                try{
                   hb.zestawienie();
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                
            default:
                
        }
    }
    
  
   
    private static void returnBook() throws SQLException {
        Connection dbConnection = null;
        CallableStatement callableStatement = null;
 
        String dodajOsobe = "{call zwrot(?,?,?)}";
 
        try {
//            dbConnection = getDBConnection();
            callableStatement = dbConnection.prepareCall(dodajOsobe);
 
            callableStatement.setInt(1, 1);
            callableStatement.setInt(2, 1);
            callableStatement.setInt(3, 1);
            
            callableStatement.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
 
        } finally {
            if (callableStatement != null) {
                    callableStatement.close();
            }
 
            if (dbConnection != null) {
                    dbConnection.close();
            }
        }
    }     
}