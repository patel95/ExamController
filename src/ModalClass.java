
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class ModalClass
{
    String question,optionA,optionB,optionC,optionD,answer,optionChosen;
    public String getQuestion() {
        return question;
    }

    public String getOptionChosen() {
        return optionChosen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.question);
        hash = 29 * hash + Objects.hashCode(this.optionA);
        hash = 29 * hash + Objects.hashCode(this.optionB);
        hash = 29 * hash + Objects.hashCode(this.optionC);
        hash = 29 * hash + Objects.hashCode(this.optionD);
        hash = 29 * hash + Objects.hashCode(this.answer);
        hash = 29 * hash + Objects.hashCode(this.optionChosen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModalClass other = (ModalClass) obj;
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.optionA, other.optionA)) {
            return false;
        }
        if (!Objects.equals(this.optionB, other.optionB)) {
            return false;
        }
        if (!Objects.equals(this.optionC, other.optionC)) {
            return false;
        }
        if (!Objects.equals(this.optionD, other.optionD)) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        if (!Objects.equals(this.optionChosen, other.optionChosen)) {
            return false;
        }
        return true;
    }

    public void setOptionChosen(String optionChosen) {
        this.optionChosen = optionChosen;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
}
