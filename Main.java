
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
public class Main
{
    public static void main(String[] args) throws IOException
    {
    	// constructs 3 stocks of increasing volatility 
    	Scanner scanner = new Scanner(System.in);        
        Stock ebs = new Stock("EBS", 0.1);
        Stock vcp = new Stock("VCP", 0.4);
        Stock sql = new Stock("SQL", 0.73);
        
        //Opens file to store stock volatility and track history of prices
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        
        //Attempts to open file
        fileStream = new FileOutputStream("StockHistory.txt");
        outFS = new PrintWriter(fileStream);

        //Starts out the program, asking for difficulty and opening the trading sequence
        Balance bal = new Balance(1000.00);
        int day = 0;
        boolean sellB = false;
        boolean buyB = false;
        int num1 = 0;
        int num2 = 0;
        double tempBal = 0;
        System.out.println("DIFFICULTY MODIFIER RULEBOARD:");
        System.out.println(">1000 for very easy, <1000 for easy, 0 for normal, 0> for realistic");
        System.out.println("No greater than 4000 or less than -4000");
        System.out.println("Default is 0");
        int dif = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Day Trading open. Risk your life savings? (type Y)");
        System.out.println("Each day has a $25 entry charge, so be careful not to go under!");
        String str = scanner.nextLine();
        //while loop initiates the actual trading sequence, continuing it unless it detects a sequence break 
        while(str.equals("Y") || str.equals("y")){
        	day += 1;
        	System.out.printf("-------DAY %d------\n", day);
        	System.out.println("-$25 entry charge to the market.");
        	bal.chargeBal(25.0);
        	tempBal = bal.getBal();
            System.out.println("$" + bal.getBal() + " in checking.");
            System.out.println("EBS stock value : " + ebs.getVal());
            System.out.println("VCP stock value : " + vcp.getVal());
            System.out.println("SQL stock value : " + sql.getVal());
            System.out.println("Buy Stocks? (type Y)");
            String buy = scanner.next();
            if (buy.equals("Y") || buy.equals("y")){
                while(true){
                    System.out.print("Buy from: ");
                    String ST = scanner.next();
                    ST = ST.toUpperCase();
                    System.out.print("Buy how many (whole number) : ");
                    num1 = scanner.nextInt();
                    buyB = false;
                    if(ST.equals("EBS")){
                        bal.buyStock(ebs, ST, num1);
                        buyB = true;
                    }
                    else if(ST.equals("VCP")){
                        bal.buyStock(vcp, ST, num1);
                        buyB = true;
                    }
                    else if(ST.equals("SQL")){
                        bal.buyStock(sql ,ST, num1);
                        buyB = true;
                    }
                    else{
                        System.out.println("Sucks to suck");
                        break;
                    }
                    break;
                }
                scanner.nextLine();
            }
            System.out.println("$" + bal.getBal() + " in checking.");
            System.out.println("Sell stocks? (type Y)");
            String sell = scanner.next();
            if (sell.equals("Y") || sell.equals("y")){
                while(true){
                    System.out.println("EBS stock amount : " + ebs.getTotal());
                    System.out.println("VCP stock amount : " + vcp.getTotal());
                    System.out.println("SQL stock amount : " + sql.getTotal());
                    System.out.print("Sell from: ");
                    String ST = scanner.next();
                    ST = ST.toUpperCase();
                    System.out.print("Sell how many (whole number) : ");
                    num2 = scanner.nextInt();
                    sellB = false;
                    if(ST.equals("EBS")){
                        bal.sellStock(ebs, ST, num2);
                        sellB = true;
                    }
                    else if(ST.equals("VCP")){
                        bal.sellStock(vcp, ST, num2);
                        sellB = true;
                    }
                    else if(ST.equals("SQL")){
                        bal.sellStock(sql ,ST, num2);
                        sellB = true;
                    }
                    else{
                        System.out.println("Better luck next time.");
                        break;
                    }
                    break;
                }
            }
            ebs.runMarket(dif);
            vcp.runMarket(dif);
            sql.runMarket(dif);
            writeReport(num1, num2, outFS, buyB, sellB, bal, tempBal, day);
            System.out.println("Keep going for another day? (Y/N)");
            str = scanner.next();
        }
        outFS.close();
        scanner.close();
        System.out.println("You've gone home with a sweet sweet total of $" + bal.getBal());
    }
    public static void writeReport(int amountBuy, int amountSell, PrintWriter output, boolean buy, boolean sell, Balance balance, double tempbal, int day) {
    	output.printf("----------Day %d Report----------\n", day);
    	output.println("Total balance: " + balance.getBal());
    	output.println("Bought Stocks: " + buy);
    	if (buy == true){
    	output.printf("Total stocks bought: %d\n", amountBuy);
    	}
    	output.println("Sold Stocks: " + sell);
    	if (sell == true) {
    	output.printf("Total stocks sold: %d\n", amountSell);
    	}
    	output.println("Net gain: " + (balance.getBal() - tempbal ));
    	output.println();
    }
}