import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.*;
class Food implements Serializable{
    int itemno;
    int quantity;   
    float price;
    Food(int itemno,int quantity){
        this.itemno=itemno;
        this.quantity=quantity;
        switch(itemno){
            case 1:price=quantity*50;
                break;
            case 2:price=quantity*50;
                break;
            case 3:price=quantity*1000;
                break;
            case 4:price=quantity*150;
                break;
            case 5:price=quantity*20;
                break;
            case 6:price=quantity*30;
                break;
            case 7:price=quantity*25;
                break;
        }
    }
}
class Singleroom implements Serializable{
    String name ;
    String contact;
    String gender;
    ArrayList<Food> food=new ArrayList<Food>();
    Singleroom(){
        this.name="";
    }
    Singleroom(String name,String contact,String gender){
        this.name=name;
        this.contact=contact;
        this.gender=gender;
    }
}
class Doubleroom extends Singleroom implements Serializable{
    String name2;
    String contact2;
    String gender2;  
    
    Doubleroom(){
        this.name="";
        this.name2="";
    }
    Doubleroom(String name,String contact,String gender,String name2,String contact2,String gender2)
    {
        this.name=name;
        this.contact=contact;
        this.gender=gender;
        this.name2=name2;
        this.contact2=contact2;
        this.gender2=gender2;
    }
}
class holder implements Serializable{
    Doubleroom economy_doubleroom[]=new Doubleroom[10]  ; 
    Doubleroom premium_doubleroom[]=new Doubleroom[10]; 
    Singleroom economy_singleroom[]=new Singleroom[10]; 
    Singleroom premium_singleroom[]=new Singleroom[10]; 
}
class Hotel_main{
    static holder hotel_ob=new holder();
    static Scanner sc=new Scanner(System.in);
    static String availability(int i){
        int count=0;
        switch(i){
            case 1:
                for(int j=0;j<10;j++){
                    if(hotel_ob.economy_singleroom[j]==null)
                        count++;
                }
                break;
            case 2:
                for(int j=0;j<10;j++){
                    if(hotel_ob.premium_singleroom[j]==null)
                        count++;
                }
                break;
            case 3:
                for(int j=0;j<10;j++){
                    if(hotel_ob.economy_doubleroom[j]==null)
                        count++;
                }
                break;
            case 4:
                for(int j=0;j<10;j++){
                    if(hotel_ob.premium_doubleroom[j]==null)
                        count++;
                }
                break;
        }
        String ret=Integer.toString(count);
        return ret;
    }
    static void types(){
        String features[][]={{"1","Single Bed Economy","1000","No","No",availability(1)},{"2","Single Bed Premium","1800","Yes","Yes",availability(2)},{"3","Double Bed Economy","2000","No","No",availability(3)},{"4","Double Bed Premium","4000","Yes","Yes",availability(4)}};
        System.out.println("S.no\t\tType\t\t\t\t\tPrice\t\tAC\t\tBreakfast\tRoom Availables");
        for(String[] x:features){
            for(int i=0;i<x.length;i++){
                if(i==1){
                System.out.print(x[i]+"\t\t\t");
                }
                else{
                    System.out.print(x[i]+"\t\t");
                }
            }
            System.out.println();
        }
    }
    static void customer_details(int i,int rn){
        String name1,name2=null,contact,contact2=null,gender,gender2=null;
        sc.nextLine();
        System.out.print("Enter Customer name: ");
        name1=sc.nextLine();
        System.out.print("Enter the contact number: ");
        contact=sc.next();
        System.out.print("Enter gender: ");
        gender=sc.next();
        if(i==3 || i==4){
            System.out.print("Enter Second customer name: ");
            sc.nextLine();
            name2=sc.nextLine();
            System.out.print("Enter second person contact number: ");
            contact=sc.next();
            System.out.print("Enter second person gender: ");
            gender2=sc.next();
        }
        switch(i){
            case 1:hotel_ob.economy_singleroom[rn]=new Singleroom(name1, contact, gender);
                break;
            case 2:hotel_ob.premium_singleroom[rn]=new Singleroom(name1, contact, gender);
                break;
            case 3:hotel_ob.economy_doubleroom[rn]=new Doubleroom(name1, contact, gender, name2, contact2, gender2);
                break;
            case 4:hotel_ob.premium_doubleroom[rn]=new Doubleroom(name1, contact, gender, name2, contact2, gender2);
        }

    }
    static void Order_food(int ty,int rn){
        int i,q;
        char wish;
        try{
            System.out.println("\n==============\n Menu: \n==============\n");
            System.out.println("1.Sandwitch\t\tRs.50");
            System.out.println("2.Maggie\t\tRs.50");
            System.out.println("3.Normal Thali\t\tRs.100");
            System.out.println("4.Special Thali\t\tRs.150");
            System.out.println("5.Tea\t\t\tRs.20");
            System.out.println("6.Coffee\t\tRs.30");
            System.out.println("7.Coke\t\t\tRs.25");
            do{
                System.out.println("Enter your choice: ");
                i=sc.nextInt();
                System.out.print("Quantity: ");
                q=sc.nextInt();
                Food f=new Food(i,q);
                switch(ty){
                    case 1:hotel_ob.economy_singleroom[rn].food.add(f);
                        break;
                    case 2:hotel_ob.premium_singleroom[rn].food.add(f);
                        break;
                    case 3:hotel_ob.economy_doubleroom[rn].food.add(f);
                        break;
                    case 4:hotel_ob.premium_doubleroom[rn].food.add(f);
                        break;
                }
                System.out.print("Do you want to order anything else ?(Y/N)");
                wish=sc.next().charAt(0);
            }while(wish=='y'||wish=='Y');
        }
        catch(NullPointerException e){
            System.out.println("Room not booked");
        }
    }
    static void bookroom(int i){
        System.out.print("Rooms Available: ");
        int ch,rn=0;
        switch(i){
            case 1:
                for(int j=0;j<hotel_ob.economy_singleroom.length;j++){
                    if(hotel_ob.economy_singleroom[j]==null){
                        System.out.print("10"+j+",");
                    }
                }
                System.out.println();
                System.out.print("Choose any room from the above available room: ");
                while(true){
                    ch=sc.nextInt();
                    rn=ch-100;
                    if(rn>=0 && rn<=9){ 
                        if(hotel_ob.economy_singleroom[rn]!=null){
                            System.out.println("Room not available!");
                            System.out.println("Choose any other room!");
                        }
                        else{
                            customer_details(i, rn);
                            break;
                        }
                    }
                    else{
                        System.out.println("Invalid Input! Try Again! ");
                    }
                }
                break;
            case 2:
                for(int j=0;j<hotel_ob.premium_singleroom.length;j++){
                    if(hotel_ob.premium_singleroom[j]==null){
                        System.out.print("20"+j+",");
                    }
                }
                System.out.println();
                System.out.print("Choose any room from the above available room: ");
                while(true){
                    ch=sc.nextInt();
                    rn=ch-200;
                    if(rn>=0 && rn<=9){ 
                        if(hotel_ob.premium_singleroom[rn]!=null){
                            System.out.println("Room not available!");
                            System.out.println("Choose any other room!");
                        }
                        else{
                            customer_details(i, rn);
                            break;
                        }
                    }
                    else{
                        System.out.println("Invalid Input! Try Again! ");
                    }
                }
                break;
            case 3:
                for(int j=0;j<hotel_ob.economy_doubleroom.length;j++){
                    if(hotel_ob.economy_doubleroom[j]==null){
                        System.out.print("30"+j+",");
                    }
                }
                System.out.println();
                System.out.print("Choose any room from the above available room: ");
                while(true){
                    ch=sc.nextInt();
                    rn=ch-300;
                    if(rn>=0 && rn<=9){ 
                        if(hotel_ob.economy_doubleroom[rn]!=null){
                            System.out.println("Room not available!");
                            System.out.println("Choose any other room!");
                        }
                        else{
                            customer_details(i, rn);
                            break;
                        }
                    }
                    else{
                        System.out.println("Invalid Input! Try Again! ");
                    }
                }
                break;
            case 4:
                for(int j=0;j<hotel_ob.premium_doubleroom.length;j++){
                    if(hotel_ob.premium_doubleroom[j]==null){
                        System.out.print("40"+j+",");
                    }
                }
                System.out.println();
                System.out.print("Choose any room from the above available room: ");
                while(true){
                    ch=sc.nextInt();
                    rn=ch-400;
                    if(rn>=0 && rn<=9){ 
                        if(hotel_ob.premium_doubleroom[rn]!=null){
                            System.out.println("Room not available!");
                            System.out.println("Choose any other room!");
                        }
                        else{
                            customer_details(i, rn);
                            break;
                        }
                    }
                    else{
                        System.out.println("Invalid Input! Try Again! ");
                    }
                }
                break;
            }
            System.out.println("Room Booked");
            System.out.print("Enter * to order food or enter any other key: ");
            char a=sc.next().charAt(0);
            if(a=='*'){
                Order_food(i,rn);
            }
        }
        static void bill(int rn,int ty){
            double amount=0;
            String list[]={"Sandwitch","Maggie","Normal Thali","Special Thali","Tea","Coffee","Coke"};
            System.out.println("\n************************");
            System.out.println("\t\tBill");
            System.out.println("************************");
            switch(ty){
                case 1:
                    amount+=1000;
                    System.out.println("\nRoom charge:- "+1000);
                    System.out.println("\n===============");
                    System.out.println("Food Charges:- ");
                    System.out.println("Item\t\t\t\tQuantity\t\tPrice");
                    System.out.println("---------------------------");
                    for(Food obb:hotel_ob.economy_singleroom[rn].food){
                        amount+=obb.price;
                        System.out.println(list[obb.itemno-1]+"\t\t\t\t"+obb.quantity+"\t\t\t"+obb.price);
                    }
                    break;
                case 2:
                    amount+=1800;
                    System.out.println("\nRoom charge:- "+1800);
                    System.out.println("\n===============");
                    System.out.println("Food Charges:- ");
                    System.out.println("Item\t\t\tQuantity\tPrice");
                    System.out.println("---------------------------");
                    for(Food obb:hotel_ob.premium_singleroom[rn].food){
                        amount+=obb.price;
                        System.out.println(list[obb.itemno-1]+"\t\t\t"+obb.quantity+"\t"+obb.price);
                    }
                    break;
                case 3:
                    amount+=2000;
                    System.out.println("\nRoom charge:- "+2000);
                    System.out.println("\n===============");
                    System.out.println("Food Charges:- ");
                    System.out.println("Item\t\t\tQuantity\tPrice");
                    System.out.println("---------------------------");
                    for(Food obb:hotel_ob.economy_doubleroom[rn].food){
                        amount+=obb.price;
                        System.out.println(list[obb.itemno-1]+"\t\t\t"+obb.quantity+"\t"+obb.price);
                    }
                    break;
                case 4:
                    amount+=4000;
                    System.out.println("\nRoom charge:- "+4000);
                    System.out.println("\n===============");
                    System.out.println("Food Charges:- ");
                    System.out.println("Item\t\t\tQuantity\tPrice");
                    System.out.println("---------------------------");
                    for(Food obb:hotel_ob.premium_doubleroom[rn].food){
                        amount+=obb.price;
                        System.out.println(list[obb.itemno-1]+"\t\t\t"+obb.quantity+"\t"+obb.price);
                    }
                    break;   
            }
            System.out.println("\nTotal Amount- "+amount);
        }
        static void Check_out(int rn,int ty){
            char w;
            switch(ty){
                case 1:
                    if(hotel_ob.economy_singleroom[rn]!=null)
                        System.out.println("Room used by "+hotel_ob.economy_singleroom[rn].name);
                    else{
                        System.out.println("Empty Already!");
                        return ;
                    }
                    System.out.println("Do you want to checkout?(y/n)");
                    w=sc.next().charAt(0);
                    if(w=='y' || w=='Y'){
                        bill(rn,ty);
                        hotel_ob.economy_singleroom[rn]=null;
                        System.out.println("Thank you for visiting our Hotel!");
                    }
                break;
                case 2:
                    if(hotel_ob.premium_singleroom[rn]!=null)
                        System.out.println("Room used by "+hotel_ob.premium_singleroom[rn].name);
                    else{
                        System.out.println("Empty Already!");
                        return ;
                    }
                    System.out.println("Do you want to checkout?(y/n)");
                     w=sc.next().charAt(0);
                    if(w=='y' || w=='Y'){
                        bill(rn,ty);
                        hotel_ob.premium_singleroom[rn]=null;
                        System.out.println("Thank you for visiting our Hotel!");
                    }
                break;
                case 3:
                    if(hotel_ob.economy_doubleroom[rn]!=null)
                        System.out.println("Room used by "+hotel_ob.economy_doubleroom[rn].name);
                    else{
                        System.out.println("Empty Already!");
                        return ;
                    }
                    System.out.println("Do you want to checkout?(y/n)");
                    w=sc.next().charAt(0);
                    if(w=='y' || w=='Y'){
                        bill(rn,ty);
                        hotel_ob.economy_doubleroom[rn]=null;
                        System.out.println("Thank you for visiting our Hotel!");
                    }
                break;
                case 4:
                    if(hotel_ob.premium_doubleroom[rn]!=null)
                        System.out.println("Room used by "+hotel_ob.premium_doubleroom[rn].name);
                    else{
                        System.out.println("Empty Already!");
                        return ;
                    }
                    System.out.println("Do you want to checkout?(y/n)");
                    w=sc.next().charAt(0);
                    if(w=='y' || w=='Y'){
                        bill(rn,ty);
                        hotel_ob.premium_doubleroom[rn]=null;
                        System.out.println("Thank you for visiting our Hotel!");
                    }
                break;
            }
        }
}
class write implements Runnable
{
    holder hotel_ob;
    write(holder hotel_ob)
    {
        this.hotel_ob=hotel_ob;
    }
    @Override
    public void run() {
          try{
        FileOutputStream fout=new FileOutputStream("Data");
        ObjectOutputStream oos=new ObjectOutputStream(fout);
        oos.writeObject(hotel_ob);
        }
        catch(Exception e)
        {
            System.out.println("Error in writing "+e);
        }         
        
    }
    
}

class Maiin_Hotel{
        public static void main(String args[]){
        int a=0;
        try{
             File f=new File("Data");
            if(f.exists()){
                FileInputStream fin=new FileInputStream(f);
                ObjectInputStream Ois=new ObjectInputStream(fin);
                Hotel_main.hotel_ob=(holder)Ois.readObject();  
            }
            Scanner sc=new Scanner(System.in);
            int ch,ch2;
            do{
                System.out.println("Enter your choice: ");
                System.out.println("1. Room Detail");
                System.out.println("2. Book Room");
                System.out.println("3. Order food");
                System.out.println("4. Check out");
                System.out.println("5. Exit");
                ch=sc.nextInt();
                switch(ch){
                    case 1:Hotel_main.types();
                        break;
                    case 2:Hotel_main.types();
                            System.out.print("Enter room type:");
                            while(true){
                                ch2=sc.nextInt();
                                if(ch2>0 && ch2<=4){
                                    if(Hotel_main.availability(ch2).equals("0")){
                                        System.out.println("Room Not available!");
                                    }
                                    else{
                                        Hotel_main.bookroom(ch2);
                                        break;
                                    }
                                }
                                else{
                                    System.out.print("Wrong choice! Try Again.");
                                }
                            }
                        break;
                    case 3:System.out.print("Enter your room Number: ");
                            ch2=sc.nextInt();
                            if((ch2/10)%10==0){
                                Hotel_main.Order_food(ch2%10, ch2/100);
                                }
                                else{
                                    System.out.println("Room doesn't exit!");
                                }
                        break;
                    case 4:System.out.print("Enter your room Number: ");
                            ch2=sc.nextInt();
                            if((ch2/10)%10==0){
                            Hotel_main.Check_out(ch2%10, ch2/100);
                            }
                            else{
                                System.out.println("Room doesn't exit!");
                            }
                        break;
                }
            }while(ch!=5);
            Thread t=new Thread(new write(Hotel_main.hotel_ob));
            t.start();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}