import java.util.*;

// Main Library Class
class Library {

    Scanner conin = new Scanner(System.in);
    ArrayList<Book> books = new ArrayList<>();
    HashMap<String, String> ausers = new HashMap<>();
    HashMap<String, String> susers = new HashMap<>();
    int userRole = 0; // 1 = Admin, 2 = Student

    public void display() {
        System.out.println("====== Welcome to Library ======");
        userauth();
    }

    // Authentication
    public void userauth() {
        ausers.put("admin", "12345");
        susers.put("student", "6789");
        books.add(new Book(101, "Java Basics", "James Gosling"));
        books.add(new Book(102, "Algorithms", "CLRS"));

        System.out.println("Are You a Student or Admin(Librarian): ");//Just for formality
        String usern = conin.nextLine().strip().toLowerCase();

        if (usern.equals("admin") || usern.equals("student")) {
            System.out.println("Enter 1 to Login and 2 to Register: ");
            int choice = conin.nextInt();
            conin.nextLine(); // consume newline

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                System.out.println("Invalid Option! Try Again.");
                userauth();
            }
        } else {
            System.out.println("Enter 'admin' or 'student' correctly.");
            userauth();
        }
    }

    // Login
    public void login() {
        System.out.println("\nLOGIN: ");
        System.out.print("Enter Username: ");
        String username = conin.nextLine();
        System.out.print("Enter Password: ");
        String password = conin.nextLine();

        if (ausers.containsKey(username) && ausers.get(username).equals(password)) {
            System.out.println("\nWelcome Admin " + username);
            userRole = 1;
            menu();
        } else if (susers.containsKey(username) && susers.get(username).equals(password)) {
            System.out.println("\nWelcome Student " + username);
            userRole = 2;
            menu();
        } else {
            System.out.println("\nInvalid Username or Password.");
            login();
        }
    }

    // Registration
    public void register() {
        System.out.println("REGISTER: ");
        System.out.print("Enter New Username: ");
        String username = conin.nextLine();
        System.out.print("Enter Password: ");
        String password = conin.nextLine();
        System.out.print("Retype Password: ");
        String confirm = conin.nextLine();
        System.out.print("Enter Role (Admin/Student): ");
        String role = conin.nextLine().strip().toLowerCase();

        if (!password.equals(confirm)) {
            System.out.println("Passwords do not match. Try Again.");
            register();
            return;
        }

        if (role.equals("admin")) {
            ausers.put(username, password);
            System.out.println("Registration Successful. Please Login.");
            login();
        } else if (role.equals("student")) {
            susers.put(username, password);
            System.out.println("Registration Successful. Please Login.");
            login();
        } else {
            System.out.println("Invalid role! Enter Admin or Student.");
            register();
        }
    }

    // Menu
    public void menu() {
        if (userRole == 1) {
            System.out.println("\n==== Admin Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Issue Book");
            System.out.println("7. Exit");
        } else {
            System.out.println("\n==== Student Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("7. Exit");
        }

        System.out.print("Enter your choice: ");
        int choice = conin.nextInt();
        conin.nextLine(); // consume newline

        switch (choice) {
            case 1: 
            if (userRole == 1){ 
            addBook(); 
            }
            break;

            case 2: 
            showBook(); 
            break;

            case 3: 
            searchTitle(); 
            break;

            case 4: 
            searchAuthor(); 
            break;

            case 5: 
            issueBook(); 
            break;

            case 7: 
            exit(); 
            break;

            case 8: 
            if (userRole == 2){ 
            rBook(); 
            }
            break;

            default: System.out.println("Invalid Choice!"); break;
        }

        if (choice != 7) {
        whattodonext();
        }
    }

    // Show Books
    public void showBook() {
        if (books.isEmpty()) {
            System.out.println("No Books Available Right Now.");
            return;
        }
        System.out.println("\n==== Library Books ====");
        for (Book book : books) {
            System.out.println(book);
        }
        whattodonext();
    }

    // Search by Title
    public void searchTitle() {
        System.out.print("Enter Book Title: ");
        String t = conin.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(t)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found){
             System.out.println("No Record Found with the Title.");
        }
        whattodonext();
    }

    // Search by Author
    public void searchAuthor() {
        System.out.print("Enter Author: ");
        String t = conin.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(t)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found){
            System.out.println("No Record Found with the Author.");
        }
        whattodonext();
    }

    // Issue Book
    public void issueBook() {
        System.out.print("Enter Book ID: ");
        int id = conin.nextInt();
        conin.nextLine();
        boolean found = false;

        for (Book book : books) {
            if (book.getId() == id) {
                found = true;
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book Issued Successfully: " + book.getTitle());
                } else {
                    System.out.println("Sorry, Book Already Issued.");
                }
                break;
            }
        }
        if (!found){ 
        System.out.println("Book with ID " + id + " not found.");
        }
        whattodonext();
    }

    // Return Book
    public void rBook() {
        System.out.print("Enter Book ID to Return: ");
        int id = conin.nextInt();
        conin.nextLine();
        boolean found = false;

        for (Book book : books) {
            if (book.getId() == id) {
                found = true;
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book Returned Successfully: " + book.getTitle());
                } else {
                    System.out.println("This Book was not Issued.");
                }
                break;
            }
        }
        if (!found){
            System.out.println("Book with ID " + id + " not found.");
        }
        whattodonext();
    }

    // Add Book
    public void addBook() {
        System.out.print("Enter Book ID: ");
        int id = conin.nextInt();
        conin.nextLine();
        System.out.print("Enter Book Title: ");
        String title = conin.nextLine();
        System.out.print("Enter Book Author: ");
        String author = conin.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book Added Successfully!");

        whattodonext();
    }


    public void whattodonext(){
        System.out.println("\nDo You Want to Continue further(Y/N): ");
        char c=conin.next().charAt(0);
        if(Character.toUpperCase(c)=='Y'){
            menu();
        }
        else{
            exit();
        }
    }
    // Exit
    public void exit() {
        System.out.println("\nThanks for Using This System.");
        System.out.println("See You Next Time.");
        System.out.println("====== Library Management System, Created with ❤️ by Asmit159 ======");
    }
}


class librarymanager {
    public static void main(String[] args) {
        Library ob = new Library();
        ob.display();
    }
}

