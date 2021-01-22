package abstractProof;

import formulanew.Sentence;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;

public interface AbstractStep {
    public boolean isValid(MutableInt rowNr, ArrayList<AbstractStep> runningSteps) throws AbstractRuleCitingException;
    public void checkPedanticValidity(MutableInt rowNr, ArrayList<AbstractStep> runningSteps) throws AbstractRuleCitingException, AbstractRulePedanticException;

    public int rowSize();
    public Sentence getSentence();
}
