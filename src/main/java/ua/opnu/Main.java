package ua.opnu;

public class Main {
    public static void main(String[] args) {
        // Student demo
        Student st = new Student("Marta", 1);
        st.addCourse("Algorithms");
        st.addCourse("Linear Algebra");
        System.out.println(st.getName() + ": кількість вивчаємих дисциплін - " + st.getCourseCount());
        System.out.println(st.getName() + ": рік навчання - " + st.getYear());
        System.out.println(st.getName() + ": заплатив за навчання - " + st.getTuition());

        // TimeSpan demo
        TimeSpan a = new TimeSpan(2, 30);
        a.add(1, 15);  // очікувано 3:45
        System.out.println("A = " + a.getHours() + "h " + a.getMinutes() + "m (" + a.getTotalMinutes() + " min)");

        TimeSpan b = new TimeSpan(1, 59);
        a.addTimeSpan(b); // 3:45 + 1:59 = 5:44
        System.out.println("A+B = " + a.getHours() + "h " + a.getMinutes() + "m");
        a.subtract(new TimeSpan(0, 45)); // 4:59
        System.out.println("A after subtract 0:45 = " + a.getHours() + "h " + a.getMinutes() + "m");
        a.scale(2); // 9:58
        System.out.println("A after scale(2) = " + a.getHours() + "h " + a.getMinutes() + "m");

        // BankAccount demo
        BankAccount x = new BankAccount("Nastia", 300);
        BankAccount y = new BankAccount("Olha", 50);
        x.setTransactionFee(2.5);

        x.deposit(100);             // 400
        x.withdraw(20);             // -22.5 => 377.5
        boolean ok = x.transfer(y, 100); // x: -102.5 => 275.0, y: +100 => 150
        System.out.println("Transfer ok? " + ok);
        System.out.println("Nastia: " + x.getBalance() + ", Olha: " + y.getBalance());
    }
}
