import java.util.*;
import ecommerce.CartSystem;
class MainApp 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        CartSystem cart = new CartSystem();
        int choice = 0;
        while (choice != 5) 
	{
            System.out.println("\n=== E-Commerce Cart ===");
            System.out.println("1. Show Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter choice : ");
            try 
	    {
                choice = sc.nextInt();
                if (choice == 1) 
		{
                    cart.showProducts();
                } 
		else if (choice == 2) 
		{
                    try 
		    {
                        System.out.print("Enter Product ID : ");
                        int id = sc.nextInt();
                        System.out.print("Enter Quantity : ");
                        int qty = sc.nextInt();
                        cart.addToCart(id, qty);
                    } 
		    catch (InputMismatchException e) 
		    {
                        System.out.println("Invalid input! Please enter numbers only.");
                        sc.nextLine();
                    }
                } 
		else if (choice == 3)
		{
                    cart.viewCart();
                }
		else if (choice == 4) 
		{
                    cart.checkout();
                } 
		else if (choice == 5) 
		{
                    System.out.println("Exiting... Thank you!");
                    break;
                } 
		else 
	        {
                    System.out.println("Invalid choice. Please Try again.");
                }
	    } 
	    catch (InputMismatchException e) 
	    {
                System.out.println("Please enter a valid numeric choice.");
                sc.nextLine();
            }
        }
    }
}
