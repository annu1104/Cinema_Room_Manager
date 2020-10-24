import java.util.Scanner;
public class Cinema {
    
    public static void show_seats(int[][] seat_detail, int rows, int seats) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0)
                    System.out.print(" ");
                else if (i == 0)
                    System.out.print(" " + j);
                else if (j == 0)
                    System.out.print(i);
                else {
                    if (seat_detail[i-1][j-1] == 1)
                        System.out.print(" B");
                    else
                        System.out.print(" S");
                }
            }
            System.out.println();
        }
    }

    public static void book_ticket(int[][] seat_detail, int rows, int seats, int total_seats, int base_price, int rear_price) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number:");
        int row = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = sc.nextInt();
        System.out.println();
        if (row < 1 || row > rows || seat < 1 || seat > seats) {
            System.out.println("Wrong input!");
            book_ticket(seat_detail, rows, seats, total_seats, base_price, rear_price);
        } else if (seat_detail[row-1][seat-1] == 1) {
            System.out.println("That ticket has already been purchased");
            book_ticket(seat_detail, rows, seats, total_seats, base_price, rear_price);
        } else {
            seat_detail[row-1][seat-1] = 1;
            int price = ticket_price(row, rows, total_seats, base_price, rear_price);
            System.out.println("Ticket price: $" + price);
        }
    }
    public static int ticket_price(int row, int rows, int total_seats, int base_price, int rear_price) {
        if (total_seats <= 60)
            return base_price;
        else {
            if (row <= (rows/2))
                return base_price;
            else
                return rear_price;
        }
    }

    public static void statistics(int rows, int seats, int[][] seat_detail, int total_seats, int base_price, int rear_price) {
        System.out.println();
        int current_income = 0;
        int purchased_tickets = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (seat_detail[i][j] == 1) {
                    current_income += ticket_price(i+1, rows, total_seats, base_price, rear_price);
                    purchased_tickets++;
                }
            }
        }

        int total_income;
        float percentage = ((float)purchased_tickets / (float)total_seats) * (float)100;
        if ((rows * seats) <= 60) {
            total_income = (rows*seats) * base_price;
        }
        else {
            total_income = ((rows/2)*seats) * base_price;
            total_income += ((rows-(rows/2)) * seats) * rear_price;
        }

        System.out.println("Number of purchased tickets: " + purchased_tickets);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + current_income);
        System.out.println("Total income: $" + total_income);
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base_price = 10;
        int rear_price = 8;
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        int[][] seat_detail = new int[rows][seats];
        int total_seats = (rows * seats);
        boolean flag = true;
        while (flag) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    show_seats(seat_detail, rows, seats);
                    break;
                case 2:
                    book_ticket(seat_detail, rows, seats, total_seats, base_price, rear_price);
                    break;
                case 3:
                    statistics(rows, seats, seat_detail, total_seats, base_price, rear_price);
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}