package Model.Cart;

import Model.Articolo.Articolo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private List<Articolo> items;
    private static int count = 0;

    public Cart (){
        this.items = new ArrayList<>();
    }

    public List<Articolo> getItems() {
        return items;
    }

    public void setItems(List<Articolo> items) {
        this.items = items;
    }

    public double total(){
        double sum = 0.0;
        for (Articolo a: this.items)
            sum += a.getPrezzo();
        return sum;
    }

    public boolean addProduct(Articolo articolo, int quantity){
        Optional<Articolo> optItem = find(articolo.getIDarticolo());
        if(optItem.isPresent()){
            optItem.get().setLocalQuantity(quantity);
            return true;
        } else {
            return items.add(articolo);
        }
    }

    public Optional<Articolo> find(int id){
        return items.stream().filter(it -> it.getIDarticolo() == id).findFirst();
    }

    public boolean removeProduct(int id){
        return items.removeIf(it -> it.getIDarticolo() == id);
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
}
