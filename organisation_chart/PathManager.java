package organisation_chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class PathManager {
    private final String pathToName;
    private final String pathFromName;
    private final HashMap<Integer , EmployeeNameId> organisationParsedMap;
    private final HashMap<Integer , EmployeeNameId> organisationParsedMapOriginal;
    private final boolean ifSameName;

    public PathManager(String pathFromName , String pathToName , HashMap<Integer , EmployeeNameId> organisationParsedMap ,
                       HashMap<Integer , EmployeeNameId> organisationParsedMapOriginal, boolean ifSameName){
        this.pathFromName = pathFromName;
        this.pathToName = pathToName;
        this.organisationParsedMap = organisationParsedMap;
        this.organisationParsedMapOriginal = organisationParsedMapOriginal;
        this.ifSameName = ifSameName;
    }

    // Uses the getPathHead and getPathBetween helper functions to obtain the names involved in forming a path
    public ArrayList<String> getPaths(){
        ArrayList<String> allPathsInString = new ArrayList<String>();
        ArrayList<ArrayList<Integer>> pathTo;
        ArrayList<ArrayList<Integer>> pathFrom;

        pathFrom = getPathToHead(pathFromName);
        pathTo = getPathToHead(pathToName);

        for (ArrayList<Integer> pathF : pathFrom){
            for (ArrayList<Integer> pathT : pathTo){
                String actualPathString = getPathBetween(pathF, pathT);
                allPathsInString.add(actualPathString);
            }
        }

        // If the required path between two same name superheroes exists, then we only output one
        // result(i.e. only one path between them).
        if(ifSameName){
            Iterator<String> iterator = allPathsInString.iterator();
            ArrayList<String> tmpArrayForSameName = new ArrayList<String>();
            while (iterator.hasNext()) {
                String s = iterator.next();
                if (s.contains("->")){
                    tmpArrayForSameName.add(s);
                    return tmpArrayForSameName;
                }
            }
        }
        return allPathsInString;
    }

    // Uses getPathToHead helper function to obtain lists of paths from two directions and merges them to obtain the
    // actual path between two names. Returns an appended string suggesting the path.
    private String getPathBetween(ArrayList<Integer> pathFrom, ArrayList<Integer> pathTo) {
        ArrayList<Integer> pathFromCopy = new ArrayList<Integer>(pathFrom);
        ArrayList<Integer> pathToCopy = new ArrayList<Integer>(pathTo);
        StringBuilder result = new StringBuilder();

        int previousPathFromLength = pathFromCopy.size();
        pathFromCopy.removeAll(pathToCopy);
        int currentPathFromLength = previousPathFromLength - pathFromCopy.size();
        Collections.reverse(pathToCopy);
        for( int i = 0 ; i < currentPathFromLength - 1 ; i ++){
            pathToCopy.remove(0);
        }

        for (int pathId: pathFromCopy){
            result.append(organisationParsedMapOriginal.get(pathId).getName() + " (" + pathId + ") -> ");
        }
        int lastValueReached = 0;
        for (int pathId: pathToCopy){
            lastValueReached ++;
            result.append(organisationParsedMapOriginal.get(pathId).getName() + " (" + pathId + ")" +
                    ((lastValueReached < pathToCopy.size()) ? " <- " : ""));
            //pathString.add(organisationParsedMap.get(pathId).getName());
        }
        return result.toString();

    }

    // This method returns array of arrays consisting of paths from a given name to the head of the tree.
    // This method is used by getPathBetween function.
    private ArrayList<ArrayList<Integer>> getPathToHead(String name) {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> pathCollection = new ArrayList<ArrayList<Integer>>();

        for (int entry : organisationParsedMap.keySet()) {
            if ((organisationParsedMap.get(entry)).getName().equals(name)) {
                keys.add(entry);
            }
        }

        for(int i = 0 ; i < keys.size() ; i ++){
            int tmp = keys.get(i);
            ArrayList<Integer> path = new ArrayList<Integer>();
            EmployeeNameId emp = organisationParsedMap.get(tmp);
            path.add(tmp);
            while(emp.getManagerId() != -1){
                path.add(emp.getManagerId());
                emp = organisationParsedMap.get(emp.getManagerId());
            }
            pathCollection.add(path);
        }
        return pathCollection;
    }

}
