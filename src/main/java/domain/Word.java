package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dictionary")
public class Word extends AbstractEntity implements Comparable<Word> {

    private static final long serialVersionUID = 1L;
    private String eng;
    private String rus;
    private int correct;
    private int total;

    public Word() {
        correct = 0;
        total = 0;
    }

    /**
     * @param eng
     * @param rus
     */
    public Word(String eng, String rus) {
        this.eng = eng;
        this.rus = rus;
        correct = 0;
        total = 0;
    }

    /**
     * @return the eng
     */
    @Column(name = "eng", nullable = false)
    public String getEng() {
        return eng;
    }

    /**
     * @param eng
     *            the eng to set
     */
    public void setEng(String eng) {
        this.eng = eng;
    }

    /**
     * @return the rus
     */
    @Column(name = "ru", nullable = false)
    public String getRus() {
        return rus;
    }

    /**
     * @param rus
     *            the rus to set
     */
    public void setRus(String rus) {
        this.rus = rus;
    }

    /**
     * @return the correct
     */
    @Column(name = "correct")
    public int getCorrect() {
        return correct;
    }

    /**
     * @param correct
     *            the correct to set
     */
    public void setCorrect(int correct) {
        this.correct = correct;
    }

    /**
     * @return the total
     */
    @Column(name = "total")
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    public void correctUp() {
        correct++;
    }

    public void totalUp() {
        total++;
    }

    public int compareTo(Word word) {
        if (total == 0 || word.getTotal() == 0) {
            return (total == 0) ? -1 : 1;
        } else {
            if(correct == 0 & word.getCorrect() == 0) {
                if(total > word.getTotal()) {
                    return -1;
                } else {
                    return (total < word.getTotal()) ? 1 : 0;
                }
            } else {
                if (accuracy() > word.accuracy()) {
                    return 1;
                } else {
                    return (accuracy() < word.accuracy()) ? -1 : 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() + 
               ": " + eng + 
               " - " + rus + 
               " = " + correct + 
               ":" + total;
    }

    private double accuracy() {
        return Double.valueOf(correct) / Double.valueOf(total);
    }

}
