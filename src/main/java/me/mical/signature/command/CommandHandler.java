package me.mical.signature.command;

import me.mical.signature.Signature;
import me.mical.signature.command.impl.ListCommand;
import me.mical.signature.command.impl.SetCommand;
import me.mical.signature.command.impl.ShowCommand;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.command.subcommands.DebugCommand;
import org.serverct.parrot.parrotx.command.subcommands.HelpCommand;
import org.serverct.parrot.parrotx.command.subcommands.ReloadCommand;
import org.serverct.parrot.parrotx.command.subcommands.VersionCommand;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;

@PAutoload
public class CommandHandler extends org.serverct.parrot.parrotx.command.CommandHandler {

    public CommandHandler() {
        super(ParrotXAPI.getPlugin(Signature.class), "signature");
        register(new ReloadCommand(plugin, ".reload"));
        register(new HelpCommand(plugin));
        register(new DebugCommand(plugin, ".debug"));
        register(new VersionCommand(plugin));
        register(new ListCommand(plugin));
        register(new SetCommand(plugin));
        register(new ShowCommand(plugin));
    }
}
