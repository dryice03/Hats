package dryice03.hats;

import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandExecutor;
import org.spongepowered.api.util.command.spec.CommandSpec;

import com.google.inject.Inject;

@Plugin(id = "hats", name = "Hats", version = "1.0")
public class Main 
{
	@Inject public Game game;
	@Inject private PluginContainer container;
	
	
	@Subscribe
	public void onServerStart(ServerStartedEvent event)
	{
		//hat
		CommandSpec hatSpec = CommandSpec.builder()
				.setDescription(Texts.of("Gives a hat of the item your holding"))
				.setPermission("hats.command.hat")
				.setArguments(GenericArguments.optionalWeak(GenericArguments.player(Texts.of("player"), this.game)))
				.setExecutor(new HatCommand(this))
				.build();
		
		//hats
		CommandSpec hatsSpec = CommandSpec.builder()
				.setDescription(Texts.of("Gives hats of the item your holding to everyone"))
				.setPermission("hats.command.hats")
				.setExecutor(new HatsCommand(this))
				.build();
		
		game.getCommandDispatcher().register(container, hatSpec, "hat");
		game.getCommandDispatcher().register(container, hatsSpec, "hats");
	}
	
	public Game getGame()
	{
		return game;
	}
	
	
}