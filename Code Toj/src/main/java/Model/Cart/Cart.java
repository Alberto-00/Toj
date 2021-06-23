package Model.Cart;

import Model.Ordine.Ordine;

import java.util.List;
import java.util.Optional;

public class Cart {

    private List<Ordine> items;

    public Cart (List<Ordine> items){
        this.items = items;
    }

    public List<Ordine> getItems() {
        return items;
    }

    public void setItems(List<Ordine> items) {
        this.items = items;
    }

    public double total(){
        return items.stream().mapToDouble(Ordine::total).reduce(0.00, Double::sum);
    }

    public boolean addOrdine(Ordine ordine, int quantity){
        Optional<Ordine> optItem = find(ordine.getID_ordine());
        if(optItem.isPresent()){
            optItem.get().setQuantita(quantity);
            return true;
        } else {
            return items.add(ordine);
        }
    }

    public Optional<Ordine> find(String id){
        return items.stream().filter(it -> it.getID_ordine().compareTo(id) == 0).findFirst();
    }

    public boolean removeOrdine(String id){
        return items.removeIf(it -> it.getID_ordine().compareTo(id) == 0);
    }

    public int quantity(){
        return items.stream().mapToInt(Ordine::getQuantita).reduce(0, Integer::sum);
    }

    public void resetCart(){
        items.clear();
    }
}
