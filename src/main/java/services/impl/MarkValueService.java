package services.impl;

import entity.MarkValue;

import java.util.List;


public class MarkValueService {

    public Double getMeanMark(List<MarkValue> markValues) {
        Double meanMark = 0.0;
        int i = 0;

        for (MarkValue currMark : markValues) {
            if (currMark.getMarkValue().equals(0)) return 0d;
            meanMark = meanMark + currMark.getMarkValue();
            i++;
        }
        meanMark = meanMark / i;

        return meanMark;
    }
}
