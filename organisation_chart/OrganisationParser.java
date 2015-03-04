package organisation_chart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class OrganisationParser {
    private HashMap<Integer, EmployeeNameId> parsedOrganisationMap = new HashMap<Integer, EmployeeNameId>();
    private HashMap<Integer,EmployeeNameId> parsedOrganisationMapOriginal = new HashMap<Integer, EmployeeNameId>();
    public OrganisationParser(String filename){
        try {
            parseFileProduceMap(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashMap<Integer,EmployeeNameId> getParsedOrganisationMap(){
        return parsedOrganisationMap;
    }
    public HashMap<Integer,EmployeeNameId> getParsedOrganisationMapOriginal(){
        return parsedOrganisationMapOriginal;
    }

    private void parseFileProduceMap(String fileName) throws IOException {
        HashMap<Integer , EmployeeNameId> organisationData = new HashMap<Integer, EmployeeNameId>();
        HashMap<Integer , EmployeeNameId> organisationDataOriginal = new HashMap<Integer, EmployeeNameId>();
        BufferedReader buff = new BufferedReader(new FileReader(fileName));
        String line = buff.readLine(); // No processing required for Heading
        line = buff.readLine();
        while (line != null) {
            line = line.trim();
            if (line.length()> 0){  // Ignoring empty lines
                String[] dataSegment = line.split(Pattern.quote("|"));
                int index = 0; // Testing if | is available at start. If not we choose index as 1
                if (dataSegment[index].length() == 0) {
                    index = 1;
                }
                int employeeId = Integer.parseInt(dataSegment[index].trim());
                String name = UTIL.stringReform(dataSegment[index + 1].trim());
                String originalName = dataSegment[index + 1].trim();
                int managerId;
                if (dataSegment[index+2].trim().isEmpty()){
                    managerId = -1 ;
                }
                else{
                    managerId = Integer.parseInt(dataSegment[index+2].trim());
                }
                EmployeeNameId employeeNameId = new EmployeeNameId(name , managerId);
                EmployeeNameId employeeNameIdOriginal = new EmployeeNameId(originalName , managerId);
                organisationData.put(employeeId,employeeNameId);
                organisationDataOriginal.put(employeeId,employeeNameIdOriginal);
            }
            line = buff.readLine();
        }
        buff.close();
        parsedOrganisationMapOriginal = organisationDataOriginal;
        parsedOrganisationMap = organisationData;
    }

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        for (Integer id: parsedOrganisationMap.keySet()){
            String value1 = parsedOrganisationMap.get(id).getName();
            String value2 = parsedOrganisationMap.get(id).getManagerId().toString();
            result.append(id + " " + value1 + " " + value2 + NEW_LINE );
        }
        return result.toString();
    }
}
