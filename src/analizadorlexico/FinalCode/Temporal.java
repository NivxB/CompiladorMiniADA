/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.FinalCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Kevin Barahona
 */
public class Temporal {

    public static LinkedHashMap<String, String> currentTemporal;
    public static LinkedHashMap<String, String> mapFakeTemporalToReal;
    public static Stack<String> freeTemporal;
    public static LinkedHashMap<String,String> argumentToTemporal;
    public static final String[] TEMPORAL_LIST = {
        "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9"
    };

    public static final String[] S_TEMPORAL_LIST = {
        "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7"
    };

    public static int A_POS = 0;

    public static final String[] A_LIST = {
        "$a0", "$a1", "$a2", "$a3"
    };

    public static String getNextA() {
        if (A_POS == 4) {
            return "NULL";
        } else {
            String retVal = A_LIST[A_POS];
            A_POS++;
            return retVal;
        }
    }

    ;

    public static void initAll() {
        currentTemporal = new LinkedHashMap();
        mapFakeTemporalToReal = new LinkedHashMap();
        freeTemporal = new Stack();
        argumentToTemporal = new LinkedHashMap();
        A_POS = 0;
        for (int i = TEMPORAL_LIST.length - 1; i >= 0; i--) {
            freeTemporal.push(TEMPORAL_LIST[i]);
        }
    }

    public static String getFreeTemporal() {

        return freeTemporal.pop();
    }

    public static String checkForTemporal(String id) {
        String retVal = currentTemporal.getOrDefault(id, "EMPTY");
        return retVal;
    }

    public static String getTempValue(String intermediateTemp) {

        if (intermediateTemp.charAt(0) == '$') {
            if (mapFakeTemporalToReal.containsKey(intermediateTemp)) {

                String retVal = mapFakeTemporalToReal.get(intermediateTemp);
                // Temporal.mapFakeTemporalToReal.remove(intermediateTemp);
                return retVal;
            }
            String tmpRetVal = getFreeTemporal();
            mapFakeTemporalToReal.put(intermediateTemp, tmpRetVal);
            return tmpRetVal;
        } else {

            String retVal = checkForTemporal(intermediateTemp);
            if (retVal.equalsIgnoreCase("EMPTY")) {
                String tmpRetVal = getFreeTemporal();
                //mapFakeTemporalToReal.put(intermediateTemp, tmpRetVal);
                currentTemporal.put(intermediateTemp, tmpRetVal);
                return tmpRetVal;
            }
            return retVal;
        }
    }

    public LinkedHashMap<String, String> currentSavedTemporal;
    public LinkedHashMap<String, String> savedMapFakeTemporalToReal;
    public Stack<String> savedFreeTemporal;

    public Temporal() {
        this.currentSavedTemporal = Temporal.currentTemporal;
        this.savedMapFakeTemporalToReal = Temporal.mapFakeTemporalToReal;
        this.savedFreeTemporal = Temporal.freeTemporal;
    }

    public List<String> getAliveTemporal() {
        ArrayList<String> retVal = new ArrayList<>();
        String[] tmp = new String[Temporal.freeTemporal.size()];
        tmp = Temporal.freeTemporal.toArray(tmp);
        if (tmp.length == Temporal.TEMPORAL_LIST.length) {
            return retVal;
        }
        List<String> aList = new LinkedList<>();
        for (String t : Arrays.asList(tmp)){
            aList.add(t);
        }
        List<String> bList = new LinkedList<>();
        for (String t : Arrays.asList(Temporal.TEMPORAL_LIST)){
            aList.add(t);
        }
        bList.removeAll(aList);
        return bList;
    }

    public static void restoreVal(Temporal t) {
        Temporal.currentTemporal = t.currentSavedTemporal;
        Temporal.mapFakeTemporalToReal = t.savedMapFakeTemporalToReal;
        Temporal.freeTemporal = t.savedFreeTemporal;
    }
}
