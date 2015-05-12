package dryice03.hats;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class HatCommand implements CommandExecutor
{
	Main hats;
	Game game;
	
	public HatCommand(Main hats)
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
			ItemStack itemHelm = null;
			
			//get source player
			Player sourcePlayer = (Player) source;
			
			//Get Hand item
			if(sourcePlayer.getItemInHand().isPresent())
				itemHand = sourcePlayer.getItemInHand().get();
			
			//there is not a player name
			if(!args.hasAny("player")) 
			{	
				//get helmet item
				if(sourcePlayer.getHelmet().isPresent())
					itemHelm = sourcePlayer.getHelmet().get();
				
				//Switch Items
				sourcePlayer.setHelmet(itemHand);
				sourcePlayer.setItemInHand(itemHelm);
				
				//Confirmation Message
				source.sendMessage(Texts.of("Enjoy your hat!"));
			}
			//there is a player name
			else 
			{
				//get player
				Player player = args.<Player>getOne("player").get();
				
				//get helmet item
				if(player.getHelmet().isPresent())
					itemHelm = player.getHelmet().get();
				
				//Switch Items
				player.setHelmet(itemHand);
				sourcePlayer.setItemInHand(itemHelm);
				
				//Confirmation Messages
				player.sendMessage(Texts.of("Enjoy your new hat!"));
				if(!player.equals(source))
				{
					source.sendMessage(Texts.of("Hat has been applied!"));
				}
			}
	        return CommandResult.success();
		}
		return null;
    }
}
