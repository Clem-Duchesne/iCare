package iHealth.nf;

/**
 *
 * @author cleme
 */
public class DPI {
    private DM dM;
    private DMA dMA;
    
    public DPI(DMA dma){
        this.dMA = dma;
    }
    
    public void addDM(DM mydm){
        this.dM =mydm;
    }
}
