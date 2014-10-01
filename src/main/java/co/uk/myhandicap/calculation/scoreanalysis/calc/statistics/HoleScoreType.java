package main.java.co.uk.myhandicap.calculation.scoreanalysis.calc.statistics;

/**
 * Hole Score Type Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 30/09/2014
 * @project MyHandicapApp
 */
public class HoleScoreType {

    private int holeNumber;
    private int eagle;
    private int birdie;
    private int par;
    private int bogey;
    private int doubleBogey;
    private int tripleBogey;
    private int other;

    private HoleScoreType(HoleScoreTypeBuilder builder) {
        holeNumber = builder.holeNumber;
        eagle = builder.eagle;
        birdie = builder.birdie;
        par = builder.par;
        bogey = builder.bogey;
        doubleBogey = builder.doubleBogey;
        tripleBogey = builder.tripleBogey;
        other = builder.other;
    }

    public static class HoleScoreTypeBuilder {

        private int holeNumber;
        private int eagle;
        private int birdie;
        private int par;
        private int bogey;
        private int doubleBogey;
        private int tripleBogey;
        private int other;

        // required attribute for HoleScoreTypeBuilder
        public HoleScoreTypeBuilder(int holeNumber) {
            this.holeNumber = holeNumber;
        }

        // optional parameters for HoleScoreTypeBuilder
        public HoleScoreTypeBuilder isEagle(int eagle) {
            this.eagle = eagle;

            return this;
        }

        public HoleScoreTypeBuilder isBirdie(int birdie) {
            this.birdie = birdie;

            return this;
        }

        public HoleScoreTypeBuilder isPar(int par) {
            this.par = par;

            return this;
        }

        public HoleScoreTypeBuilder isBogey(int bogey) {
            this.bogey = bogey;

            return this;
        }

        public HoleScoreTypeBuilder isDoubleBogey(int doubleBogey) {
            this.doubleBogey = doubleBogey;

            return this;
        }

        public HoleScoreTypeBuilder isTrippBogey(int tripleBogey) {
            this.tripleBogey = tripleBogey;

            return this;
        }

        public HoleScoreTypeBuilder isOther(int other) {
            this.other = other;

            return this;
        }

        public HoleScoreType build() {
            return new HoleScoreType(this);
        }

    }

    public int getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    public int getEagle() {
        return eagle;
    }

    public void setEagle(int eagle) {
        this.eagle = eagle;
    }

    public int getBirdie() {
        return birdie;
    }

    public void setBirdie(int birdie) {
        this.birdie = birdie;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getBogey() {
        return bogey;
    }

    public void setBogey(int bogey) {
        this.bogey = bogey;
    }

    public int getDoubleBogey() {
        return doubleBogey;
    }

    public void setDoubleBogey(int doubleBogey) {
        this.doubleBogey = doubleBogey;
    }

    public int getTripleBogey() {
        return tripleBogey;
    }

    public void setTripleBogey(int tripleBogey) {
        this.tripleBogey = tripleBogey;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "HoleScoreType{" +
                "holeNumber=" + holeNumber +
                ", eagle=" + eagle +
                ", birdie=" + birdie +
                ", par=" + par +
                ", bogey=" + bogey +
                ", doubleBogey=" + doubleBogey +
                ", tripleBogey=" + tripleBogey +
                ", other=" + other +
                '}';
    }

}
