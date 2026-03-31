package com.practice.algo;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class VectorAddExample {

    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    public static void main(String[] args) {

        float[] a = {1,2,3,4,5,6,7,8};
        float[] b = {10,20,30,40,50,60,70,80};
        float[] c = new float[a.length];

        int i = 0;
        int upperBound = SPECIES.loopBound(a.length);

        for (; i < upperBound; i += SPECIES.length()) {

            FloatVector va = FloatVector.fromArray(SPECIES, a, i);
            FloatVector vb = FloatVector.fromArray(SPECIES, b, i);

            FloatVector vc = va.add(vb);

            vc.intoArray(c, i);
        }

        // scalar tail
        for (; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }

        for (float v : c) {
            System.out.println(v);
        }
    }
}