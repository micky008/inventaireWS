package com.msc.inventairews.utility;

import com.msc.inventairews.entity.Boite;
import java.util.Iterator;

/**
 *
 * @author Michael
 */
public class BoiteUtils {

    /**
     * Enleve un enfant de la boite parent.
     *
     * @param parent la boite ou chercher.
     * @param uuidChild l'uuid qu'on veux delete
     * @return true si on a remove false on a rien fait
     */
    public static boolean removeChildFromParent(Boite parent, Boite childBoite) {
        if (parent == null) {
            return false;
        }
        if (parent.getBoites() == null || parent.getBoites().isEmpty()) {
            return false;
        }
        Iterator<Boite> itb = parent.getBoites().iterator();
        while (itb.hasNext()) {
            Boite b = itb.next();
            if (b.equals(childBoite)){
                itb.remove();
                break;
            }
        }
        return true;
    }

}
