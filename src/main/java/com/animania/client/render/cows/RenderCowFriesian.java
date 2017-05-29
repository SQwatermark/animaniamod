package com.animania.client.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelCow;
import com.animania.common.entities.cows.EntityCowFriesian;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCowFriesian<T extends EntityCowFriesian> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();

    private static final ResourceLocation cowTextures      = new ResourceLocation("animania:textures/entity/cows/cow_friesian.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_friesian_blink.png");
    Random                                rand             = new Random();

    public RenderCowFriesian(RenderManager rm) {
        super(rm, new ModelCow(), 0.5F);
    }

    protected ResourceLocation getCowTextures(T par1EntityCow) {
        return RenderCowFriesian.cowTextures;
    }

    protected ResourceLocation getCowTexturesBlink(T par1EntityCow) {
        return RenderCowFriesian.cowTexturesBlink;
    }

    protected void preRenderScale(T entity, float f) {
        GL11.glScalef(1.24F, 1.24F, 1.24F);
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        int blinkTimer = entity.blinkTimer;

        if (blinkTimer < 7 && blinkTimer >= 0)
            return this.getCowTexturesBlink(entity);
        else
            return this.getCowTextures(entity);
    }

    static class Factory<T extends EntityCowFriesian> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderCowFriesian(manager);
        }
    }
}