package Builder;

import org.bukkit.block.Block;

import ExplosionTypes.ExplosionType;
import ExplosionTypes.NormalExplosion;
import Fuzes.Fuze;
import Tnt.Tnt;

public class TntBuilder {
    private Fuze fuze;
    private Block block;
    private ExplosionType type;

    public void setFuze(Fuze fuze){
        this.fuze = fuze;
    }

    public void setBlock(Block block){
        this.block = block;
    }

    public void setExpType(ExplosionType type) {
        this.type = type;
    }

    public Tnt build(){
        if (this.type == null) {
            // Defaults to normal explosion
            return new Tnt(fuze, new NormalExplosion(block));
        }

        return new Tnt(fuze, type);
    }
}
