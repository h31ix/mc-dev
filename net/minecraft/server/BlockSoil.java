package net.minecraft.server;

import java.util.Random;

public class BlockSoil extends Block {

    protected BlockSoil(int i) {
        super(i, Material.EARTH);
        this.textureId = 87;
        this.a(true);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.g(255);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) (i + 0), (double) (j + 0), (double) (k + 0), (double) (i + 1), (double) (j + 1), (double) (k + 1));
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int a(int i, int j) {
        return i == 1 && j > 0 ? this.textureId - 1 : (i == 1 ? this.textureId : 2);
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (!this.h(world, i, j, k) && !world.v(i, j + 1, k)) {
            int l = world.getData(i, j, k);

            if (l > 0) {
                world.setData(i, j, k, l - 1);
            } else if (!this.g(world, i, j, k)) {
                world.setTypeId(i, j, k, Block.DIRT.id);
            }
        } else {
            world.setData(i, j, k, 7);
        }
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        if (world.random.nextInt(4) == 0) {
            world.setTypeId(i, j, k, Block.DIRT.id);
        }
    }

    private boolean g(World world, int i, int j, int k) {
        byte b0 = 0;

        for (int l = i - b0; l <= i + b0; ++l) {
            for (int i1 = k - b0; i1 <= k + b0; ++i1) {
                int j1 = world.getTypeId(l, j + 1, i1);

                if (j1 == Block.CROPS.id || j1 == Block.MELON_STEM.id || j1 == Block.PUMPKIN_STEM.id) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean h(World world, int i, int j, int k) {
        for (int l = i - 4; l <= i + 4; ++l) {
            for (int i1 = j; i1 <= j + 1; ++i1) {
                for (int j1 = k - 4; j1 <= k + 4; ++j1) {
                    if (world.getMaterial(l, i1, j1) == Material.WATER) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        super.doPhysics(world, i, j, k, l);
        Material material = world.getMaterial(i, j + 1, k);

        if (material.isBuildable()) {
            world.setTypeId(i, j, k, Block.DIRT.id);
        }
    }

    public int getDropType(int i, Random random, int j) {
        return Block.DIRT.getDropType(0, random, j);
    }
}
