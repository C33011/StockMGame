import java.lang.Math;

public class Stock {
	private String name; 
    private double volatility;
    private double value;
    private int stocktotal;
    
    // constructor for individual stocks
    public Stock(String tempName, double tempVol)
    {
        this.name = tempName;
        this.volatility = tempVol;
        this.value = (Math.random() * 100) + 25;
        this.stocktotal = 0;
    }
    // cycles a market turn, rolling a chance based on difficulty to increase or decrease the value of stocks
    public void runMarket(int diff){
        if (((Math.random()*10000) >= (5000 - (diff/2) ))){
            value = (value) * (1 + (Math.random() *volatility)) - 2;
        }
        else {
            value = (value) * (1 - (Math.random() *volatility)) + 3;
        }
    }
    // returns value of a stock
    public double getVal(){
        return this.value;
    }
    // returns total stock owned
    public int getTotal(){
        return this.stocktotal;
    }
    // adds the amount of stocks bought to the total owned
    public void setTotal(int amount){
        this.stocktotal += amount;
    }
    // subtracts amount of stocks sold from the total owned
    public void subTotal(int amount){
        this.stocktotal -= amount;
    }

}