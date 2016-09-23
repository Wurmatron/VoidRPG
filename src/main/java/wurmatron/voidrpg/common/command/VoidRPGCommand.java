package wurmatron.voidrpg.common.command;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.config.ConfigHandler;
import wurmatron.voidrpg.common.cube.CubeRegistry;
import wurmatron.voidrpg.common.items.VoidRPGItems;
import wurmatron.voidrpg.common.reference.Global;
import wurmatron.voidrpg.common.reference.Local;
import wurmatron.voidrpg.common.utils.VersionChecker;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class VoidRPGCommand implements ICommand {

		private ArrayList<String> aliases = new ArrayList<String>();

		public VoidRPGCommand () {
				aliases.add(Global.NAME.toLowerCase());
				aliases.add(Global.NAME.toUpperCase());
				aliases.add(Global.NAME.toLowerCase());
				aliases.add(Global.MODID.toUpperCase());
				aliases.add(Global.MODID.toLowerCase());
				aliases.add(Global.MODID.toUpperCase());
		}

		@Override
		public String getCommandName () {
				return Global.NAME;
		}

		@Override
		public String getCommandUsage (ICommandSender sender) {
				return null;
		}

		@Override
		public List<String> getCommandAliases () {
				return aliases;
		}

		@Override
		public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
				switch (args.length) {
						case (0): {
								execute(server, sender, new String[] {"help"});
								break;
						}
						case (1): {
								if (args.length > 0) {
										if (args[0].equalsIgnoreCase("version")) {
												sender.addChatMessage(new TextComponentString("Current Version " + Global.VERSION).setStyle(new Style().setColor(TextFormatting.GOLD)));
												sender.addChatMessage(new TextComponentString("Latest Version " + VersionChecker.getVersion(Global.UPDATE_URL)).setStyle(new Style().setColor(TextFormatting.GOLD)));
										} else if (args[0].equalsIgnoreCase("reload")) {
												ConfigHandler.loadJsonCubes();
												sender.addChatMessage(new TextComponentString("Reloaded Cube Json's").setStyle(new Style().setColor(TextFormatting.AQUA)));
												sender.addChatMessage(new TextComponentString("(May require an restart to remove cubes)").setStyle(new Style().setColor(TextFormatting.AQUA)));
										} else if (args[0].equalsIgnoreCase("names") || args[0].equalsIgnoreCase("cubes")) {
												String temp = "";
												for (ICube cube : CubeRegistry.cubes)
														temp = temp + I18n.format(cube.getUnlocalizedName()) + ", ";
												sender.addChatMessage(new TextComponentString("Cubes: " + temp).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
										} else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
												sender.addChatMessage(new TextComponentString("/voidrpg <version | reload | cubes | info>").setStyle(new Style().setColor(TextFormatting.GREEN)));
										} else if (args[0].equalsIgnoreCase("info")) {
												if (args.length >= 2) {
												} else {
														sender.addChatMessage(new TextComponentString("/voidrpg info <cube-name>").setStyle(new Style().setColor(TextFormatting.RED)));
														execute(server, sender, new String[] {"names"});
												}
										}
								}
								break;
						}
						case (2): {
								if (args[0].equalsIgnoreCase("info")) {
										if (args.length >= 2) {
												ICube cube = CubeRegistry.INSTANCE.getCubesFromName(args[1]);
												if (cube != null) {
														sender.addChatMessage(new TextComponentString("=-=-=-= " + I18n.format(cube.getUnlocalizedName()) + "=-=-=-= ").setStyle(new Style().setColor(TextFormatting.AQUA)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.WEIGHT) + ": " + cube.getWeight()).setStyle(new Style().setColor(TextFormatting.YELLOW)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.COMPLEXITY) + ": " + cube.getComplexity()).setStyle(new Style().setColor(TextFormatting.YELLOW)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.DURABILITY) + ": " + cube.getDurability()).setStyle(new Style().setColor(TextFormatting.YELLOW)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.MAXAMOUNT_HELMET) + ": " + cube.getMaxAmount(VoidRPGItems.armorHelmet)).setStyle(new Style().setColor(TextFormatting.YELLOW)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.MAXAMOUNT_CHEST) + ": " + cube.getMaxAmount(VoidRPGItems.armorChestplate)).setStyle(new Style().setColor(TextFormatting.YELLOW)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.MAXAMOUNT_LEGS) + ": " + cube.getMaxAmount(VoidRPGItems.armorLeggings)).setStyle(new Style().setColor(TextFormatting.YELLOW)));
														sender.addChatMessage(new TextComponentString(I18n.format(Local.MAXAMOUNT_BOOTS) + ": " + cube.getMaxAmount(VoidRPGItems.armorBoots)).setStyle(new Style().setColor(TextFormatting.YELLOW)));
												} else
														sender.addChatMessage(new TextComponentString("Enter an valid cube name").setStyle(new Style().setColor(TextFormatting.RED)));
										}
								}
								break;
						}
				}
		}

		@Override
		public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
				return true;
		}

		@Override
		public List<String> getTabCompletionOptions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
				return null;
		}

		@Override
		public boolean isUsernameIndex (String[] args, int index) {
				return false;
		}

		@Override
		public int compareTo (ICommand o) {
				return 0;
		}

}
