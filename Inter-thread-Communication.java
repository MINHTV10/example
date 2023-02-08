Inter-thread-Communication

class Customer {
    int amount = 10000;
 
    synchronized void withdraw(int amount) {
        System.out.println("Rút tiền...");
 
        if (this.amount < amount) {
            System.out.println("Tài khoản không đủ; đợi gửi tiền...");
            try {
                wait();
            } catch (Exception e) {
            }
        }
        this.amount -= amount;
        System.out.println("Hoàn thành rút tiền!");
    }
 
    synchronized void deposit(int amount) {
        System.out.println("Gửi tiền...");
        this.amount += amount;
        System.out.println("Hoàn thành gửi tiền!");
        notify();
    }
}
 
public class Test {
    public static void main(String args[]) {
        final Customer c = new Customer();
        new Thread() {
            public void run() {
                c.withdraw(15000);
            }
        }.start();
        new Thread() {
            public void run() {
                c.deposit(10000);
            }
        }.start();
    }
}

/*

Output:

Rút tiền...
Tài khoản không đủ; đợi gửi tiền...
Gửi tiền...
Hoàn thành gửi tiền!
Hoàn thành rút tiền!

*/