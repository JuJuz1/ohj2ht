/**
 * 
 */
package rekisteri;

/**
 * Oma säilöpoikkeus-luokka
 * @author Juuso Piippo & Elias Lehtinen
 * Emails:
 * juuso.piippo1@gmail.com
 * elias.a.lehtinen@gmail.com
 * @version 29.4.2023
 *
 */
public class SailoException extends Exception {
    
    private static final long serialVersionUID = 1L;

    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
