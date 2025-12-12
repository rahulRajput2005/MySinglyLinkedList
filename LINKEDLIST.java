import java.util.Scanner;

public class LINKEDLIST{

    static class Node{
        String data;
        Node next;

        Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    static Node head;

    // --- Core Operations (Logic remains the same, but with slight message tweaks) ---

    public static void addStart(String data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
        System.out.println("-> SUCCESS: Added '" + data + "' to the start.");
    }

    public static void addLast(String data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }

        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
        System.out.println("-> SUCCESS: Added '" + data + "' to the end.");
    }


    public static void addAtIndex(int index, String data){
        if(index < 0){
            System.out.println("-> ERROR: Invalid Index (must be >= 0).");
            return;
        }

        Node newNode = new Node(data);

        if(index == 0){
            newNode.next = head;
            head = newNode;
            System.out.println("-> SUCCESS: Added '" + data + "' at index 0.");
            return;
        }
        int count = 0;
        Node temp = head;

        while(count < index-1 && temp != null){
            temp = temp.next;
            count++;
        }

        if(temp == null){
            System.out.println("-> ERROR: Invalid Index " + index + ". List too short.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println("-> SUCCESS: Added '" + data + "' at index " + index + ".");
    }


    public static void removeAtIndex(int index){
        if(head == null){
            System.out.println("-> ERROR: Empty List. Nothing to remove.");
            return;
        }

        if(index == 0){
            removeFirst();
            return;
        }

        Node temp = head;
        int count = 0;

        while(count < index-1 && temp != null){
            temp = temp.next;
            count++;
        }

        if(temp == null || temp.next == null){
            System.out.println("-> ERROR: INDEX OUT OF BOUNDS. Cannot remove at index " + index + ".");
            return;
        }

        Node nodeToRemove = temp.next;
        temp.next = temp.next.next;
        System.out.println("-> SUCCESS: Removed element '" + nodeToRemove.data + "' at index " + index + ".");
    }

    public static void removeFirst(){
        if(head == null){
            System.out.println("-> ERROR: Empty List. Nothing to remove.");
            return;
        }

        String removedData = head.data;
        head = head.next;
        System.out.println("-> SUCCESS: Removed first element: " + removedData);
    }

    public static void removeLast(){
        if(head == null){
            System.out.println("-> ERROR: Empty List. Nothing to remove.");
            return;
        }

        if(head.next == null){
            String removedData = head.data;
            head = null;
            System.out.println("-> SUCCESS: Removed last element: " + removedData);
            return;
        }

        Node temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }

        String removedData = temp.next.data;
        temp.next = null;
        System.out.println("-> SUCCESS: Removed last element: " + removedData);
    }

    public static void getFirst(){
        if(head == null){
            System.out.println("-> INFO: List is Empty.");
            return;
        }
        System.out.println("-> RESULT: First element: **" + head.data + "**");
    }

    public static void getLast(){
        if(head == null){
            System.out.println("-> INFO: List is Empty.");
            return;
        }
        Node temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        System.out.println("-> RESULT: Last element: **" + temp.data + "**");
    }


    public static void getAtIndex(int index){
        if(head == null){
            System.out.println("-> INFO: Empty list.");
            return;
        }
        if (index < 0) {
            System.out.println("-> ERROR: Invalid Index (must be >= 0).");
            return;
        }

        int count = 0;
        Node temp = head;

        while(temp != null){
            if (count == index) {
                System.out.println("-> RESULT: Element at index " + index + ": **" + temp.data + "**");
                return;
            }
            temp = temp.next;
            count++;
        }
        System.out.println("-> ERROR: Index " + index + " is Out of Bounds.");
    }


    public static int getLength(){
        if(head == null){
            return 0;
        }
        int count = 0;
        Node temp = head;

        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }


    public static void updateData(String oldData, String newData){

        if(head == null){
            System.out.println("-> INFO: Empty List. Nothing to update.");
            return;
        }

        Node temp = head;

        while(temp != null){
            if(temp.data.equals(oldData)){
                temp.data = newData;
                System.out.println("-> SUCCESS: Updated '" + oldData + "' to '"+ newData + "'.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("-> ERROR: Update failed. Could not find element '" + oldData + "'.");
    }

    public static void display(){
        System.out.println("\n------------------------------------");
        if (head == null) {
            System.out.println(" CURRENT LIST: [Empty]");
            System.out.println("------------------------------------");
            return;
        }

        Node temp = head;
        System.out.print(" CURRENT LIST ("+getLength()+" nodes) : ");
        while(temp != null){
            System.out.print("[" + temp.data + "]");

            if(temp.next != null){
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
        System.out.println("------------------------------------");
    }

    public static void exit() throws InterruptedException {
        int i = 0;
        System.out.print("Exiting Linked List Program");
        while(i < 5){
            Thread.sleep(280);
            System.out.print(".");
            i++;
        }
        System.out.println("\n--- Program Closed. ---");
    }

    // --- Sub-Menu Methods (Calls updated core methods) ---

    public static void addMenu(Scanner scanner){
        boolean adding = true;
        while(adding){
            System.out.println("\n--- ADD OPERATIONS SUB-MENU ---");
            System.out.println("1. Add First");
            System.out.println("2. Add Last");
            System.out.println("3. Add at specific Index");
            System.out.println("4. **RETURN TO MAIN MENU**");
            System.out.print("Select Add Operation: ");

            if (!scanner.hasNextInt()) {
                System.out.println("-> ERROR: Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter Data: ");
                    String dataToStart = scanner.nextLine();
                    addStart(dataToStart);
                    display();
                    break;
                case 2:
                    System.out.print("Enter Data: ");
                    String dataToLast = scanner.nextLine();
                    addLast(dataToLast);
                    display();
                    break;
                case 3:
                    System.out.print("Enter index: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("-> ERROR: Invalid index input.");
                        scanner.nextLine();
                        break;
                    }
                    int indexAdd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Data: ");
                    String dataToIndex = scanner.nextLine();
                    addAtIndex(indexAdd, dataToIndex);
                    display();
                    break;
                case 4:
                    adding = false;
                    System.out.println("\n--- Returning to Main Menu ---");
                    break;
                default:
                    System.out.println("-> ERROR: Invalid Choice!!");
                    break;
            }
        }
    }

    public static void removeMenu(Scanner scanner){
        boolean removing = true;
        while(removing){
            System.out.println("\n--- REMOVE OPERATIONS SUB-MENU ---");
            System.out.println("1. Remove First");
            System.out.println("2. Remove Last");
            System.out.println("3. Remove at specific Index");
            System.out.println("4. **RETURN TO MAIN MENU**");
            System.out.print("Select Remove Operation: ");

            if (!scanner.hasNextInt()) {
                System.out.println("-> ERROR: Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    removeFirst();
                    display();
                    break;
                case 2:
                    removeLast();
                    display();
                    break;
                case 3:
                    System.out.print("Enter index to remove: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("-> ERROR: Invalid index input.");
                        scanner.nextLine();
                        break;
                    }
                    int indexRemove = scanner.nextInt();
                    scanner.nextLine();
                    removeAtIndex(indexRemove);
                    display();
                    break;
                case 4:
                    removing = false;
                    System.out.println("\n--- Returning to Main Menu ---");
                    break;
                default:
                    System.out.println("-> ERROR: Invalid Choice!!");
                    break;
            }
        }
    }

    public static void getMenu(Scanner scanner){
        boolean getting = true;
        while(getting){
            System.out.println("\n--- GET OPERATIONS SUB-MENU ---");
            System.out.println("1. Get First");
            System.out.println("2. Get Last");
            System.out.println("3. Get at specific Index");
            System.out.println("4. **RETURN TO MAIN MENU**");
            System.out.print("Select Get Operation: ");

            if (!scanner.hasNextInt()) {
                System.out.println("-> ERROR: Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    getFirst();
                    break;
                case 2:
                    getLast();
                    break;
                case 3:
                    System.out.print("Enter Index to retrieve: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("-> ERROR: Invalid index input.");
                        scanner.nextLine();
                        break;
                    }
                    int indexGet = scanner.nextInt();
                    scanner.nextLine();
                    getAtIndex(indexGet);
                    break;
                case 4:
                    getting = false;
                    System.out.println("\n--- Returning to Main Menu ---");
                    break;
                default:
                    System.out.println("-> ERROR: Invalid Choice!!");
                    break;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;

        System.out.println("=============================================");
        System.out.println("== Singly Linked List Implementation in Java ==");
        System.out.println("=============================================");

        while(isTrue){
            System.out.println("\n*********************************************");
            System.out.println("          MAIN MENU (List Length: " + getLength() + ")");
            System.out.println("*********************************************");
            System.out.println("1. **ADD MENU** (Insert operations)");
            System.out.println("2. **REMOVE MENU** (Deletion operations)");
            System.out.println("3. **GET MENU** (Retrieval operations)");
            System.out.println("4. View All Nodes (Display)");
            System.out.println("5. Update Data");
            System.out.println("6. Check Length");
            System.out.println("7. EXit Program");
            System.out.println("*********************************************");

            System.out.print("Enter your choice (1-7): ");

            if (!scanner.hasNextInt()) {
                System.out.println("\n-> ERROR: Invalid input. Please enter a number from the menu.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline


            switch(choice){
                case 1:
                    addMenu(scanner);
                    break;
                case 2:
                    removeMenu(scanner);
                    break;
                case 3:
                    getMenu(scanner);
                    break;
                case 4:
                    display();
                    break;
                case 5:
                    System.out.print("Enter Old Data to replace: ");
                    String oldData = scanner.nextLine();
                    System.out.print("Enter New Data: " );
                    String newData = scanner.nextLine();
                    updateData(oldData, newData);
                    display();
                    break;
                case 6:
                    System.out.println("-> INFO: Current Length of List: **" + getLength() + "**");
                    break;
                case 7:
                    exit();
                    isTrue = false;
                    break;
                default:
                    System.out.println("\n-> ERROR: Invalid Choice!! Please select an option from 1 to 7.");
                    break;
            }
        }
        scanner.close();
    }
}