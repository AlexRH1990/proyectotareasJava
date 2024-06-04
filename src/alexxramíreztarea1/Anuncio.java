

package alexxramíreztarea1;



import java.util.Scanner;

public class Anuncio{
    
    static Scanner entrada = new Scanner(System.in);
    
     public static void main(String[] args) {
     
     int aux; 
     System.out.print("Si tiene interes en algÃºn anuncio indique o deseas aÃ±adir otro presiona 1:");
      
     int seguir = entrada.nextInt(); 
              
                        
          
        System.out.println("Si tiene interes en algún anuncio indique la sección:");
        System.out.println("1.Nacionales :");
        System.out.println("2.Internacionales:");
        System.out.println("3.Deportes:");
        System.out.println("4.Culturales:");
        System.out.println("5.Económicos:");
          
              int seccion = entrada.nextInt();
             
              
              switch(seccion)               
                  
         {  case 1 :
           int xNaccost= 80; 
             aux= xNaccost; 
              System.out.printf("%nLa suma de los dÃ­gitos del número %d es %d", aux, costo(aux), iva(aux));
             break;

         case 2 :
           int xintercost= 70;
           aux = xintercost;
           System.out.printf("%nLa suma de los dÃ­gitos del número %d es %d", aux, costo(aux));
           
         break;
      
         case 3:
           int  xdepcost= 85;      
             aux = xdepcost; 
             System.out.printf("%nLa suma de los dÃ­gitos del número %d es %d", aux, costo(aux));
         break;      
    
         case 4 :
           int  xeculcost= 85;      
             aux = xeculcost; 
             System.out.printf("%nLa suma de los dÃ­gitos del número %d es %d", aux, costo(aux));
         break;

          case 5 :
           int xeconcost = 50;
             aux = xeconcost;
             System.out.printf("%nLa suma de los dÃ­gitos del número %d es %d", aux, costo(aux));
             break;    
                
         
              default :
         System.out.println("Invalid input");     }       
                                    
        System.out.println("Si tiene interes en algÃºn anuncio indique o deseas aÃ±adir otro presiona 1:");       
             
        
System.out.print("Esperamos haberle sido de ayuda, tenga un buen dÃ­a");
}
private static int costo(int aux){ 
    System.out.println("indique la cantidad de letras (contando el título)");
              
          int cantidadletras= entrada.nextInt();
               int precio = aux*cantidadletras;
           


return precio;}

private static double iva(int aux){
          double iva= costo(aux)*0.13; 
          double preciofinal = costo(aux)+iva; 
return preciofinal;}}

   
