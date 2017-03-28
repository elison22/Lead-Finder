package experiments;

import org.familysearch.api.client.ft.FamilySearchFamilyTree;
import org.familysearch.api.client.ft.FamilyTreePersonState;
import org.gedcomx.rs.client.PersonState;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TreeReader {

    public static void main(String... args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.load(new FileInputStream("local.properties"));

        boolean useSandbox = Boolean.valueOf(props.getProperty("use_sandbox"));
        String devKey = props.getProperty("dev_key");
        String username;
        String password;

        if(useSandbox) {
            username = props.getProperty("sand_username");
            password = props.getProperty("sand_password");
        } else {
            username = props.getProperty("prod_username");
            password = props.getProperty("prod_password");
        }

        FamilySearchFamilyTree ft = new FamilySearchFamilyTree(useSandbox)
                .authenticateViaOAuth2Password(username, password, devKey);

        PersonState currentPerson = ft.readPersonForCurrentUser();
        System.out.println(currentPerson.toString());

        String id = scanner.nextLine();
        FamilyTreePersonState otherPerson = ft.readPersonById(id);
        System.out.println(otherPerson.toString());

    }

}
