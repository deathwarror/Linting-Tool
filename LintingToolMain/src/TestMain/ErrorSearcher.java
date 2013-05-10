
package TestMain;

import java.util.ArrayList;

/**@author Kenneth Hassey
 *@Version 1.000
 * @date: 3/8/2013
 * Function:
 *      Responsible for searching for errors in the files
 */
public class ErrorSearcher {
    ArrayList<Error> EL;
    ErrorSearcher()
    {
      EL = new ArrayList();  
    }
    public ArrayList<Error> Start(Parser parser)
    {
        ClearErrorList();
        merge(parser.getParserErrorList());
        if(EL.isEmpty()){
            merge(Error06IdentifyMultiplyDrivenSignals.getErrors(parser));
            merge(Error15IdentifyBlockingNonBlocking.getErrors(parser));
            merge(Error14IdentifyBackwardPortDirection.getErrors(parser));
            merge(Error13IdentifyOperatorsInSensList.getErrors(parser));
            merge(Error03IdentifyAssignmentVsSensType.getErrors(parser));
            merge(Error01_05_07IdentifyClockErrors.getErrors(parser));
            merge(Error09a_09bIdentifyIncompleteSensList.getErrors(parser));
            merge(Error10VectorArrayInSensList.getErrors(parser));
            merge(Error11LogicalNotWithVector.getErrors(parser));
            merge(Error16IdentifyLatches.getErrors(parser));
        }
        return EL;
    }
    private void ClearErrorList()
    {
        EL = new ArrayList();
    }
    private void merge(ArrayList<Error> ErrorList)
    {
        EL.addAll(ErrorList);
    }
}