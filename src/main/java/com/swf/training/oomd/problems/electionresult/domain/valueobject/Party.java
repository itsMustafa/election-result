package com.swf.training.oomd.problems.electionresult.domain.valueobject;

public enum Party {

    BJP("Bhartiya Janta Party"),
    INC("Indian National Congress"),
    BSP("Bahujan Samaj Party"),
    CPI("Communist Party of India"),
    NCP("Nationalist Congress Party"),
    IND("Independent");

    private final String fullName;

    Party(String fullName) {
        this.fullName = fullName;
    }

    public static boolean isValidPartyCode(String rawPartyCode) {
        return (
                rawPartyCode.equalsIgnoreCase("BJP") || rawPartyCode.equalsIgnoreCase("INC") ||
                        rawPartyCode.equalsIgnoreCase("BSP") || rawPartyCode.equalsIgnoreCase("CPI") ||
                        rawPartyCode.equalsIgnoreCase("NCP") || rawPartyCode.equalsIgnoreCase("IND")
        );
    }

    public String getFullName() {
        return this.fullName;
    }
}
