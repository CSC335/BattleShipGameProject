package battleship;

import java.io.*;
import java.net.*;

// Server class that implements the BattleShipStrategy to interact with client moves
public class Server implements BattleShipStrategy {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for a connection...");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get desired move from client
    @Override
    public int[] desiredMove(Board b) {
        out.println("Send Move");
        try {
            String move = in.readLine();
            String[] parts = move.split(",");
            return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get desired ship placement from client
    @Override
    public int[][] desiredShip(Board b, Ship ship) {
        out.println("Send Ship," + ship.size());
        try {
            String shipData = in.readLine();
            String[] parts = shipData.split(",");
            return ShipInfoToArray(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 
                                   Integer.parseInt(parts[2]), ship.size());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnections() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Connections closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(1234);

        server.closeConnections();
    }
}
