import organisation_chart.EmployeeNameId;
import organisation_chart.OrganisationParser;
import organisation_chart.PathManager;
import organisation_chart.UTIL;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String []args){

        OrganisationParser organisationParser = new OrganisationParser(args[0]);
        HashMap<Integer , EmployeeNameId> organisationParsedMap = organisationParser.getParsedOrganisationMap();
        // This contains the original text as from file i.e. no lowercase and no space removal
        HashMap<Integer , EmployeeNameId> organisationParsedMapOriginal = organisationParser.getParsedOrganisationMapOriginal();

        // Printing the hash map after construction using overridden toString for testing purpose
        // System.out.println(organisationParser);

        String fromName = UTIL.stringReform(args[1]); // converts the string to lowercase and removes extra spaces
        String toName = UTIL.stringReform(args[2]);

        PathManager p = new PathManager(fromName , toName , organisationParsedMap , organisationParsedMapOriginal);
        ArrayList<String> allPaths = p.getPaths();
        for(String path : allPaths){
            System.out.println(path);
        }
    }
}