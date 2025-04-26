package ecommerce;
import java.util.*;
class Item 
{
    int id;
    String name;
    double price;
    Item(int i, String n, double p) 
    {
        this.id = i;
        this.name = n;
        this.price = p;
    }
}
class Product extends Item 
{
    Product(int i, String n, double p) 
    {
        super(i, n, p);
    }
}
class CartItem 
{
    Product product;
    int quantity;
    CartItem(Product p, int q) 
    {
        product = p;
        quantity = q;
    }
    double getTotal() 
    {
        return product.price * quantity;
    }
}
public class CartSystem 
{
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<CartItem> cart = new ArrayList<>();
    public CartSystem() 
    {
        products.add(new Product(1, "T-Shirt", 500));
        products.add(new Product(2, "Jeans", 1200));
        products.add(new Product(3, "Shoes", 1800));
    }
    public void showProducts() 
    {
        for (Product p : products) 
	{
            System.out.println(p.id + ". " + p.name + " - Rs." + p.price);
        }
    }
    public void addToCart(int id, int quantity) 
    {
        int found = 0;
        for (Product p : products) 
	{
            if (p.id == id) 
	    {
                cart.add(new CartItem(p, quantity));
                System.out.println(quantity + " x " + p.name + " added to cart.");
                found = 1;
            }
        }
        if (found == 0) 
	{
            System.out.println("Product not found.");
        }
    }
    public void viewCart() 
    {
        if (cart.size() == 0) 
	{
            System.out.println("Cart is empty.");
        } 
	else 
	{
            System.out.println("Items in Cart : ");
            for (CartItem item : cart) 
	    {
                System.out.println(item.product.name + " x " + item.quantity + " = Rs." + item.getTotal());
            }
        }
    }
    public void checkout() 
    {
    Scanner sc = new Scanner(System.in);
    if (cart.size() == 0) 
    {
        System.out.println("Cart is empty.");
        System.out.print("Do you want to add products before checkout? (yes/no): ");
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("yes")) 
        {
            showProducts();
            try 
            {
                System.out.print("Enter Product ID : ");
                int id = sc.nextInt();
                System.out.print("Enter Quantity : ");
                int qty = sc.nextInt();
                addToCart(id, qty);
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid input! Please enter valid numbers.");
                sc.nextLine();
            }
        } 
        else 
        {
            return;
        }
    }
    if (cart.size() > 0) 
    {
        double total = 0;
        for (CartItem item : cart) 
        {
            total += item.getTotal();
        }
        System.out.println("Total Bill : Rs." + total);
        Thread t = new OrderProcessor();
        t.start();
        try 
        {
            t.join(); 
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Order processing interrupted.");
        }
        cart.clear();
    }
   }
}
class OrderProcessor extends Thread 
{
    public void run() 
    {
        try 
	{
            System.out.println("Processing your order...");
            Thread.sleep(2000);
            System.out.println("Order placed successfully!");
        } 
	catch (InterruptedException e) 
	{
            System.out.println("Order processing interrupted.");
        }
    }
}
