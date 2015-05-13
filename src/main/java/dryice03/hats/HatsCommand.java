package dryice03.hats;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class HatsCommand implements CommandExecutor
{
	Main hats;
	Game game;
	
	public HatsCommand(Main hats)
	{
		this.hats = hats;
		this.game = hats.game;
	}
	
	public CommandResult execute(CommandSource source, CommandContext args) throws CommandException 
	{
		if(source instanceof Player)
		{
			//Variables
			ItemStack itemHand = null;
			
			//get source player
			Player sourcePlayer = (Player) source;
			
			//Get Hand item
			if(sourcePlayer.getItemInHand().isPresent())
				itemHand = sourcePlayer.getItemInHand().get();
			
			//give all players hat
			for(Player player : game.getServer().getOnlinePlayers())
			{
				/*//give current helmet back to player
				if(player.getHelmet().isPresent())
					player.getInventory().offer(player.getHelmet().get());*/
				
				player.setHelmet(itemHand);
			}
			
			source.sendMessage(Texts.of("Hats have been applied!"));
			return CommandResult.success();
		}
		return null;
    }
	
	//private void setHat();
}
