package daripher.skilltree.mixin.minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import daripher.skilltree.item.ItemHelper;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemStack.class)
public class MixinItemStack {
	@Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
	private void getMaxDamage(CallbackInfoReturnable<Integer> callbackInfo) {
		if (!ItemHelper.hasDurabilityBonus((ItemStack) (Object) this)) {
			return;
		}
		var durabilityBonus = ItemHelper.getDurabilityBonus((ItemStack) (Object) this);
		callbackInfo.setReturnValue((int) (callbackInfo.getReturnValue() * (1 + durabilityBonus)));
	}
}
