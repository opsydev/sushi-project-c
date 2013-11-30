package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCartBean {
	private ClientBean client;
	private double total;
	private double shipping;
	private double hstValue;
	private double hst;
	private double grandTotal;
	private List<ItemBean> items;
	
	public ShoppingCartBean(){
		client = null;
		items = new ArrayList<ItemBean>();
	}

	public ClientBean getClient() {
		return client;
	}

	public void setClient(ClientBean client) {
		this.client = client;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getShipping() {
		return shipping;
	}

	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public double getHst() {
		return hst;
	}

	public void setHst(double hst) {
		this.hst = hst;
	}
	
	public double getHstValue() {
		return hstValue;
	}

	public void setHstValue(double hstValue) {
		this.hstValue = hstValue;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public List<ItemBean> getItems() {
		return items;
	}

	public void setItems(List<ItemBean> items) {
		this.items = items;
	}

	public void addItem(ItemBean i, int quantity) {
		Iterator<ItemBean> it = items.iterator();
		boolean exists = false;
		while(it.hasNext()){
			ItemBean temp = it.next();
			if(temp.getNumber().equals(i.getNumber())){
				temp.setQuantity(quantity);
				exists = true;
			}
		}
		if(exists != true){
			i.setQuantity(quantity);
			this.items.add(i);
		}
		
		this.calculateTotal();
		
	}
	
	private void calculateTotal(){
		double t = 0;
		Iterator<ItemBean> it = items.iterator();
		while(it.hasNext()){
			ItemBean temp = it.next();
			t += (temp.getQuantity()*temp.getPrice());
		}
		
		this.total = t;
		if(t>100){
			this.setShipping(0);
		}
		double taxes = (this.getTotal() + this.getShipping()) * (this.getHstValue()/100.0);
		this.setHst(taxes);
		
		this.setGrandTotal(this.getHst() + this.getTotal() + this.getShipping());
	}
}
