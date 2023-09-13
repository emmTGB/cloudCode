import java.util.Scanner;

public class Game {
    private Room currentRoom;

    public Game(){
        createRooms();
    }
    public static void printHelp(){
        System.out.println("Got lost? Here are some commands you can give: go, bye, help.");
        System.out.println("For example, you can type \"go east.\"");
    }
    public void printWelcome(){
        System.out.println();
        System.out.println("Welcome to my castle!");
        System.out.println("This is a boring game.");
        System.out.println("If you need some help, just type \"help\".");
        printRoomInfo();
    }
    public void printRoomInfo(){
        System.out.println("You are now in " + currentRoom);
        System.out.println(currentRoom.showExits());
    }

    public void createRooms(){
        Room outside, lobby, pub, study, bedroom;
        outside = new Room("outside the castle");
        lobby = new Room("the lobby");
        pub = new Room("the pub");
        study = new Room("the study");
        bedroom = new Room("the bedroom");

        outside.setExit("east", lobby);
        outside.setExit("south", study);
        outside.setExit("west", pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north", outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);
        lobby.setExit("up", pub);
        pub.setExit("down", lobby);

        currentRoom = outside;
    }
    public void goRoom(String direction){
        if(currentRoom.getExit(direction) == null){
            System.out.println("Wrong step!");
        }else{
            currentRoom = currentRoom.getExit(direction);
            printRoomInfo();
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        game.printWelcome();

        label:
        while(true){
            String line = in.nextLine();
            String[] commands = line.split(" ");
            switch (commands[0]) {
                case "help":
                    Game.printHelp();
                    break;
                case "go":
                    game.goRoom(commands[1]);
                    break;
                case "bye":
                    break label;
            }
        }

        System.out.println("Thank you, bye.");
        in.close();
    }
}
