package abstractProof;

import formula.Sentence;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;

/**
 * This class implements the abstract representation of a premise. It consists of a single well-formed-formula sentence.
 * It implements the AbstractStep interface.
 */
public final class AbstractPremise implements AbstractStep {
    private final Sentence sentence;


    public AbstractPremise(Sentence sentence) {
        this.sentence = sentence;
    }

    @Override
    public boolean isValid(MutableInt rowNr, ArrayList<AbstractStep> runningSteps) throws AbstractRuleCitingException {
        rowNr.increment();
        runningSteps.add(this);
        return true;
    }

    @Override
    public void checkPedanticValidity(MutableInt rowNr, ArrayList<AbstractStep> runningSteps) throws AbstractRuleCitingException, AbstractRulePedanticException {
        rowNr.increment();
        runningSteps.add(this);
    }

    @Override
    public int rowSize() {
        return 1;
    }

    @Override
    public Sentence getSentence() {
        return sentence;
    }
}
