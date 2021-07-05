package Model.Cart;

import Model.Articolo.Articolo;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Articolo> items;
    private final static double spedizione = 4.5;
    private int count;
    private int subtotal;

    public Cart (){
        this.items = new ArrayList<>();
        count = 0;
        subtotal = 0;
    }

    public List<Articolo> getItems() {
        return items;
    }

    public void setItems(List<Articolo> items) {
        this.items = items;
    }

    public double subTotal(){
        int sum = 0;
        for (Articolo a: this.items)
            sum += a.totalPrice();
        return this.subtotal = sum;
    }

    public double applyCoupon(double coupon){
        return Math.round((this.subtotal * coupon)*100.0)/100.00;
    }

    public boolean addProduct(Articolo articolo, int quantity, String size) {
        for(Articolo a: this.items) {
            if(a.getChosenSize().compareTo(size) == 0 && a.getIDarticolo() == articolo.getIDarticolo()) {
                a.setLocalQuantity(quantity);
                return true;
            }
        }
        articolo.setLocalQuantity(quantity);
        articolo.setChosenSize(size);
        items.add(count, articolo);
        count++;
        return false;
    }

    public Articolo find(int id, String size){
        for (Articolo a: this.items)
            if(a.getChosenSize().compareTo(size) == 0 && a.getIDarticolo() == id)
                return a;
        return null;
    }

    public boolean removeProduct(int id, String size){
        count--;
        return items.removeIf(it -> it.getIDarticolo() == id && it.getChosenSize().compareTo(size) == 0);
    }

    public int quantity(){
        int sum = 0;
        for (Articolo a: this.items)
            sum += a.getLocalQuantity();
        return sum;
    }

    public void resetCart(){
        items.clear();
    }

    public static double getSpedizione() {
        return spedizione;
    }
}
