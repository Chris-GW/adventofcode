package de.adventofcode.chrisgw.day22;

public enum CaveReqionType {

    ROCKY(0, '.'), NARROW(1, '|'), WET(2, '=');

    private final int riskLevel;
    private final char typeSign;


    CaveReqionType(int riskLevel, char typeSign) {
        this.riskLevel = riskLevel;
        this.typeSign = typeSign;
    }

    public static CaveReqionType fromRiskLevel(int riskLevel) {
        for (CaveReqionType caveReqionType : CaveReqionType.values()) {
            if (caveReqionType.riskLevel == riskLevel) {
                return caveReqionType;
            }
        }
        throw new IllegalArgumentException("Unknown risk level: " + riskLevel);
    }


    public int getRiskLevel() {
        return riskLevel;
    }

    public char getTypeSign() {
        return typeSign;
    }

}
