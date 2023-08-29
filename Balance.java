import java.util.*;
import java.lang.*;
public class Balance extends Main {
    private double bal;

    // constructor for game balance
    public Balance(double balance){
        this.bal = balance;
    }
    // method assisting in selling stocks, using stock total to subtract and add to balance
    public void sellStock(Stock stock, String name, int amount){
        if ( (name.equals("EBS")) || (name.equals("VCP")) || (name.equals("SQL")) ){
            if(name.equals("EBS")){
                if(!(stock.getTotal() < amount)){
                bal += (stock.getVal() * amount);
                stock.subTotal(amount);                   
                }
                else{
                    System.out.println("You can't sell stock you don't own");
                }
            }
            else if(name.equals("VCP")){
                if(!(stock.getTotal() < amount)){
                bal += (stock.getVal() * amount);
                stock.subTotal(amount);                   
                }
                else{
                    System.out.println("You can't sell stock you don't own");

                }
            }
            else if(name.equals("SQL")){
                if(!(stock.getTotal() < amount)){
                bal += (stock.getVal() * amount);
                stock.subTotal(amount);                   
                }
                else{
                    System.out.println("You can't sell stock you don't own");
                }
            }
            if (amount == 0){
                System.out.println("You can't sell nothing.");            }
        }
        else{
            System.out.print("WOW someone cant read directions! Sucks that you lost out on your chance to sell stocks");
        }
    }
    // 
    public void buyStock(Stock stock, String name, int amount){
        if ( (name.equals("EBS")) || (name.equals("VCP")) || (name.equals("SQL")) ){
            if(name.equals("EBS")){
                bal -= (stock.getVal() * amount);
                stock.setTotal(amount);
            }
            else if(name.equals("VCP")){
                bal -= (stock.getVal() * amount);
                stock.setTotal(amount);
            }
            else if(name.equals("SQL")){
                bal -= (stock.getVal() * amount);
                stock.setTotal(amount);
            }
            if (bal <= 0){
                System.out.println("You can't afford that much!");
                bal += (stock.getVal() * amount);
                stock.setTotal(-amount);
            }
        }
        else{
            System.out.print("WOW someone cant read directions! Sucks that you lost out on your chance to buy stocks");
        }
       
    }

    public double getBal(){
        return this.bal;
    }
    public void chargeBal(double cost) {
    	bal = bal - cost;
    }
}