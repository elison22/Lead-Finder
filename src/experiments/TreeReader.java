package experiments;

import org.familysearch.api.client.ft.FamilySearchFamilyTree;
import org.familysearch.api.client.ft.FamilyTreePersonState;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TreeReader {

    public static void main(String... args) throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream("local.properties"));

        System.out.println(props.getProperty("username"));

        FamilySearchFamilyTree ft = new FamilySearchFamilyTree(Boolean.getBoolean(props.getProperty("use_sandbox")))
                //and authenticate.
                .authenticateViaOAuth2Password(
                        props.getProperty("username"),
                        props.getProperty("password"),
                        props.getProperty("dev_key")
                );

        FamilyTreePersonState person = ft.readPersonById("id");
        System.out.println(person.toString());

    }

}
