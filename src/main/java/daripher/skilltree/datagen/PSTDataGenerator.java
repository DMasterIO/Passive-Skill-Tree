package daripher.skilltree.datagen;

import daripher.skilltree.SkillTreeMod;
import daripher.skilltree.datagen.translation.PSTEnglishTranslationProvider;
import daripher.skilltree.datagen.translation.PSTRussianTranslationProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = SkillTreeMod.MOD_ID, bus = Bus.MOD)
public class PSTDataGenerator {
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		dataGenerator.addProvider(event.includeClient(), new PSTEnglishTranslationProvider(dataGenerator));
		dataGenerator.addProvider(event.includeClient(), new PSTRussianTranslationProvider(dataGenerator));
		dataGenerator.addProvider(event.includeClient(), new PSTItemModelsProvider(dataGenerator, fileHelper));
		var blockTagsProvider = new PSTBlockTagsProvider(dataGenerator, fileHelper);
		dataGenerator.addProvider(event.includeServer(), blockTagsProvider);
		dataGenerator.addProvider(event.includeServer(), new PSTItemTagsProvider(dataGenerator, blockTagsProvider, fileHelper));
		dataGenerator.addProvider(event.includeServer(), new PSTSkillsProvider(dataGenerator));
		dataGenerator.addProvider(event.includeServer(), new PSTLootTablesProvider(dataGenerator));
		dataGenerator.addProvider(event.includeServer(), new PSTRecipesProvider(dataGenerator));
	}
}
