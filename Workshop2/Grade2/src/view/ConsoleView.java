//package view;
//
//import model.MemberRegister;
//
//import java.util.Scanner;
//
///**
// * Created by joakimbergqvist on 2017-09-13.
// */
//public class ConsoleView implements ViewInterface
//{
//    MemberRegister memberRegister;
//
//    boolean loop = true;
//    String exitString = "q";
//
//    @Override
//    public void start(model.MemberRegister memberRegister)
//    {
//        this.memberRegister = memberRegister;
//        while(loop)
//        {
//            mainMenu();
//        }
//    }
//
//    @Override
//    public void mainMenu()
//    {
//        showWelcome();
//        String command = listenForCommand();
//        command.toLowerCase();
//
//        switch(command)
//        {
//            case "h":
//            case "help":
//            case "1":
//                showHelp();
//                break;
//
//            case "2":
//            case "am":
//            case "add member":
//                addMember();
//                break;
//
//            case "3":
//            case "sl":
//            case "show list":
//            case "view members":
//                showList();
//                break;
//
//            case "4":
//            case "sm":
//            case "show specific member":
//            case "show member":
//                showSpecificMember();
//                break;
//
//            case "9":
//            case "q":
//            case "quit":
//                quit();
//
//
//            case "test":           // TODO, remove before release.
//            {
//                System.out.println(memberRegister.getMembers().get(0).getFirstName());
//            }
//        }
//    }
//
//
//
//
//    @Override
//    public void showWelcome()
//    {
//        System.out.println("\nWelcome to managing system of \"The happy pirate\"\n" +
//                "\nWrite command, or write \"help\" to see list of commands");
//    }
//
//    @Override
//    public void showHelp()
//    {
//        System.out.println("You can either press the correspondning number, or the command that is written here in the help section\n\n" +
//            "1. Help\n" +
//            "2. Add Member\n" +
//            "3. Show list\n" +
//            "4. Show specific member\n" +
//            "9. Quit\n");
//    }
//
//    private void clearConsole()          // TODO, get this working
//    {
//        try
//        {
//            final String os = System.getProperty("os.name");
//
//            if (os.contains("Windows"))
//            {
//                Runtime.getRuntime().exec("cls");
//            }
//            else
//            {
//                Runtime.getRuntime().exec("clear");
//            }
//        }
//        catch (final Exception e)
//        {
//
//        }
//    }
//
//    private String listenForCommand()
//    {
//        Scanner in = new Scanner(System.in);
//        try
//        {
//            return in.nextLine();
//        } catch(Exception e)
//        {
//            return "error";
//        }
//    }
//
//    private void addMember()
//    {
//        System.out.println("Please enter user first name, or \"" + exitString + "\" to cancel");
//        String firstName = listenForCommand();
//        if(exitCheck(firstName))
//        {
//            mainMenu();
//        }
//
//        System.out.println("Please enter user last name, or \"" + exitString + "\" to cancel");
//        String lastName = listenForCommand();
//        if(exitCheck(lastName))
//        {
//            mainMenu();
//        }
//
//        System.out.println("Please enter personal number, 10 digits, or \"" + exitString + "\" to cancel");
//        String personalID = listenForCommand();
//        if(exitCheck(personalID))           // TODO, add a verifier if userID is correct
//        {
//            mainMenu();
//        }
//
//        System.out.print("Your user details is:\n" +
//                firstName + " " + lastName + ", id: " + personalID
//            + "\nIs this correct?");
//        String isCorrectString = listenForCommand();
//        isCorrectString.toLowerCase();
//        if(isCorrectString.equals("y") || isCorrectString.equals("yes"))
//        {
//           memberRegister.addMember(firstName, lastName, personalID);
//
//        }
//
//    }
//
//    /**
//     * Checks if String is equal to exitString
//     * @param checkingString String to be compared
//     * @return True if equal, else false
//     */
//    private boolean exitCheck(String checkingString)
//    {
//        if(checkingString.equals(exitString))
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    private void showList()
//    {
//        System.out.println("1 for compact list, 2 for verbose list");
//        String input = listenForCommand();
//        input.toLowerCase();
//        switch(input)
//        {
//            case "1":
//            case "compact list":
//                showCompactList();
//                break;
//
//            case "2":
//            case "verbose list":
//                showVerboseList();
//                break;
//        }
//    }
//
//    private void showCompactList()
//    {
//        System.out.println("\n-------  List of Members  -------\n");
//
//        for(model.Member m : memberRegister.getMemberList())
//        {
//            System.out.println("member: " + m.getMemberID() + ", " +
//                    m.getFirstName() + " " + m.getLastName() +
//                ", number of boats: " + m.getBoatCount());
//        }
//    }
//
//    private void showVerboseList()
//    {
//        System.out.println("\n------- List of Members  --------\n");
//
//        for(model.Member m : memberRegister.getMemberList())
//        {
//            System.out.println("member: " + m.getMemberID() + ", " +
//                    m.getFirstName() + " " + m.getLastName() + ", personal ID: " + m.getPersonalNumber() +
//                    ", number of boats: " + m.getBoatCount());
//            for(model.Boat b: m.getBoatList())
//            {
//                System.out.println("reg nr: " + b.getBoatID() + ", type: " + b.getBoatType().toString().toLowerCase() +
//                ", length: " + b.getLength() + "cm");
//            }
//        }
//    }
//
//    private void showSpecificMember()               // TODO, not done yet
//    {
//        System.out.println("\nEnter members member id, or \'q\' to quit");
//        boolean loop = true;
//        while(loop)                 // loop to let the user try get the list of members
//        {
//            String userInput = listenForCommand();
//            char c = '0';
//            int counter = 0;
//            while( c != 'q' || Character.isDigit(c)|| counter < userInput.length())
//            {
//                c = userInput.charAt(counter++);            // add one to counter after reading it to variable c
//                if(!Character.isDigit(c))
//                {
//                    System.out.println("The member id should be an all digit number\n" +
//                            "please try again:");
//                }
//
//
//
//            }
//
//        }
//
//
//    }
//
//    private void quit()
//    {
//        System.exit(0);
//    }
//}
