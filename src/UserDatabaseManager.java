import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class UserDatabaseManager {
    String fileName = "Users_Credential.csv";

    //Create new csv file
    public void create() {
        try (FileWriter writer = new FileWriter(fileName)) {
            //header row
            writer.write("username,password,role\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Add new users to the csv sheet
    public void write(String username,String password,String role){
        try (FileWriter writer = new FileWriter(fileName,true)) {
            writer.write(username+","+password+","+role+"\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Count number of entries
    public int countRows() {
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                rowCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (rowCount - 1);
    }

    public void loadData(HashMap<String, String> ausers, HashMap<String, String> susers) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Skip header row
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] data = line.split(",");

                // data[0] = Username, data[1] = Password, role = data[2]
                String username = data[0];
                String password = data[1];
                String role = data[2];

                if(role.equals("admin")){
                    ausers.put(username, password);
                }
                else{
                    susers.put(username, password);
                }
            } 
        } 
        catch (IOException e) {
        }
    }

    // public String getPassword() {
    // }

    // public String getRole() {
    // }
}
