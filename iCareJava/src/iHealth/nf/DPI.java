package iHealth.nf;

/**
 *
 * @author cleme
 */
public class DPI {
    private DM dM;
    private DMA dMA;
    
    /**
     * Constructor de la classe DPI.
     * @param dma 
     */
    public DPI(DMA dma){
        this.dMA = dma;
    }
    
    /**
     * Define un Dossier Medical.
     * @param mydm 
     */
    public void addDM(DM mydm){
        this.dM =mydm;

    }
}
