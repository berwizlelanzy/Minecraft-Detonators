package Builder;

import org.bukkit.block.Block;

import Fuzes.Fuze;
import Tnt.Tnt;

public class TntBuilder {
    private Fuze fuze;
    private Block block;

    public void setFuze(Fuze fuze){
        this.fuze = fuze;
    }

    public void setBlock(Block block){
        this.block = block;
    }

    public Tnt build(){
        return new Tnt(block, fuze);
    }
}
