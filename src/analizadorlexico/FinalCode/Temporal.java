/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico.FinalCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Kevin Barahona
 */
public class Temporal {

    public static HashMap<String, String> currentTemporal;
    public static HashMap<String, String> mapFakeTemporalToReal;
    public static Stack<String> freeTemporal;
    public static final String[] temporalList = {
        "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7"
    };

    public static void initAll() {
        currentTemporal = new HashMap();
        mapFakeTemporalToReal = new HashMap();
        freeTemporal = new Stack();
        for (int i = temporalList.length - 1; i >= 0; i--) {
            freeTemporal.push(temporalList[i]);
        }
    }

    public static String getFreeTemporal() {
        return freeTemporal.pop();
    }

    public static String checkForTemporal(String id) {
        return currentTemporal.getOrDefault(id, "EMPTY");
    }

    public static void liberateTemporal(String temporal) {
        freeTemporal.push(temporal);
    }

    public static String getTempValue(String intermediateTemp) {
        if (intermediateTemp.charAt(0) == '$') {
            if (mapFakeTemporalToReal.containsKey(intermediateTemp)) {
                return mapFakeTemporalToReal.get(intermediateTemp);
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

}
