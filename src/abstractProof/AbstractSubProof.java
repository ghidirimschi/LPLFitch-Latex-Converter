package abstractProof;

import formula.Sentence;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;

/**
 * This class implements the abstract representation of a Fitch subproof. It consists of a list of premises and another
 * list of steps. It implements the AbstractStep interface.
 */
public final class AbstractSubProof implements AbstractStep {
    private final AbstractPremise premise;
    private final ArrayList<AbstractStep> steps;

    public AbstractSubProof(AbstractPremise premise, ArrayList<AbstractStep> steps) {
        this.premise = premise;
        this.steps = new ArrayList<>(steps);
    }

    @Override
    public boolean isValid(MutableInt rowNr, ArrayList<AbstractStep> runningSteps) throws AbstractRuleCitingException {
        ArrayList<AbstractStep> tmpRunningSteps = new ArrayList<>(runningSteps);
        premise.isValid(rowNr, tmpRunningSteps);
        for (AbstractStep step : steps) {
            if (!step.isValid(rowNr, tmpRunningSteps)) {
                return false;
            }
        }
        runningSteps.add(this);
        return true;
    }

    @Override
    public void checkPedanticValidity(MutableInt rowNr, ArrayList<AbstractStep> runningSteps) throws AbstractRuleCitingException, AbstractRulePedanticException {
        ArrayList<AbstractStep> tmpRunningSteps = new ArrayList<>(runningSteps);
        premise.checkPedanticValidity(rowNr, tmpRunningSteps);
        for (AbstractStep step : steps) {
            step.checkPedanticValidity(rowNr, tmpRunningSteps);
        }
        runningSteps.add(this);
    }

    @Override
    public int rowSize() {
        int sum = 1;
        for (AbstractStep step : steps) {
            sum += step.rowSize();
        }
        return sum;
    }

    public boolean isDeryiving(Sentence sentence) {
        for (AbstractStep step : steps) {
            if (sentence.equals(step.getSentence())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Sentence getSentence() {
        return null;
    }

    public AbstractPremise getPremise() {
        return premise;
    }
}
